INSERT INTO ad_universities (id, NAME, contact, email, established_year, address)
VALUES
    (1, 'Patuakhali Science & Technology University', '01700000000', 'pstu@email.com', 2000, 'Patuakhali'),
    (2, 'Dhaka University', '01900000000', 'du@email.com', 1970, 'Dhaka');






-- Data for Patuakhali Science & Technology University
INSERT INTO ad_users (id, NAME, cell, STATUS, ROLE, university_id, email, gender, address, date_of_birth, blood_group, joining_date)
VALUES
    (1, 'Dr. John Smith', '01700000001', 'ACTIVE', 'ADMIN', 1, 'john.smith@email.com', 'Male', '123 University Rd, Patuakhali', '1970-05-15', 'O+', '2024-01-01'),
    (2, 'Alice Johnson', '01700000002', 'ACTIVE', 'STAFF', 1, 'alice.johnson@email.com', 'Female', '456 University St, Patuakhali', '1985-09-22', 'A-', '2024-02-01'),
    (3, 'Robert Brown', '01700000003', 'ACTIVE', 'STAFF', 1, 'robert.brown@email.com', 'Male', '789 University Ave, Patuakhali', '1990-12-05', 'B+', '2024-03-01'),
    (4, 'Emily Davis', '01700000004', 'ACTIVE', 'TEACHER', 1, 'emily.davis@email.com', 'Female', '101 University Blvd, Patuakhali', '1980-11-11', 'AB-', '2024-04-01'),
    (5, 'Michael Wilson', '01700000005', 'ACTIVE', 'TEACHER', 1, 'michael.wilson@email.com', 'Male', '202 University Park, Patuakhali', '1988-04-12', 'O-', '2024-05-01'),
    (6, 'Jessica Taylor', '01700000006', 'ACTIVE', 'TEACHER', 1, 'jessica.taylor@email.com', 'Female', '303 University Ln, Patuakhali', '1992-07-30', 'B-', '2024-06-01'),

-- Data for Dhaka University
    (7, 'Dr. Sarah Green', '01900000001', 'ACTIVE', 'ADMIN', 2, 'sarah.green@email.com', 'Female', '12 Main Rd, Dhaka', '1968-03-10', 'A+', '2024-01-15'),
    (8, 'David Lee', '01900000002', 'ACTIVE', 'STAFF', 2, 'david.lee@email.com', 'Male', '34 Main St, Dhaka', '1982-08-20', 'O+', '2024-02-15'),
    (9, 'Laura Martinez', '01900000003', 'ACTIVE', 'STAFF', 2, 'laura.martinez@email.com', 'Female', '56 Main Ave, Dhaka', '1987-11-05', 'AB+', '2024-03-15'),
    (10, 'James Clark', '01900000004', 'ACTIVE', 'TEACHER', 2, 'james.clark@email.com', 'Male', '78 Main Blvd, Dhaka', '1975-04-22', 'B-', '2024-04-15'),
    (11, 'Sophia White', '01900000005', 'ACTIVE', 'TEACHER', 2, 'sophia.white@email.com', 'Female', '90 Main Ln, Dhaka', '1983-12-10', 'O+', '2024-05-15'),
    (12, 'William Harris', '01900000006', 'ACTIVE', 'TEACHER', 2, 'william.harris@email.com', 'Male', '101 Main Park, Dhaka', '1990-06-01', 'A-', '2024-06-15');
-- Additional teachers for Patuakhali Science & Technology University
INSERT INTO ad_users (id, NAME, cell, STATUS, ROLE, university_id, email, gender, address, date_of_birth, blood_group, joining_date)
VALUES
    (13, 'Anna Williams', '01700000007', 'ACTIVE', 'TEACHER', 1, 'anna.williams@email.com', 'Female', '404 University St, Patuakhali', '1985-02-10', 'AB+', '2024-07-01'),
    (14, 'Liam Adams', '01700000008', 'ACTIVE', 'TEACHER', 1, 'liam.adams@email.com', 'Male', '505 University Ave, Patuakhali', '1991-10-15', 'B+', '2024-08-01'),
    (15, 'Olivia Scott', '01700000009', 'ACTIVE', 'TEACHER', 1, 'olivia.scott@email.com', 'Female', '606 University Blvd, Patuakhali', '1988-06-22', 'O+', '2024-09-01'),
    (16, 'Ethan Harris', '01700000010', 'ACTIVE', 'TEACHER', 1, 'ethan.harris@email.com', 'Male', '707 University Ln, Patuakhali', '1994-04-30', 'A-', '2024-10-01'),
    (17, 'Sophia Miller', '01700000011', 'ACTIVE', 'TEACHER', 1, 'sophia.miller@email.com', 'Female', '808 University Park, Patuakhali', '1992-01-12', 'B-', '2024-11-01'),
    (18, 'Jacob Wilson', '01700000012', 'ACTIVE', 'TEACHER', 1, 'jacob.wilson@email.com', 'Male', '909 University Dr, Patuakhali', '1990-07-08', 'AB-', '2024-12-01'),

