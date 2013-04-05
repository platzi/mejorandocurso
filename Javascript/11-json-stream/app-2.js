/* Importamos las librerias necesarias */
var request = require('request'),
  	JSONStream = require('JSONStream'),
  	es = require('event-stream');
/* guardamos en parser un JSONStream */
var parser = JSONStream.parse(['rows', true, 'doc']),
	/* hacemos un request a la url */
  	req = request({url: 'http://isaacs.couchone.com/registry/_all_docs?include_docs=true'}),
  	/* Guardamos en logger un es.mapSync con una funcion que recibe el data */
  	logger = es.mapSync(function (data) {
  		/* si la descripcion de data y la busqueda de stream son mayores o iguales a cero */
  		if( data.description && data.description.search('stream') >= 0){
  			/* mostramos en consola la descripcion */
   			console.log(data.description);
  		}
  		/* retornamos data */
      	return data
    });
/* hacemos el req a pipe con parametro parser y otro con logger */
req.pipe(parser).pipe(logger);



