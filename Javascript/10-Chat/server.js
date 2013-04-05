/* Importo las librerias express, swig, consolidate y los guardamos en una variable */
var express = require('express'),
	swig    = require('swig'),
	cons    = require('consolidate');
/* definimos nuestra app con express */
var app = express();
/* le decimos a swig que no tendra cache */
swig.init({
	cache : false
});

// inciamos View engine primero decimos que interprete todos los .html
app.engine('.html', cons.swig);
/* decimos que haga un view engine a todo archivo html */
app.set('view engine', 'html');
/* establecemos la carpeta ./views como la carpeta de las vistas */
app.set('views', './views');

// importamos los archivos estaticos
app.use( express.static('./public') );

// Esto nos hace posible hacer un post
app.use( express.bodyParser() );
app.use( express.cookieParser() );

/* definimos en mensajes y ress dos arreglos vacios */
var mensajes = [],
    ress = [];

/* hacemos un get al "/" con una funcion que tendra un request y un response */
app.get('/', function (req, res) {
	/* hacemos un response con un render de home y le enviamos el arreglo mensajes con la variable mensajes */
	res.render('home', {
		mensajes : mensajes
	});
});

/* hacemos un post a la url donde enviare los mensajes */
app.post('/mensajes/new', function(req, res){
	/* hacemos un posh con el mensaje, (usamos body y no params por que ahora nuestor mensaje vendra del body y no del request) */
	mensajes.push(req.body.mensaje);
	/* hacemos un forEach al arreglo ress y mandamos uno por uno los items al response */
	ress.forEach(function(res){
		res.send(mensajes);
	});
	/* hacemos un send a repsonse diciendo "tu mensaje es" y añadimos el mensaje que envio */
	res.send('Tu mensaje es '+ req.body.mensaje);
});

app.get('/mensajes/list', function(req, res){
	/* hacemos un push del arreglo ress con un response */
	ress.push(res);
	/* comentamos la linea de abajo pues esto era lo que se hacia hace 5 años y ESTO NO SE DEBE HACER AHORA */
	// res.send(mensajes+'<script>setTimeout(function(){window.location.reload()},1000);</script>');
});
/* nuestra app escuchara por el puerto 3000 */
app.listen(3000);
/* mandamos un mensaje a la consola que nos informa que la app esta funcionando */
console.log('Applicacion funcionando en el Puerto 3000');

