var express = require('express');
var server = express();

server.get('/', function (req, res) {
	res.send('Home');
});

server.listen(3000);