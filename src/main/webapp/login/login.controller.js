//Written by Midhat Shaar

(function(){
    'use strict';
    angular
        .module("CouponManagement")
        .controller("LoginCtrl", LoginCtrl);

    LoginCtrl.$inject = ['$resource', '$scope', '$state'];
    function LoginCtrl($resource, $scope, $state) {
            $scope.myVar = false;
            $scope.submit = function () {
                var dataObj = {
                    userName: $scope.userName,
                    userPassword: $scope.password,
                    userType: $scope.userType
                };

                var User = $resource('http://localhost:8080/couponsweb/webresources/LoginService');
                User.save(dataObj, function (response) {
                    console.log(response);
                    //$scope.message = response.message;
                    toastr.info('login Succeed');

                    if (dataObj.userType == "ADMIN"){
                        $state.go('Admin');
                    }

                    if(dataObj.userType == "COMPANY"){
                        $state.go('Company');
                    }

                    if (dataObj.userType == "CUSTOMER"){
                        $state.go("Customer");
                    }

                }, function(error){
                    console.log(error);
                    //$scope.message = error.message;
                    toastr.error('login failed, please try again.')

                    });


            };


    };

})();









