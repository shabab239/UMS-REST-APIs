INSERT INTO ad_universities (id, NAME, contact, email, established_year, address)
VALUES 
(1, 'Harvard University', '12345678901', 'harvard@example.com', 1636, 'Cambridge, MA, USA'),
(2, 'Stanford University', '98765432109', 'stanford@example.com', 1885, 'Stanford, CA, USA')








-- Insert Users for Harvard University
INSERT INTO ad_users (id, NAME, cell, STATUS, ROLE, university_id, email, gender, address, avatar, date_of_birth, blood_group, joining_date)
VALUES 
(1, 'Harvard Admin', '11111111111', 'ACTIVE', 'ADMIN', 1, 'harvard_admin@example.com', 'M', 'Cambridge, MA, USA', NULL, '1980-01-01', 'O+', '2020-01-01'),
(2, 'John Smith', '11111111112', 'ACTIVE', 'STAFF', 1, 'john.smith@example.com', 'M', 'Cambridge, MA, USA', NULL, '1985-02-15', 'A+', '2021-03-01'),
(3, 'Jane Doe', '11111111113', 'ACTIVE', 'STAFF', 1, 'jane.doe@example.com', 'F', 'Cambridge, MA, USA', NULL, '1987-05-22', 'B+', '2021-03-01'),
(4, 'Alice Johnson', '11111111114', 'ACTIVE', 'TEACHER', 1, 'alice.johnson@example.com', 'F', 'Cambridge, MA, USA', NULL, '1983-07-10', 'AB+', '2022-04-01'),
(5, 'Bob Brown', '11111111115', 'ACTIVE', 'TEACHER', 1, 'bob.brown@example.com', 'M', 'Cambridge, MA, USA', NULL, '1986-08-30', 'O-', '2022-04-01'),
(6, 'Charlie Davis', '11111111116', 'ACTIVE', 'TEACHER', 1, 'charlie.davis@example.com', 'M', 'Cambridge, MA, USA', NULL, '1988-10-20', 'A-', '2022-04-01'),
(7, 'Diana Evans', '11111111117', 'ACTIVE', 'TEACHER', 1, 'diana.evans@example.com', 'F', 'Cambridge, MA, USA', NULL, '1990-03-11', 'B-', '2022-04-01'),
(8, 'Eric Fisher', '11111111118', 'ACTIVE', 'TEACHER', 1, 'eric.fisher@example.com', 'M', 'Cambridge, MA, USA', NULL, '1982-12-25', 'O+', '2022-04-01'),
(9, 'Fiona Green', '11111111119', 'ACTIVE', 'TEACHER', 1, 'fiona.green@example.com', 'F', 'Cambridge, MA, USA', NULL, '1989-11-15', 'A+', '2022-04-01'),
(10, 'George Harris', '11111111120', 'ACTIVE', 'TEACHER', 1, 'george.harris@example.com', 'M', 'Cambridge, MA, USA', NULL, '1991-09-18', 'B+', '2022-04-01'),
(11, 'Hannah Ives', '11111111121', 'ACTIVE', 'TEACHER', 1, 'hannah.ives@example.com', 'F', 'Cambridge, MA, USA', NULL, '1993-06-05', 'AB+', '2022-04-01');

-- Insert Users for Stanford University
INSERT INTO ad_users (id, NAME, cell, STATUS, ROLE, university_id, email, gender, address, avatar, date_of_birth, blood_group, joining_date)
VALUES 
(12, 'Stanford Admin', '22222222222', 'ACTIVE', 'ADMIN', 2, 'stanford_admin@example.com', 'M', 'Stanford, CA, USA', NULL, '1980-01-01', 'O+', '2020-01-01'),
(13, 'Liam White', '22222222223', 'ACTIVE', 'STAFF', 2, 'liam.white@example.com', 'M', 'Stanford, CA, USA', NULL, '1985-02-15', 'A+', '2021-03-01'),
(14, 'Emma Harris', '22222222224', 'ACTIVE', 'STAFF', 2, 'emma.harris@example.com', 'F', 'Stanford, CA, USA', NULL, '1987-05-22', 'B+', '2021-03-01'),
(15, 'Olivia Clark', '22222222225', 'ACTIVE', 'TEACHER', 2, 'olivia.clark@example.com', 'F', 'Stanford, CA, USA', NULL, '1983-07-10', 'AB+', '2022-04-01'),
(16, 'Noah Lewis', '22222222226', 'ACTIVE', 'TEACHER', 2, 'noah.lewis@example.com', 'M', 'Stanford, CA, USA', NULL, '1986-08-30', 'O-', '2022-04-01'),
(17, 'Sophia Lee', '22222222227', 'ACTIVE', 'TEACHER', 2, 'sophia.lee@example.com', 'F', 'Stanford, CA, USA', NULL, '1988-10-20', 'A-', '2022-04-01'),
(18, 'James Martinez', '22222222228', 'ACTIVE', 'TEACHER', 2, 'james.martinez@example.com', 'M', 'Stanford, CA, USA', NULL, '1990-03-11', 'B-', '2022-04-01'),
(19, 'Isabella Rodriguez', '22222222229', 'ACTIVE', 'TEACHER', 2, 'isabella.rodriguez@example.com', 'F', 'Stanford, CA, USA', NULL, '1982-12-25', 'O+', '2022-04-01'),
(20, 'Mason Wilson', '22222222230', 'ACTIVE', 'TEACHER', 2, 'mason.wilson@example.com', 'M', 'Stanford, CA, USA', NULL, '1989-11-15', 'A+', '2022-04-01'),
(21, 'Mia Johnson', '22222222231', 'ACTIVE', 'TEACHER', 2, 'mia.johnson@example.com', 'F', 'Stanford, CA, USA', NULL, '1991-09-18', 'B+', '2022-04-01'),
(22, 'Lucas Moore', '22222222232', 'ACTIVE', 'TEACHER', 2, 'lucas.moore@example.com', 'M', 'Stanford, CA, USA', NULL, '1993-06-05', 'AB+', '2022-04-01');









