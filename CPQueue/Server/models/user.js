var mongoose = require('mongoose');
var user = mongoose.model('User');
var jwt = require('jwt-simple');
var config = require('../config/database');
var buckets = require('buckets-js');
var gcm = require('node-gcm');
var config = require('../config/database');

/**
 * Classe Usuario, somente para dados locais
 */
function User(people, username, phoneId) {
  var _username = username;
  var _people = parseInt(people) + 1;
  var _timeWait = 0;
  var _admin = false;
  var _tables = [];
  var _thread;
  var _phoneId = phoneId;
  startTime();

  /**
   * Função que da start em tudo, para quando o usuário logar
   * @param  {Number} people Quantidade de pessoas com o usuário
   */


  this.getPeople = function() {
    return _people;
  };

  this.getPhoneId = function() {
    return _phoneId;
  };

  this.getName = function() {
    return _username;
  };

  /**
   * Contador do tempo de espera
   */
  function startTime() {
    _thread = setInterval(function() {
      _timeWait += 1;
    }, 1000);
  }

  this.stopTime = function() {
    clearInterval(_thread);
  };

  this.getTimeWait = function() {
    return _timeWait;
  };

  this.addTableUser = function(table) {
    _tables.push(table);
  };

  this.getTables = function() {
    return _tables;
  };

  this.notify = function() {
    var sender = new gcm.Sender(config.apiKey);

    // Prepare a message to be sent
    var message = new gcm.Message({
      priority: 'high',
      notification: {
        title: "Olá",
        body: "Por favor, se dirija a(s) mesa(s): " + _tables
      }
    });

    // Specify which registration IDs to deliver the message to
    var regTokens = [_phoneId];

    // Actually send the message
    sender.send(message, {
      registrationTokens: regTokens
    }, function(err, response) {
      if (err) console.error(err);
      else console.log(response);
    });
  };

  /**
   * Salva o usuário no DB
   * @param  {String} name     nome do usuário
   * @param  {Stirng} username username do usuário
   * @param  {String} password senha do usuario
   * @return {Promise}          resposta a ação
   */
  User.save = function(name, username, password) {
    var u = new user({
      name: name,
      username: username,
      password: password
    });

    return u.save()
      .then(function(user) {
        return {
          success: true,
          msg: 'Usuário(a) ' + user.username + ' cadastrado(a) com sucesso!'
        };
      })
      .catch(function(err) {
        throw {
          success: false,
          msg: 'Usuário já existe'
        };
      });
  };

  /**
   * Loga o usuário no sistema
   * @param  {String} username username do usuário
   * @param  {String} password senha do usuário
   * @return {Promise}          resposta à ação
   */
  User.login = function(username, password) {
    return user.findOne({
        username: username
      })
      .then(function(user) { //TODO verificar promise
        if (!user) {
          throw 'Usuário não encontrado';
        } else {
          return user.comparePassword(password)
            .then(function(isMatch) {
              var token = jwt.encode({ //TODO ver oq cabe colocar, quem sabe o id no banco
                _id: user._id,
                role: user.role,
                name: user.name,
                username: user.username
              }, config.secret);

              return {
                success: true,
                //setando o token da sessao
                token: 'JWT ' + token,
                msg: 'Seja bem vindo(a), ' + user.name + '!'
              };
            })
            .catch(function(err) {
              throw err; //lança pro outro try e catch
            });
        }
      })
      .catch(function(err) {
        throw {
          success: false,
          msg: err
        };
      });
  };

  User.getAll = function() {
    return user.find()
      .then(function(users) {
        return {
          success: true,
          msg: users
        };
      })
      .catch(function(err) {
        throw {
          success: false,
          msg: err
        };
      });
  };



}

module.exports = User;