/**
 * 
 */
var noteApp = angular.module("noteApp", []);

noteApp.controller("noteController", function($scope, $http) {
	$scope.init = function() {
		console.log("I've been pressed!");
		$http.get("http://localhost:8000/getAllNotes").then(
				function successCallback(response) {
					$scope.data = response.data;
					console.log(response);
				}, function errorCallback(response) {
					console.log("Unable to perform get request");
				});
	};

	$scope.edit = function(index) {
		if ($scope.mode == undefined) {
			$scope.mode = [];
		}
		console.log($scope.mode);
		if ($scope.mode[index] == undefined) {
			$scope.mode.push("read");
		}
		if ($scope.mode[index] == "edit") {
			$scope.mode[index] = "read";
		} else {
			$scope.mode[index] = "edit";
		}
	};

	$scope.updateNote = function(index) {
		console.log("Updating the note text");
		if ($scope.mode[index] != "edit") {
			return;
		}
		if ($scope.data) {
			var noteId = $scope.data[index].id;
			var noteText = $scope.data[index].note;
			$http.put("http://localhost:8000/update/" + noteId, noteText).then(
					function successCallback(response) {
						console.log(response);
						$scope.data[index].id = response.data.id;
						$scope.data[index].note = response.data.note;
						$scope.edit(index);
					}, function errorCallback(response) {
						console.log("Unable to perform update request");
					});
		}
	};
	
	$scope.deleteNote = function(index) {
		console.log("Deleting the note text");
		if ($scope.data) {
			var noteId = $scope.data[index].id;
			$http.delete("http://localhost:8000/delete/" + noteId).then(
					function successCallback(response) {
						$scope.init();
					}, function errorCallback(response) {
						console.log("Unable to perform delete request");
					}	
			);
		}
	};
	
	$scope.insertNote = function() {
		console.log(this.note);
		$http.post("http://localhost:8000/post", this.note).then(
				function successCallback(response) {
					$scope.init();
					$scope.note = null;
				}, function errorCallback(response) {
					console.log("Unable to perform insert request");
				}
		);
	};

});