class CreateMovies < ActiveRecord::Migration
  def change
    create_table :movies do |t|
      t.string :title
      t.text :description
      t.integer :release_year
      t.string :trailer_url
      t.string :poster_url

      t.timestamps
    end
  end
end
