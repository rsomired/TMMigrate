var loanapp = angular.module("loanapp", []);

loanapp.controller('loanCtrl',function($scope, $rootScope, $http, $window) {

			$scope.Success_Msg = false;
			
			$scope.submitApplcn = function() {
				$http({
						url : 'http://localhost:8081/checkloaneligibility',
						//method : 'POST',
						params : {
								name : $scope.name,
								age : $scope.age,
								email : $scope.email,
								phone : $scope.phone,
								occupation : $scope.occupation,
								salary : $scope.salary,
								loanType : $scope.loanType,
								loanAmount : $scope.loanAmount,
								tenure: $scope.tenure,
								workExperience : $scope.workExperience
							}
					}).success(function (data) {
						alert(message);
						console.log(data.message);
					}).error(function (data, status, headers, config) {
						alert(data.message);
							$scope.Success_Msg = true;	
				});				
			}
			
		});

