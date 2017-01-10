var buckets = require('buckets-js');
var Table = require('./table');
var tb = new Table();
var Promise = require('bluebird');

//mauriciosantos.github.io/Buckets-JS/symbols/buckets.PriorityQueue.html

function QueueSingleton() {
  if (!QueueSingleton.instance) {
    QueueSingleton.instance = this;
    var naMesa = [];

    var users = new buckets.PriorityQueue(Compare);

    var getPosition = function(queue, usuario) {
      var fila = queue.toArray();
      var k = new buckets.PriorityQueue(Compare);
      for (i = 0; i < queue.size(); i++) k.add(fila[i]);
      i = 0;
      while (!k.isEmpty()) fila[i++] = k.dequeue();
      for (i = 0; i < queue.size(); i++) {
        if (fila[i].getName() === usuario) {
          return 'Posição na fila: ' + parseInt(i + 1) + ' Tempo de espera: ' + fila[i].getTimeWait();
        }
      }
    };

    var insertUser = function(user) {
      return Table.getAvailable()
        .then(function(tables) {
          var msg = getPosition(users, user.getName());
          var necessaryTables = parseInt((user.getPeople() / 4) + 0.99999);
          var ok = false;
          if (tables.length >= necessaryTables) {
            ok = true;
            naMesa.push(user);
            user = users.dequeue();
            user.stopTime();

            for (i = 0; i < tables.length; i++) {
              necessaryTables--;
              var t = tables.splice(i, 1);
              Table.updateStatus(t[0]._id, false);
              user.addTableUser(t[0]._id);
              if (!necessaryTables) break;
            }
          }
          if (ok)
            user.notify();
          

          msg += ' Mesas: ' + user.getTables();

          console.log(msg);

          return {
            success: true,
            msg: msg 
          };

        });
    };

    /**
     * Adciona o usuário na fila e já procura mesas vagas
     * @param {User} user novo usuário
     */
    this.addUser = function(user) {
      var msg = getPosition(users, user.getName()); //ja ta na fila
      if (getPosition(users, user.getName())) {
        return new Promise(function(resolve, reject) {
          resolve({
            sucess: true,
            msg: msg
          });
        });
      }

      if (checkNaMesa(user)) { //ja foi atendido
        return new Promise(function(resolve, reject) {
          resolve({
            sucess: true,
            msg: 'Você já está em uma(s) mesa(s), tenha um bom apetite!'
          });
        });
      }

      //novo usuário na fila
      users.add(user);
      return insertUser(user);

    };

    var checkNaMesa = function(user) {
      for (var i = 0; i < naMesa.length; i++) {
        if (naMesa[i].getName() == user.getName()){
          console.log(naMesa[i].getName(), user.getName())
          return true;
        }
      }
    };


    /**
     * Libera as mesas e já busca o próximo
     * @param  {String} username do usuário
     */
    this.removeFromList = function(username) {
      var user;
      for (var i = 0; i < naMesa.length; i++) {
        if (naMesa[i].getName() === username) {
          var us = naMesa.splice(i,1);
          user = us[0];
          console.log(user);
          break;
        }
      }

      if (user) {
        var tables = user.getTables();
        for (i = 0; i < tables.length; i++)
          Table.updateStatus(tables[i], true);
      }
      if (!users.isEmpty())
        insertUser(users.peek());
    };
  }

  return QueueSingleton.instance;
}

function Compare(userA, userB) {
  if (userA.getTimeWait() > 60)
    return userA.getTimeWait() - userB.getTimeWait();
  if (userB.getTimeWait() > 60)
    return userB.getTimeWait() - userA.getTimeWait();
  if (userA.getPeople() >= 4 && userA.getPeople() % 4 === 0 && userB.getPeople() % 4 === 0 && userB.getPeople() >= 4)
    return userA.getPeople() - userB.getPeople();
  if (userB.getPeople() >= 4 && userB.getPeople() % 4 === 0)
    return -1;
  if (userA.getPeople() >= 4 && userA.getPeople() % 4 === 0)
    return 1;
  return userA.getPeople() - userB.getPeople();
}

module.exports = {
  QueueSingleton: QueueSingleton
};