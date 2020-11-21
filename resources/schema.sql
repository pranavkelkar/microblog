-- Initialize the database.
-- Drop any existing data and create empty tables.

DROP TABLE IF EXISTS author_tbl;
DROP TABLE IF EXISTS post_tbl;

CREATE TABLE IF NOT EXISTS author_tbl (
  id SERIAL PRIMARY KEY,
  username TEXT UNIQUE NOT NULL,
  password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS post_tbl (
  id SERIAL PRIMARY KEY,
  author_id INTEGER NOT NULL,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  title TEXT NOT NULL,
  content TEXT NOT NULL,
  FOREIGN KEY (author_id) REFERENCES author_tbl (id)
);
