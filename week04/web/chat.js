$(function() {

	var writeToChatboard = function(text) {
		$("#chatarea").val(text + "\n" + $("#chatarea").val());
	}

	var url = "ws://localhost:8080/week04/chat";
	var socket = new WebSocket(url);

	$("#sendBtn").on("click", function() {
		socket.send($("#message").val());
		$("#message").val("");
	})

	socket.onmessage = function(evt) {
		// {message: "the message" , timestamp: "time" }
		var msg = JSON.parse(evt.data);
		writeToChatboard(msg.timestamp + ": " + msg.message);
	}
	socket.onopen = function() {
		writeToChatboard("Connected to chat server");
	}
	socket.onclose = function() {
		writeToChatboard("Disconnected from chat server");
	}

});