-- Additional teachers for Dhaka University
    (19, 'Ava Brown', '01900000007', 'ACTIVE', 'TEACHER', 2, 'ava.brown@email.com', 'Female', '112 Main Rd, Dhaka', '1983-05-16', 'O-', '2024-07-15'),
    (20, 'Mason Green', '01900000008', 'ACTIVE', 'TEACHER', 2, 'mason.green@email.com', 'Male', '223 Main St, Dhaka', '1989-09-24', 'A+', '2024-08-15'),
    (21, 'Isabella Miller', '01900000009', 'ACTIVE', 'TEACHER', 2, 'isabella.miller@email.com', 'Female', '334 Main Ave, Dhaka', '1990-11-30', 'B+', '2024-09-15'),
    (22, 'James Smith', '01900000010', 'ACTIVE', 'TEACHER', 2, 'james.smith@email.com', 'Male', '445 Main Blvd, Dhaka', '1984-04-18', 'AB+', '2024-10-15'),
    (23, 'Mia Davis', '01900000011', 'ACTIVE', 'TEACHER', 2, 'mia.davis@email.com', 'Female', '556 Main Ln, Dhaka', '1991-08-22', 'O+', '2024-11-15'),
    (24, 'Benjamin Johnson', '01900000012', 'ACTIVE', 'TEACHER', 2, 'benjamin.johnson@email.com', 'Male', '667 Main Park, Dhaka', '1987-12-05', 'A-', '2024-12-15');







-- Data for Patuakhali Science & Technology University
INSERT INTO acd_faculties (id, NAME, dean_id, university_id, email, contact)
VALUES
    (1, 'Faculty of Engineering', 4, 1, 'eng.faculty.pstu@email.com', '01700001001'),
    (2, 'Faculty of Science', 5, 1, 'sci.faculty.pstu@email.com', '01700001002'),
    (3, 'Faculty of Business', 6, 1, 'bus.faculty.pstu@email.com', '01700001003'),

-- Data for Dhaka University
    (4, 'Faculty of Arts', 10, 2, 'arts.faculty.du@email.com', '01900001001'),
    (5, 'Faculty of Social Sciences', 11, 2, 'soc.faculty.du@email.com', '01900001002'),
    (6, 'Faculty of Law', 12, 2, 'law.faculty.du@email.com', '01900001003');






-- Data for Patuakhali Science & Technology University
INSERT INTO acd_departments (id, NAME, head_id, faculty_id)
VALUES
    (1, 'Department of Computer Engineering', 4, 1),
    (2, 'Department of Physics', 5, 2),
    (3, 'Department of Marketing', 6, 3),

-- Data for Dhaka University
    (4, 'Department of English Literature', 10, 4),
    (5, 'Department of Sociology', 11, 5),
    (6, 'Department of Constitutional Law', 12, 6);






-- Data for Patuakhali Science & Technology University
INSERT INTO acd_programs (id, NAME, duration_year, department_id)
VALUES
    -- For Faculty of Engineering
    (1, 'BSc in Computer Engineering (Honours)', 4, 1),
    (2, 'MSc in Computer Engineering', 2, 1),

    -- For Faculty of Science
    (3, 'BSc in Physics (Honours)', 4, 2),
    (4, 'MSc in Physics', 2, 2),

    -- For Faculty of Business
    (5, 'BBA (Honours)', 4, 3),
    (6, 'MBA', 2, 3),

-- Data for Dhaka University
    -- For Faculty of Arts
    (7, 'BA in English Literature (Honours)', 4, 4),
    (8, 'MA in English Literature', 2, 4),

    -- For Faculty of Social Sciences
    (9, 'BA in Sociology (Honours)', 4, 5),
    (10, 'MA in Sociology', 2, 5),

    -- For Faculty of Law
    (11, 'LLB (Honours)', 4, 6),
    (12, 'LLM', 2, 6);






