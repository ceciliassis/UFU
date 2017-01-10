var express = require('express');
var router = express.Router();
var mongoose = require('mongoose');
var config = require('../config/database');
var Table = require('../models/table');
var t = new Table();
var User = require('../models/user');
var u = new User();
var Queue = require('../models/queueSingleton');
var q = Queue.QueueSingleton();
var passport = require('passport');

router.all('*', passport.authenticate('jwt', {
  session: false
}));

router.route('/users')
  .get(function(req, res) {
    User.getAll()
      .then(function(result) {
        return res.json(result);
      })
      .catch(function(err) {
        return res.json(err);
      });
  })
  .post(function(req, res) {
    q.addUser(new User(req.body.acompanhantes,
        req.body.username, req.body.phoneId))
      .then(function(result) {
        return res.json(result);
      });
  });


router.route('/users/:username')
  .get(function(req, res) {
    console.log(req + " " + res);

    //Mostrar posicao
  });


router.route('/tables')
  .get(function(req, res) {
    Table.getAll()
      .then(function(result) {
        return res.json(result);
      })
      .catch(function(err) {
        return res.json(err);
      });
  })
  .post(function(req, res) {
    Table.save()
      .then(function(result) {
        return res.json(result);
      })
      .catch(function(err) {
        return res.json(err);
      });
  });

//parte restrita, cara tem q estar autenticadi
// apiRoutes.get('/userinfo', passport.authenticate('jwt', {
//   session: false
// }), function(req, res) {
//   var token = getToken(req.headers);
//   if (token) {
//     var decoded = jwt.decode(token, config.secret);
//     user.model.findOne({
//         username: decoded.username
//       })
//       .then(function(user) {
//         if (!user) {
//           return res.status(403).send({
//             success: false,
//             msg: 'Usuário não encontrado.'
//           });
//         } else {
//           res.json({
//             success: true,
//             msg: 'Welcome in the member area ' + user.name + '!'
//           });
//         }
//       })
//       .catch(function(err) {
//         throw err;
//       });
//   } else {
//     return res.status(403).send({
//       success: false,
//       msg: 'No token provided.'
//     });
//   }
// });

module.exports = router;