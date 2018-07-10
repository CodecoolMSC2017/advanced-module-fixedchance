DROP TABLE IF EXISTS course_topics;
DROP TABLE IF EXISTS course_reviews;
DROP TABLE IF EXISTS schedules;
DROP TABLE IF EXISTS advertisements;
DROP TABLE IF EXISTS course_answers;
DROP TABLE IF EXISTS course_questions;
DROP TABLE IF EXISTS course_videos;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	email TEXT NOT NULL UNIQUE,
	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL,
	birth_date DATE NOT NULL,
	registration_date DATE NOT NULL,
	experience INTEGER NOT NULL DEFAULT '0',
	password TEXT NOT NULL,
	role TEXT NOT NULL,
	description TEXT,
	CONSTRAINT email_not_empty CHECK (email <> ''),
	CONSTRAINT firstname_not_empty CHECK (first_name <> ''),
	CONSTRAINT lastname_not_empty CHECK (last_name <> ''),
	CONSTRAINT password_not_empty CHECK (password <> '')
);


CREATE TABLE companies (
	id SERIAL PRIMARY KEY,
	name TEXT NOT NULL UNIQUE,
	registration_date DATE NOT NULL,
	email TEXT NOT NULL UNIQUE,
	password TEXT NOT NULL,
	active BOOLEAN NOT NULL DEFAULT 'false',
	subscription TEXT NOT NULL,
	description TEXT,
	CONSTRAINT name_not_empty CHECK (name <> ''),
	CONSTRAINT password_not_empty CHECK (password <> ''),
	CONSTRAINT email_not_empty CHECK (email <> '')
);



CREATE TABLE courses (
	id SERIAL PRIMARY KEY,
	teacher_id INTEGER NOT NULL,
	student_id INTEGER NOT NULL,
	name TEXT NOT NULL UNIQUE,
	is_validated BOOLEAN NOT NULL DEFAULT 'false',
	CONSTRAINT name_not_empty CHECK (name <> '')
);



CREATE TABLE course_videos (
	id SERIAL PRIMARY KEY,
	course_id INTEGER NOT NULL,
	name TEXT NOT NULL,
	video TEXT NOT NULL,
	description TEXT,
	CONSTRAINT name_not_empty CHECK (name <> ''),
	CONSTRAINT video_not_empty CHECK (video <> '')
);



CREATE TABLE course_questions (
	id SERIAL PRIMARY KEY,
	course_id INTEGER NOT NULL,
	question TEXT NOT NULL,
	CONSTRAINT question_not_empty CHECK (question <> '')
);



CREATE TABLE course_answers (
	id SERIAL PRIMARY KEY,
	answer TEXT NOT NULL,
	question_id INTEGER NOT NULL,
	is_right BOOLEAN NOT NULL,
	CONSTRAINT answer_not_empty CHECK (answer <> '')
);



CREATE TABLE advertisements (
	id SERIAL PRIMARY KEY,
	company_id INTEGER NOT NULL,
	name TEXT NOT NULL,
	description TEXT NOT NULL,
	CONSTRAINT name_not_empty CHECK (name <> ''),
	CONSTRAINT description_not_empty CHECK (description <> '')
);



CREATE TABLE schedules (
	id SERIAL PRIMARY KEY,
	teacher_id INTEGER NOT NULL,
	student_id INTEGER NOT NULL,
	date DATE NOT NULL,
	start_time INTEGER NOT NULL
);



CREATE TABLE course_reviews (
	id SERIAL PRIMARY KEY,
	course_id INTEGER NOT NULL,
	rating INTEGER NOT NULL,
	description TEXT
);

CREATE TABLE course_topics (
    id SERIAL PRIMARY KEY,
    course_id INTEGER NOT NULL,
    name TEXT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(id)
);
