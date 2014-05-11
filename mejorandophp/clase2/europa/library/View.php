<?php

class View extends Response {

    protected $template;
    protected $vars;

    public function __construct($template, $vars = array())
    {
        $this->template = $template;
        $this->vars = $vars;
    }

    public function getTemplate()
    {
        return $this->template;
    }

    public function getVars()
    {
        return $this->vars;
    }

    public function getTemplateFileName()
    {
        return 'views/' . $this->getTemplate() . '.tpl.php';
    }

    public function execute()
    {
        $template = $this->getTemplateFileName();
        $vars = $this->getVars();

        call_user_func(function () use ($template, $vars) {
            extract($vars);

            ob_start();

            require $template;

            $tpl_content = ob_get_clean();

            require 'views/layout.tpl.php';
        });
    }

}