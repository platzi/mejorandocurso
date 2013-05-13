Puls3.Views.ArticleView = Backbone.View.extend({
	events:{
		"click > article" : "navigate",
		"click .likes_up" : "upvote",
		"click .likes_down" : "downvote"
	},
	className:"",
	initialize : function(model){
		var self = this;

		this.model = model;

		this.model.on('change', function(){
			self.render();
		});

		window.routers.on('route:root', function(){
			self.render();
		});

		window.routers.on('route:articleSingle', function(){
			self.render();
		});

		this.template = swig.compile($("#Article_tpl").html());
		this.templateExtended = swig.compile($("#ArticleExtended_tpl").html());
	},
	navigate : function(){
		console.log('navigate',this.model.toJSON() );

		Backbone.history.navigate('article/' + this.model.get('id'), {trigger: true});
	},
	upvote : function(e){
		e.stopPropagation();
		var votes = this.model.get("votes");

		this.model.set("votes", parseInt(votes, 10) + 1 );
		this.model.save();
	},
	downvote : function(e){
		e.stopPropagation();
		var votes = this.model.get("votes");

		this.model.set("votes", parseInt(votes, 10) - 1 );
		this.model.save();
	},
	render: function(data) {
		var self = this;
		var locals ={
			post : this.model.toJSON()
		};

		if(window.app.state === "articleSingle"){
			if(window.app.article === this.model.get('id') ){
				this.$el.show();
				this.$el.html( this.templateExtended(locals) );
			}else{
				this.$el.hide();
				this.$el.html('');
			}
		}else{
			this.$el.html( this.template(locals) );
		}

		return this;
	}
});
