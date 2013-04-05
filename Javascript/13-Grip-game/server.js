var http     = require('http'), 
	express  = require('express'),
	pony     = require('socket.io'),
	cons     = require('consolidate'),
	swig     = require('swig'),
	sanitize = require('validator').sanitize;

var app      = express();
var server   = http.createServer(app);
var io       = pony.listen(server);

var users = {};

app.use(express.static('./public'));

swig.init({
	cache : false
});

app.engine('.html', cons.swig);
app.set('view engine','html');
app.set('views', './views');

app.get('/', function (req, res) {
	res.render('home', {
		titulo : 'grid game'
	});
});

io.sockets.on('connection', function(socket){
	console.log('socket', socket.store.id);

	socket.emit('init',users)

	socket.on('move', function(user){
		users[user.id] = user;
		socket.broadcast.emit('move',user);
	});

	socket.on('disconect', function(){
		delete users[socket.store.id];
		socket.broadcast.emit('remove', {id: socket.store.id})
	});
});

server.listen(3000);
