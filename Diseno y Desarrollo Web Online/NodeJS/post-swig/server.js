var express = require('express'),
	server  = express()
	cons    = require('consolidate');

server.engine('.html', cons.swig);
server.set('view engine', 'html');

server.use(express.static('./public'));
server.use(express.bodyParser());

server.get('/', function (req, res) {
	res.render('index');
});

server.post('/post', function (req, res) {
	res.send(req.body);
});

server.listen(3000);