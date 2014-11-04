json.array!(@stars) do |star|
  json.extract! star, :id
  json.url star_url(star, format: :json)
end