-- Data for session 2022-2023
INSERT INTO acd_semesters (id, NAME, SESSION, CODE, program_id)
VALUES
    -- For Program: BSc in Computer Engineering (Honours)
    (1, 'Semester 1', '2022-23', 'L1S1', 1),
    (2, 'Semester 2', '2022-23', 'L1S2', 1),
    (3, 'Semester 3', '2022-23', 'L2S1', 1),
    (4, 'Semester 4', '2022-23', 'L2S2', 1),
    
    -- For Program: MSc in Computer Engineering
    (5, 'Semester 1', '2022-23', 'L1S1', 2),
    (6, 'Semester 2', '2022-23', 'L1S2', 2),
    (7, 'Semester 3', '2022-23', 'L2S1', 2),
    (8, 'Semester 4', '2022-23', 'L2S2', 2),

    -- For Program: BSc in Physics (Honours)
    (9, 'Semester 1', '2022-23', 'L1S1', 3),
    (10, 'Semester 2', '2022-23', 'L1S2', 3),
    (11, 'Semester 3', '2022-23', 'L2S1', 3),
    (12, 'Semester 4', '2022-23', 'L2S2', 3),

    -- For Program: MSc in Physics
    (13, 'Semester 1', '2022-23', 'L1S1', 4),
    (14, 'Semester 2', '2022-23', 'L1S2', 4),
    (15, 'Semester 3', '2022-23', 'L2S1', 4),
    (16, 'Semester 4', '2022-23', 'L2S2', 4),

-- Data for session 2023-2024
    -- For Program: BSc in Computer Engineering (Honours)
    (17, 'Semester 1', '2023-24', 'L1S1', 1),
    (18, 'Semester 2', '2023-24', 'L1S2', 1),

    -- For Program: MSc in Computer Engineering
    (19, 'Semester 1', '2023-24', 'L1S1', 2),
    (20, 'Semester 2', '2023-24', 'L1S2', 2),

    -- For Program: BSc in Physics (Honours)
    (21, 'Semester 1', '2023-24', 'L1S1', 3),
    (22, 'Semester 2', '2023-24', 'L1S2', 3),

    -- For Program: MSc in Physics
    (23, 'Semester 1', '2023-24', 'L1S1', 4),
    (24, 'Semester 2', '2023-24', 'L1S2', 4);






-- Data for Semester 2022-23 L1S1
INSERT INTO acd_students (id, id_no, NAME, email, cell, admission_date, SESSION, STATUS, semester_id, gender, date_of_birth, blood_group, religion, father_name, father_cell, mother_name, mother_cell, present_address, permanent_address)
VALUES
    (1, '23001', 'John Doe', 'john.doe@example.com', '01900000001', '2022-01-15', '2022-23', 'Active', 1, 'Male', '2002-05-15', 'A+', 'Christian', 'Michael Doe', '01900000011', 'Jane Doe', '01900000021', '123 Elm Street', '456 Oak Avenue'),
    (2, '23002', 'Jane Smith', 'jane.smith@example.com', '01900000002', '2022-01-16', '2022-23', 'Active', 1, 'Female', '2003-06-22', 'B-', 'Muslim', 'Robert Smith', '01900000012', 'Emily Smith', '01900000022', '789 Pine Street', '101 Maple Avenue'),
    (3, '23003', 'Michael Johnson', 'michael.johnson@example.com', '01900000003', '2022-01-17', '2022-23', 'Active', 1, 'Male', '2002-07-30', 'O+', 'Hindu', 'David Johnson', '01900000013', 'Linda Johnson', '01900000023', '234 Birch Street', '567 Cedar Avenue'),
    (4, '23004', 'Emily Davis', 'emily.davis@example.com', '01900000004', '2022-01-18', '2022-23', 'Active', 1, 'Female', '2001-08-11', 'AB+', 'Buddhist', 'William Davis', '01900000014', 'Sarah Davis', '01900000024', '345 Fir Street', '678 Spruce Avenue'),
    (5, '23005', 'James Brown', 'james.brown@example.com', '01900000005', '2022-01-19', '2022-23', 'Active', 1, 'Male', '2002-09-25', 'A-', 'Sikh', 'Charles Brown', '01900000015', 'Laura Brown', '01900000025', '456 Maple Street', '789 Willow Avenue'),

