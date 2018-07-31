INSERT INTO users (username, email, first_name, last_name, birth_date, registration_date, password, enabled) VALUES
    ('matteo', 'matteo@gmail.com', 'Máté', 'Hricz', '1993-04-26', '2018-07-02', '$2a$04$rQEGRkmhYgiVz8n6cwM00OSei7zr3c1AdpGDhTqfn2x5It3rC/uTy', true), --1
    ('balint', 'balint@gmail.com', 'Bálint', 'Csizmadia', '1988-03-16', '2018-07-02', '$2a$04$pxPucsFlobeOrZSqbFdgx.8Mn5PhJX6apJzuBI5VElimJHRcgzTEq', true), --2
    ('csanad', 'csanad@gmail.com', 'Csanád', 'Hegedűs', '1996-11-12', '2018-07-02', '$2a$04$uUK5k5HofChxb2/xYZ/Yoema6Py0Rw6pWn8SOynvKImyUPWGW7yK6', true), --3
    ('uszkai', 'uszkai@gmail.com', 'Dávid', 'Uszkai', '1987-02-17', '2018-07-10', '$2a$04$Psu.WEJiSQ0qX9O4QLe4uOYyQqXP7xII7TGgg9pC0RYglZzwu3Q.y', true), --4
    ('norbi', 'norbi@gmail.com', 'Norbert', 'Csecskedy', '1986-04-11', '2018-07-16', '$2a$04$/xXRsGXGE/sU7swhtDmyq.kCTDyicJHIBQl7M3YmFHLHmtOAZUSXS', true), --5
    ('kohrobi', 'kohrobi@gmail.com', 'Róbert', 'Kohányi', '1987-06-18', '2018-07-21', '$2a$04$sSneTQcV4U1Bv1CTpdFcEeL79ObP/Dwci.o3NwXUTiVuTDnYzZemq', true), --6
    ('pakkko', 'pakkko@gmail.com', 'Pál', 'Monoczki', '1982-02-20', '2018-08-03', '$2a$04$/HUPO77ug5EIJkJCSfpzhOG8XDNip9m4kb61Nu9TYSzv2OILEjPXq', true);  --7

INSERT INTO authorities (username, authority) VALUES
    ('matteo', 'ROLE_ADMIN'),
    ('balint', 'ROLE_ADMIN'),
    ('csanad', 'ROLE_ADMIN'),
    ('uszkai', 'ROLE_STUDENT'),
    ('norbi', 'ROLE_STUDENT'),
    ('kohrobi', 'ROLE_TEACHER'),
    ('pakkko', 'ROLE_TEACHER');

INSERT INTO companies (name, username, registration_date, email, password, active, subscription, payment_date, enabled) VALUES
    ('Hitachi', 'hitachi', '2018-07-16', 'support@hitachi.com', '$2a$04$rAIJdDCU/721uR2uzYCb3ezLBMLpq1qoyc26OM.MGBINLIiLeC8MG', true, '12month', '2018-07-16', true), --1
    ('Intel', 'intel', '2018-07-26', 'info@intel.com', '$2a$04$rZUQN6w4n5F.fEHtYbAvI.Ufclo8mPPc7Y1fmTLWB8/OW1UpM2LGm', true, '6month', '2018-07-29', true); --2

INSERT INTO company_authorities (username) VALUES
    ('hitachi'),
    ('intel');

INSERT INTO advertisements (company_id, name, description) VALUES
    (1, 'BACKEND JAVA Developer needed ASAP', 'If you''re familiar with JAVA, this job is for you!'), --1
    (1, 'FRONTEND JavaScript & Angular dev', 'If you''re experienced in these technologies, apply'), --2
    (1, 'Project Manager or Scrum Master', 'We''re looking for an experienced leader personality to lead our team'), --3
    (1, 'Mobile Application Developer', 'Develop mobile apps in NativeScript with a great team!'), --4
    (1, 'Promotion Agent', 'We need an ambitious person to promote our projects to other companies!'), --5
    (2, 'Website Designer', 'If you''re an expert in CSS, SCSS, HTML, JavaScript, we are looking for you!'), --6
    (2, 'Software Developer needed', 'We are looking for a software developer who knows Python'); --7

