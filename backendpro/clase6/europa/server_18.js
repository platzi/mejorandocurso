var express = require('express.io'),
	swig = require('swig'),
	_ = require('underscore');

var server = express();
server.http().io();

var RedisStore = require('connect-redis')(express);

var users = [];

swig.setDefaults({
	cache : false
});

// View engine
server.engine('html', swig.renderFile );
server.set('view engine', 'html');
server.set('views', './app/views');

// Add post, cookie and session support
server.configure(function(){
	server.use( express.static('./public') );

	server.use( express.logger() );
	server.use( express.cookieParser() );
	server.use( express.bodyParser() );

	server.use( express.session({
		secret : "lolcatz",
		store  : new RedisStore({})
		// store  : new RedisStore({
		//	host : conf.redis.host,
		//	port : conf.redis.port,
		//	user : conf.redis.user,
		//	pass : conf.redis.pass
		// });	
	}) );
});

// Controllers
var homeController = require('./app/controllers/home');
var appController = require('./app/controllers/app');

homeController(server, users);
appController(server, users);

server.io.route('hello?', function(req){
	req.io.emit('ready', {
		message : 'server ready to rock!!!'
	});
});

server.listen(3000);
console.log('Servidor corriendo en http://127.0.0.1:3000');