INSERT INTO `auth_tokens` (`id`, `password`, `username`, `user_id`) VALUES('1','$2y$12$l/VDlnXtTK.9MA40H4u5wu/Sknh.3qaZ7NQ.SvuFpVfXMQPGAQWs6','admin1','1');
INSERT INTO `auth_tokens` (`id`, `password`, `username`, `user_id`) VALUES('2','$2y$12$l/VDlnXtTK.9MA40H4u5wu/Sknh.3qaZ7NQ.SvuFpVfXMQPGAQWs6','admin2','12');








-- Insert Faculties for Harvard University
INSERT INTO acd_faculties (id, NAME, dean_id, university_id, email, contact)
VALUES 
(1, 'Faculty of Arts and Sciences', 4, 1, 'artsandsciences@harvard.edu', '11111111122'),
(2, 'Faculty of Engineering and Applied Sciences', 5, 1, 'engineering@harvard.edu', '11111111123'),
(3, 'Faculty of Business Administration', 6, 1, 'business@harvard.edu', '11111111124');

-- Insert Faculties for Stanford University
INSERT INTO acd_faculties (id, NAME, dean_id, university_id, email, contact)
VALUES 
(4, 'Faculty of Humanities and Sciences', 12, 2, 'humanities@stanford.edu', '22222222223'),
(5, 'Faculty of Engineering', 13, 2, 'engineering@stanford.edu', '22222222224'),
(6, 'Faculty of Graduate School of Business', 14, 2, 'business@stanford.edu', '22222222225');










-- Insert Departments for Harvard University
INSERT INTO acd_departments (id, NAME, head_id, faculty_id)
VALUES 
(1, 'Department of Literature', 4, 1), -- Faculty of Arts and Sciences
(2, 'Department of History', 5, 1),    -- Faculty of Arts and Sciences
(3, 'Department of Computer Science', 6, 2), -- Faculty of Engineering and Applied Sciences
(4, 'Department of Electrical Engineering', 7, 2), -- Faculty of Engineering and Applied Sciences
(5, 'Department of Marketing', 8, 3), -- Faculty of Business Administration
(6, 'Department of Finance', 9, 3);   -- Faculty of Business Administration

-- Insert Departments for Stanford University
INSERT INTO acd_departments (id, NAME, head_id, faculty_id)
VALUES 
(7, 'Department of Philosophy', 12, 4), -- Faculty of Humanities and Sciences
(8, 'Department of Psychology', 13, 4), -- Faculty of Humanities and Sciences
(9, 'Department of Mechanical Engineering', 14, 5), -- Faculty of Engineering
(10, 'Department of Bioengineering', 15, 5), -- Faculty of Engineering
(11, 'Department of Strategic Management', 16, 6), -- Faculty of Graduate School of Business
(12, 'Department of Organizational Behavior', 17, 6); -- Faculty of Graduate School of Business










