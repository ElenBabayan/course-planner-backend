CREATE TABLE IF NOT EXISTS Course (
                                      courseID       BIGSERIAL PRIMARY KEY,
                                      name           VARCHAR(100) NOT NULL,
    prerequisiteID BIGINT REFERENCES Course(courseID),
    startDate      TIMESTAMP,
    endDate        TIMESTAMP,
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
