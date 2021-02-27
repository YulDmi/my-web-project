DROP TABLE IF EXISTS students;

CREATE TABLE IF NOT EXISTS students
(
    id    bigint AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    age  INTEGER
);

INSERT INTO students (name, age)
VALUES
('Student1', 30),
('Student2', 21),
('Student3', 22),
('Student4', 18),
('Student5', 24);