-- Insert Students for Department of Literature, Harvard University
INSERT INTO acd_students (id, id_no, NAME, email, cell, admission_date, SESSION, STATUS, department_id, gender, date_of_birth, blood_group, religion, father_name, father_cell, mother_name, mother_cell, present_address, permanent_address)
VALUES 
(1, 'HAR001', 'Alice Johnson', 'alice.johnson@harvard.edu', '11111111101', '2023-08-01', '2023-2024', 'Active', 1, 'F', '2002-05-15', 'O+', 'Christian', 'Robert Johnson', '11111111111', 'Linda Johnson', '11111111112', '123 Harvard St, Cambridge, MA', '456 Harvard St, Cambridge, MA'),
(2, 'HAR002', 'John Smith', 'john.smith@harvard.edu', '11111111102', '2023-08-01', '2023-2024', 'Active', 1, 'M', '2001-11-20', 'A-', 'Jewish', 'Michael Smith', '11111111113', 'Sarah Smith', '11111111114', '789 Harvard St, Cambridge, MA', '101 Harvard St, Cambridge, MA'),
(3, 'HAR003', 'Emma Davis', 'emma.davis@harvard.edu', '11111111103', '2023-08-01', '2023-2024', 'Active', 1, 'F', '2002-03-10', 'B+', 'Buddhist', 'James Davis', '11111111115', 'Emily Davis', '11111111116', '234 Harvard St, Cambridge, MA', '567 Harvard St, Cambridge, MA'),
(4, 'HAR004', 'Michael Brown', 'michael.brown@harvard.edu', '11111111104', '2023-08-01', '2023-2024', 'Active', 1, 'M', '2001-07-22', 'AB-', 'Muslim', 'William Brown', '11111111117', 'Jessica Brown', '11111111118', '345 Harvard St, Cambridge, MA', '678 Harvard St, Cambridge, MA'),
(5, 'HAR005', 'Sophia Wilson', 'sophia.wilson@harvard.edu', '11111111105', '2023-08-01', '2023-2024', 'Active', 1, 'F', '2002-09-09', 'O-', 'Hindu', 'Thomas Wilson', '11111111119', 'Olivia Wilson', '11111111120', '456 Harvard St, Cambridge, MA', '789 Harvard St, Cambridge, MA');

-- Insert Students for Department of History, Harvard University
INSERT INTO acd_students (id, id_no, NAME, email, cell, admission_date, SESSION, STATUS, department_id, gender, date_of_birth, blood_group, religion, father_name, father_cell, mother_name, mother_cell, present_address, permanent_address)
VALUES 
(6, 'HAR006', 'Olivia Taylor', 'olivia.taylor@harvard.edu', '11111111106', '2023-08-01', '2023-2024', 'Active', 2, 'F', '2002-02-12', 'A+', 'Christian', 'Henry Taylor', '11111111121', 'Rachel Taylor', '11111111122', '567 Harvard St, Cambridge, MA', '890 Harvard St, Cambridge, MA'),
(7, 'HAR007', 'Liam Martinez', 'liam.martinez@harvard.edu', '11111111107', '2023-08-01', '2023-2024', 'Active', 2, 'M', '2001-04-19', 'B-', 'Jewish', 'David Martinez', '11111111123', 'Sophia Martinez', '11111111124', '678 Harvard St, Cambridge, MA', '901 Harvard St, Cambridge, MA'),
(8, 'HAR008', 'Ava Lee', 'ava.lee@harvard.edu', '11111111108', '2023-08-01', '2023-2024', 'Active', 2, 'F', '2002-10-05', 'O+', 'Buddhist', 'Chris Lee', '11111111125', 'Karen Lee', '11111111126', '789 Harvard St, Cambridge, MA', '123 Harvard St, Cambridge, MA'),
(9, 'HAR009', 'Noah Anderson', 'noah.anderson@harvard.edu', '11111111109', '2023-08-01', '2023-2024', 'Active', 2, 'M', '2001-12-30', 'AB+', 'Muslim', 'Gary Anderson', '11111111127', 'Nina Anderson', '11111111128', '890 Harvard St, Cambridge, MA', '234 Harvard St, Cambridge, MA'),
(10, 'HAR010', 'Isabella Thomas', 'isabella.thomas@harvard.edu', '11111111110', '2023-08-01', '2023-2024', 'Active', 2, 'F', '2002-06-18', 'A-', 'Hindu', 'Joseph Thomas', '11111111129', 'Angela Thomas', '11111111130', '901 Harvard St, Cambridge, MA', '345 Harvard St, Cambridge, MA');

-- Insert Students for Department of Computer Science, Harvard University
INSERT INTO acd_students (id, id_no, NAME, email, cell, admission_date, SESSION, STATUS, department_id, gender, date_of_birth, blood_group, religion, father_name, father_cell, mother_name, mother_cell, present_address, permanent_address)
VALUES 
(11, 'HAR011', 'James Wilson', 'james.wilson@harvard.edu', '11111111111', '2023-08-01', '2023-2024', 'Active', 3, 'M', '2001-01-25', 'B+', 'Christian', 'Andrew Wilson', '11111111131', 'Laura Wilson', '11111111132', '123 Harvard St, Cambridge, MA', '456 Harvard St, Cambridge, MA'),
(12, 'HAR012', 'Mia White', 'mia.white@harvard.edu', '11111111112', '2023-08-01', '2023-2024', 'Active', 3, 'F', '2002-04-12', 'O-', 'Jewish', 'Daniel White', '11111111133', 'Jennifer White', '11111111134', '234 Harvard St, Cambridge, MA', '567 Harvard St, Cambridge, MA'),
(13, 'HAR013', 'Benjamin Harris', 'benjamin.harris@harvard.edu', '11111111113', '2023-08-01', '2023-2024', 'Active', 3, 'M', '2001-05-30', 'A+', 'Buddhist', 'Steven Harris', '11111111135', 'Linda Harris', '11111111136', '345 Harvard St, Cambridge, MA', '678 Harvard St, Cambridge, MA'),
(14, 'HAR014', 'Charlotte Martin', 'charlotte.martin@harvard.edu', '11111111114', '2023-08-01', '2023-2024', 'Active', 3, 'F', '2002-07-15', 'B-', 'Muslim', 'Paul Martin', '11111111137', 'Nancy Martin', '11111111138', '456 Harvard St, Cambridge, MA', '789 Harvard St, Cambridge, MA'),
(15, 'HAR015', 'Ethan Lee', 'ethan.lee@harvard.edu', '11111111115', '2023-08-01', '2023-2024', 'Active', 3, 'M', '2001-09-22', 'AB+', 'Hindu', 'George Lee', '11111111139', 'Anna Lee', '11111111140', '567 Harvard St, Cambridge, MA', '890 Harvard St, Cambridge, MA');

