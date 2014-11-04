class Award < ActiveRecord::Base
	belongs_to :winnable, polymorphic: :true
	validates :year, numericality: {greater_than: 1800, less_than: 2015}
end
