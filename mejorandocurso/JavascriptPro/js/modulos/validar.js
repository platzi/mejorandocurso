define(['core/sandbox'], function(sandbox){
	return {
		iniciar: function() {
			this.sandbox = new sandbox(this);
			this.sandbox.on('buscar-validacion', this.validar);
		},
		validar: function(info) {
			if(info.value != "") {
				this.sandbox.emit('input-validado', info.value);
			}
		}
	}
});