INSERT INTO schedules (teacher_id, student_id, date, start_time) VALUES
    (6, 4, '2018-07-28', 9), --1
    (6, 4, '2018-07-28', 10), --2
    (6, 5, '2018-07-28', 11), --3
    (6, 5, '2018-07-28', 12), --4
    (7, 4, '2018-07-29', 14), --5
    (7, 5, '2018-07-29', 16), --6
    (7, 5, '2018-07-29', 17); --7

INSERT INTO courses (teacher_id, name, is_validated) VALUES
    (6, 'Python for dummies', true), --1
    (6, 'Python for beginners', true), --2
    (6, 'Python intermediate', true), --3
    (6, 'Python upper intermediate', true), --4
    (6, 'All about JAVA collections', false), --5
    (7, 'C# basics', true), --6
    (7, 'C# for rookies', true), --7
    (7, 'C# intermediate', false); --8

INSERT INTO course_student (course_id, student_id) VALUES
    (1, 4),
    (2, 4),
    (3, 4),
    (5, 4),
    (6, 5),
    (7, 5);

INSERT INTO course_questions (course_id, question, question_type) VALUES
    (1, 'Can you create classes in Python?', 'YN'), --1
    (1, 'Are arguments pass-by-value in Python?', 'YN'), --2
    (1, 'Do you have to declare the type of the variables?', 'YN'), --3
    (1, 'You can delete a file with Python', 'TF'), --4
    (1, 'You can create global variables in Python', 'TF'), --5
    (1, 'You can communicate with servers via Python', 'TF'), --6
    (1, 'What is the syntax for defining a function?', 'SA'), --7
    (1, 'How do you define a dictionary?', 'SA'), --8
    (1, 'What is the syntax of printing the last letter of a string?', 'SA'), --9
    (1, 'Select the available types in Python', 'MA'), --10
    (1, 'Select the true statements', 'MA'), --11
    (1, 'Select the available built-in functions in Python', 'MA'), --12
    (1, 'Describe iterators', 'WA'), --13
    (1, 'Describe decorators', 'WA'), --14
    (2, 'Can you define a variable without giving it a type?', 'YN'), --15
    (2, 'You can use ''break'' in a while cycle', 'TF'), --16
    (3, 'Can you iterate through a dictionary?', 'YN'), --17
    (3, 'You can use ''continue'' in a while cycle', 'TF'), --18
    (4, 'Can you copy a class?', 'YN'), --19
    (4, 'You can develop an AI in Python.', 'TF'), --20
    (5, 'Can you sort a HashMap?', 'YN'), --21
    (5, 'You can remove an item from a List while iterating over it with a for cycle.', 'TF'), --22
    (6, 'Can you debug in C#?', 'YN'), --23
    (6, 'You can write unit tests in C#.', 'TF'), --24
    (7, 'Can you access threads in C#?', 'YN'), --25
    (7, 'You can create a GUI with C#.', 'TF'); --26