-- Insert Students for Department of Electrical Engineering, Harvard University
INSERT INTO acd_students (id, id_no, NAME, email, cell, admission_date, SESSION, STATUS, department_id, gender, date_of_birth, blood_group, religion, father_name, father_cell, mother_name, mother_cell, present_address, permanent_address)
VALUES 
(16, 'HAR016', 'Lucas Robinson', 'lucas.robinson@harvard.edu', '11111111116', '2023-08-01', '2023-2024', 'Active', 4, 'M', '2001-12-11', 'O+', 'Christian', 'Frank Robinson', '11111111141', 'Jessica Robinson', '11111111142', '678 Harvard St, Cambridge, MA', '901 Harvard St, Cambridge, MA'),
(17, 'HAR017', 'Amelia Martinez', 'amelia.martinez@harvard.edu', '11111111117', '2023-08-01', '2023-2024', 'Active', 4, 'F', '2002-06-05', 'A-', 'Jewish', 'Carlos Martinez', '11111111143', 'Maria Martinez', '11111111144', '789 Harvard St, Cambridge, MA', '123 Harvard St, Cambridge, MA'),
(18, 'HAR018', 'Mason Johnson', 'mason.johnson@harvard.edu', '11111111118', '2023-08-01', '2023-2024', 'Active', 4, 'M', '2001-10-10', 'B+', 'Buddhist', 'Richard Johnson', '11111111145', 'Helen Johnson', '11111111146', '890 Harvard St, Cambridge, MA', '234 Harvard St, Cambridge, MA'),
(19, 'HAR019', 'Harper Wilson', 'harper.wilson@harvard.edu', '11111111119', '2023-08-01', '2023-2024', 'Active', 4, 'F', '2002-01-20', 'O-', 'Muslim', 'Ethan Wilson', '11111111147', 'Laura Wilson', '11111111148', '901 Harvard St, Cambridge, MA', '345 Harvard St, Cambridge, MA'),
(20, 'HAR020', 'Daniel Clark', 'daniel.clark@harvard.edu', '11111111120', '2023-08-01', '2023-2024', 'Active', 4, 'M', '2001-11-30', 'AB+', 'Hindu', 'Kevin Clark', '11111111149', 'Sandra Clark', '11111111150', '123 Harvard St, Cambridge, MA', '456 Harvard St, Cambridge, MA');

-- Insert Students for Department of Marketing, Harvard University
INSERT INTO acd_students (id, id_no, NAME, email, cell, admission_date, SESSION, STATUS, department_id, gender, date_of_birth, blood_group, religion, father_name, father_cell, mother_name, mother_cell, present_address, permanent_address)
VALUES 
(21, 'HAR021', 'Ella Brown', 'ella.brown@harvard.edu', '11111111121', '2023-08-01', '2023-2024', 'Active', 5, 'F', '2002-03-25', 'A+', 'Christian', 'George Brown', '11111111151', 'Nancy Brown', '11111111152', '234 Harvard St, Cambridge, MA', '567 Harvard St, Cambridge, MA'),
(22, 'HAR022', 'Jackson Taylor', 'jackson.taylor@harvard.edu', '11111111122', '2023-08-01', '2023-2024', 'Active', 5, 'M', '2001-08-30', 'B-', 'Jewish', 'Thomas Taylor', '11111111153', 'Linda Taylor', '11111111154', '345 Harvard St, Cambridge, MA', '678 Harvard St, Cambridge, MA'),
(23, 'HAR023', 'Mia White', 'mia.white2@harvard.edu', '11111111123', '2023-08-01', '2023-2024', 'Active', 5, 'F', '2002-04-20', 'O+', 'Buddhist', 'Eddie White', '11111111155', 'Sarah White', '11111111156', '456 Harvard St, Cambridge, MA', '789 Harvard St, Cambridge, MA'),
(24, 'HAR024', 'Ethan Harris', 'ethan.harris@harvard.edu', '11111111124', '2023-08-01', '2023-2024', 'Active', 5, 'M', '2001-09-09', 'AB-', 'Muslim', 'Paul Harris', '11111111157', 'Karen Harris', '11111111158', '567 Harvard St, Cambridge, MA', '890 Harvard St, Cambridge, MA'),
(25, 'HAR025', 'Zoe Martin', 'zoe.martin@harvard.edu', '11111111125', '2023-08-01', '2023-2024', 'Active', 5, 'F', '2002-02-28', 'A-', 'Hindu', 'Daniel Martin', '11111111159', 'Julia Martin', '11111111160', '678 Harvard St, Cambridge, MA', '901 Harvard St, Cambridge, MA');

