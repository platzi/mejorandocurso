define(['core/sandbox'], function(sandbox){
	var input, boton, id = 1;
	return {
		inputId : 'todo-input',
		botonId : 'todo-boton',
		listaId : 'todo-lista',
		iniciar : function() {
			that = this;
			this.input = document.getElementById(this.inputId);
			this.boton = document.getElementById(this.botonId);
			this.lista = document.getElementById(this.listaId);
			this.boton.onclick = function() {
				console.log('click');
				that.buscarValidacion(that.input.value);
			}

			this.sandbox = new sandbox(this);
			this.sandbox.on('input-validado', this.limpiarInput);
			this.sandbox.on('input-validado', this.agregarTodo);
		},
		limpiarInput : function() {
			this.input.value = "";
		},
		agregarTodo : function(value) {
			var li = document.createElement('li');
			li.innerHTML = value;
			this.lista.insertBefore(li, this.lista.firstChild);
			this.sandbox.emit('nuevo-todo');

		},
		buscarValidacion : function() {
			this.sandbox.emit('buscar-validacion', {
				value : this.input.value
			})
		}
	}
});