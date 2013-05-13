Puls3.Models.ArticleModel = Backbone.Model.extend({
	urlRoot:"/articles",
	defaults:{

	},
	prettyDate : function(date){
		if (!date || date === "0000-00-00 00:00:00") return "";
		var date = Date.parse(date);
		return date.toString("MMMM dd, yyyy")
	},
	parse : function(resp) {
		// the collection does not output same json format to models;
		if(resp.data){
			return resp.data;
		}else{
			return resp;
		}
	}
});
Puls3.Models.Article = Puls3.Models.ArticleModel;