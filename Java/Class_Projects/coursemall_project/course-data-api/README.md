-- ======================================== Create Statement =========================================

-- Table 1: wiki.candidate

-- DROP TABLE IF EXISTS wiki.candidate;

CREATE TABLE IF NOT EXISTS wiki.candidate
(
"candidateId" integer NOT NULL,
"candidateName" text COLLATE pg_catalog."default",
"mobileNo" text COLLATE pg_catalog."default",
"emailId" text COLLATE pg_catalog."default",
"candidateRemark" text COLLATE pg_catalog."default",
CONSTRAINT candidate_pkey PRIMARY KEY ("candidateId")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS wiki.candidate
OWNER to postgres;

-- ======================================== Create Statement =========================================

-- Table 2: wiki.course

-- DROP TABLE IF EXISTS wiki.course;

CREATE TABLE IF NOT EXISTS wiki.course
(
"courseId" integer NOT NULL,
"courseName" text COLLATE pg_catalog."default",
"coursePrice" integer,
"courseRemark" text COLLATE pg_catalog."default",
CONSTRAINT course_pkey PRIMARY KEY ("courseId")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS wiki.course
OWNER to postgres;


-- ======================================== Insert Statements =========================================

-- Table 1: candidate
INSERT INTO wiki.candidate("candidateId", "candidateName", "mobileNo", "emailId", "candidateRemark")
VALUES (100, 'Abhijeet Soni', '99804 26900', 'toabhijeetsoni@gmail.com', 'Online: Bangalore');

INSERT INTO wiki.candidate("candidateId", "candidateName", "mobileNo", "emailId", "candidateRemark")
VALUES (101, 'Surya Kumar', '99804 26800', 'tosuryakumar@gmail.com', 'Offline: Bangalore');

-- Table 2: course
INSERT INTO wiki.course("courseId", "courseName", "coursePrice", "courseRemark")
VALUES (500, 'Angular', 90, 'Complete: Angular');

INSERT INTO wiki.course("courseId", "courseName", "coursePrice", "courseRemark")
VALUES (500, 'React', 90, 'Complete: React');

-- ========================================== Create: Sequences =======================================

-- candidate_seq
-- course_seq

-- ====================================================================================================
