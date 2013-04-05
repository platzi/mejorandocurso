$(document).ready(function () {
	console.log('Hola');

	window.grid = new Grid();
	grid.render( $('#grid') );

	window.user = {
		x : 10,
		y : 10,
		color : "#000000",
		id : '1'
	}

	window.client = io.connect(window.location.href);

	client.on('connect', function(socket){
		user.id = client.socket.sessionid
		console.log('user id', user.id);

		client.emit('move', user);
		grid.pintar(user.x, user.y, user.color, user.id);
	});

	client.on('move', function(user){
		grid.clear(user.id);
		grid.pintar(user.x, user.y, user.color, user.id);
	});

	client.on('remove', function(user){
		grid.clear(user.id);
	});

	client.on('init', function(users){
		$.each(users, function(i, user){
			grid.pintar(user.x, user.y, user.color, user.id);
		});
	});

	key('up, down,left,right', function(event){
		grid.clear(user.id);

		if(event.keyIdentifier === "Right"){
			user.x++;
		}

		if(event.keyIdentifier === "Left"){
			user.x--;
		}		

		if(event.keyIdentifier === "Up"){
			user.y--;
		}

		if(event.keyIdentifier === "Down"){
			user.y++;
		}

		client.emit('move', user);
		grid.pintar(user.x, user.y, user.color, user.id);
	});
});
