var http     = require('http'), 
	express  = require('express'),
	faye     = require('faye'),
	cons     = require('consolidate'),
	jade     = require('jade'),
	sanitize = require('validator').sanitize;

var messages = [];

var app = express();

var server = http.createServer(app);

app.use(express.static('./public'));

app.engine('.html', cons.jade);
app.set('view engine','html');
app.set('views', './views');

app.get('/', function (req, res) {
	res.render('home');
});

app.get('/:chatRoom', function (req, res) {
	res.render('chatRoom',{
		messages : messages['/'+ req.params.chatRoom] || []
	});
});

// faye server
var fayeServer = new faye.NodeAdapter({mount: '/faye'});
var extension = {
	incoming : function(message, callback){
		if(message.channel.indexOf('meta') === -1){
			console.log(message.data.text);

			if(!messages[message.channel]){
				messages[message.channel] = [];
			}

			message.data.text = sanitize(message.data.text).xss();

			messages[message.channel].push(message.data);
		} 

		callback(message)
	}
}

fayeServer.addExtension(extension);
fayeServer.attach(server);
// Acaba faye server

server.listen(2000);


console.log('Server listening to port 2000')
