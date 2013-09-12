var express = require('express'),
	swig = require('swig');

var RedisStore = require('connect-redis')(express);

var server = express();

// Configuracion para renderear vistas
server.engine('html', swig.renderFile);
server.set('view engine', 'html');
server.set('views', './app/views');

// Agregamos post, cookie y sessiones
server.configure(function() {
	server.use(express.logger());
	server.use(express.cookieParser());
	server.use(express.bodyParser());

	server.use(express.session({
		secret : "lolcatz",
		store  : new RedisStore({})
		// store  : new RedisStore({
		//	host : conf.redis.host,
		//	port : conf.redis.port,
		//	user : conf.redis.user,
		//	pass : conf.redis.pass
		// });	
	}));
});

server.get('/', function (req, res) {
	res.render('home');
});

server.post('/log-in', function (req, res) {
	res.render('Quien eres?');
});

server.listen(3000);