-- Data for Semester 2022-23 L1S2
    (6, '23006', 'Alice Green', 'alice.green@example.com', '01900000006', '2022-02-01', '2022-23', 'Active', 2, 'Female', '2002-10-05', 'B+', 'Christian', 'James Green', '01900000016', 'Olivia Green', '01900000026', '567 Oak Street', '890 Pine Avenue'),
    (7, '23007', 'Robert Wilson', 'robert.wilson@example.com', '01900000007', '2022-02-02', '2022-23', 'Active', 2, 'Male', '2003-11-12', 'O-', 'Muslim', 'Ethan Wilson', '01900000017', 'Sophia Wilson', '01900000027', '678 Pine Street', '901 Cedar Avenue'),
    (8, '23008', 'Sophia Martinez', 'sophia.martinez@example.com', '01900000008', '2022-02-03', '2022-23', 'Active', 2, 'Female', '2002-12-20', 'A-', 'Hindu', 'Daniel Martinez', '01900000018', 'Emma Martinez', '01900000028', '789 Maple Street', '912 Birch Avenue'),
    (9, '23009', 'Daniel Garcia', 'daniel.garcia@example.com', '01900000009', '2022-02-04', '2022-23', 'Active', 2, 'Male', '2001-01-15', 'B+', 'Buddhist', 'Mark Garcia', '01900000019', 'Alice Garcia', '01900000029', '890 Elm Street', '123 Willow Avenue'),
    (10, '23010', 'Rachel Lee', 'rachel.lee@example.com', '01900000010', '2022-02-05', '2022-23', 'Active', 2, 'Female', '2002-02-25', 'AB-', 'Sikh', 'Andrew Lee', '01900000020', 'Linda Lee', '01900000030', '901 Oak Street', '234 Pine Avenue'),

-- Data for Semester 2022-23 L2S1
    (11, '23011', 'Megan Taylor', 'megan.taylor@example.com', '01900000011', '2022-03-01', '2022-23', 'Active', 3, 'Female', '2001-03-05', 'A+', 'Christian', 'George Taylor', '01900000021', 'Nancy Taylor', '01900000031', '234 Elm Street', '345 Oak Avenue'),
    (12, '23012', 'William Anderson', 'william.anderson@example.com', '01900000012', '2022-03-02', '2022-23', 'Active', 3, 'Male', '2002-04-15', 'B-', 'Muslim', 'Frank Anderson', '01900000022', 'Karen Anderson', '01900000032', '345 Maple Street', '456 Pine Avenue'),
    (13, '23013', 'Emily Thomas', 'emily.thomas@example.com', '01900000013', '2022-03-03', '2022-23', 'Active', 3, 'Female', '2002-05-20', 'O+', 'Hindu', 'Samuel Thomas', '01900000023', 'Megan Thomas', '01900000033', '456 Oak Street', '567 Cedar Avenue'),
    (14, '23014', 'Michael Lewis', 'michael.lewis@example.com', '01900000014', '2022-03-04', '2022-23', 'Active', 3, 'Male', '2003-06-10', 'AB+', 'Buddhist', 'Mark Lewis', '01900000024', 'Alice Lewis', '01900000034', '567 Pine Street', '678 Birch Avenue'),
    (15, '23015', 'Olivia Robinson', 'olivia.robinson@example.com', '01900000015', '2022-03-05', '2022-23', 'Active', 3, 'Female', '2002-07-25', 'A-', 'Sikh', 'Philip Robinson', '01900000025', 'Sandra Robinson', '01900000035', '678 Cedar Street', '789 Willow Avenue'),

-- Data for Semester 2022-23 L2S2
    (16, '23016', 'Ethan Walker', 'ethan.walker@example.com', '01900000016', '2022-04-01', '2022-23', 'Active', 4, 'Male', '2002-08-05', 'B+', 'Christian', 'Brian Walker', '01900000026', 'Emily Walker', '01900000036', '789 Elm Street', '890 Oak Avenue'),
    (17, '23017', 'Isabella Hall', 'isabella.hall@example.com', '01900000017', '2022-04-02', '2022-23', 'Active', 4, 'Female', '2001-09-10', 'O-', 'Muslim', 'Albert Hall', '01900000027', 'Alice Hall', '01900000037', '890 Pine Street', '901 Maple Avenue'),
    (18, '23018', 'Aiden Young', 'aiden.young@example.com', '01900000018', '2022-04-03', '2022-23', 'Active', 4, 'Male', '2003-10-20', 'A-', 'Hindu', 'Henry Young', '01900000028', 'Lily Young', '01900000038', '901 Oak Street', '912 Cedar Avenue'),
    (19, '23019', 'Mia Hernandez', 'mia.hernandez@example.com', '01900000019', '2022-04-04', '2022-23', 'Active', 4, 'Female', '2002-11-15', 'AB-', 'Buddhist', 'David Hernandez', '01900000029', 'Sarah Hernandez', '01900000039', '012 Birch Street', '123 Willow Avenue'),
    (20, '23020', 'Logan Martinez', 'logan.martinez@example.com', '01900000020', '2022-04-05', '2022-23', 'Active', 4, 'Male', '2002-12-25', 'B+', 'Sikh', 'Charles Martinez', '01900000030', 'Laura Martinez', '01900000040', '123 Maple Street', '234 Oak Avenue'),

