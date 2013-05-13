Class('CustomEvent')({
	bubbles   : true,
	cancelable : true,
	currentTarget : null,
	timesStamp : 0,
	target    : null,
	type      : '',
	isPropagationStopped : false,
	isDefaultPrevented : false,
	isImmediatePropagationStopped : false,
	areImmediateHandlersPrevented : false,
	prototype : {
		init : function(type, data){
			var property;
			this.type = type;
			for (property in data) {
				if (data.hasOwnProperty(property)) {
					this[property] = data[property];
				}
			}
		},
		stopPropagation : function(){
			this.isPropagationStopped = true;
		},
		preventDefault : function(){
			this.isDefaultPrevented = true;
		},
		stopImmediatePropagation : function(){
			this.stopImmediateHandlers();
			this.stopPropagation();
		},
		preventImmediateHandlers : function(){
			this.areImmediateHandlersPrevented = true;
		}
	}
});
