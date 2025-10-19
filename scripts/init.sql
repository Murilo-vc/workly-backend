DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS app_user;

CREATE TYPE user_role AS ENUM ('PROJECT', 'WORK', 'BUDGET', 'COST', 'USER', 'FILE');

CREATE TABLE app_user (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username varchar(30) NOT NULL UNIQUE CHECK (username <> ''),
    name text NOT NULL CHECK (name <> ''),
    password text NOT NULL CHECK (password <> ''),
    role user_role NOT NULL,
    email text NULL CHECK (email <> ''),
    phone varchar(20) NULL CHECK (phone <> ''),
    experience text NULL CHECK (experience <> ''),
    education text NULL CHECK(education <> '')
);