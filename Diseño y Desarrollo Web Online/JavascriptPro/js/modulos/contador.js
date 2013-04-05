define(['core/sandbox'], function(sandbox) {
	return {
		elementId : 'todo-counter',
		element : null,
		counter : -1,
		iniciar : function() {
			this.element = document.getElementById(this.elementId);
			this.update();

			this.sandbox = new sandbox(this);
			this.sandbox.on('nuevo-todo', this.update);			
		},
		update : function() {
			this.element.innerHTML = ++this.counter;
		},
	}
});