-- Data for Semester 2023-24 L1S1
    (21, '24001', 'Sophia King', 'sophia.king@example.com', '01900000021', '2023-01-05', '2023-24', 'Active', 5, 'Female', '2003-01-10', 'O+', 'Christian', 'James King', '01900000031', 'Anna King', '01900000041', '234 Elm Street', '345 Oak Avenue'),
    (22, '24002', 'Liam Scott', 'liam.scott@example.com', '01900000022', '2023-01-06', '2023-24', 'Active', 5, 'Male', '2002-02-20', 'A-', 'Muslim', 'Robert Scott', '01900000032', 'Olivia Scott', '01900000042', '345 Maple Street', '456 Pine Avenue'),
    (23, '24003', 'Mia Adams', 'mia.adams@example.com', '01900000023', '2023-01-07', '2023-24', 'Active', 5, 'Female', '2002-03-25', 'B+', 'Hindu', 'Daniel Adams', '01900000033', 'Emma Adams', '01900000043', '456 Oak Street', '567 Cedar Avenue'),
    (24, '24004', 'Noah Carter', 'noah.carter@example.com', '01900000024', '2023-01-08', '2023-24', 'Active', 5, 'Male', '2003-04-30', 'AB-', 'Buddhist', 'Paul Carter', '01900000034', 'Sophia Carter', '01900000044', '567 Pine Street', '678 Birch Avenue'),
    (25, '24005', 'Emma Turner', 'emma.turner@example.com', '01900000025', '2023-01-09', '2023-24', 'Active', 5, 'Female', '2002-05-15', 'A+', 'Sikh', 'Philip Turner', '01900000035', 'Laura Turner', '01900000045', '678 Cedar Street', '789 Willow Avenue'),

-- Data for Semester 2023-24 L1S2
    (26, '24006', 'Lucas Nelson', 'lucas.nelson@example.com', '01900000026', '2023-02-01', '2023-24', 'Active', 6, 'Male', '2002-06-05', 'B-', 'Christian', 'Andrew Nelson', '01900000036', 'Lily Nelson', '01900000046', '789 Elm Street', '890 Oak Avenue'),
    (27, '24007', 'Olivia Parker', 'olivia.parker@example.com', '01900000027', '2023-02-02', '2023-24', 'Active', 6, 'Female', '2001-07-10', 'O+', 'Muslim', 'David Parker', '01900000037', 'Sarah Parker', '01900000047', '890 Pine Street', '901 Maple Avenue'),
    (28, '24008', 'Ethan Roberts', 'ethan.roberts@example.com', '01900000028', '2023-02-03', '2023-24', 'Active', 6, 'Male', '2002-08-15', 'A-', 'Hindu', 'James Roberts', '01900000038', 'Emma Roberts', '01900000048', '901 Oak Street', '912 Cedar Avenue'),
    (29, '24009', 'Ava Campbell', 'ava.campbell@example.com', '01900000029', '2023-02-04', '2023-24', 'Active', 6, 'Female', '2003-09-20', 'AB+', 'Buddhist', 'Robert Campbell', '01900000039', 'Mia Campbell', '01900000049', '012 Birch Street', '123 Willow Avenue'),
    (30, '24010', 'Mason Mitchell', 'mason.mitchell@example.com', '01900000030', '2023-02-05', '2023-24', 'Active', 6, 'Male', '2002-10-25', 'B+', 'Sikh', 'George Mitchell', '01900000040', 'Laura Mitchell', '01900000050', '123 Maple Street', '234 Oak Avenue');