-- Insert Students for Department of Finance, Harvard University
INSERT INTO acd_students (id, id_no, NAME, email, cell, admission_date, SESSION, STATUS, department_id, gender, date_of_birth, blood_group, religion, father_name, father_cell, mother_name, mother_cell, present_address, permanent_address)
VALUES 
(26, 'HAR026', 'Megan Harris', 'megan.harris@harvard.edu', '11111111126', '2023-08-01', '2023-2024', 'Active', 6, 'F', '2002-05-13', 'O+', 'Christian', 'Aaron Harris', '11111111161', 'Emily Harris', '11111111162', '789 Harvard St, Cambridge, MA', '123 Harvard St, Cambridge, MA'),
(27, 'HAR027', 'Oliver Anderson', 'oliver.anderson@harvard.edu', '11111111127', '2023-08-01', '2023-2024', 'Active', 6, 'M', '2001-06-20', 'A-', 'Jewish', 'James Anderson', '11111111163', 'Emma Anderson', '11111111164', '890 Harvard St, Cambridge, MA', '234 Harvard St, Cambridge, MA'),
(28, 'HAR028', 'Avery Young', 'avery.young@harvard.edu', '11111111128', '2023-08-01', '2023-2024', 'Active', 6, 'F', '2002-07-05', 'B+', 'Buddhist', 'Samuel Young', '11111111165', 'Lisa Young', '11111111166', '901 Harvard St, Cambridge, MA', '345 Harvard St, Cambridge, MA'),
(29, 'HAR029', 'Isabella Scott', 'isabella.scott@harvard.edu', '11111111129', '2023-08-01', '2023-2024', 'Active', 6, 'F', '2001-10-10', 'AB-', 'Muslim', 'David Scott', '11111111167', 'Laura Scott', '11111111168', '123 Harvard St, Cambridge, MA', '456 Harvard St, Cambridge, MA'),
(30, 'HAR030', 'Ethan Williams', 'ethan.williams@harvard.edu', '11111111130', '2023-08-01', '2023-2024', 'Active', 6, 'M', '2002-01-15', 'O-', 'Hindu', 'Robert Williams', '11111111169', 'Susan Williams', '11111111170', '234 Harvard St, Cambridge, MA', '567 Harvard St, Cambridge, MA');

-- Insert Students for Department of Literature, Stanford University
INSERT INTO acd_students (id, id_no, NAME, email, cell, admission_date, SESSION, STATUS, department_id, gender, date_of_birth, blood_group, religion, father_name, father_cell, mother_name, mother_cell, present_address, permanent_address)
VALUES 
(31, 'STAN001', 'Olivia Harris', 'olivia.harris@stanford.edu', '21111111101', '2023-08-01', '2023-2024', 'Active', 7, 'F', '2002-03-14', 'A+', 'Christian', 'William Harris', '21111111111', 'Emily Harris', '21111111112', '101 Stanford Ave, Stanford, CA', '202 Stanford Ave, Stanford, CA'),
(32, 'STAN002', 'Liam Walker', 'liam.walker@stanford.edu', '21111111102', '2023-08-01', '2023-2024', 'Active', 7, 'M', '2001-07-21', 'B-', 'Jewish', 'Michael Walker', '21111111113', 'Sara Walker', '21111111114', '303 Stanford Ave, Stanford, CA', '404 Stanford Ave, Stanford, CA'),
(33, 'STAN003', 'Emma Clark', 'emma.clark@stanford.edu', '21111111103', '2023-08-01', '2023-2024', 'Active', 7, 'F', '2002-02-18', 'O+', 'Buddhist', 'James Clark', '21111111115', 'Olivia Clark', '21111111116', '505 Stanford Ave, Stanford, CA', '606 Stanford Ave, Stanford, CA'),
(34, 'STAN004', 'Noah Davis', 'noah.davis@stanford.edu', '21111111104', '2023-08-01', '2023-2024', 'Active', 7, 'M', '2001-10-25', 'AB-', 'Muslim', 'Robert Davis', '21111111117', 'Laura Davis', '21111111118', '707 Stanford Ave, Stanford, CA', '808 Stanford Ave, Stanford, CA'),
(35, 'STAN005', 'Sophia Martinez', 'sophia.martinez@stanford.edu', '21111111105', '2023-08-01', '2023-2024', 'Active', 7, 'F', '2002-06-09', 'A-', 'Hindu', 'Thomas Martinez', '21111111119', 'Nancy Martinez', '21111111120', '909 Stanford Ave, Stanford, CA', '101 Stanford Ave, Stanford, CA');

