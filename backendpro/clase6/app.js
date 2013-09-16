var appController = function (server, users) {
	console.log('Estoy cargando el controllador llamado appController');

	var isntLoggedIn = function (req, res, next){
		if(!req.session.user){
			res.redirect('/');
			return;
		}

		next();
	};

	server.get('/app', isntLoggedIn, function (req, res) {
		res.render('app', {
			user : req.session.user,
			users : users
		});
	});
};

module.exports = appController;