-- Data for Semester 2022-23 L1S1
INSERT INTO acd_courses (id, NAME, CODE, credit, semester_id, description)
VALUES
    (1, 'Introduction to Computer Science', 'CS101', 3, 1, 'Basics of computer science, programming concepts, and algorithms.'),
    (2, 'Calculus I', 'MATH101', 4, 1, 'Fundamentals of calculus including limits, derivatives, and integrals.'),
    (3, 'Introduction to Sociology', 'SOC101', 3, 1, 'Overview of sociology and its major concepts.'),

-- Data for Semester 2022-23 L1S2
    (4, 'Data Structures', 'CS102', 3, 2, 'Advanced concepts in data structures including trees, graphs, and hash tables.'),
    (5, 'Calculus II', 'MATH102', 4, 2, 'Continuation of Calculus I, focusing on advanced integration techniques.'),
    (6, 'Introduction to Psychology', 'PSY101', 3, 2, 'Basics of psychology including behavioral theories and cognitive processes.'),

-- Data for Semester 2023-24 L1S1
    (7, 'Algorithms', 'CS201', 3, 3, 'Study of algorithms, their efficiency, and complexity analysis.'),
    (8, 'Linear Algebra', 'MATH201', 4, 3, 'Introduction to linear algebra, matrices, and vector spaces.'),
    (9, 'Principles of Economics', 'ECO101', 3, 3, 'Fundamental concepts of micro and macroeconomics.'),

-- Data for Semester 2023-24 L1S2
    (10, 'Operating Systems', 'CS202', 3, 4, 'Overview of operating system concepts, processes, and memory management.'),
    (11, 'Probability and Statistics', 'MATH202', 4, 4, 'Basics of probability theory and statistical methods.'),
    (12, 'Introduction to Philosophy', 'PHIL101', 3, 4, 'Fundamental philosophical concepts and theories.');

-- Data for course_teachers
INSERT INTO course_teachers (course_id, teacher_id)
VALUES
    -- Courses for Semester 2022-23 L1S1
    (1, 1), -- Introduction to Computer Science
    (1, 2), -- Introduction to Computer Science
    (2, 3), -- Calculus I
    (3, 4), -- Introduction to Sociology

    -- Courses for Semester 2022-23 L1S2
    (4, 5), -- Data Structures
    (4, 6), -- Data Structures
    (5, 7), -- Calculus II
    (6, 8), -- Introduction to Psychology

    -- Courses for Semester 2023-24 L1S1
    (7, 2), -- Algorithms
    (8, 3), -- Linear Algebra
    (9, 9), -- Principles of Economics

    -- Courses for Semester 2023-24 L1S2
    (10, 10), -- Operating Systems
    (11, 11), -- Probability and Statistics
    (12, 12); -- Introduction to Philosophy




-- Data for acd_fees
-- Semester 2022-23
INSERT INTO acd_fees (TYPE, amount, semester_id)
VALUES
    ('SEMESTER', 5000.00, 1), -- Semester fee for L1S1 2022-23
    ('HALL', 2000.00, 1),      -- Hall fee for L1S1 2022-23
    ('SEMESTER', 5000.00, 2), -- Semester fee for L1S2 2022-23
    ('HALL', 2000.00, 2),      -- Hall fee for L1S2 2022-23
    ('SEMESTER', 5000.00, 3), -- Semester fee for L2S1 2022-23
    ('HALL', 2000.00, 3),      -- Hall fee for L2S1 2022-23
    ('SEMESTER', 5000.00, 4), -- Semester fee for L2S2 2022-23
    ('HALL', 2000.00, 4);      -- Hall fee for L2S2 2022-23

-- Semester 2023-24
INSERT INTO acd_fees (TYPE, amount, semester_id)
VALUES
    ('SEMESTER', 5000.00, 5), -- Semester fee for L1S1 2023-24
    ('HALL', 2000.00, 5),      -- Hall fee for L1S1 2023-24
    ('SEMESTER', 5000.00, 6), -- Semester fee for L1S2 2023-24
    ('HALL', 2000.00, 6);      -- Hall fee for L1S2 2023-24



-- Data for auth_tokens
INSERT INTO auth_tokens (username, PASSWORD, user_id)
VALUES
    ('admin1', '$2y$12$gyOTFHFiNEcMN0fEaUB2BOXQBaKu8wz.irM21ATMXtLEsaVnoNU0O', 1),
    ('admin2', '$2y$12$gyOTFHFiNEcMN0fEaUB2BOXQBaKu8wz.irM21ATMXtLEsaVnoNU0O', 7);
