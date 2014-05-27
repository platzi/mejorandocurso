<?php

Route::get('account', ['as' => 'account', 'uses' => 'UsersController@account']);
Route::put('account', ['as' => 'update_account', 'uses' => 'UsersController@updateAccount']);
Route::get('profile', ['as' => 'profile', 'uses' => 'UsersController@profile']);
Route::put('profile', ['as' => 'update_profile', 'uses' => 'UsersController@updateProfile']);

Route::get('logout', ['as' => 'logout', 'uses' => 'AuthController@logout']);
