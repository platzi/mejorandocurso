<?php namespace HireMe\Managers;

class RegisterManager extends BaseManager {

    public function getRules()
    {
        $rules = [
            'full_name' => 'required',
            'email'     => 'required|email|unique:users,email',
            'password'  => 'required|confirmed',
            'password_confirmation' => 'required'
        ];

        return $rules;
    }


} 