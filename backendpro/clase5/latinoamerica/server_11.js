var express = require('express');
var server = express();

var messages = [];
var responses = [];

server.get('/', function (req, res) {
	res.send('Hello world');
});

server.get('/messages/:message', function(req, res){
	messages.push(req.params.message);

	responses.forEach(function(res){
		res.send(messages + '<script>window.location.reload()</script>');
	});

	res.send('Gracias por tu mensaje: ' + req.params.message);
});

server.get('/messages/', function(req, res){
	// res.send(messages + '<script>setTimeout(function(){window.location.reload()},1000)</script>');

	responses.push(res);
});

server.listen(3000);
console.log('Servidor corriendo en http://127.0.0.1:3000');