-- Insert Students for Department of History, Stanford University
INSERT INTO acd_students (id, id_no, NAME, email, cell, admission_date, SESSION, STATUS, department_id, gender, date_of_birth, blood_group, religion, father_name, father_cell, mother_name, mother_cell, present_address, permanent_address)
VALUES 
(36, 'STAN006', 'Ethan Scott', 'ethan.scott@stanford.edu', '21111111106', '2023-08-01', '2023-2024', 'Active', 8, 'M', '2002-01-10', 'O+', 'Christian', 'Paul Scott', '21111111121', 'Angela Scott', '21111111122', '212 Stanford Ave, Stanford, CA', '323 Stanford Ave, Stanford, CA'),
(37, 'STAN007', 'Isabella Lewis', 'isabella.lewis@stanford.edu', '21111111107', '2023-08-01', '2023-2024', 'Active', 8, 'F', '2001-11-25', 'A-', 'Jewish', 'David Lewis', '21111111123', 'Jessica Lewis', '21111111124', '434 Stanford Ave, Stanford, CA', '545 Stanford Ave, Stanford, CA'),
(38, 'STAN008', 'Mason Anderson', 'mason.anderson@stanford.edu', '21111111108', '2023-08-01', '2023-2024', 'Active', 8, 'M', '2002-07-15', 'B+', 'Buddhist', 'Gary Anderson', '21111111125', 'Sara Anderson', '21111111126', '656 Stanford Ave, Stanford, CA', '767 Stanford Ave, Stanford, CA'),
(39, 'STAN009', 'Ava Johnson', 'ava.johnson@stanford.edu', '21111111109', '2023-08-01', '2023-2024', 'Active', 8, 'F', '2001-05-05', 'AB-', 'Muslim', 'James Johnson', '21111111127', 'Laura Johnson', '21111111128', '878 Stanford Ave, Stanford, CA', '989 Stanford Ave, Stanford, CA'),
(40, 'STAN010', 'Oliver Brown', 'oliver.brown@stanford.edu', '21111111110', '2023-08-01', '2023-2024', 'Active', 8, 'M', '2002-08-20', 'O-', 'Hindu', 'William Brown', '21111111129', 'Nancy Brown', '21111111130', '101 Stanford Ave, Stanford, CA', '212 Stanford Ave, Stanford, CA');

-- Insert Students for Department of Computer Science, Stanford University
INSERT INTO acd_students (id, id_no, NAME, email, cell, admission_date, SESSION, STATUS, department_id, gender, date_of_birth, blood_group, religion, father_name, father_cell, mother_name, mother_cell, present_address, permanent_address)
VALUES 
(41, 'STAN011', 'James Miller', 'james.miller@stanford.edu', '21111111111', '2023-08-01', '2023-2024', 'Active', 9, 'M', '2001-03-10', 'A+', 'Christian', 'Thomas Miller', '21111111131', 'Jessica Miller', '21111111132', '323 Stanford Ave, Stanford, CA', '434 Stanford Ave, Stanford, CA'),
(42, 'STAN012', 'Mia Wilson', 'mia.wilson@stanford.edu', '21111111112', '2023-08-01', '2023-2024', 'Active', 9, 'F', '2002-04-25', 'B-', 'Jewish', 'Edward Wilson', '21111111133', 'Emily Wilson', '21111111134', '545 Stanford Ave, Stanford, CA', '656 Stanford Ave, Stanford, CA'),
(43, 'STAN013', 'Benjamin Davis', 'benjamin.davis@stanford.edu', '21111111113', '2023-08-01', '2023-2024', 'Active', 9, 'M', '2001-11-15', 'O+', 'Buddhist', 'Daniel Davis', '21111111135', 'Sarah Davis', '21111111136', '767 Stanford Ave, Stanford, CA', '878 Stanford Ave, Stanford, CA'),
(44, 'STAN014', 'Charlotte Green', 'charlotte.green@stanford.edu', '21111111114', '2023-08-01', '2023-2024', 'Active', 9, 'F', '2002-06-10', 'A-', 'Muslim', 'Matthew Green', '21111111137', 'Laura Green', '21111111138', '989 Stanford Ave, Stanford, CA', '101 Stanford Ave, Stanford, CA'),
(45, 'STAN015', 'Ethan Martinez', 'ethan.martinez@stanford.edu', '21111111115', '2023-08-01', '2023-2024', 'Active', 9, 'M', '2001-07-01', 'AB+', 'Hindu', 'George Martinez', '21111111139', 'Sandra Martinez', '21111111140', '212 Stanford Ave, Stanford, CA', '323 Stanford Ave, Stanford, CA');