INSERT INTO course_answers (question_id, answer, is_right) VALUES
    (1, 'Yes', true),
    (1, 'No', false),
    (2, 'Yes', true),
    (2, 'No', false),
    (3, 'Yes', false),
    (3, 'No', true),
    (4, 'True', true),
    (4, 'False', true),
    (5, 'True', true),
    (5, 'False', false),
    (6, 'True', true),
    (6, 'False', false),
    (7, 'define function[]:', false),
    (7, 'function{}:', false),
    (7, 'def function():', true),
    (8, 'Dictionary dict = ["key1" : "value1", "key2" : "value2"]', false),
    (8, 'dict = {"key1" : "value1", "key2" : "value2"}', true),
    (8, 'dict = "DICTIONARY("key1" :: "value1", "key2" :: "value2")', false),
    (9, 'print(string[-1:]', true),
    (9, 'print(string(--1)', false),
    (9, 'print(string(1-;]', false),
    (10, 'Set', true),
    (10, 'Book', false),
    (10, 'Tuple', true),
    (10, 'Shelf', false),
    (10, 'Table', false),
    (11, 'Python is pass-by-value', true),
    (11, 'You can pass-by-reference, if you want to', false),
    (11, 'Python can not be object-oriented', false),
    (11, 'Python is fully os-independent', false),
    (11, 'You can define methods in classes', true),
    (12, 'array.remove(arrayItem)', true),
    (12, 'array.append(arrayItem', true),
    (12, 'dictionary.delete(dictionaryItem)', false),
    (12, 'del(dictionary[dictionaryItem])', true),
    (12, 'dictionary.pop(dictionaryItem)', false),
    (13, 'An iterator is an object that implements next, which is expected to return the next element of the iterable object that returned it, and raise a StopIteration exception when no more elements are available.', true),
    (14, 'A decorator is the name used for a software design pattern. Decorators dynamically alter the functionality of a function, method, or class without having to directly use subclasses or change the source code of the function being decorated.', true),
    (15, 'Yes', true),
    (15, 'No', false),
    (16, 'True', true),
    (16, 'False', false),
    (17, 'Yes', true),
    (17, 'No', false),
    (18, 'True', true),
    (18, 'False', false),
    (19, 'Yes', true),
    (19, 'No', false),
    (20, 'True', true),
    (20, 'False', false),
    (21, 'Yes', false),
    (21, 'No', true),
    (22, 'True', false),
    (22, 'False', true),
    (23, 'Yes', true),
    (23, 'No', false),
    (24, 'True', true),
    (24, 'False', false),
    (25, 'Yes', true),
    (25, 'No', false),
    (26, 'True', true),
    (26, 'False', false);

INSERT INTO course_videos (course_id, name, video, description) VALUES
    (1, 'Understanding the python syntax', 'https://www.youtube.com/watch?v=N4mEzFDjqtA', 'In this video you will learn the basic syntax of python.'),
    (1, 'Basic OOP in Python', 'https://www.youtube.com/watch?v=ZDa-Z5JzLYM', 'This video will show you how to use classes.'),
    (1, 'Python matrix tutorial', 'https://www.youtube.com/watch?v=Go-FfGhxbSM', 'This video will teach you how to use matrixes.'),
    (2, 'Python algorithms 1', 'https://www.youtube.com/watch?v=ob4faIum4kQ', 'Learn how to make algorithms.'),
    (2, 'Python algorithms 2', 'https://www.youtube.com/watch?v=zeULw-a7Mw8', 'Binary search.'),
    (2, 'Python algorithms 3', 'https://www.youtube.com/watch?v=CB_NCoxzQnk', 'Quick sort.'),
    (3, 'Python game tutorial', 'https://www.youtube.com/watch?v=l90vKQMDHPU', 'Learn how to create a basic game.'),
    (3, 'Python tic-tac-toe tutorial', 'https://www.youtube.com/watch?v=mMO-spo20jU', 'Learn to write tic-tac-toe.'),
    (3, 'Python maze game', 'https://www.youtube.com/watch?v=-0q_miviUDs', 'Basic maze game.'),
    (4, 'Python AI programming', 'https://www.youtube.com/watch?v=GsAVf3fn3yM', 'AI in Python.'),
    (4, 'Advanced guide', 'https://www.youtube.com/watch?v=E_kZDvwofHY', 'Python advanced.'),
    (4, 'Logging', 'https://www.youtube.com/watch?v=jxmzY9soFXg', 'Python logging.'),
    (5, 'Java Collections', 'https://www.youtube.com/watch?v=jU5ACV5MucM', 'Learn to use basic collections in Java.'),
    (5, 'Java servlets', 'https://www.youtube.com/watch?v=CRvcm7GKrF0', 'Learn to deal with servlets.'),
    (5, 'Communicating with Databases', 'https://www.youtube.com/watch?v=2i4t-SL1VsU', 'This video will teach you how to connect to databases.'),
    (6, 'Beginner guide to C#', 'https://www.youtube.com/watch?v=pSiIHe2uZ2w', 'Introduction to the language.'),
    (6, 'Another one', 'https://www.youtube.com/watch?v=UI2RRcsR83s', 'Deepening our knowledge.'),
    (6, 'Delegates', 'https://www.youtube.com/watch?v=ifbYA8hyvjc', 'Learn delegates'),
    (7, 'Deepen your knowledge', 'https://www.youtube.com/watch?v=lisiwUZJXqQ', 'Some more advanced tips and tricks'),
    (7, 'Advanced Async', 'https://www.youtube.com/watch?v=ZTKGRJy5P2M', 'Progress reports, cancelling tasks'),
    (7, 'Generics', 'https://www.youtube.com/watch?v=U4E5j45swiI', 'Learn generics.'),
    (8, 'Windows forms', 'https://www.youtube.com/watch?v=J4J3ZcXRN_E', 'Create a basic GUI.'),
    (8, 'Game', 'https://www.youtube.com/watch?v=o8hPt8s_bwU', 'Learn to develop a game.'),
    (8, 'OOP Game', 'https://www.youtube.com/watch?v=decfmj7b5Vg', 'Learn to develop an OOP game');

