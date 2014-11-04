class CreateMoviesSongs < ActiveRecord::Migration
  def change
    create_table :movies_songs do |t|
    	t.belongs_to :movie
    	t.belongs_to :song
    end
  end
end
