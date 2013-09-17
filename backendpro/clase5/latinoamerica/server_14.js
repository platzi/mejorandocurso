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

// Add post, cookie and session support
server.configure(function(){
	server.use( express.logger() );
	server.use( express.cookieParser() );
	server.use( express.bodyParser() );
});

server.get('/', function (req, res) {
	res.render('home');
});

server.post('/log-in', function (req, res) {
	debugger;

	res.send('bienvenido '+ req.body.username);
});

server.listen(3000);
console.log('Servidor corriendo en http://127.0.0.1:3000');