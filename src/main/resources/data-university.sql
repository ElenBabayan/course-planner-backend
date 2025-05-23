-- ---------- 20 students ----------

INSERT INTO student(name)
VALUES ('Alice Johnson'),
       ('Bob Smith'),
       ('Carla Lopez'),
       ('David Kim'),
       ('Ella Brown'),
       ('Frank Zhang'),
       ('Grace Miller'),
       ('Hassan Ali'),
       ('Ivy Thompson'),
       ('Jonas Fischer'),
       ('Kara White'),
       ('Liam Patel'),
       ('Maya Hernandez'),
       ('Noah Russo'),
       ('Olivia Clark'),
       ('Pedro Santos'),
       ('Quinn Evans'),
       ('Rita Costa'),
       ('Sanjay Rao'),
       ('Tara Nguyen');

INSERT INTO schedule (starts_at, ends_at, weekdays)
VALUES
    ('09:00', '12:00', 'MONDAY,WEDNESDAY,FRIDAY'),
    ('14:00', '17:00', 'TUESDAY,THURSDAY'),
    ('08:00', '11:00', 'MONDAY,TUESDAY,WEDNESDAY'),
    ('10:00', '13:00', 'THURSDAY,FRIDAY'),
    ('12:00', '15:00', 'MONDAY,FRIDAY'),
    ('13:00', '16:00', 'TUESDAY,SATURDAY'),
    ('15:00', '18:00', 'WEDNESDAY,THURSDAY'),
    ('17:00', '20:00', 'MONDAY,SUNDAY'),
    ('09:00', '12:00', 'TUESDAY,FRIDAY'),
    ('14:00', '17:00', 'SATURDAY,SUNDAY');

-- ---------- 30 courses ----------
-- Foundation block
INSERT INTO course (name, prerequisite_id, start_date, end_date, credits, semester, course_schedule)
VALUES
    ('Intro to Computer Science', NULL, '2025-09-01', '2025-12-15', 4, 1, 1),
    ('Data Structures', 1, '2025-09-01', '2025-12-15', 4, 3, 2),
    ('Algorithms', 2, '2026-01-10', '2026-05-20', 4, 2, 3),
    ('Computer Organization', 1, '2025-09-01', '2025-12-15', 3, 3, 4),
    ('Operating Systems', 4, '2026-01-10', '2026-05-20', 4, 2, 5),
    ('Databases', 2, '2026-01-10', '2026-05-20', 4, 2, 6),
    ('Networks', 4, '2026-01-10', '2026-05-20', 3, 1, 7),
    ('Software Engineering', 2, '2026-01-10', '2026-05-20', 4, 1, 8),
    ('Artificial Intelligence', 3, '2026-08-25', '2026-12-10', 4, 4, 9),
    ('Machine Learning', 9, '2027-01-09', '2027-05-18', 4, 3, 10);

INSERT INTO student_course (student_id, course_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 1),
    (2, 5),
    (2, 2),
    (2, 6),
    (2, 3),
    (2, 7),
    (3, 1),
    (3, 5),
    (3, 4),
    (3, 8),
    (3, 9),
    (4, 1),
    (4, 4),
    (4, 10),
    (4, 2),
    (5, 1),
    (5, 2),
    (6, 1),
    (6, 5),
    (6, 3),
    (6, 6),
    (7, 2),
    (7, 6),
    (7, 5),
    (8, 1),
    (8, 3),
    (8, 7),
    (9, 1),
    (9, 4),
    (10, 1),
    (10, 5),
    (10, 4),
    (11, 2),
    (11, 6),
    (12, 1),
    (12, 5),
    (12, 8),
    (13, 9),
    (14, 1),
    (14, 4),
    (14, 9),
    (15, 2),
    (16, 1),
    (16, 5),
    (17, 3),
    (17, 2),
    (18, 1),
    (18, 4),
    (18, 9),
    (19, 1),
    (19, 3),
    (19, 7),
    (20, 2),
    (20, 10);
