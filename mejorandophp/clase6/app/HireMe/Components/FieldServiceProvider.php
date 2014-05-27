<?php
/**
 * Created by PhpStorm.
 * User: silence
 * Date: 5/20/14
 * Time: 8:35 PM
 */

namespace HireMe\Components;

use Illuminate\Support\ServiceProvider;

class FieldServiceProvider extends ServiceProvider {

    /**
     * Register the service provider.
     *
     * @return void
     */
    public function register()
    {
        $this->app['field'] = $this->app->share(function($app)
        {
            $fieldBuilder = new FieldBuilder($app['form'], $app['view'], $app['session.store']);
            return $fieldBuilder;
        });
    }


} 