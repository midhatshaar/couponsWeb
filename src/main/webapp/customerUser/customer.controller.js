(function(){
    ' use strict';
    angular
        .module('CouponManagement')
        .controller("CustomerCtrl", ["companyResource", CustomerCtrl]);

    CustomerCtrl.$inject = ['$resource', '$scope', '$location'];

    function CustomerCtrl(){
    }


})();