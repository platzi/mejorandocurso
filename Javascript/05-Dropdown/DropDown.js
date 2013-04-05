var DropDown = function(config){
	this.targetElement = config.targetElement;
	this.options = config.options;
	this.placeHolder = config.placeHolder;
	this.showing = false;
	this.value = null;

	this.targetElement.html('<label>'+ this.placeHolder +'</label>');

	this._renderDropdown();
	this._bindEvents();
}

DropDown._HTML = '\
<div class="dropdown-container">\
</div>\
';

DropDown.prototype._bindEvents = function() {
	var dropdown = this;


	this.targetElement.click(function(){
		debugger;
		if(!dropdown.showing){
			dropdown.show();

			$(document).click(function(event){
				debugger;
				if(!$(event.target).closest( '#' + dropdown.targetElement.attr('id') ).length){
					dropdown.hide();
				}
			});	
		}
	});

	this.dropdownContainer.find('.dropdown-item').click(function(event){
		var $target = $(event.target);
		dropdown.setValue( $target.attr('value'), $target.text() );
	});
}

DropDown.prototype._renderDropdown = function() {
	var dropdownContainer = $(this.constructor._HTML);

	this.options.forEach(function(item){
		$('<div class="dropdown-item" value="'+item.value+'">'+ item.label +'<div>').
		  appendTo(dropdownContainer);
	});

	dropdownContainer.hide();

	dropdownContainer.appendTo(this.targetElement);

	this.dropdownContainer = dropdownContainer;
};
DropDown.prototype._position = function(){
	var targetElementPosition = this.targetElement.offset(); 

	this.dropdownContainer.css({
		top : targetElementPosition.top + this.targetElement.height() + 5,
		left : targetElementPosition.left
	});	
}

DropDown.prototype.setValue = function(value, label) {
	this.value = value;

	this.targetElement.find('label').text(label);
}

DropDown.prototype.show = function() {
	debugger;
	this.showing = true;
	this.dropdownContainer.show();

	this._position();
};
DropDown.prototype.hide = function() {
	this.showing = false;
	this.dropdownContainer.hide();
};
