<?php namespace HireMe\Components;

use Illuminate\Support\Facades\Facade;

/**
 * @see \Illuminate\Html\FormBuilder
 */
class Field extends Facade {

	/**
	 * Get the registered name of the component.
	 *
	 * @return string
	 */
	protected static function getFacadeAccessor() { return 'field'; }

}
