-- ---------- 20 students ----------
INSERT INTO student(name) VALUES
                              ('Alice Johnson'),    ('Bob Smith'),       ('Carla Lopez'),     ('David Kim'),
                              ('Ella Brown'),       ('Frank Zhang'),     ('Grace Miller'),    ('Hassan Ali'),
                              ('Ivy Thompson'),     ('Jonas Fischer'),   ('Kara White'),      ('Liam Patel'),
                              ('Maya Hernandez'),   ('Noah Russo'),      ('Olivia Clark'),    ('Pedro Santos'),
                              ('Quinn Evans'),      ('Rita Costa'),      ('Sanjay Rao'),      ('Tara Nguyen');






-- ---------- 5 schedules ----------
   INSERT INTO schedule (starts_at, ends_at) VALUES
   ('09:00:00', '10:30:00'),  -- id 1
   ('10:45:00', '12:15:00'),  -- id 2
   ('13:00:00', '14:30:00'),  -- id 3
   ('15:00:00', '16:30:00'),  -- id 4
   ('17:00:00', '18:30:00');  -- id 5

-- ---------- 5 schedule days ----------
INSERT INTO schedule_days (schedule_id, day_of_week) VALUES
    (1, 'MONDAY'), (1, 'WEDNESDAY'), (1, 'FRIDAY'),
    (2, 'TUESDAY'), (2, 'THURSDAY'),
    (3, 'MONDAY'), (3, 'WEDNESDAY'), (3, 'FRIDAY')
    (4, 'TUESDAY'), (4, 'THURSDAY')
    (5, 'MONDAY'), (5, 'WEDNESDAY'), (5, 'FRIDAY')

-- ---------- 30 courses ----------
-- Foundation block
INSERT INTO Course(courseID,name,prerequisiteID,startDate,endDate,credits) VALUES

-- As per requirement added semesters and schedule
                                                                               (101,'Intro to Computer Science',NULL,1,'2025-09-01','2025-12-15',1,4),
                                                                               (102,'Data Structures',101,1,'2025-09-01','2025-12-15',2,4),
                                                                               (103,'Algorithms',102,2,'2026-01-10','2026-05-20',1,4),
                                                                               (104,'Computer Organization',101,1,'2025-09-01','2025-12-15',2,3),
                                                                               (105,'Operating Systems',104,2,'2026-01-10','2026-05-20',4,4),
                                                                               (106,'Databases',102,2,'2026-01-10','2026-05-20',3,4),
                                                                               (107,'Networks',104,2,'2026-01-10','2026-05-20',5,3),
                                                                               (108,'Software Engineering',102,2,'2026-01-10','2026-05-20',1,4),
                                                                               (109,'Artificial Intelligence',103,1,'2026-08-25','2026-12-10',2,4),
                                                                               (110,'Machine Learning',109,2,'2027-01-09','2027-05-18',1,4),

-- Humanities block
                                                                               (111,'English Literature I',NULL,1,'2025-09-01','2025-12-15',1,3),
                                                                               (112,'English Literature II',111,2,'2026-01-10','2026-05-20',1,3),
                                                                               (113,'World History',NULL,1,'2025-09-01','2025-12-15',2,3),
                                                                               (114,'Philosophy 101',NULL,1,'2025-09-01','2025-12-15',5,3),
                                                                               (115,'Ethics in Technology',114,2,'2026-01-10','2026-05-20',5,3),

-- Math / Science block
                                                                               (116,'Calculus I',NULL,1,'2025-09-01','2025-12-15',1,4),
                                                                               (117,'Calculus II',116,2,'2026-01-10','2026-05-20',1,4),
                                                                               (118,'Linear Algebra',116,2,'2026-01-10','2026-05-20',2,4),
                                                                               (119,'Statistics',116,1,'2025-09-01','2025-12-15',1,3),
                                                                               (120,'Discrete Mathematics',101,1,'2025-09-01','2025-12-15',2,4),

-- Electives
                                                                               (121,'Intro to Psychology',NULL,1,'2025-09-01','2025-12-15',4,3),
                                                                               (122,'Microeconomics',NULL,1,'2025-09-01','2025-12-15',3,3),
                                                                               (123,'Macroeconomics',122,2,'2026-01-10','2026-05-20',4,3),
                                                                               (124,'Digital Marketing',NULL,1,'2025-09-01','2025-12-15',1,3),
                                                                               (125,'Graphic Design Basics',NULL,1,'2025-09-01','2025-12-15',1,3),
                                                                               (126,'User Experience Design',125,2,'2026-01-10','2026-05-20',3,3),
                                                                               (127,'Project Management',108,1,'2026-08-25','2026-12-10',2,3),
                                                                               (128,'Cloud Computing',105,1,'2026-08-25','2026-12-10',5,4),
                                                                               (129,'Cybersecurity Fundamentals',104,1,'2026-08-25','2026-12-10',1,3),
                                                                               (130,'Data Visualization',118,1,'2026-08-25','2026-12-10',2,3);

-- ---------- Sample Student_Course completions ----------
-- Alice (1)
INSERT INTO student_course VALUES (1,101),(1,111),(1,116),(1,104);

-- Bob (2)
INSERT INTO student_course VALUES (2,101),(2,102),(2,111),(2,112),(2,116),(2,117);

-- Carla (3)
INSERT INTO student_course VALUES (3,101),(3,102),(3,104),(3,106),(3,119);

-- David (4)
INSERT INTO student_course VALUES (4,101),(4,104),(4,105),(4,120);

-- … repeat similar  records so every student has 3–6 completions …
INSERT INTO student_course VALUES
                               (5,101),(5,111),(5,113),
                               (6,101),(6,102),(6,103),(6,118),
                               (7,111),(7,112),(7,121),
                               (8,101),(8,116),(8,117),
                               (9,101),(9,104),
                               (10,101),(10,102),(10,120),
                               (11,111),(11,112),
                               (12,101),(12,102),(12,106),
                               (13,113),(13,114),
                               (14,101),(14,120),(14,119),
                               (15,111),(15,113),
                               (16,101),(16,102),
                               (17,121),(17,122),
                               (18,101),(18,104),(18,129),
                               (19,101),(19,116),(19,117),
                               (20,111),(20,121);
