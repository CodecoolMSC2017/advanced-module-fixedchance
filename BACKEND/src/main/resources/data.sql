INSERT INTO users (email, first_name, last_name, birth_date, registration_date, password, role) VALUES
    ('admin', 'admin', 'user', '1990-01-01', '1990-01-01', 'admin', 'ADMIN'),
    ('student', 'student', 'student', '1990-01-01', '1990-01-01', 'student', 'STUDENT'),
    ('teacher', 'teacher', 'teacher', '1990-01-01', '1990-01-01', 'teacher', 'TEACHER');

INSERT INTO companies (name, registration_date, email, password, active, subscription) VALUES
    ('admin', '1990-01-01', 'admin', 'admin', true, '12month');

INSERT INTO advertisements (company_id, name, description) VALUES
    (1, 'first_company_AD', 'YouWillBuyOurProducts');

INSERT INTO schedules (teacher_id, student_id, date, start_time) VALUES
    (2, 2, '2018-01-01', 9);

INSERT INTO courses (teacher_id, student_id, name, is_validated) VALUES
    (3, 2, 'Course #1', true),
    (3, 2, 'Course #2', false);

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