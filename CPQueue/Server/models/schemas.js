var Promise = require('bluebird');
var bcrypt = Promise.promisifyAll(require('bcrypt'));
var mongoose = require('mongoose');
mongoose.Promise = Promise;

/**
 * Tabela no Banco de Dados - Usuário
 * @type {Mongoose#Schema}
 */
var UserSchema = mongoose.Schema({
  name: {
    type: String,
    require: true
  },
  username: {
    type: String,
    require: true,
    unique: true,
    index: true
  },
  password: {
    type: String,
    require: true
  },
  role: {
    type: String,
    default: 'public'
  }
});

/**
 * Criptografa a senha antes de salvar no banco
 * @param  {String} nome da funcao que será chamada antes
 * @param {Function} Callback
 */
UserSchema.pre('save', function(next) {
  var user = this;
  if (this.isModified('passport') || this.isNew) {
    bcrypt.genSaltAsync(10)
      .then(function(salt) {
        bcrypt.hashAsync(user.password, salt)
          .then(function(hash) {
            user.password = hash;
            next();
          }).catch(function(err) {
            return next(err);
          });
      })
      .catch(function(err) {
        return next(err);
      });
  }
});

/**
 * Compara a senha que está no banco, com a recebida
 * @param  {String} passw Senha digitada
 * @return {Promise} Resposta da Comparação
 */
UserSchema.methods.comparePassword = function(passw) {
  return bcrypt.compareAsync(passw, this.password)
    .then(function(isMatch) {
      if (isMatch) return isMatch;
      else throw 'Senha Incorreta!';
    }).catch(function(err) {
      throw err;
    });
};

mongoose.model('User', UserSchema);

///////////////////////////////////////////////////////////////////////////////// table

var TableSchema = mongoose.Schema({
  _id: Number,
  disponivel: {
    type: Boolean,
    default: true
  }
});

var model = mongoose.model('Table', TableSchema);