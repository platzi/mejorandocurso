Class('PonyExpress').includes(CustomEventSupport)({
	capitalise : function(string){
		return string.charAt(0).toUpperCase() + string.slice(1);
	},
	prototype : {
		_io : null, //Socket Io Connection
		init : function (config) {
			console.log('connection to:', config.io);
			var ponyExpress = this;

			this._io = io.connect(config.io);

			this._io.on('connect', function(data){
				ponyExpress.connected = true;
				ponyExpress.dispatch('connect');
			});
		}
	}
});

Class(PonyExpress,'Plug').includes(CustomEventSupport)({
	prototype : {
		namespace : "",// Model Name
		init : function (config) {
			if(!window.ponyExpress){
				throw "Create a PonyExpress before a PonyExpress Model";
			}

			if(!config.name){
				throw "All ponyExpress models require a name";
			}

			config.events = config.events || [];

			for (var property in config) {
				if (config.hasOwnProperty(property)) {
					this[property] = config[property];
				}
			}

			this._bindCrud();
		},
		_bindCrud : function () {
			var ponyExpressPlug = this;

			window.ponyExpress._io.on(this.name + '::create', function(data){ ponyExpressPlug.onCreate(data);});
			window.ponyExpress._io.on(this.name + '::delete', function(data){ ponyExpressPlug.onDelete(data);});
			window.ponyExpress._io.on(this.name + '::update', function(data){ ponyExpressPlug.onUpdate(data);});

			this.events.forEach(function(event){
				window.ponyExpress._io.on(ponyExpressPlug.name + '::' + event, function(data){
					ponyExpressPlug['on'+PonyExpress.capitalise(event)](data);

					ponyExpressPlug.dispatch(event, data);
				});
			});
		},
		onCreate : function(data){
			if(!data.id){
				throw "PonyExpress Items need id";
			}

			this.dispatch('create', data);
		},
		onDelete : function(data){
			if(!data.id){
				throw "PonyExpress Items need id";
			}

			this.dispatch('delete', data);
		},
		onUpdate : function(data){
			if(!data.id){
				throw "PonyExpress Items need id";
			}

			this.dispatch('update', data);
		}
	}
});

Class(PonyExpress,'BackbonePlug').inherits(PonyExpress.Plug)({
	prototype : {
		namespace : "",// Model Name
		events : [],
		init : function (config) {
			config.name = config.name || config.collection.name;

			if(config.channel){
				ponyExpress._io.emit('channel', config.channel);
			}

			PonyExpress.Plug.prototype.init.call(this, config);
		},
		onCreate : function(data){
			if(!data.id){
				throw "PonyExpress Items need id";
			}

			this.collection.add(data);

			this.dispatch('create', data);
		},
		onDelete : function(data){
			if(!data.id){
				throw "PonyExpress Items need id";
			}

			var item = this.collection.find(function(item){
				return item.get('id') === data.id;
			});

			// Item could have been removed before
			if(!item){
				return;
			}

			// Destroy model with out notifiing the server
			// http://stackoverflow.com/questions/10218578/backbone-js-how-to-disable-sync-for-delete
			item.trigger('destroy', item, item.collection);

			this.dispatch('update', data);

		},
		onUpdate : function(data){
			if(!data.id){
				throw "PonyExpress Items need id";
			}

			var item = this.collection.find(function(item){
				return item.get('id') === data.id;
			});

			// Item could have been removed before
			if(!item){
				return;
			}

			item.set(data);

			this.dispatch('update', data);
		}
	}
});