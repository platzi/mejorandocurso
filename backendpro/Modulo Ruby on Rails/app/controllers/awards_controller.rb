class AwardsController < ApplicationController
	def index
		@awards = Award.all.order(:year)
		respond_with @awards
	end

	def show
		@award = Award.find params[:id]
		respond_with @award
	end

	def create
		@award = Award.new award_params
		if @award.save!
			respond_with @award, status: :created, location: @award
		else
			respond_with @awards.errors, status: :unprocessable_entity
		end
	end

	def update
		@award = Award.find params[:id]
		if @award.update_attributes!(award_params)
			respond_with @award, status: :ok, location: @award
		else
			respond_with @awards.errors, status: :unprocessable_entity
		end
	end

	def destroy
		@award = Award.find params[:id]
		if @award.destroy
			render json: {status: :ok}		
		else
			respond_with @awards.errors, status: :unprocessable_entity	
		end
	end

	protected
	def award_params
		params.require(:award).permit(:name, :category, :year)
	end
end
