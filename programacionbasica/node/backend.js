
var basededatos = {
	usuario: "freddier",
	password: "mejorandola"
};

console.log("Arrancando server de Node");

var express = require("express");
var parcero = require("body-parser");
var web = express();
web.use( parcero.urlencoded({extended: true}) );
var servidor;

servidor = web.listen(8080, function () {
	console.log("Servidor arrancado :D");
});

//Home
web.get("/", function (req, res) {
	res.sendfile("formulario.html");
});

web.post("/entrar", function (req, res) {
	if(req.body.usuario == basededatos.usuario && req.body.clave == basededatos.password)
	{
		res.send("Bienvenido al area secreta~");
	}
	else
	{
		res.send("Shuu, shuu, fuera de aquí!!!1!");
	}
});


/* Prueba de URLs amigables */
//Prueba de variables GET y variables globales
web.get("/test", function (req, res) {
	res.send("Tu avión es el " + req.query.avion + " y tu edad es " + req.query.edad);
});

//Prueba de HTML
web.get("/hola/mama-es-linda", function (req, res) {
	res.send("Hola <strong>mamá</strong>, estoy triunfando");
});