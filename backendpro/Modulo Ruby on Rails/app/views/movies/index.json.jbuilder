json.array!(@movies) do |movie|
  json.extract! movie, :id
  json.url movie_url(movie, format: :json)
end
