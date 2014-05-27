<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the Closure to execute when that URI is requested.
|
*/

Route::get('/', ['as' => 'home', 'uses' => 'HomeController@index']);

//candidates/backend-developers/1
Route::get('candidates/{slug}/{id}', ['as' => 'category', 'uses' => 'CandidatesController@category']);

// duilio-palacios/1
Route::get('{slug}/{id}', ['as' => 'candidate', 'uses' => 'CandidatesController@show']);