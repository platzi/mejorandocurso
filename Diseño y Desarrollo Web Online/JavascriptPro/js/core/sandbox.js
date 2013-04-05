define(['core/eventos'], function(Eventos) {
	
	var sandboxPrototype = {
		eventos : Eventos,
		init : function(module) {
			this.module = module;
			return this;
		},
		on: function (tipoEvento, callback) {
			this.eventos.on(tipoEvento, callback, this.module);
		},
		emit : function (tipoEvento) {
			args = Array.prototype.slice.call(arguments, 1);
			this.eventos.emit(tipoEvento, args);
		}
	}

	function F(module) {
		this.module = module;
	};
	F.prototype = sandboxPrototype;
	
	return F;
})