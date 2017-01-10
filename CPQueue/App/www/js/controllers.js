angular.module('cpgQueue.controllers', [])
  .controller('homeCtrl', function($scope, $ionicModal,
    $state, $window, $ionicPopup, AuthService, AUTH_EVENTS, $ionicPlatform,
    PublicService, $rootScope, $cordovaLocalNotification, $cordovaToast) {

    $scope.username = AuthService.username();

    $scope.$on(AUTH_EVENTS.notAuthorized, function(event) {
      var alertPopup = $ionicPopup.alert({
        title: 'Não Autorizado!',
        template: 'Você não pode acessar essa página.'
      });
    });

    $scope.$on(AUTH_EVENTS.notAuthenticated, function(event) {
      AuthService.logout();
      $state.go('home');
      var alertPopup = $ionicPopup.alert({
        title: 'Sessão perdida!',
        template: 'Logue novamente.'
      });
    });

    $scope.logout = function() {
      AuthService.logout();
      $state.go('home');
      $ionicPlatform.ready(function() {
        $cordovaToast.showShortBottom('Volte sempre!');
      });
    };

    //////////////////////////////// fetchTable
    $scope.fetchTableData = {
      acompanhantes: undefined
    };

    $ionicModal.fromTemplateUrl('templates/modals/fetchtable.html', {
        scope: $scope
      })
      .then(function(modal) {
        $scope.fetchTableModal = modal;
      });

    $scope.openFetchTableModal = function() {
      $scope.fetchTableModal.show();
    };
    $scope.closeFetchTableModal = function() {
      $scope.fetchTableModal.hide();
    };


    var firstTime = true;

    $scope.doFetchTable = function() {
      if ($scope.fetchTableData.acompanhantes) {
        $scope.fetchTableData.username = $scope.username;
        PublicService.buscarMesa($scope.fetchTableData)
          .then(function(msg) {
            firstTime = false;
            $ionicPlatform.ready(function() {
              $cordovaToast.showLongBottom(msg);
            });
          });
        $scope.closeFetchTableModal();
      } else {
        $ionicPopup.alert({
          title: 'Erro!',
          template: 'Por favor selecione o número de acompanhantes.'
        });
      }
    };

    $scope.fetched = firstTime ? false : true;

    $scope.queueInfo = function(argument) {
      PublicService.buscarMesa($scope.fetchTableData)
        .then(function(msg) {
          firstTime = false;
          $ionicPlatform.ready(function() {
            $ionicPopup.alert({
              title: 'Informaçãoes!',
              template: msg
            });
            // $cordovaToast.showLongBottom(msg);
          });
        });
    };

    $rootScope.$on('cloud:push:notification', function(event, data) {
      var msg = data.message;

      $ionicPopup.alert({
          title: 'Sucesso!',
          template: msg.text
        });

      $ionicPlatform.ready(function() {
        $cordovaLocalNotification.schedule({
          title: msg.title,
          text: msg.text
        });
      });
    });

    $rootScope.$on('logout', function() {
      firstTime = true;
    });

  })

