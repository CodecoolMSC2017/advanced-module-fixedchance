INSERT INTO users (username, email, first_name, last_name, birth_date, registration_date, password, enabled) VALUES
    ('admin', 'admin', 'Admin', 'Admin', '1988-04-01', '1990-01-01', '$2a$04$VB690Lz8ElA8kfDOepbcM.kuKbzMEx4Zv3Ou0Yh6n9OeGauykcNKm', true),
    ('student', 'student', 'John', 'Doe', '1990-05-12', '1990-01-01', '$2a$04$6q5yNrA2VKNCwiYVibi3we5ojKOVTKKSlgXz2pd.uA4/hqwr9s8tK', true),
    ('teacher', 'teacher', 'Jane', 'Doe', '1978-06-24', '1990-01-01', '$2a$04$u8Kp3qxzJ49Xysc.b0O9MuOQqxoKT7J2KCqEASlT1KZdfnfy9.Uam', true),
    ('csanad', 'csanad@gmail.com', 'Csanád', 'Hegedűs', '1996-11-12', '2018-07-12', '$2a$04$uUK5k5HofChxb2/xYZ/Yoema6Py0Rw6pWn8SOynvKImyUPWGW7yK6', true);

INSERT INTO authorities (username, authority) VALUES
    ('admin', 'ROLE_ADMIN'),
    ('student', 'ROLE_STUDENT'),
    ('teacher', 'ROLE_TEACHER'),
    ('csanad', 'ROLE_ADMIN');

INSERT INTO companies (name, username, registration_date, email, password, active, subscription, enabled) VALUES
    ('admin', 'compadmin', '1990-01-01', 'admin', 'admin', true, '12month', false);

INSERT INTO company_authorities (username, authority) VALUES
    ('compadmin', 'ROLE_COMPANY');

INSERT INTO advertisements (company_id, name, description) VALUES
    (1, 'first_company_AD', 'YouWillBuyOurProducts');

INSERT INTO schedules (teacher_id, student_id, date, start_time) VALUES
    (2, 2, '2018-01-01', 9);

INSERT INTO courses (teacher_id, name, is_validated) VALUES
    (3, 'Course #1', true),
    (3, 'Course #2', false);

INSERT INTO course_student (course_id, student_id) VALUES
    (1, 2);

INSERT INTO course_questions (course_id, question) VALUES
    (1, 'Are you okay?'),
    (1, 'Are you gay?');

INSERT INTO course_answers (question_id, answer, is_right) VALUES
    (1, 'Maybe', false),
    (1, 'No', false),
    (1, 'Yes', true),
    (2, 'Dont know', false),
    (2, 'I guess', false),
    (2, 'Positive', true);

INSERT INTO course_videos (course_id, name, video, description) VALUES
    (1, 'Vid#1', '[URL]', 'asdasd'),
    (1, 'Vid#2', '[URL]', 'bdsbds');

INSERT INTO course_reviews (course_id, rating, description) VALUES
    (1, 3, 'tsotsotjso'),
    (1, 5, 'iethjiwtheiwht'),
    (1, 1, 'bad course');

INSERT INTO course_topics (course_id, name) VALUES
    (1, 'Programming'),
    (1, 'English'),
    (1, 'Information Technology');