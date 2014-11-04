# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20140822003741) do

  create_table "awards", force: true do |t|
    t.string   "name"
    t.string   "category"
    t.integer  "year"
    t.integer  "winnable_id"
    t.string   "winnable_type"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "movies", force: true do |t|
    t.string   "title"
    t.text     "description"
    t.integer  "release_year"
    t.string   "trailer_url"
    t.string   "poster_url"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.string   "genre"
  end

  create_table "movies_songs", force: true do |t|
    t.integer "movie_id"
    t.integer "song_id"
  end

  create_table "performances", force: true do |t|
    t.string   "role"
    t.integer  "movie_id"
    t.integer  "star_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "performances", ["movie_id"], name: "index_performances_on_movie_id"
  add_index "performances", ["star_id"], name: "index_performances_on_star_id"

  create_table "songs", force: true do |t|
    t.string   "title"
    t.string   "artist"
    t.string   "length"
    t.integer  "release_year"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "stars", force: true do |t|
    t.string   "name"
    t.text     "bio"
    t.string   "photo_url"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

end
