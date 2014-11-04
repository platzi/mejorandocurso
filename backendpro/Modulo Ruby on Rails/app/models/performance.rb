class Performance < ActiveRecord::Base
  belongs_to :movie
  belongs_to :star

  validates_associated :star
  validates_associated :movie
end
