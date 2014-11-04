class CreateSongs < ActiveRecord::Migration
  def change
    create_table :songs do |t|
      t.string :title
      t.string :artist
      t.string :length
      t.integer :release_year

      t.timestamps
    end
  end
end
