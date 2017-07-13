var express = require('express');
var bodyParser = require('body-parser');
var mongoose = require('mongoose');
var morgan = require('morgan');
var passport = require('passport');
var config = require('./config/database');
var assert = require('assert');


var port = process.env.PORT || 8080;

var app = express(); //creating the express app
mongoose.Promise = require('bluebird');

//compilando models do banco
require('./models/schemas');

//add middleware(interceptor) necessary for REST API's
app.use(bodyParser.urlencoded({
  extended: false
}));

//permite pegar os vars do request
app.use(bodyParser.json());

//log to console
app.use(morgan('dev'));

app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
  res.header('Access-Control-Allow-Methods', 'POST, GET, PUT, DELETE, OPTIONS');
  if (req.method === 'OPTIONS') {
    res.end();
  } else {
    next();
  }
});

require('./config/passport')(passport);

app.use('/auth', require('./routes/auth'));
app.use('/cpg', require('./routes/cpg'));

//passport package - authentication
app.use(passport.initialize());

//demo route
app.get('/', function(req, res) {
  res.send('Hello!');
});

//connect db
mongoose.connect(String(config.database)); //HACK stack error

console.log('connected - port', port);
app.listen(port);