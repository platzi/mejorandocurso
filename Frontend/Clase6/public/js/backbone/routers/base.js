Puls3.Routers.BaseRouter = Backbone.Router.extend({
	routes: {
		"" :  "root",
		"article/:id" : "articleSingle"
	},
	initialize : function(){
		var self = this;

	},
	root: function(){
		var self = this;

		console.log('root');

		window.app.state = "root";
	},
	articleSingle : function(id){
		console.log('articleSingle', id);

		window.app.state = "articleSingle";
		window.app.article = id;
	}
});
