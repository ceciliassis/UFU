angular.module('conFusion', ['ionic', 'ngCordova', 'conFusion.controllers', 'conFusion.services']).run(function ($ionicPlatform, $rootScope, $ionicLoading, $cordovaSplashscreen, $timeout) {
  $ionicPlatform.ready(function () {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);
    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }

    //ngCordova precisa ser usado aqui dentro, porque só quando o app
    //está pronto, é que o podem ocorrer requests para o Cordova
    $timeout(function () {
      $cordovaSplashscreen.hide();
    }, 2000);

  });
  $rootScope.$on('loading:show', function () {
    $ionicLoading.show({
      template: '<ion-spinner></ion-spinner> Loading ...'
    })
  });
  $rootScope.$on('loading:hide', function () {
    $ionicLoading.hide();
  });
  //mudou de tela, mostra que está carregando
  $rootScope.$on('$stateChangeStart', function () {
    console.log('Loading...');
    $rootScope.$broadcast('loading:show');
  });
  //terminou de mudar com sucesso,tira o carregamento
  $rootScope.$on('$stateChangeSuccess', function () {
    console.log('done');
    $rootScope.$broadcast('loading:hide');
  })
}).config(function ($stateProvider, $urlRouterProvider) {
  $stateProvider.state('app', {
    url: '/app',
    abstract: true, //top state
    templateUrl: 'templates/sidebar.html',
    controller: 'AppCtrl'
  }).state('app.home', { //nested states
    url: '/home',
    views: {
      'mainContent': { //name of the ion nav view
        templateUrl: 'templates/home.html',
        controller: 'IndexController',
        resolve: {
          dish: ['menuFactory', function (menuFactory) {
            return menuFactory.get({
              id: 0
            });
          }],
          leader: ['corporateFactory', function (corporateFactory) {
            return corporateFactory.get({
              id: 3
            });
          }],
          promotion: ['promotionFactory', function (promotionFactory) {
            return promotionFactory.get({
              id: 0
            });
          }]
        }
      }
    }
  }).state('app.aboutus', {
    url: '/aboutus',
    views: {
      'mainContent': {
        templateUrl: 'templates/aboutus.html',
        controller: 'AboutController',
        resolve: {
          leaders: ['corporateFactory', function (corporateFactory) {
            return corporateFactory.query();
          }]
        }
      }
    }
  }).state('app.contactus', {
    url: '/contactus',
    views: {
      'mainContent': {
        templateUrl: 'templates/contactus.html'
      }
    }
  }).state('app.favorites', {
    url: '/favorites',
    views: {
      'mainContent': {
        templateUrl: 'templates/favorites.html',
        controller: 'FavoritesController',
        resolve: {
          dishes: ['menuFactory', function (menuFactory) {
            return menuFactory.query();
          }],
          favorites: ['favoriteFactory', function (favoriteFactory) {
            return favoriteFactory.getFavorites();
          }]
        }
      }
    }
  }).state('app.menu', {
    url: '/menu',
    views: {
      'mainContent': {
        templateUrl: 'templates/menu.html',
        controller: 'MenuController',
        resolve: {
          dishes: ['menuFactory', function (menuFactory) {
            return menuFactory.query();
          }]
        }
      }
    }
  }).state('app.dishdetails', {
    url: '/menu/:id',
    views: {
      'mainContent': {
        templateUrl: 'templates/dishdetail.html',
        controller: 'DishDetailController',
        resolve: {
          dish: ['$stateParams', 'menuFactory', function ($stateParams, menuFactory) {
            return menuFactory.get({
              id: parseInt($stateParams.id, 10)
            });
          }]
        }
      }
    }
  });
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/app/home');
});