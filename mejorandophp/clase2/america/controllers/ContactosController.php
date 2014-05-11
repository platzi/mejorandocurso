<?php

class ContactosController {

    public function indexAction()
    {
        return new View('contactos');
    }

    public function ciudadAction($ciudad)
    {
        exit('contactos ' . $ciudad);
    }

}