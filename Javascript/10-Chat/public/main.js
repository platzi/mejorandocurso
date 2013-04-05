/* Definimos la funcion onReady que sera la que usaremos cuando del DOM este listo */
var onReady = function () {
	/* Decimos que cuando halla un evento click sobre el elemento con el id #enviar-mensaje ejecute la funcion clickHandler */
	$('#enviar-mensaje').on('click', clickHandler);
	/* Decimos que ejecute la funcion messageRequest luego de completar la funcion clickHandler */
	messagesRequest();
}
/* Definimos la funcion clickHandler */
var clickHandler = function (){
	/* Enviamos un mensaje a la consola con el valor ingresado a el elemento con id #mensaje */
	console.log( $('#mensaje').val() );
	/* guardamos en xhr un post a la url donde enviabamos los mensajes y enviar el valor del elemento con id #mensaje guardandolo en la variable mensaje  */
	var xhr = $.post('/mensajes/new', {
		mensaje : $('#mensaje').val()
	});
	/* Si el post es enviado con exito vamos a mandar a la consola "mensajes mandandola con extio" y el data del envio */
	xhr.done(function(data){
		console.log('mensajes mandandola con exito', data);
	});
	/* Si el post falla mostramos nuestro error en consola */
	xhr.fail(function(data){
		console.log(data)
		throw 'Errow';
	});
	/* limpiamos el elemento con id #mensaje */
	$('#mensaje').val('');
}
/* definimsos la funcion messageRequest */
var messagesRequest = function(){
	/* Guardamos en xhr un get para obtener los mensajes que tenemos actualmente y que antes veiamos en /mensajes/list */
	var xhr = $.get('/mensajes/list');
	/* Si es exitoso lo vamos a mostrar cono un elemento de la lista #chat */
	xhr.done(function(data){
		$('#chat').html('');
		data.forEach(function(mensaje){
			$('<li>'+ mensaje +'</li>').appendTo( $('#chat') );
		});
		/* Volvemos a llamar a messageRequest para que establesca una nueva conexion con el server despues de haber obtenido los ultimos mensajes */
		messagesRequest();
	});
}
/* Decimos que cuando el DOM este listo ejecutamos la funcion onReady */
$(document).on('ready', onReady);