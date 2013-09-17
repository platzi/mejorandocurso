var express = require('express'),
	swig = require('swig');

var server = express();

swig.setDefaults({
	cache : false
});

// View engine
server.engine('html', swig.renderFile );
server.set('view engine', 'html');
server.set('views', './app/views');

server.get('/', function (req, res) {
	res.render('home');
});

server.listen(3000);
console.log('Servidor corriendo en http://127.0.0.1:3000');