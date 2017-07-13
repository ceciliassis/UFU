var JwtStrategy = require('passport-jwt').Strategy,
  ExtractJwt = require('passport-jwt').ExtractJwt,
  mongoose = require('mongoose'),
  User = mongoose.model('User');

//database config file
var config = require('../config/database');

module.exports = function(passport) {
  var opts = {};
  opts.secretOrKey = config.secret;
  opts.jwtFromRequest = ExtractJwt.fromAuthHeader();

  passport.use(new JwtStrategy(opts, function(jwt_payload, done) {
    User.findById(jwt_payload._id)
      .then(function(user) {
        if (user) return done(null, true);
        else return done(null, false);
      })
      .catch(function(err) {
        return done(err, false);
      });
  }));

};