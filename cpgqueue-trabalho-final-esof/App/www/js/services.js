angular.module('cpgQueue.services', [])

/**
 * Authentidador
 * @param  {Object} USER_ROLES @see constants.js
 * @param  {Object} AUTH_ENDPOINT) Routes @see constants.js
 */
.service('AuthService', function AuthService($http, USER_ROLES,
  AUTH_ENDPOINT, Auth, jwtHelper, $ionicPush, $ionicPopup, $rootScope) {
  var LOCAL_TOKEN_KEY = 'token';
  var username = '';
  var name = '';
  var isAuthenticated = false; //logou?
  var role = '';
  var authToken;
  var phoneId;

  /**
   * Carrega as credenciais armazenadas no browser
   */
  function registerPushService() {
    $ionicPush.register()
      .then(function(t) {
        return $ionicPush.saveToken(t);
      })
      .then(function(t) {
        phoneId = t.token;
      })
      .catch(function(err) {
        $rootScope.$broadcast('loading:hide');
        $ionicPopup.alert({
          title: 'Erro!',
          template: 'Ocorreu um erro na conexão, cheque sua internet, tente novamente.'
        });
      });
    destroyUserCredentials();
  }

  /**
   * Salva credenciais == token no browser
   * @param  {String} token
   */
  function storeUserCredentials(token) {
    window.localStorage.setItem(LOCAL_TOKEN_KEY, token);
    useCredentials(token);
  }

  /**
   * Faz o trabalho da autenticação continua no Cliente
   * @param  {String} token de autenticação
   */
  function useCredentials(token) {
    var decoded = jwtHelper.decodeToken(token);
    userRole = decoded.role;
    name = decoded.name;
    username = decoded.username;

    isAuthenticated = true;
    authToken = token;
    if (userRole == 'admin') role = USER_ROLES.admin;
    if (userRole == 'public') role = USER_ROLES.public;

    Auth.setRole(role);

    $http.defaults.headers.common.Authorization = authToken;
  }

  /**
   * Destroi as credenciais de autenticação
   */
  function destroyUserCredentials() {
    authToken = undefined;
    username = '';
    isAuthenticated = false;
    $http.defaults.headers.common.Authorization = undefined;
    window.localStorage.removeItem(LOCAL_TOKEN_KEY);
  }

  /**
   * Login do usuário
   * @param  {Object} Dados do usuário que está tentando cadastrar no banco
   * @return {String} Mensagem de resposta à requisição
   */
  var login = function(user) {
    return $http.post(AUTH_ENDPOINT.url + '/login', user)
      .then(function(result) {
        if (result.data.success) {
          //autentifica no servidor
          storeUserCredentials(result.data.token);
          //retorna a mensagem de sucesso!
          return result.data.msg;
        } else {
          throw result.data.msg;
        }
      }).catch(function(err) {
        if (err.status === -1) {
          $rootScope.$broadcast('loading:hide');
          throw 'Ocorreu um problema no servidor, por favor aguarde.';
        } else if (err.status === 503) {
          throw 'Servidor fora do ar.';
        } else throw err;

      });
  };

  var logout = function() {
    $rootScope.$broadcast('logout');
    $ionicPush.unregister();
    var promise = $http.post(AUTH_ENDPOINT.url + '/logout', {
      username: username
    });
    destroyUserCredentials();
    return promise;
  };

  /**
   * Verifica se o usuário fez o login, e está autenticado
   * @param  {[type]}  authorizedRoles [description]
   * @return {Boolean}                 [description]
   */
  var isAuthorized = function(authorizedRoles) {
    if (!angular.isArray(authorizedRoles)) {
      //Se não for um array @see app.js , recebe um, com o role dentro.
      authorizedRoles = [authorizedRoles];
    }
    return (isAuthenticated && authorizedRoles.indexOf(role) !== -1);
  };

  var register = function(user) {
    return $http.post(AUTH_ENDPOINT.url + '/signup', user)
      .then(function(result) {
        if (result.data.success)
          return result.data.msg;
        else
          throw result.data.msg;
      });
  };


  registerPushService();

  return {
    //Funcao de login
    login: login,
    //Funcao de logout
    logout: logout,
    //Funcao de autorização
    isAuthorized: isAuthorized,

    isAuthenticated: function() {
      return isAuthenticated;
    },
    username: function() {
      return username;
    },
    role: function() {
      return role;
    },
    name: function() {
      return name;
    },
    phoneId: function() {
      return phoneId;
    },
    register: register
  };

})

.factory('AuthInterceptor', function($rootScope, $q,
  AUTH_EVENTS) {
  return {
    responseError: function(response) {
      $rootScope.$broadcast({
        401: AUTH_EVENTS.notAuthenticated,
        403: AUTH_EVENTS.notAuthorized
      }[response.status], response);
      return $q.reject(response);
    }
  };
})

.factory('LoadingInterceptor', function($rootScope) {
  return {
    request: function(config) {
      if (!(config && config.data &&
          config.data.control &&
          config.data.control.pageOffset !== 0)) {
        $rootScope.$broadcast('loading:show');
      }
      return config;
    },

    response: function(response) {
      $rootScope.$broadcast('loading:hide');
      return response;
    }
  };
})

.config(function($httpProvider) {
  $httpProvider.interceptors.push('AuthInterceptor');
  $httpProvider.interceptors.push('LoadingInterceptor');
})


.provider('Auth', function() {
  var role;
  var setRole = function(role) {
    role = role;
  };
  this.$get = function() {
    return {
      role: role,
      setRole: setRole
    };
  };


})

.service('AdminService', function($http, API_ENDPOINT) {

  var tables = function() {
    return $http.get(API_ENDPOINT.url + '/tables')
      .then(function(result) {
        if (result.data.success)
          return result.data.msg;
        else
          throw result.data.msg;
      });
  };

  var newTable = function() {
    return $http.post(API_ENDPOINT.url + '/tables')
      .then(function(result) {
        if (result.data.success)
          return result.data.msg;
        else
          throw result.data.msg;
      });
  };

  var users = function() {
    return $http.get(API_ENDPOINT.url + '/users')
      .then(function(result) {
        if (result.data.success)
          return result.data.msg;
        else
          throw result.data.msg;
      });
  };

  return {
    newTable: newTable,
    tables: tables,
    users: users
  };

})

.service('PublicService', function($http, API_ENDPOINT, AuthService) {

  var buscarMesa = function(user) {
    user.phoneId = AuthService.phoneId();
    return $http.post(API_ENDPOINT.url + '/users', user)
      .then(function(result) {
        return result.data.msg;
      });
  };

  return {
    buscarMesa: buscarMesa
  };

});