<?php

Route::get('admin/candidate/{id}', ['as' => 'admin_candidate_edit', function ($id) {

    return 'Editando el candidato ' . $id;

}]);