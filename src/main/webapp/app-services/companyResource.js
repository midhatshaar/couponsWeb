(function (){
    'use strict';

    angular
        .module('CouponManagement')
        .factory('companyResource', ["$resource", companyResource]);

/*
    used $resource here for practice only.
 */
    function companyResource($resource){
        return $resource('http://localhost:8080/couponsweb/webresources/company/:id');
    }

}());