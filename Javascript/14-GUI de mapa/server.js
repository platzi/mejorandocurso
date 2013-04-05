var express  = require('express'),
	http     = require('http'),
	pony     = require('socket.io'),
	cons     = require('consolidate'),
	swig     = require('swig'),
	mongoose = require('mongoose');

mongoose.connect('localhost', 'maps');

var Schema = mongoose.Schema;
var Marks  = mongoose.model('Mark', Schema({ 
	title : 'string',
	ib    : 'string',
	jb    : 'string'
}));

var app    = express();
var server = http.createServer(app);
var io     = pony.listen(server);

server.listen(3000);

swig.init({
	cache : false
});

app.use( express.static('./public') );

app.engine('.html', cons.swig);
app.set('view engine','html');
app.set('views', './views');

app.use(express.bodyParser());
app.use(express.cookieParser());

app.get('/',function (req, res) {
	res.render('home');
});

app.post('/mark/new', function(req, res){
	var mark = new Marks({
		title : req.body.title,
		ib    : req.body.ib,
		jb    :req.body.jb
	});

	mark.save(function(err){
		if(err){
			res.send(500);
			console.log('err :', err);
		}else{
			console.log('markSaved', mark);
			debugger;
			io.sockets.emit('new', {
				marks : [{
					title : mark.title,
					ib    : mark.ib,
					jb    : mark.jb
				}]
			});			
			res.send(200);
		}
	});
});

io.sockets.on('connection', function(socket){
	Marks.find({},function(err, marks){
		socket.emit('init', {
			marks : marks
		});
	})
});