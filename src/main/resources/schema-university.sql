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
    course_schedule schedule,

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


CREATE TABLE IF NOT EXISTS schedule{
    scheduleID BIGSERIAL PRIMARY KEY,
    startsAt TIME,
    endsAt TIME,
}

CREATE TYPE days_of_week AS ENUM (
    'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY',
    'FRIDAY', 'SATURDAY', 'SUNDAY'
);

CREATE TABLE schedule_days (
    schedule_id BIGINT REFERENCES schedule(scheduleID),
    day_of_week days_of_week NOT NULL,
);

