CREATE TABLE IF NOT EXISTS users (
  id serial,
  customer_id text NOT NULL,
  sub_id text DEFAULT NULL,
  username text  NOT NULL,
  password text  NOT NULL,
  company text DEFAULT NULL,
  email text DEFAULT NULL,
  telephone text DEFAULT NULL,
  firstname text NOT NULL,
  lastname text NOT NULL,
  address text NOT NULL,
  citystate text NOT NULL,
  country text NOT NULL,
  postalcode text NOT NULL,
  about text DEFAULT NULL,
  picture text DEFAULT NULL,
  title text DEFAULT NULL,
  description text DEFAULT NULL,
  PRIMARY KEY (id)
);