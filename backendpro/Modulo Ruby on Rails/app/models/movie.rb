class Movie < ActiveRecord::Base
  has_many :performances
  has_many :stars, through: :performances	
  has_and_belongs_to_many :songs
  has_many :awards, as: :winnable

  validates :title, presence: :true, uniqueness: true
  validates :release_year, numericality: {greater_than: 1800, less_than: 2015}
end
