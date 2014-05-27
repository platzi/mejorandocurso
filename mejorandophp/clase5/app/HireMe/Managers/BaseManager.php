<?php
/**
 * Created by PhpStorm.
 * User: silence
 * Date: 5/20/14
 * Time: 7:46 PM
 */

namespace HireMe\Managers;


abstract class BaseManager {

    protected $entity;
    protected $data;
    protected $errors;

    public function __construct($entity, $data)
    {
        $this->entity = $entity;
        $this->data   = array_only($data, array_keys($this->getRules()));
    }

    abstract public function getRules();

    public function isValid()
    {
        $rules = $this->getRules();

        $validation = \Validator::make($this->data, $rules);

        $isValid = $validation->passes();
        $this->errors = $validation->messages();

        return $isValid;
    }

    public function save()
    {
        if ( ! $this->isValid())
        {
            return false;
        }

        $this->entity->fill($this->data);
        $this->entity->save();

        return true;
    }

    public function getErrors()
    {
        return $this->errors;
    }

} 