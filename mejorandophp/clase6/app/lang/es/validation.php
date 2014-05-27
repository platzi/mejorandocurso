<?php 

return array(

	/*
	|--------------------------------------------------------------------------
	| Validation Language Lines
	|--------------------------------------------------------------------------
	|
	| The following language lines contain the default error messages used
	| by the validator class. Some of the rules contain multiple versions,
	| such as the size (max, min, between) rules. These versions are used
	| for different input types such as strings and files.
	|
	| These language lines may be easily changed to provide custom error
	| messages in your application. Error messages for custom validation
	| rules may also be added to this file.
	|
	*/

	"accepted"       => "El campo :attribute debe ser aceptado.",
	"active_url"     => "El campo :attribute no es una URL válida.",
	"after"          => "El campo :attribute debe ser una fecha después de :date.",
	"alpha"          => "El campo :attribute sólo puede contener letras.",
	"alpha_dash"     => "El campo :attribute sólo puede contener letras, números y guiones.",
	"alpha_num"      => "El campo :attribute sólo puede contener letras y números.",
	"before"         => "El campo :attribute debe ser una fecha antes :date.",
	"between"        => array(
		"numeric" => "El campo :attribute debe estar entre :min - :max.",
		"file"    => "El campo :attribute debe estar entre :min - :max kilobytes.",
		"string"  => "El campo :attribute debe estar entre :min - :max caracteres.",
	),
	"confirmed"      => "El campo :attribute confirmación no coincide.",
	"different"      => "El campo :attribute and :other debe ser diferente.",
	"email"          => "El formato del :attribute  es invalido.",
	"exists"         => "El campo :attribute seleccionado  is invalid.",
	"image"          => "El campo :attribute debe ser una imagen.",
	"in"             => "El campo :attribute seleccionado  is invalid.",
	"integer"        => "El campo :attribute debe ser un entero.",
	"ip"             => "El campo :attribute Debe ser una dirección IP válida.",
	"match"          => "El formato :attribute es invalido.",
	"max"            => array(
		"numeric" => "El campo :attribute debe ser menor que :max.",
		"file"    => "El campo :attribute debe ser menor que :max kilobytes.",
		"string"  => "El campo :attribute debe ser menor que :max caracteres.",
	),
	"mimes"          => "El campo :attribute debe ser un archivo de tipo :values.",
	"min"            => array(
		"numeric" => "El campo :attribute debe tener al menos :min.",
		"file"    => "El campo :attribute debe tener al menos :min kilobytes.",
		"string"  => "El campo :attribute debe tener al menos :min caracteres.",
	),
	"not_in"         => "El campo :attribute seleccionado es invalido.",
	"numeric"        => "El campo :attribute debe ser un numero.",
	"required"       => "El campo :attribute es requerido",
	"same"           => "El campo :attribute y :other debe coincidir.",
	"size"           => array(
		"numeric" => "El campo :attribute must be :size.",
		"file"    => "El campo :attribute must be :size kilobyte.",
		"string"  => "El campo :attribute must be :size caracteres.",
	),
	"unique"         => "El campo :attribute ya ha sido tomado.",
	"url"            => "El formato de :attribute es invalido.",

	/*
	|--------------------------------------------------------------------------
	| Custom Validation Language Lines
	|--------------------------------------------------------------------------
	|
	| Here you may specify custom validation messages for attributes using the
	| convention "attribute_rule" to name the lines. This helps keep your
	| custom validation clean and tidy.
	|
	| So, say you want to use a custom validation message when validating that
	| the "email" attribute is unique. Just add "email_unique" to this array
	| with your custom message. The Validator will handle the rest!
	|
	*/

	'custom' => array(),

	/*
	|--------------------------------------------------------------------------
	| Validation Attributes
	|--------------------------------------------------------------------------
	|
	| The following language lines are used to swap attribute place-holders
	| with something more reader friendly such as "E-Mail Address" instead
	| of "email". Your users will thank you.
	|
	| The Validator class will automatically search this array of lines it
	| is attempting to replace the :attribute place-holder in messages.
	| It's pretty slick. We think you'll like it.
	|
	*/

	'attributes' => array(
		'full_name' => 'nombre completo',
        'email'     => 'correo electronico',
        'password'  => 'clave',
        'password_confirmation' => 'repita su clave',
        'category_id' => 'Categoría'
	),

);