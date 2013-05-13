Puls3.Views.ArticleExtendedView = Backbone.View.extend({
	events:{
		"click .likes_up" : "upvote",
		"click .likes_down" : "downvote"
	},
	className:"",
	initialize : function(model){
		var self = this;
		this.model = model;
		this.template = swig.compile( $("#ArticleExtended_tpl").html() );

		this.model.on('change', function(){
			self.render();
		});
	},
	upvote : function(e){
		e.preventDefault();

		this.model.set("votes", parseInt( this.model.get("votes"), 10 ) + 1 );
		this.model.save();
	},
	downvote : function(e){
		e.preventDefault();

		this.model.set("votes", parseInt( this.model.get("votes"), 10 ) - 1 );
		this.model.save();
	},
	render: function(data) {
		var self = this;
		var locals = self.model.toJSON();

		this.$el.html(this.template({post:locals}));

		return this;
	}
});