INSERT INTO course_reviews (course_id, student_id, rating, description) VALUES
    (1, 4, 5, 'Great course! Learned a lot from it!'),
    (1, 5, 5, 'Helped me a lot in starting python!'),
    (2, 4, 4, 'Hard, but great!'),
    (2, 5, 5, 'Very nice!'),
    (3, 4, 5, 'Liked it a lot.'),
    (3, 5, 5, 'Best course ever'),
    (4, 4, 5, 'Wow! GREAT!'),
    (4, 5, 5, 'Just what I needed'),
    (5, 4, 5, 'Perfect!'),
    (5, 5, 5, 'Great job!'),
    (6, 4, 5, 'Thanks for the course!'),
    (6, 5, 5, 'I Love it!'),
    (7, 4, 5, 'Thank youuuuu!'),
    (7, 5, 5, 'I''d give it 6 out of 5, if I could.');

INSERT INTO course_topics (course_id, name) VALUES
    (1, 'Programming'),
    (1, 'English'),
    (1, 'Information Technology'),
    (2, 'Programming'),
    (2, 'English'),
    (2, 'Information Technology'),
    (3, 'Programming'),
    (3, 'English'),
    (3, 'Information Technology'),
    (4, 'Programming'),
    (4, 'English'),
    (4, 'Information Technology'),
    (5, 'Programming'),
    (5, 'English'),
    (5, 'Information Technology'),
    (6, 'Programming'),
    (6, 'English'),
    (6, 'Information Technology'),
    (7, 'Programming'),
    (7, 'English'),
    (7, 'Information Technology'),
    (8, 'Programming'),
    (8, 'English'),
    (8, 'Information Technology');

INSERT INTO posts (user_id, post_content, post_topic) VALUES
    (3, 'Hi everyone, if you are interested in Python You should check out this article: https://gist.github.com/sloria/7001839', 'PYTHON' ),
    (2, 'Hey! What''s up? Here is a really good post about BlockChain and Java : https://medium.com/programmers-blockchain/create-simple-blockchain-java-tutorial-from-scratch-6eeed3cb03fa', 'JAVA'),
    (1, 'Hey FixedChancers! If you are interested in JavaScript here is a nice github repo which explains everythin from the basics: https://github.com/wesbos/es6-articles', 'JAVASCRIPT'),
    (4, 'If you have some free time and want to read something interesting you should check this out: https://codeburst.io/the-2018-web-developer-roadmap-826b1b806e8d', 'PHP'),
    (5, 'In case you need a fake online REST API for testing and prototyping: https://jsonplaceholder.typicode.com/', 'JAVASCRIPT'),
    (3, 'If you need some extra help with your project feel free to write me a message! Check out my profile', 'JAVA'),
    (2, 'Hey FixedChance Society! Iam stuck with my angular project and I was wondering if someone could help me.', 'JAVASCRIPT'),
    (1, 'Hey Everyone! I just finished the Java Collections course and it really helped me a lot so I recommend it to you if you want to know more about Collections!!','JAVA'),
    (4, 'Please someone recommend me a good Java teacher here!!', 'JAVA'),
    (5, 'Hey Fellas! Here is a RESTful API Design — Step By Step Guide: https://hackernoon.com/restful-api-design-step-by-step-guide-2f2c9f9fcdbf', 'JAVA');