.controller('loginCtrl', function($scope, $state, $ionicPopup, $rootScope,
  AuthService, $ionicModal, USER_ROLES, $cordovaToast, $ionicPlatform) {

  var logout = false;
  $rootScope.$on('logout', function() {
    logout = true;
  });

  $scope.user = {
    name: '',
    username: '',
    password: ''
  };

  ////////////////////////////////////// 
  $ionicModal.fromTemplateUrl('templates/modals/login.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.loginModal = modal;
  });

  $scope.closeLoginModal = function() {
    $scope.loginModal.hide();
  };

  $scope.openLoginModal = function() {
    if (logout) { //TESTAR
      $ionicPopup.alert({
        title: 'Sessão perdida!',
        template: 'Por favor, reinicie o aplicativo.'
      });
    } else $scope.loginModal.show();
  };

  /**
   * Faz o login do usuário
   */
  $scope.login = function() {
    if (isLoginValid()) {
      AuthService.login($scope.user)
        .then(function(msg) {
          $ionicPlatform.ready(function() {
            $cordovaToast.showLongBottom(msg);
          });

          if (AuthService.role() == USER_ROLES.admin)
            $state.go('main.admin');
          else
            $state.go('app.home');
        })
        .catch(function(err) {
          $rootScope.$broadcast('loading:hide');
          var alertPopup = $ionicPopup.alert({
            title: 'Erro no login!',
            template: err
          });
        });
      $scope.closeLoginModal();
      $scope.user = {
        name: '',
        username: '',
        password: ''
      };
    }
  };

  ////////////////////////////////// CADASTRO
  $ionicModal.fromTemplateUrl('templates/modals/signup.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.signUpModal = modal;
  });

  $scope.openSignUpModal = function() {
    $scope.signUpModal.show();
  };
  $scope.closeSignUpModal = function() {
    $scope.signUpModal.hide();
  };

  /**
   * Cadastrar usuário
   */
  $scope.signup = function() {
    // console.log('HERE');
    if (isSignupValid()) {
      AuthService.register($scope.user)
        .then(function(msg) {
          $ionicPlatform.ready(function() {
            $cordovaToast.showLongBottom(msg);
          });
          $state.go('app.home');
        })
        .catch(function(err) {
          var alertPopup = $ionicPopup.alert({
            title: 'Erro no cadastro!',
            template: err
          });
        });
      $scope.closeSignUpModal();
      $scope.user = {
        name: '',
        username: '',
        password: ''
      };
    }
  };


  function isSignupValid() {
    if (!$scope.user.name || !$scope.user.username || !$scope.user.password) {
      $ionicPopup.alert({
        title: 'Erro no cadastro!',
        template: 'Por favor, digite todos os campos.'
      });
      return false;
    }

    if ($scope.user.username.length < 6 || $scope.user.name.length < 6 || $scope.user.password.length < 6) {
      $ionicPopup.alert({
        title: 'Erro no cadastro!',
        template: 'Nome e/ou Username menor que 6 caracteres.'
      });

      $scope.user.password = '';
      return false;
    }
    return true;
  }



  function isLoginValid() {
    if (!$scope.user.username || !$scope.user.password) {
      $ionicPopup.alert({ //TODO aumentar o tamanho
        title: 'Erro no cadastro!',
        template: 'Por favor, digite todos os campos.'
      });
      $scope.user.password = '';
      return false;
    }
    return true;
  }

})

.controller('adminCtrl', function(AuthService, AdminService, $ionicPopup,
  $scope, $state, $ionicModal, $ionicPlatform, $cordovaToast) {

  $scope.logout = function() {
    AuthService.logout();
    $state.go('home');
  };

  $scope.newTable = function() {
    var confirmPopup = $ionicPopup.confirm({
      title: 'Cadastrar Mesa',
      template: 'Tem certeza que deseja cadastrar uma nova mesa?',
      cancelText: 'Não',
      cancelType: 'button-positive',
      okText: 'Sim',
      okType: 'button-default'
    });

    confirmPopup.then(function(ok) {
      if (ok) {
        AdminService.newTable()
          .then(function(msg) {

            $ionicPlatform.ready(function() {
              $cordovaToast.showLongBottom(msg);
            });
            AdminService.tables()
              .then(function(tables) {
                $scope.tables = tables;
              });
          })
          .catch(function(err) {
            var alertPopup = $ionicPopup.alert({
              title: 'Erro no cadastro!',
              template: err
            });
          });
      }
    });
  };

  $ionicModal.fromTemplateUrl('templates/modals/tables.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.tableModal = modal;
  });

  $scope.closeTablesModal = function() {
    $scope.tableModal.hide();
  };

  var openTablesModal = function() {
    $scope.tableModal.show();
  };

  $scope.seeTables = function() {
    AdminService.tables()
      .then(function(tables) {
        $scope.tables = tables;
        openTablesModal();
      })
      .catch(function(err) {
        var alertPopup = $ionicPopup.alert({
          title: 'Erro na busca pelas mesas',
          template: err
        });
      });
  };

  $ionicModal.fromTemplateUrl('templates/modals/users.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.usersModal = modal;
  });

  $scope.closeUsersModal = function() {
    $scope.usersModal.hide();
  };

  var openUsersModal = function() {
    $scope.usersModal.show();
  };

  $scope.seeUsers = function() {
    AdminService.users()
      .then(function(users) {
        $scope.users = users;
        openUsersModal();
      })
      .catch(function(err) {
        var alertPopup = $ionicPopup.alert({
          title: 'Erro na busca pelos usuários',
          template: err
        });
      });
  };

});
