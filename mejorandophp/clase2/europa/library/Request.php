<?php

class Request {

    protected $url;
    protected $controller;
    protected $action;
    protected $params;

    protected $defaultController = 'home';
    protected $defaultAction = 'index';

    public function __construct($url)
    {
        $this->url = $url;

        $segments = explode('/', $this->getUrl());

        $this->resolveController($segments);
        $this->resolveAction($segments);
        $this->resolveParams($segments);
    }

    protected function resolveController(&$segments)
    {
        $this->controller = array_shift($segments);

        if (empty($this->controller))
        {
            $this->controller = $this->defaultController;
        }
    }

    protected function resolveAction(&$segments)
    {
        $this->action = array_shift($segments);

        if (empty($this->action))
        {
            $this->action = $this->defaultAction;
        }
    }

    protected function resolveParams(&$segments)
    {
        $this->params = $segments;
    }

    public function getUrl()
    {
        return $this->url;
    }

    public function getController()
    {
        return $this->controller;
    }

    public function getAction()
    {
        return $this->action;
    }

    public function getActionMethodName()
    {
        return Inflector::lowerCamel($this->getAction()) . 'Action';
    }

    public function getControllerClassName()
    {
        return Inflector::camel($this->getController()) . 'Controller';
    }

    public function getControllerFileName()
    {
        return 'controllers/' . $this->getControllerClassName() . '.php';
    }

    public function getParams()
    {
        return $this->params;
    }

    public function execute()
    {
        $controllerName = $this->getControllerClassName();
        $actionName = $this->getActionMethodName();
        $controllerFile = $this->getControllerFileName();

        if ( ! file_exists($controllerFile))
        {
            exit('error 404');
        }

        require $controllerFile;

        $controllerClass = new $controllerName;
        $response = $controllerClass->$actionName();

        $this->printResponse($response);
    }

    public function printResponse($response)
    {
        if (is_string($response))
        {
            echo $response;
        }
        elseif(is_array($response))
        {
            echo json_encode($response);
        }
        elseif ($response instanceof Response)
        {
            $response->execute();
        }
        else
        {
            exit('Respuesta no valida');
        }
    }

}