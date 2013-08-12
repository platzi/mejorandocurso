module.exports = function(grunt) {
	grunt.initConfig({
		bb_generate: {
			options: {
				appname       : "Puls3",
				appsrc        : "public/js/backbone",
				routersrc     : "public/js/backbone/routers/",
				modelsrc      : "public/js/backbone/models/",
				viewsrc       : "public/js/backbone/views/",
				collectionsrc : "public/js/backbone/collections/",
				templatesrc   : "public/js/backbone/templates/"
			},
			router:{},
			view:{},
			collection:{},
			model:{},
			template:{}
		}
	});


	grunt.loadNpmTasks('grunt-bb-generate');
};