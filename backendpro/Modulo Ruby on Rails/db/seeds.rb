# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)

Movie.create(title: "Guardians of the Galaxy", 
                     description: "Light years from Earth, 26 years after being abducted, Peter Quill finds himself the prime target of a manhunt after discovering an orb wanted by Ronan the Accuser.",
                     release_year: 2014, trailer_url: "http://www.youtube.com/watch?v=B16Bo47KS2g",
                     poster_url: "http://1.media.dorkly.cvcdn.com/26/95/18b149286ca6f2920e017bd5d2ffcbf5.jpg")

Star.create(name: "Chris Pratt", photo_url: "http://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/Chris_Pratt_-_Guardians_of_the_Galaxy_premiere_-_July_2014_%28cropped%29.jpg/440px-Chris_Pratt_-_Guardians_of_the_Galaxy_premiere_-_July_2014_%28cropped%29.jpg", bio: "He is known for television roles, including Bright Abbott in Everwood (2002–2006), and Andy Dwyer in Parks and Recreation since 2009, as well as for his film performances as Scott Hatteberg in Moneyball (2011), U.S. Navy SEAL Justin in Zero Dark Thirty (2012), Paul in Her (2013), Brett in Delivery Man (2013) and as Peter Quill / Star-Lord in Guardians of the Galaxy (2014). He also provided the voice of Emmet Brickowski in the 2014 computer animated film The Lego Movie.")

Song.create(title: "Hooked on a Feeling", artist: "Blue Swede", length: "2:48", release_year: 1974)
