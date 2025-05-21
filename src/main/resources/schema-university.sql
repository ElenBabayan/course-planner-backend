DROP TABLE IF EXISTS course CASCADE;
DROP TABLE IF EXISTS student CASCADE;
DROP TABLE IF EXISTS student_course CASCADE;
DROP TABLE IF EXISTS schedule CASCADE;

CREATE TABLE IF NOT EXISTS course
(
    course_id
    BIGSERIAL
    PRIMARY
    KEY,
    name
    VARCHAR
(
    100
) NOT NULL,
    prerequisite_id BIGINT REFERENCES course
(
    course_id
),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    credits INT,
    semester INT,
    course_schedule BIGSERIAL REFERENCES schedule(scheduleID)
    );

CREATE TABLE IF NOT EXISTS student
(
    student_id
    BIGSERIAL
    PRIMARY
    KEY,
    name
    VARCHAR
(
    100
) NOT NULL
    );

DROP TABLE IF EXISTS student_course CASCADE;

CREATE TABLE IF NOT EXISTS student_course
(
    student_id
    BIGINT
    REFERENCES
    student
(
    student_id
),
    course_id BIGINT REFERENCES course
(
    course_id
),
    PRIMARY KEY
(
    student_id,
    course_id
)
    );


CREATE TABLE IF NOT EXISTS schedule
(
    scheduleID BIGSERIAL PRIMARY KEY,
    starts_at TIME NOT NULL,
    ends_at TIME NOT NULL,
    weekdays TEXT[]
);

