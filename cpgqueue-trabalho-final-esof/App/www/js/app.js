angular.module('cpgQueue', ['ionic', 'cpgQueue.controllers',
  'cpgQueue.services', 'cpgQueue.constants', 'angular-jwt',
  'ionic.cloud', 'ngCordova'
])

.run(function($ionicPlatform, $httpBackend, $rootScope,
  AuthService, $state, $ionicLoading, $ionicPush) {
  $ionicPlatform.ready(function() {
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);
    }
    if (window.StatusBar) {
      StatusBar.styleDefault();
    }

  });

  $rootScope.$on('$stateChangeStart', function(event, next, nextParams, fromState) {

    if ('data' in next && 'authorizedRoles' in next.data) {
      var authorizedRoles = next.data.authorizedRoles;
      if (!AuthService.isAuthorized(authorizedRoles)) {
        event.preventDefault();
        $state.go($state.current, {}, {
          reload: true
        });
        $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
      }
    }

    if (!AuthService.isAuthenticated()) { //nao logou
      if (next.name !== 'home') { //nao pode ir para outra pagina
        event.preventDefault();
        $state.go('login');
      }
    }
  });

  //on broadbanding a 'loading:show' $ionicLoading will appear
  $rootScope.$on('loading:show', function() {
    $ionicLoading.show({
      template: "Carregando...<br/><ion-spinner icon='ripple' class='spinner-balanced'></ion-spinner>",
      noBackdrop: true
    });
  });

  //on broadbanding a 'loading:hide' $ionicLoading will hide
  $rootScope.$on('loading:hide', function() {
    $ionicLoading.hide();
  });
})

.config(function($stateProvider, $urlRouterProvider, USER_ROLES,
  AuthProvider, $ionicCloudProvider) {
  $stateProvider
    .state('home', { //Login
      url: '/home',
      templateUrl: 'templates/login.html',
      controller: 'loginCtrl'
    })

  .state('app', {
    url: '/app',
    abstract: true,
    templateUrl: 'templates/menu.html',
    controller: 'homeCtrl'
  })

  .state('app.aboutQueue', {
    url: '/aboutQueue',
    views: {
      'menuContent': {
        templateUrl: 'templates/aboutqueue.html'
          //TODO mostrar lugar na fila do cara
          //TODO mostrar tempo esperando
          //TODO MODALLLL
      }
    }
  })

  .state('app.home', { //Buscar mesa
    url: '/home',
    views: {
      'menuContent': {
        templateUrl: 'templates/home.html',
        controller: 'homeCtrl'
      }
    }
  })

  ///////// AREA RESTRITA

  .state('main', {
    url: '/main',
    abstract: true,
    templateUrl: 'templates/menu-adm.html',
    controller: 'adminCtrl'
  })

  .state('main.admin', { //TODO area autenticada
    url: '/admin',
    views: {
      'admin-tab': {
        templateUrl: 'templates/admin.html',
        controller: 'adminCtrl'
      }
    },
    data: {
      authorizedRoles: [USER_ROLES.admin]
    }
  });

  if (AuthProvider.$get.role == USER_ROLES.admin)
    $urlRouterProvider.otherwise('/main/admin');
  else
    $urlRouterProvider.otherwise('/home');

  $ionicCloudProvider.init({
    "core": {
      "app_id": "9136e11b"
    },
    "push": {
      "sender_id": "70663042747"
    }
  });

});