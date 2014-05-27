<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;

class CreateCandidatesTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('candidates', function(Blueprint $table) {
			$table->increments('id');

            $table->string('website_url');
            $table->text('description');
            $table->enum('job_type', ['full', 'partial', 'freelance']);
            $table->integer('category_id')->unsigned();
            $table->boolean('available');
            $table->string('slug');

            $table->foreign('category_id')->references('id')->on('categories');

			$table->timestamps();
		});
	}


	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('candidates');
	}

}
