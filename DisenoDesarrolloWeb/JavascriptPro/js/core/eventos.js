define(function() {
	var modulos = {};

	var on = function( tipo, callback, modulo ) {
		if( !modulos[tipo] ) {
			modulos[tipo] = [];
		}

		modulos[tipo].push({
			callback: callback,
			contexto: modulo
		});
	}

	var emit = function( tipo, args ) {
		if(!modulos[tipo]) {
			return false;
		}
		for (var i in modulos[tipo]) {
			info = modulos[tipo][i];
			info.callback.apply(info.contexto, args);
		}
	}

	return {
		on : on,
		emit : emit,
		modulos : modulos
	}
});