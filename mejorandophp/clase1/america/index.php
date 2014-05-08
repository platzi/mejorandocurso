<?php

/*
 * El frontend controller se encarga de
 * configurar nuestra aplicacion
 */
require 'config.php';
require 'helpers.php';

//Llamar al controlador indicado

controller($_GET['url']);