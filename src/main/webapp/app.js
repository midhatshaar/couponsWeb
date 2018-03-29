
(function () {
   var app = angular.module("CouponManagement", ['common.services','ngResource', 'ui.router',
       'ui.bootstrap']);

    app.config(["$stateProvider",
            "$urlRouterProvider",
            function ($stateProvider, $urlRouterProvider) {
                $urlRouterProvider.otherwise("/");

                $stateProvider

                    .state("login", {
                        url: "/",
                        templateUrl: "login/login.view.html",
                        controller: "LoginCtrl as vm"
                    })

                    .state("Admin", {
                        url: "/Admin",
                        templateUrl: "admin/admin.view.html",
                        controller: "AdminCtrl as vm"
                    })

                    .state("createCustomer", {
                        url: "/createCustomer",
                        templateUrl: "customer/createCustomer.html",
                        controller: "createCustomerCtrl"
                    })

                    .state("customerList", {
                        url: "/customerList",
                        templateUrl: "customer/customerList.html",
                        controller: "CustomerListCtrl"
                    })

                    .state("createCompany", {
                        url: "/createCompany",
                        templateUrl: "company/createCompany.html",
                        controller: "createCompanyCtrl"
                    })
                    .state("companyList", {
                        url:"/companyList",
                        templateUrl: "company/companyList.html",
                        controller: "CompanyListCtrl"
                    })

                    .state("Company", {
                        url: "/Company",
                        templateUrl: "companyUser/companyUser.view.html",
                        controller: "CompanyCtrl as vm"
                    })

                    .state("createCoupon", {
                        url: "/createCoupon",
                        templateUrl: "companyCoupon/createCoupon.html",
                        controller: "createCouponCtrl"
                    })

                    .state("companyCouponList", {
                        url: "/CompanyCouponList",
                        templateUrl: "companyCoupon/companyCouponList.html",
                        controller: "CouponListCtrl as vm"
                    })

                    .state("Customer", {
                        url: "/Customer",
                        templateUrl: "customerUser/customerUser.view.html",
                        controller: "CustomerCtrl as vm"
                    })

                    .state("purchaseCoupon", {
                        url:"/purchaseCoupon",
                        templateUrl: "customerUser/purchaseCoupon.html",
                        controller: "PurchaseCouponCtrl as vm"
                    })

                    .state("customerCouponList", {
                        url: "/customerCouponList",
                        templateUrl: "customerUser/customerCouponList.html",
                        controller: "customerCouponListCtrl as vm"
                    })

                    .state("companyEdit", {
                        url: "/companyEdit",
                        templateUrl: "company/companyEdit.html",
                        controller: "companyEditCtrl as vm"
                    })



            }]);



app.controller('EditableFormCtrl', function($scope, $filter, $http) {
        $scope.user = {
            id: 1,
            name: 'awesome user',
            status: 2,
            group: 4,
            groupName: 'admin'
        };


        $scope.groups = [];
        $scope.loadGroups = function() {
            return $scope.groups.length ? null : $http.get('/groups').success(function(data) {
                $scope.groups = data;
            });
        };

        $scope.showGroup = function() {
            if($scope.groups.length) {
                var selected = $filter('filter')($scope.groups, {id: $scope.user.group});
                return selected.length ? selected[0].text : 'Not set';
            } else {
                return $scope.user.groupName;
            }
        };

        $scope.checkName = function(data) {
            if (data !== 'awesome' && data !== 'error') {
                return "Username should be `awesome` or `error`";
            }
        };

        $scope.saveUser = function() {
            // $scope.user already updated!
            return $http.post('/saveUser', $scope.user).error(function(err) {
                if(err.field && err.msg) {
                    // err like {field: "name", msg: "Server-side error for this username!"}
                    $scope.editableForm.$setError(err.field, err.msg);
                } else {
                    // unknown error
                    $scope.editableForm.$setError('name', 'Unknown error!');
                }
            });
        };
    });

app.controller("editCompanyCtrl", function ($http, $scope, $state, $stateParams){
    $scope.editedCompany = {};
    editedCompany.id = $stateParams.company.id;
    editedCompany.compName = $stateParams.company.compName;
    editedCompany.password = $stateParams.company.password;
    editedCompany.email = $stateParams.company.email;

    $scope.submit = function(editedCompany){
        editedCompany.id = $scope.
        $http.put("http://localhost:8080/couponsweb/webresources/company", editedCompany)
            .success(function(response){
                console.log(response);
                toastr.info("Company " + company.compName + " has updated");
                $state.go("companyList");
            })
            .error(function(response){
                console.log(response);
                toastr.error("Error. Company could not updated");
                $state.go("companyList");
            });
    }
});

app.controller("companyEditctrl", function($http, $scope, $state, $stateParams){

})

    /*
        Admin
     */

app.controller("CompanyListCtrl", function($http, $scope, $state){
    $http.get("http://localhost:8080/couponsweb/webresources/company/getAllCompanies")
        .success(function(response){
            $scope.companies = response;
            console.log(response);
        })
        .error(function(response){
            console.log(response);
            toastr.error("Could not bring companies");
            $state.go("Admin");
        });

    $scope.removeComp = function(company){
        var companyId = company.id;
        var companyName = company.compName;
        $http.delete("http://localhost:8080/couponsweb/webresources/company/" + companyId)
            .success(function(response){
                console.log(response);
                toastr.info("Company " + companyName + " has been deleted successfully.");
                $state.go($state.current, {}, {reload: true});
            })
            .error(function(response){
                console.log(response);
                toastr.error("ERROR - Could not remove the company");
            });
    };

    $scope.editComp = function(company){
        $scope.company2 = company;

        $scope.opts = {
            backdrop: true,
            keyboard: true,
            dialogFade: true,
            backdropClick: false,
            templateUrl:  "company/editCompany.html",
            controller: 'EditCompanyCtrl',
            resolve: {
                company :function(){
                    return $scope.company;
                }
            }
        };

        $scope.openDialog = function(action){
            var d = $dialog.dialog($scope.opts);
            d.open();
        };
    }



});

app.controller("createCompanyCtrl", function($http, $scope, $state){
   $scope.submit = function(){
       var newCompany = {
           id: $scope.company.companyId,
           compName: $scope.company.companyName,
           password: $scope.company.password,
           email: $scope.company.email
       };

    $http.post("http://localhost:8080/couponsweb/webresources/company/", newCompany)
        .success(function(response){
            console.log(response);
            toastr.info("New Company saved successfully");
            $state.go("Admin");
        })
        .error(function(error){
            console.log(error);
            toastr.error("error");
        });

   };

    $scope.cancel = function(){
        $state.go("Admin");
    }

});

    /*
        Company activity
     */

app.controller("createCouponCtrl", function($http, $scope, $state){
        $scope.submit = function(){
            var newCoupon = {
                id: $scope.coupon.couponId,
                title: $scope.coupon.couponTitle,
                amount: $scope.coupon.couponAmount,
                startDate: Date.parse($scope.coupon.couponStartDate),
                endDate: Date.parse($scope.coupon.couponEndDate),
                price: $scope.coupon.couponPrice,
                message: $scope.coupon.couponMessage,
                type: $scope.coupon.couponType
            };

            $http.post("http://localhost:8080/couponsweb/webresources/companycoupon/", newCoupon)
                .success(function(response){
                    console.log(response);
                    toastr.info("New Coupon has been saved successfully");
                    $state.go("Company");
                })
                .error(function(error){
                    console.log(error);
                    toastr.error("error");
                });

        };

        $scope.cancel = function(){
            $state.go("Company");
        }

    });

app.controller("CouponListCtrl", function($http, $scope, $state, $stateParams){
        $http.get("http://localhost:8080/couponsweb/webresources/companycoupon/AllCoupons")
            .success(function(response){
                $scope.coupons = response;
                console.log(response);
            })
            .error(function(response){
                console.log(response);
                toastr.error("Could not bring company's coupons");
                $state.go("Company");
            });

        $scope.removeCoupon = function(coupon){
            var couponId = coupon.id;
            var couponTitle = coupon.title;
            $http.delete("http://localhost:8080/couponsweb/webresources/companycoupon/" + couponId)
                .success(function(response){
                    console.log(response);
                    toastr.info("Coupon " + couponTitle + " has been deleted successfully.");
                    $state.go($state.current, {}, {reload: true});
                })
                .error(function(response){
                    console.log(response);
                    toastr.error("ERROR - Could not remove the coupon");
                });
        };
    });

/*
*********  Customer user activity Ctrls (for Customer)*********
 */

    app.controller("PurchaseCouponCtrl", function($http, $scope, $state, $stateParams){
        $http.get("http://localhost:8080/couponsweb/webresources/customer/getAllCoupons")
            .success(function(response){
                $scope.coupons = response;
                console.log(response);
            })
            .error(function(response){
                console.log(response);
                toastr.error("Could not bring the coupons");
                $state.go("Customer");
            });

        $scope.purchaseCoupon = function(coupon){
            var couponId = coupon.id;
            var couponTitle = coupon.title;
            $http.post("http://localhost:8080/couponsweb/webresources/customercoupon/purchaseCoupon/" + couponId)
                .success(function(response){
                    console.log(response);
                    toastr.info("Coupon " + couponTitle + " has been purchased successfully.");
                    $state.go($state.current, {}, {reload: true});
                })
                .error(function(response){
                    console.log(response);
                    toastr.error("ERROR - Could not purchase coupon.");
                });
        };
    });

    app.controller("customerCouponListCtrl", function($http, $scope, $state, $stateParams){
        $http.get("http://localhost:8080/couponsweb/webresources/customercoupon/AllPurchasedCoupons")
            .success(function(response){
                $scope.coupons = response;
                console.log(response);
            })
            .error(function(response){
                console.log(response);
                toastr.error("Could not bring the coupons");
                $state.go("Customer");
            });

    });

    app.controller("allCompaniesCtrl", function($http ,$resource, $scope) {
    var companyResource = $resource('http://localhost:8080/couponsweb/webresources/company/11111');
    $scope.companies;

    companyResource.get(function (response) {
            $scope.companies = response;
            console.log($scope.companies);
        },
        function (response) {
            if (response.status == 404) {
                toastr.error("Error accessing resource: " +
                    response.config.method + " " + response.config.url);
            } else {
                toastr.info(response.statusText);
            }
        }).$promise;


});

/*
********** CUSTOMER Ctrls (for Admin)*******************
 */

    app.controller("createCustomerCtrl", function($http, $scope, $state){
        $scope.submit = function(){
            var newCustomer = {
                id: $scope.customer.customerId,
                name: $scope.customer.customerName,
                password: $scope.customer.password,
            };

            $http.post("http://localhost:8080/couponsweb/webresources/customer/", newCustomer)
                .success(function(response){
                    console.log(response);
                    toastr.info("New Customer has been saved successfully");
                    $state.go("Admin");
                })
                .error(function(error){
                    console.log(error);
                    toastr.error("error");
                });

        };

        $scope.cancel = function(){
            $state.go("Admin");
        }

    });

    app.controller("CustomerListCtrl", function($http, $scope, $state){
        $http.get("http://localhost:8080/couponsweb/webresources/customer/getAllCustomers")
            .success(function(response){
                $scope.customers = response;
                console.log(response);
            })
            .error(function(response){
                console.log(response);
                toastr.error("Could not bring customers");
                $state.go("Admin");
            });

        $scope.removeCust = function(customer){
            var customerId = customer.id;
            var customerName = customer.name;
            $http.delete("http://localhost:8080/couponsweb/webresources/customer/" + customerId)
                .success(function(response){
                    console.log(response);
                    toastr.info("Customer " + customerName + " has been deleted successfully.");
                    $state.go($state.current, {}, {reload: true});
                })
                .error(function(response){
                    console.log(response);
                    toastr.error("ERROR - Could not remove the customer");
                });
        };

        $scope.editCust = function(customer){
            $scope.customer = customer;

            $scope.opts = {
                backdrop: true,
                keyboard: true,
                dialogFade: true,
                backdropClick: false,
                templateUrl:  "company/editCompany.html",
                controller: 'EditCompanyCtrl',
                resolve: {
                    company :function(){
                        return $scope.customer;
                    }
                }
            };

            $scope.openDialog = function(action){
                var d = $dialog.dialog($scope.opts);
                d.open();
            };
        }



    });



})();


