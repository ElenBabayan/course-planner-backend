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
    credits INT
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


