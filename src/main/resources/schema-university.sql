CREATE TABLE IF NOT EXISTS Course (
                                      courseID       BIGSERIAL PRIMARY KEY,
                                      name           VARCHAR(100) NOT NULL,
    prerequisiteID BIGINT REFERENCES Course(courseID),
    semester       INT,
    startDate      TIMESTAMP,
    endDate        TIMESTAMP,
    schedule       Schedule,
    credits        INT
    );


CREATE TABLE IF NOT EXISTS Student (
                                       studentID BIGSERIAL PRIMARY KEY,
                                       name      VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS Student_Course (
                                              studentID BIGINT REFERENCES Student(studentID),
    courseID  BIGINT REFERENCES Course(courseID),
    PRIMARY KEY (studentID, courseID)
    );


CREATE TABLE IF NOT EXISTS Schedule{
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