-- Insert Students for Department of Electrical Engineering, Stanford University
INSERT INTO acd_students (id, id_no, NAME, email, cell, admission_date, SESSION, STATUS, department_id, gender, date_of_birth, blood_group, religion, father_name, father_cell, mother_name, mother_cell, present_address, permanent_address)
VALUES 
(46, 'STAN016', 'Lucas Harris', 'lucas.harris@stanford.edu', '21111111116', '2023-08-01', '2023-2024', 'Active', 10, 'M', '2002-08-20', 'B+', 'Christian', 'William Harris', '21111111141', 'Emily Harris', '21111111142', '434 Stanford Ave, Stanford, CA', '545 Stanford Ave, Stanford, CA'),
(47, 'STAN017', 'Amelia Lewis', 'amelia.lewis@stanford.edu', '21111111117', '2023-08-01', '2023-2024', 'Active', 10, 'F', '2001-05-10', 'A-', 'Jewish', 'David Lewis', '21111111143', 'Jessica Lewis', '21111111144', '656 Stanford Ave, Stanford, CA', '767 Stanford Ave, Stanford, CA'),
(48, 'STAN018', 'Oliver White', 'oliver.white@stanford.edu', '21111111118', '2023-08-01', '2023-2024', 'Active', 10, 'M', '2002-09-12', 'O-', 'Buddhist', 'Michael White', '21111111145', 'Laura White', '21111111146', '878 Stanford Ave, Stanford, CA', '989 Stanford Ave, Stanford, CA'),
(49, 'STAN019', 'Harper Johnson', 'harper.johnson@stanford.edu', '21111111119', '2023-08-01', '2023-2024', 'Active', 10, 'F', '2001-02-05', 'AB+', 'Muslim', 'Robert Johnson', '21111111147', 'Emily Johnson', '21111111148', '101 Stanford Ave, Stanford, CA', '212 Stanford Ave, Stanford, CA'),
(50, 'STAN020', 'Mia Clark', 'mia.clark@stanford.edu', '21111111120', '2023-08-01', '2023-2024', 'Active', 10, 'F', '2002-11-30', 'O+', 'Hindu', 'Thomas Clark', '21111111149', 'Nancy Clark', '21111111150', '323 Stanford Ave, Stanford, CA', '434 Stanford Ave, Stanford, CA');









-- Courses for Department of Literature
INSERT INTO acd_courses (id, NAME, CODE, credits, fee, department_id, description)
VALUES
(1, 'Introduction to Literature', 'LIT101', 3, 500.00, 7, 'An overview of classic and contemporary literature with an emphasis on critical analysis.'),
(2, 'Modern American Fiction', 'LIT202', 4, 600.00, 7, 'Study of significant works of fiction in American literature from the 20th century to the present.'),
(3, 'Shakespearean Drama', 'LIT303', 3, 550.00, 7, 'Detailed examination of the plays of William Shakespeare, focusing on their themes, characters, and historical context.');

-- Assign teachers to courses for Department of Literature
INSERT INTO course_teachers (course_id, teacher_id) VALUES
(1, 1), (1, 2),
(2, 1),
(3, 2);
-- Courses for Department of History
INSERT INTO acd_courses (id, NAME, CODE, credits, fee, department_id, description)
VALUES
(4, 'World History: Ancient to Modern', 'HIS101', 3, 550.00, 8, 'A comprehensive survey of world history from ancient civilizations to the modern era.'),
(5, 'European History: 19th Century', 'HIS202', 4, 650.00, 8, 'Exploration of major events, movements, and figures in European history during the 19th century.'),
(6, 'American Civil War', 'HIS303', 3, 600.00, 8, 'In-depth analysis of the American Civil War, including causes, major battles, and social impacts.');

-- Assign teachers to courses for Department of History
INSERT INTO course_teachers (course_id, teacher_id) VALUES
(4, 3), (4, 4),
(5, 3),
(6, 4);
-- Courses for Department of Computer Science
INSERT INTO acd_courses (id, NAME, CODE, credits, fee, department_id, description)
VALUES
(7, 'Introduction to Programming', 'CS101', 4, 700.00, 9, 'Fundamentals of programming using a high-level language, including syntax, control structures, and data handling.'),
(8, 'Data Structures and Algorithms', 'CS202', 4, 750.00, 9, 'Study of data structures and algorithms with a focus on efficiency, complexity, and practical application.'),
(9, 'Software Engineering Principles', 'CS303', 3, 800.00, 9, 'Introduction to software engineering principles, including design, testing, and project management.');

