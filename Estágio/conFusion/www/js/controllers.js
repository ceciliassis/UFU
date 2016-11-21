angular.module('conFusion.controllers', []).controller('AppCtrl', function ($scope, $ionicModal, $timeout, $localStorage, $ionicPlatform, $cordovaCamera, $cordovaImagePicker) {
  // Form data for the login modal
  //recuperando os dados armazenados no localstorage
  $scope.loginData = $localStorage.getObject('userinfo', '{}');
  $scope.reservation = {};
  $scope.registration = {};

  // Create the login modal that we will use later
  $ionicModal.fromTemplateUrl('templates/login.html', {
    scope: $scope
  }).then(function (modal) {
    $scope.modal = modal;
  });
  // Triggered in the login modal to close it
  $scope.closeLogin = function () {
    $scope.modal.hide();
  };
  // Open the login modal
  $scope.login = function () {
    $scope.modal.show();
  };
  // Perform the login action when the user submits the login form
  $scope.doLogin = function () {
    console.log('Doing login', $scope.loginData);
    $localStorage.storeObject('userinfo', $scope.loginData);
    // Simulate a login delay. Remove this and replace with your login
    // code if using a login system
    $timeout(function () {
      $scope.closeLogin();
    }, 1000);
  };
  $ionicModal.fromTemplateUrl('templates/reserve.html', {
    scope: $scope
  }).then(function (modal) {
    $scope.reserveform = modal;
    //another name for another modal
  });
  $scope.closeReserve = function () {
    $scope.reserveform.hide();
  };
  $scope.reserve = function () {
    //clicou na página, mostrou o formulário!
    $scope.reserveform.show();
  };
  $scope.doReserve = function () {
    console.log('Doing reservation', $scope.reservation);
    // Simulate a login delay. Remove this and replace with your login
    // code if using a login system
    $timeout(function () {
      $scope.closeReserve();
    }, 1000);
  };

  // Create the registration modal that we will use later
  $ionicModal.fromTemplateUrl('templates/register.html', {
    scope: $scope
  }).then(function (modal) {
    $scope.registerform = modal;
  });

  // Triggered in the registration modal to close it
  $scope.closeRegister = function () {
    $scope.registerform.hide();
  };

  // Open the registration modal
  $scope.register = function () {
    $scope.registerform.show();
  };

  // Perform the registration action when the user submits the registration form
  $scope.doRegister = function () {
    // Simulate a registration delay. Remove this and replace with your registration
    // code if using a registration system
    $timeout(function () {
      $scope.closeRegister();
    }, 1000);
  };

  $ionicPlatform.ready(function () {
    var ops = {
      maximumImagesCount: 1,
      width: 100,
      height: 100,
      quality: 70
    };
    $scope.gallery = function () {
      $cordovaImagePicker.getPictures(ops)
        .then(function (results) {
          $scope.registration.imgSrc = results[0];
        }, function (error) {
          console.log(error);
        });
      $scope.registerform.show();
    };
    ////////////////////////////////////////
    var options = {
      quality: 50,
      destinationType: Camera.DestinationType.DATA_URL,
      sourceType: Camera.PictureSourceType.CAMERA,
      allowEdit: true,
      encodingType: Camera.EncodingType.JPEG,
      targetWidth: 100,
      targetHeight: 100,
      popoverOptions: CameraPopoverOptions,
      saveToPhotoAlbum: false
    };
    $scope.takePicture = function () {
      $cordovaCamera.getPicture(options).then(function (imageData) {
        $scope.registration.imgSrc = "data:image/jpeg;base64," + imageData;
      }, function (err) {
        console.log(err);
      });
      $scope.registerform.show();
    };
  });
}).controller('MenuController', ['$scope', 'dishes', 'menuFactory', 'favoriteFactory', '$ionicListDelegate', 'baseURL', '$ionicPlatform', '$cordovaLocalNotification', '$cordovaToast', function ($scope, dishes, menuFactory, favoriteFactory, $ionicListDelegate, baseURL, $ionicPlatform, $cordovaLocalNotification, $cordovaToast) {
  $scope.baseURL = baseURL;
  $scope.tab = 1;
  $scope.filtText = '';
  $scope.dishes = dishes;
  $scope.select = function (setTab) {
    $scope.tab = setTab;
    if (setTab === 2) {
      $scope.filtText = "appetizer";
    } else if (setTab === 3) {
      $scope.filtText = "mains";
    } else if (setTab === 4) {
      $scope.filtText = "dessert";
    } else {
      $scope.filtText = "";
    }
  };
  $scope.isSelected = function (checkTab) {
    return ($scope.tab === checkTab);
  };
  $scope.addFavorite = function (index) {

    favoriteFactory.addToFavorites(index);
    $ionicListDelegate.closeOptionButtons();

    $ionicPlatform.ready(function () {
      $cordovaLocalNotification.schedule({
        id: 1,
        title: "Added Favorite",
        text: $scope.dishes[index].name
      }).then(function () {
        console.log('Added Favorite ' + $scope.dishes[index].name);
      }, function () {
        console.log('Failed to add Favorites');
      });
      $cordovaToast.show('Added Favorite ' + $scope.dishes[index].name, 'long', 'center').then(function (success) {}, function (error) {});
    });
  };
}]).controller('DishDetailController', ['$scope', '$stateParams', 'dish', 'menuFactory', 'baseURL', '$ionicPopover', 'favoriteFactory', '$ionicModal', '$cordovaLocalNotification', '$cordovaToast', '$ionicPlatform', function ($scope, $stateParams, dish, menuFactory, baseURL, $ionicPopover, favoriteFactory, $ionicModal, $cordovaLocalNotification, $cordovaToast, $ionicPlatform) {
  $scope.baseURL = baseURL;
  $scope.dish = dish;
  //////////////////////////////////////////////////
  $ionicPopover.fromTemplateUrl('templates/dish-detail-popover.html', {
    scope: $scope
  }).then(function (popover) {
    $scope.popover = popover;
  });
  $scope.openPopover = function ($event) {
    $scope.popover.show($event);
  };

  $scope.addFavorites = function () {
    console.log($scope.dish.id);
    favoriteFactory.addToFavorites($scope.dish.id);
    $scope.popover.hide();
    $ionicPlatform.ready(function () {
      $cordovaLocalNotification.schedule({
        id: 1,
        title: "Added Favorite",
        text: $scope.dish.name
      }).then(function () {
        console.log('Added Favorite ' + $scope.dish.name);
      }, function () {
        console.log('Failed to add Favorites');
      });
      $cordovaToast.show('Added Favorite ' + $scope.dish.name, 'long', 'bottom').then(function (success) {}, function (error) {});
    });

  };
  /////////////////////////////////////////////////////
  $scope.commentData = {
    rating: 5,
    comment: "",
    author: "",
    date: ""
  };
  $ionicModal.fromTemplateUrl('templates/dish-comment.html', {
    scope: $scope
  }).then(function (modal) {
    $scope.commentModal = modal;
  });
  $scope.closeComment = function () {
    $scope.commentModal.hide();
    $scope.popover.hide();
  };
  $scope.addComment = function () {
    $scope.commentModal.show();
    $scope.popover.hide();
  };
  $scope.doComment = function () {
    //coloca a data de hj
    $scope.commentData.date = new Date().toISOString();
    console.log($scope.commentData);
    //passa de string para int
    $scope.commentData.rating = parseInt($scope.commentData.rating);
    //manda o obj js
    $scope.dish.comments.push($scope.commentData);
    //carrega o novo comentário no DB
    menuFactory.update({
      id: $scope.dish.id
    }, $scope.dish);
    $scope.commentModal.hide();
  };
}]).controller('IndexController', ['$scope', 'leader', 'dish', 'promotion', 'baseURL', function ($scope, leader, dish, promotion, baseURL) {
  $scope.baseURL = baseURL;
  $scope.leader = leader;
  $scope.dish = dish;
  $scope.promotion = promotion;
}]).controller('AboutController', ['$scope', 'corporateFactory', 'baseURL', 'leaders', function ($scope, corporateFactory, baseURL, leaders) {
  $scope.baseURL = baseURL;
  $scope.leaders = leaders;
  console.log($scope.leaders);
}]).controller('FavoritesController', ['$scope', 'dishes', 'favorites', 'favoriteFactory', 'baseURL', '$ionicListDelegate', '$ionicPopup', '$ionicPlatform', '$cordovaVibration', function ($scope, dishes, favorites, favoriteFactory, baseURL, $ionicListDelegate, $ionicPopup, $ionicPlatform, $cordovaVibration) {
  $scope.baseURL = baseURL;
  $scope.shouldShowDelete = false;
  $scope.favorites = favorites;
  $scope.dishes = dishes;
  //console.log($scope.dishes, $scope.favorites);
  $scope.toggleDelete = function () {
    $scope.shouldShowDelete = !$scope.shouldShowDelete;
    console.log($scope.shouldShowDelete);
  };

  $scope.deleteFavorite = function (index) {
    var confirmPopup = $ionicPopup.confirm({
      title: "Confirm Delete",
      template: "Are you sure you want to delete this item?"
    });
    confirmPopup.then(function (result) {
      if (result) { //ok
        console.log("Ok to delete");
        favoriteFactory.deleteFromFavorites(index);
        $ionicPlatform.ready(function () {
          $cordovaVibration.vibrate(500);
        });
      } else {
        console.log("Canceled delete");
      }
    });
    $scope.shouldShowDelete = false;
  };


}]).filter('favoriteFilter', function () {
  return function (dishes, favorites) {
    var out = [];
    for (var i = 0; i < favorites.length; i++) {
      for (var j = 0; j < dishes.length; j++) {
        if (dishes[j].id === favorites[i].id) out.push(dishes[j]);
      }
    }
    return out;
  }
});
