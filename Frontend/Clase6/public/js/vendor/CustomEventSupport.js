Module('CustomEventSupport')({
	eventListeners : null,
	bind : function(type, eventHandler){
		var found, eventListeners;
		
		if(!this.hasOwnProperty('eventListeners')){
			this.eventListeners = {};
		}

		if(!this.eventListeners[type]){
			this.eventListeners[type] = [];
		}

		found  = false;
		
		eventListeners = this.eventListeners[type];
		
		for (var i=0; i < eventListeners.length; i++) {
			if (eventListeners[i] == eventHandler) {
				found = true;
				break;
			}
		}

		if(!found){
			this.eventListeners[type].push(eventHandler);
		}

		return this;
	},
	unbind : function(type, eventHandler){
		var i, found, eventListeners;
		
		found  = false;

		if(!this.eventListeners){
			this.eventListeners = {};
		}
		
		if(typeof eventHandler == 'undefined'){
			this.eventListeners[type] = [];
		}
		
		for (i=0; i < eventListeners.length; i++) {
			if (eventListeners[i] == eventHandler) {
				found = true;
				break;
			}
		}

		if(found){
			this.eventListeners[type].splice(i, 1);
		}

		return this;
	},
	dispatch : function(type, data){
		var event, listeners, that, allowDefault;
		
		if(!this.eventListeners){
			this.eventListeners = {};
		}

		event         = new CustomEvent(type, data);
		event.target  = this;
		listeners     = this.eventListeners[type] || [];
		allowDefault  = true;
		
		for (var i=0; i < listeners.length; i++) {
			listeners[i].call(this, event);
			if(event.areImmediateHandlersPrevented){
				break;
			}
		}
		
		return event;
	},
	prototype : {
		eventListeners : null,
		bind : function(type, eventHandler){
			var found;
			
			if(!this.eventListeners){
				this.eventListeners = {};
			}

			if(!this.eventListeners[type]){
				this.eventListeners[type] = [];
			}

			found  = false;
			
			for (var i=0; i < this.eventListeners.length; i++) {
				if (this.eventListeners[i] == eventHandler) {
					found = true;
					break;
				}
			}

			if(!found){
				this.eventListeners[type].push(eventHandler);
			}

			return this;
		},
		unbind : function(type, eventHandler){
			var i, found, eventListeners;

			found  = false;

			if(!this.eventListeners){
				this.eventListeners = {};
			}

			if(typeof eventHandler == 'undefined'){
				this.eventListeners[type] = [];
			}

			for (i=0; i < eventListeners.length; i++) {
				if (eventListeners[i] == eventHandler) {
					found = true;
					break;
				}
			}

			if(found){
				this.eventListeners[type].splice(i, 1);
			}

			return this;
		},
		dispatch : function(type, data){
			var event, listeners, that, allowDefault;

			if(!this.eventListeners){
				this.eventListeners = {};
			}

			event         = new CustomEvent(type, data);
			event.target  = this;
			listeners     = this.eventListeners[type] || [];
			allowDefault  = true;

			for (var i=0; i < listeners.length; i++) {
				listeners[i].call(this, event);
				if(event.areImmediateHandlersPrevented){
					break;
				}
			}

			return event;
		}
	}
});

