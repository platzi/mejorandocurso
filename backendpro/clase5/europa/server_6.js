var express = require('express'),
	swig = require('swig'),
	_ = require('underscore');

var RedisStore = require('connect-redis')(express);

var server = express();
var users = [];

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

var isntLoggedIn = function (req, res, next) {
	if(!req.session.user){
		res.redirect('/');
		return;
	}

	next();
};

var inLoggedIn = function (req, res, next) {
	if(req.session.user){
		res.redirect('/app');
		return;
	}

	next();
};

server.get('/', inLoggedIn, function (req, res) {
	res.render('home');
});

server.get('/app', isntLoggedIn, function (req, res) {
	res.render('app', {
		user : req.session.user,
		users : users
	});
});

server.post('/log-in', function (req, res) {
	users = _.without(users, req.session.user);
	
	req.session.destroy();
	res.redirect('/');
});

server.post('/log-in', function (req, res) {
	users.push(req.body.username);
	req.session.user = req.body.username;

	res.redirect('/app');
});

server.listen(3000);