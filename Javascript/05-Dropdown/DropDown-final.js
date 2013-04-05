var DropDown = function(config){
	this.HTML = config.HTML || this.constructor._HTML;

	this.targetElement = config.targetElement;
	this.targetElement.html('<label/>');
	this.options = config.options;

	this._renderDropdown();
	this._bindEvents();

	this.showing = false;
	this.value = null;

	if(this.options.length){
		this.setValue( this.options[0].value, this.options[0].label );
	}
}

DropDown._HTML = '\
<div class="dropdown-container">\
</div>\
';

DropDown.prototype._bindEvents = function() {
	var dropdown = this;

	this.targetElement.on('click', function(e){
		if(!dropdown.showing){
			dropdown.show();


			dropdown._documentClickHandler = function(e){
				if( !$(e.target).closest( dropdown.targetElement ).length ){
					dropdown.hide();
					$(document).off('click', dropdown._documentClickHandler);
					dropdown._documentClickHandler = null;
				}
			}

			$(document).on('click', dropdown._documentClickHandler);
		}

		e.stopPropagation();
	});

	this.dropdownContainer.on('click', '.dropdown-item', function(e){
		var dropdownItem = $(e.target);

		dropdown.setValue( dropdownItem.attr('value'), dropdownItem.text() );
	});
}


DropDown.prototype._renderDropdown = function() {
	console.log(this.options);

	var dropdownContainer = $(this.HTML);

	this.options.forEach(function(item){
		$('<div class="dropdown-item" value="'+ item.value +'">'+ item.label +'</div>').appendTo(dropdownContainer);
	});

	dropdownContainer.hide();
	dropdownContainer.appendTo(this.targetElement);

	this.dropdownContainer = dropdownContainer;
};

DropDown.prototype.setValue = function(value, label) {
	this.targetElement.find('label').html(label);
	this.value = value;
}

DropDown.prototype.show = function(first_argument) {
	this.showing = true;
	this.dropdownContainer.show();
	this._position();
};

DropDown.prototype.hide = function(first_argument) {
	this.showing = false;
	this.dropdownContainer.hide();	
};

DropDown.prototype._position = function(){
	var targetElementPosition = this.targetElement.offset(); 

	this.dropdownContainer.css({
		top : targetElementPosition.top + this.targetElement.height() + 5,
		left : targetElementPosition.left
	});
}