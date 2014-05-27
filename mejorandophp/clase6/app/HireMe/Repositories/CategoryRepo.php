<?php

namespace HireMe\Repositories;

use HireMe\Entities\Category;

class CategoryRepo extends BaseRepo {

    public function getModel()
    {
        return new Category;
    }

    public function getList()
    {
        return Category::lists('name', 'id');
    }

} 