-- Assign teachers to courses for Department of Computer Science
INSERT INTO course_teachers (course_id, teacher_id) VALUES
(7, 5), (7, 6),
(8, 5),
(9, 6);
-- Courses for Department of Electrical Engineering
INSERT INTO acd_courses (id, NAME, CODE, credits, fee, department_id, description)
VALUES
(10, 'Circuit Analysis', 'EE101', 4, 700.00, 10, 'Fundamentals of circuit analysis, including Ohm\'s law, Kirchhoff\'s laws, and various network theorems.'),
(11, 'Digital Systems Design', 'EE202', 4, 750.00, 10, 'Design and analysis of digital systems, including logic gates, flip-flops, and combinational circuits.'),
(12, 'Electromagnetics', 'EE303', 3, 800.00, 10, 'Introduction to electromagnetics, including Maxwell\'s equations, wave propagation, and antenna theory.');

-- Assign teachers to courses for Department of Electrical Engineering
INSERT INTO course_teachers (course_id, teacher_id) VALUES
(10, 7), (10, 8),
(11, 7),
(12, 8);
-- Courses for Department of Marketing
INSERT INTO acd_courses (id, NAME, CODE, credits, fee, department_id, description)
VALUES
(13, 'Principles of Marketing', 'MKT101', 3, 600.00, 11, 'An introduction to marketing concepts, strategies, and practices with a focus on consumer behavior.'),
(14, 'Digital Marketing Strategies', 'MKT202', 4, 650.00, 11, 'Exploration of digital marketing tools and strategies including SEO, SEM, and social media marketing.'),
(15, 'Market Research and Analytics', 'MKT303', 3, 700.00, 11, 'Study of market research methods and analytical techniques for making data-driven marketing decisions.');

-- Assign teachers to courses for Department of Marketing
INSERT INTO course_teachers (course_id, teacher_id) VALUES
(13, 9), (13, 10),
(14, 9),
(15, 10);
-- Courses for Department of Finance
INSERT INTO acd_courses (id, NAME, CODE, credits, fee, department_id, description)
VALUES
(16, 'Introduction to Finance', 'FIN101', 3, 700.00, 12, 'Fundamentals of finance including financial statements, time value of money, and basic investment principles.'),
(17, 'Corporate Finance', 'FIN202', 4, 750.00, 12, 'Study of corporate finance topics including capital budgeting, risk management, and financial analysis.'),
(18, 'Investment Analysis', 'FIN303', 3, 800.00, 12, 'Analysis of investment opportunities and portfolio management, with emphasis on stock, bond, and real estate investments.');

-- Assign teachers to courses for Department of Finance
INSERT INTO course_teachers (course_id, teacher_id) VALUES
(16, 11), (16, 12),
(17, 11),
(18, 12);










-- Enrollments for Principles of Marketing (MKT101)
INSERT INTO acd_enrollments (id, enrollment_date, fee_paid, STATUS, student_id) VALUES
(1, '2024-08-01', 600.00, 'Active', 1),
(2, '2024-08-01', 600.00, 'Active', 2);

INSERT INTO enrollment_courses (enrollment_id, course_id) VALUES
(1, 13),
(2, 13);

-- Enrollments for Digital Marketing Strategies (MKT202)
INSERT INTO acd_enrollments (id, enrollment_date, fee_paid, STATUS, student_id) VALUES
(3, '2024-08-01', 650.00, 'Active', 3),
(4, '2024-08-01', 650.00, 'Active', 4);

INSERT INTO enrollment_courses (enrollment_id, course_id) VALUES
(3, 14),
(4, 14);

-- Enrollments for Market Research and Analytics (MKT303)
INSERT INTO acd_enrollments (id, enrollment_date, fee_paid, STATUS, student_id) VALUES
(5, '2024-08-01', 700.00, 'Active', 5),
(6, '2024-08-01', 700.00, 'Active', 6);

INSERT INTO enrollment_courses (enrollment_id, course_id) VALUES
(5, 15),
(6, 15);

-- Enrollments for Introduction to Finance (FIN101)
INSERT INTO acd_enrollments (id, enrollment_date, fee_paid, STATUS, student_id) VALUES
(7, '2024-08-01', 700.00, 'Active', 7),
(8, '2024-08-01', 700.00, 'Active', 8);

INSERT INTO enrollment_courses (enrollment_id, course_id) VALUES
(7, 16),
(8, 16);

-- Enrollments for Corporate Finance (FIN202)
INSERT INTO acd_enrollments (id, enrollment_date, fee_paid, STATUS, student_id) VALUES
(9, '2024-08-01', 750.00, 'Active', 9),
(10, '2024-08-01', 750.00, 'Active', 10);

INSERT INTO enrollment_courses (enrollment_id, course_id) VALUES
(9, 17),
(10, 17);

-- Enrollments for Investment Analysis (FIN303)
INSERT INTO acd_enrollments (id, enrollment_date, fee_paid, STATUS, student_id) VALUES
(11, '2024-08-01', 800.00, 'Active', 11),
(12, '2024-08-01', 800.00, 'Active', 12);

INSERT INTO enrollment_courses (enrollment_id, course_id) VALUES
(11, 18),
(12, 18);
