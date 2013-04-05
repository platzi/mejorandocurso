var express = require('express');

var app = express();

var mensajes = [],
	peticionesPendientes =[];

app.get('/', function(req, res){
  	res.send('Hello world');
});

app.get('/mensajes/new/:mensaje', function (req, res) {
	mensajes.push(req.params.mensaje);

	peticionesPendientes.forEach(function (res) {
		res.send(mensajes+'<script>window.location.reload()</script>');
	});

	res.send('Gracias por tu mensaje: '+ req.params.mensaje);
});

app.get('/mensajes/list', function (req, res) {
	peticionesPendientes.push(res);
	
});

app.listen(3000);






console.log("Express server running at\n  => http://localhost:3000/\nCTRL + C to shutdown");
