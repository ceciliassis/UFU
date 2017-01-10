var express = require('express');
var router = express.Router();
var mongoose = require('mongoose');
var passport = require('passport');
var User = require('../models/user');
var Table = require('../models/table');
var us = new User();
var Queue = require('../models/queueSingleton');
var q = Queue.QueueSingleton();

router.post('/signup', function(req, res) {
  var username = req.body.username,
    name = req.body.name,
    password = req.body.password;
  if (!username || !password || !name) {
    return res.json({
      success: false,
      msg: 'Por favor, forneça seu nome, username e a senha'
    });
  } else {
    User.save(name, username, password)
      .then(function(data) {
        return res.json(data);
      })
      .catch(function(err) {
        return res.json(err);
      });
  }
});

router
  .post('/logout', function(req, res) {
    q.removeFromList(req.body.username);
    return res.json();
  });

router
  .post('/login', function(req, res) {
    var username = req.body.username,
      password = req.body.password;
    if (!username || !password) {
      return res.json({
        success: false,
        msg: 'Por favor, forneça seu username e a senha'
      });
    } else {
      User.login(username, password)
        .then(function(data) {
          return res.json(data);
        })
        .catch(function(err) {
          return res.send(err);
        });
    }
  });

module.exports = router;