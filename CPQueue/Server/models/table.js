var mongoose = require('mongoose');
var table = mongoose.model('Table');

/**
 * Classe Mesa
 */
function Table() {

  Table.getTables = function() {
    return table.find()
      .then(function(tables) {
        return tables;
      })
      .catch(function(err) {
        return [];
      });
  };

  Table.getAvailable = function() {
    return table.find({
        disponivel: true
      })
      .then(function(tables) {
        return tables;
      })
      .catch(function(err) {
        return [];
      });
  };

  Table.updateStatus = function(id, status) {
    return table.findOneAndUpdate({
        _id: id
      }, {
        disponivel: status
      })
      .then(function(table) {
        return table;
      })
      .catch(function(err) {
        return [];
      });
  };

  Table.getNotAvailable = function() {
    return table.find({
        disponivel: false
      })
      .then(function(tables) {
        return tables;
      })
      .catch(function(err) {
        return [];
      });
  };


  table.find()
    .then(function(tables) {
      if (tables.length > 0)
        Table.cont = tables[tables.length - 1]._id;
      else Table.cont = 0;
    });

  /**
   * Salva a mesa no banco
   */
  Table.save = function() {
    var t = new table({
      _id: ++Table.cont
    });

    return t.save()
      .then(function(table) {
        return {
          success: true,
          msg: 'Mesa ' + table._id + ' cadastrada com sucesso!'
        };
      })
      .catch(function(err) {
        console.log(err);
        throw {
          success: false,
          msg: 'Essa mesa já existe'
        };
      });
  };

  Table.getAll = function() {
    return table.find()
      .then(function(posts) {
        return {
          success: true,
          msg: posts
        };
      })
      .catch(function(err) {
        throw {
          success: false,
          msg: 'Não existem mesas no banco'
        };
      });
  };

}

module.exports = Table;