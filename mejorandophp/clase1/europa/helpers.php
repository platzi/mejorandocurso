<?php

//Declara la funcion
function view($template, $vars = array())
{
    extract($vars);

    require "views/$template.tpl.php";
}

//~/contactos

function controller($name)
{
    if (empty($name))
    {
        $name = 'home';
    }

    $file = "controllers/$name.php";

    if (file_exists($file))
    {
        return require $file;
    }

    //~/sdfsdfdsfdss
    header("HTTP/1.0 404 Not Found");
    exit("Pagina no encontrada");
}