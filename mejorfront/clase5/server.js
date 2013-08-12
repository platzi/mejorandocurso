var express = require('express'),
	swig    = require('swig'),
	cons    = require('consolidate'),
	fs      = require('fs'),
	uuid    = require('node-uuid');

var app      = express(),
	baseData = fs.readFileSync('./base-data.json').toString(),
	server   = require('http').createServer(app),
	io       = require('socket.io').listen(server);

var data = JSON.parse(baseData);

swig.init({
	cache : false
});

// View engine
app.engine('.html', cons.swig);
app.set('view engine', 'html');
app.set('views', './app/views');

// Add POST, PUT, DELETE methods to the app
app.use(express.bodyParser());
app.use(express.cookieParser());
app.use(express.methodOverride());

// Static files
app.use( express.static('./public') );

// Routes
app.get('/articles/all', function(req, res){
	res.send(data);
});

app.post('/articles', function (req, res){
	req.body.id = uuid.v1();
	req.body.votes = 0;
	req.body.image = "/img/img3.jpg";
	req.body.user  = "Siedrix";

	data.push(req.body);

	io.sockets.emit('articles::create', req.body);

	res.send(200, {status:"Ok", id: req.body.id});
});

app.put('/articles/:id', function (req, res){
	var article;

	for (var i = data.length - 1; i >= 0; i--) {
		article = data[i];

		if(article.id === req.params.id){
			data[i] = req.body;
		}
	}

	io.sockets.emit('articles::update', req.body);

	res.send(200, {status:"Ok"});


	res.send('put');
});

var home = function (req, res) {
	res.render('index',{
		posts : data
	});
};

app.get('/', home);
app.get('/article/:id', home);


server.listen(3000);