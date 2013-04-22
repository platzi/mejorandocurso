define(
	['modulos/contador', 'modulos/ingreso', 'modulos/validar'],
	function(test, test2) {
		var modules = Array.prototype.slice.call(arguments);

		return {
			iniciar : function() {
				for(var name in modules) {
					modules[name].iniciar();
				}
			}
		}
	}
)