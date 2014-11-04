class CreatePerformances < ActiveRecord::Migration
  def change
    create_table :performances do |t|
      t.string :role
      t.references :movie, index: true
      t.references :star, index: true

      t.timestamps
    end
  end
end
