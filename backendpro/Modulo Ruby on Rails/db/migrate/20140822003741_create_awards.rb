class CreateAwards < ActiveRecord::Migration
  def change
    create_table :awards do |t|
      t.string :name
      t.string :category
      t.integer :year
      t.integer :winnable_id
      t.string :winnable_type

      t.timestamps
    end
  end
end
