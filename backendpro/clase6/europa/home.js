var _ = require('underscore');

var homeController = function (server, users) {
	console.log('Estoy cargando el controllador llamado homeController');

	var isLoggedIn = function (req, res, next){
		if(req.session.user){
			res.redirect('/app');
			return;
		}

		next();
	};

	server.get('/', isLoggedIn, function (req, res) {
		res.render('home');
	});

	server.post('/log-in', function (req, res) {
		req.session.user = req.body.username;
		server.io.broadcast("log-in", {username: req.body.username});

		users.push(req.session.user);

		res.redirect('/app');
	});

	server.get('/log-out', function (req, res) {
		users = _.without(users, req.session.user);
		server.io.broadcast('log-out', {username: req.session.user});

		req.session.destroy();
		res.redirect('/');
	});
};

module.exports = homeController;