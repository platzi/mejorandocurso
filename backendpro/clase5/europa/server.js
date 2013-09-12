var express = require('express');
var server = express();

server.get('/', function (req, res) {
	res.send('hello world');
});

server.listen(3000);