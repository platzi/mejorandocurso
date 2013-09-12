var express = require('express');
var server = express();

var messages = [];
var responses = [];

server.get('/', function (req, res) {
	res.send('hello world');
});

server.get('/messages', function (req, res) {
	responses.push(res);

	// res.send(messages+ '<script>setTimeout(function(){window.location.reload()}, 1000)</script>');
});

server.get('/messages/:message', function (req, res) {
	messages.push(req.params.message);

	responses.forEach(function(res){
		res.send(messages+ '<script>window.location.reload()</script>');
	})

	res.send('tu mensaje es '+ req.params.message);
});

server.listen(3000);