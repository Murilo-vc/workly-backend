DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS app_user;

CREATE TABLE role (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    role_key varchar(30) NOT NULL UNIQUE CHECK (role_key),
    role_name text NOT NULL CHECK (role_name <> '')
);

CREATE TABLE app_user (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    role_id INTEGER NOT NULL REFERENCES role (id),
    username varchar(30) NOT NULL UNIQUE CHECK (username <> '')
    name text NOT NULL CHECK (user_name <> ''),
    password text NOT NULL CHECK (password <> ''),
    email text NULL CHECK (email <> ''),
    phone varchar(20) NULL CHECK (phone <> ''),
    experience text NULL CHECK (experience <> ''),
    education text NULL CHECK(education <> '')
);