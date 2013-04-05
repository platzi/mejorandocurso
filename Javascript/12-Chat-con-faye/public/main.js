$(document).ready(function () {
	$('#create').click(function(){
		if( $('#chat-room').val() ){
			window.location = '/' + $('#chat-room').val();
		}
	});

	if(Faye){
		window.client = new Faye.Client('/faye',{retry:5});

		$('#enviar').click(function(){
			if( $('#mensaje').val() ){
				client.publish(window.location.pathname,{
					text : $('#mensaje').val()
				});
			}

			$('#mensaje').val('');
		});

		client.subscribe(window.location.pathname, function(mensaje){
			$('#mensajes').prepend('<li>'+mensaje.text+'</li>');
		});


	}
});