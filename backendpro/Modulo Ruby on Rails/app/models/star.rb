class Star < ActiveRecord::Base
  has_many :performances
  has_many :movies, through: :performances	
  has_many :awards, as: :winnable

  validates :name, presence: true
end
