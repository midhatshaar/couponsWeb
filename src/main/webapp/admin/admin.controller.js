(function(){
   ' use strict';
    angular
        .module('CouponManagement')
        .controller("AdminCtrl", ["companyResource", AdminCtrl]);

    AdminCtrl.$inject = ['$resource', '$scope', '$location'];

    function AdminCtrl(){

    }


})();