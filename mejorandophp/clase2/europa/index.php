<?php

require 'config.php';
require 'helpers.php';

// Libraries
require 'library/Request.php';
require 'library/Inflector.php';
require 'library/Response.php';
require 'library/View.php';

if (empty($_GET['url']))
{
    $url = '';
}
else
{
    $url = $_GET['url'];
}

$request = new Request($url);
$request->execute();


//our-team/about/freddier