<?php namespace HireMe\Managers;

class AccountManager extends BaseManager {

    public function getRules()
    {
        $rules = [
            'full_name' => 'required',
            'email'     => 'required|email|unique:users,email,' . $this->entity->id,
            'password'  => 'confirmed',
            'password_confirmation' => ''
        ];

        return $rules;
    }

    public function prepareData($data)
    {
        $data['full_name'] = strip_tags($data['full_name']);

        return $data;
    }


} 