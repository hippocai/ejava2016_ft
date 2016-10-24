(function() {

	var ChatApp = angular.module("ChatApp", []);

	var ChatCtrl = function($scope) {
		var socket = null;
		var chatCtrl = this;

		chatCtrl.chats = ""
		chatCtrl.message = "";

		var displayMessage = function(msg) {
			chatCtrl.chats = msg + "\n" + chatCtrl.chats;
		}

		chatCtrl.connect = function() {
			socket = new WebSocket("ws://");
			socket.onmessage = function(event) {
				$scope.$apply(function() {
					displayMessage(event.data);
				});
			}
			socket.onopen = function() {
				$scope.$apply(function() {
					displayMessage("*** Connected");
				});
			}
			socket.onclose = function() {
				$scope.$apply(function() {
					displayMessage("Disconnected ***");
				});
			}
		}
		chatCtrl.disconnect = function() {
			if (!!socket)
				socket.close();
		}

		chatCtrl.send = function() {
			socket.send(chatCtrl.message);
			chatCtrl.message = "";
		}
	}

	ChatApp.controller("ChatCtrl", ["$scope", ChatCtrl])

})();

