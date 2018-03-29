(function(){
    ' use strict';
    angular
        .module('CouponManagement')
        .controller("CompanyCtrl", ["companyResource", CompanyCtrl]);

    CompanyCtrl.$inject = ['$resource', '$scope', '$location'];

    function CompanyCtrl(){
    }


})();