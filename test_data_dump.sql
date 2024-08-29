/*
SQLyog Professional v13.1.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - ums
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ums` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `ums`;

/*Table structure for table `acd_courses` */

DROP TABLE IF EXISTS `acd_courses`;

CREATE TABLE `acd_courses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) NOT NULL,
  `credit` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `name` varchar(60) NOT NULL,
  `semester_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt1hlh6tdy3sg1y97q8fvcrqu0` (`semester_id`),
  CONSTRAINT `FKt1hlh6tdy3sg1y97q8fvcrqu0` FOREIGN KEY (`semester_id`) REFERENCES `acd_semesters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `acd_courses` */

insert  into `acd_courses`(`id`,`code`,`credit`,`description`,`name`,`semester_id`) values 
(1,'CS101',3,'Basics of computer science, programming concepts, and algorithms.','Introduction to Computer Science',1),
(2,'MATH101',4,'Fundamentals of calculus including limits, derivatives, and integrals.','Calculus I',1),
(3,'SOC101',3,'Overview of sociology and its major concepts.','Introduction to Sociology',1),
(4,'CS102',3,'Advanced concepts in data structures including trees, graphs, and hash tables.','Data Structures',2),
(5,'MATH102',4,'Continuation of Calculus I, focusing on advanced integration techniques.','Calculus II',2),
(6,'PSY101',3,'Basics of psychology including behavioral theories and cognitive processes.','Introduction to Psychology',2),
(7,'CS201',3,'Study of algorithms, their efficiency, and complexity analysis.','Algorithms',3),
(8,'MATH201',4,'Introduction to linear algebra, matrices, and vector spaces.','Linear Algebra',3),
(9,'ECO101',3,'Fundamental concepts of micro and macroeconomics.','Principles of Economics',3),
(10,'CS202',3,'Overview of operating system concepts, processes, and memory management.','Operating Systems',4),
(11,'MATH202',4,'Basics of probability theory and statistical methods.','Probability and Statistics',4),
(12,'PHIL101',3,'Fundamental philosophical concepts and theories.','Introduction to Philosophy',4);

/*Table structure for table `acd_departments` */

DROP TABLE IF EXISTS `acd_departments`;

CREATE TABLE `acd_departments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `faculty_id` bigint(20) NOT NULL,
  `head_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK1h6ibe00j3dj0v0h5ax8t75lx` (`head_id`),
  KEY `FKre2bf5hu2ks201qaaxo7q99qc` (`faculty_id`),
  CONSTRAINT `FK1an74rcvosng6shjvs4yhwb4s` FOREIGN KEY (`head_id`) REFERENCES `ad_users` (`id`),
  CONSTRAINT `FKre2bf5hu2ks201qaaxo7q99qc` FOREIGN KEY (`faculty_id`) REFERENCES `acd_faculties` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `acd_departments` */

insert  into `acd_departments`(`id`,`name`,`faculty_id`,`head_id`) values 
(1,'Department of Computer Engineering',1,4),
(2,'Department of Physics',2,5),
(3,'Department of Marketing',3,6),
(4,'Department of English Literature',4,10),
(5,'Department of Sociology',5,11),
(6,'Department of Constitutional Law',6,12);

/*Table structure for table `acd_faculties` */

DROP TABLE IF EXISTS `acd_faculties`;

CREATE TABLE `acd_faculties` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contact` varchar(11) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `dean_id` bigint(20) DEFAULT NULL,
  `university_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKmvsg74hsa47a0jt59kw4jf6p2` (`contact`),
  UNIQUE KEY `UKso3j02mbqysx2xn5b53k91jry` (`email`),
  UNIQUE KEY `UKx31kbk5xvg7px6e17o8k79lb` (`dean_id`),
  KEY `FKtolvus2g3bkxy2eq3xwo0w83a` (`university_id`),
  CONSTRAINT `FKo915s62lqfemwjd03uc9g6xok` FOREIGN KEY (`dean_id`) REFERENCES `ad_users` (`id`),
  CONSTRAINT `FKtolvus2g3bkxy2eq3xwo0w83a` FOREIGN KEY (`university_id`) REFERENCES `ad_universities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `acd_faculties` */

insert  into `acd_faculties`(`id`,`contact`,`email`,`name`,`dean_id`,`university_id`) values 
(1,'01700001001','eng.faculty.pstu@email.com','Faculty of Engineering',4,1),
(2,'01700001002','sci.faculty.pstu@email.com','Faculty of Science',5,1),
(3,'01700001003','bus.faculty.pstu@email.com','Faculty of Business',6,1),
(4,'01900001001','arts.faculty.du@email.com','Faculty of Arts',10,2),
(5,'01900001002','soc.faculty.du@email.com','Faculty of Social Sciences',11,2),
(6,'01900001003','law.faculty.du@email.com','Faculty of Law',12,2);

/*Table structure for table `acd_fees` */

DROP TABLE IF EXISTS `acd_fees`;

CREATE TABLE `acd_fees` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `type` enum('HALL','SEMESTER') NOT NULL,
  `semester_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKslfyhqflcp1ee8kmsq3ttrndn` (`semester_id`),
  CONSTRAINT `FKslfyhqflcp1ee8kmsq3ttrndn` FOREIGN KEY (`semester_id`) REFERENCES `acd_semesters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `acd_fees` */

insert  into `acd_fees`(`id`,`amount`,`type`,`semester_id`) values 
(1,5000,'SEMESTER',1),
(2,2000,'HALL',1),
(3,5000,'SEMESTER',2),
(4,2000,'HALL',2),
(5,5000,'SEMESTER',3),
(6,2000,'HALL',3),
(7,5000,'SEMESTER',4),
(8,2000,'HALL',4),
(9,5000,'SEMESTER',5),
(10,2000,'HALL',5),
(11,5000,'SEMESTER',6),
(12,2000,'HALL',6);

/*Table structure for table `acd_programs` */

DROP TABLE IF EXISTS `acd_programs`;

CREATE TABLE `acd_programs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `duration_year` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKry1t9fs69jh5mol7s8wk9gler` (`department_id`),
  CONSTRAINT `FKry1t9fs69jh5mol7s8wk9gler` FOREIGN KEY (`department_id`) REFERENCES `acd_departments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `acd_programs` */

insert  into `acd_programs`(`id`,`duration_year`,`name`,`department_id`) values 
(1,4,'BSc in Computer Engineering (Honours)',1),
(2,2,'MSc in Computer Engineering',1),
(3,4,'BSc in Physics (Honours)',2),
(4,2,'MSc in Physics',2),
(5,4,'BBA (Honours)',3),
(6,2,'MBA',3),
(7,4,'BA in English Literature (Honours)',4),
(8,2,'MA in English Literature',4),
(9,4,'BA in Sociology (Honours)',5),
(10,2,'MA in Sociology',5),
(11,4,'LLB (Honours)',6),
(12,2,'LLM',6);

/*Table structure for table `acd_semesters` */

DROP TABLE IF EXISTS `acd_semesters`;

CREATE TABLE `acd_semesters` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(4) NOT NULL,
  `name` varchar(100) NOT NULL,
  `session` varchar(9) NOT NULL,
  `program_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg4jai87dr6w5u23m79r27e3br` (`program_id`),
  CONSTRAINT `FKg4jai87dr6w5u23m79r27e3br` FOREIGN KEY (`program_id`) REFERENCES `acd_programs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `acd_semesters` */

insert  into `acd_semesters`(`id`,`code`,`name`,`session`,`program_id`) values 
(1,'L1S1','Semester 1','2022-23',1),
(2,'L1S2','Semester 2','2022-23',1),
(3,'L2S1','Semester 3','2022-23',1),
(4,'L2S2','Semester 4','2022-23',1),
(5,'L1S1','Semester 1','2022-23',2),
(6,'L1S2','Semester 2','2022-23',2),
(7,'L2S1','Semester 3','2022-23',2),
(8,'L2S2','Semester 4','2022-23',2),
(9,'L1S1','Semester 1','2022-23',3),
(10,'L1S2','Semester 2','2022-23',3),
(11,'L2S1','Semester 3','2022-23',3),
(12,'L2S2','Semester 4','2022-23',3),
(13,'L1S1','Semester 1','2022-23',4),
(14,'L1S2','Semester 2','2022-23',4),
(15,'L2S1','Semester 3','2022-23',4),
(16,'L2S2','Semester 4','2022-23',4),
(17,'L1S1','Semester 1','2023-24',1),
(18,'L1S2','Semester 2','2023-24',1),
(19,'L1S1','Semester 1','2023-24',2),
(20,'L1S2','Semester 2','2023-24',2),
(21,'L1S1','Semester 1','2023-24',3),
(22,'L1S2','Semester 2','2023-24',3),
(23,'L1S1','Semester 1','2023-24',4),
(24,'L1S2','Semester 2','2023-24',4);

/*Table structure for table `acd_students` */

DROP TABLE IF EXISTS `acd_students`;

CREATE TABLE `acd_students` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admission_date` date NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `cell` varchar(11) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `father_cell` varchar(11) DEFAULT NULL,
  `father_name` varchar(100) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `id_no` varchar(255) NOT NULL,
  `mother_cell` varchar(11) DEFAULT NULL,
  `mother_name` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `permanent_address` varchar(400) DEFAULT NULL,
  `present_address` varchar(400) DEFAULT NULL,
  `religion` varchar(255) DEFAULT NULL,
  `session` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `semester_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKl5u69ga9hox76p1m1gk2t6f9j` (`email`),
  UNIQUE KEY `UKs5ijnt9v4yi0j4o1m72gkawc3` (`id_no`),
  KEY `FKonklf4rcdjtb6xp0be52gelim` (`semester_id`),
  CONSTRAINT `FKonklf4rcdjtb6xp0be52gelim` FOREIGN KEY (`semester_id`) REFERENCES `acd_semesters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `acd_students` */

insert  into `acd_students`(`id`,`admission_date`,`avatar`,`blood_group`,`cell`,`date_of_birth`,`email`,`father_cell`,`father_name`,`gender`,`id_no`,`mother_cell`,`mother_name`,`name`,`permanent_address`,`present_address`,`religion`,`session`,`status`,`semester_id`) values 
(1,'2022-01-15',NULL,'A+','01900000001','2002-05-15','john.doe@example.com','01900000011','Michael Doe','Male','23001','01900000021','Jane Doe','John Doe','456 Oak Avenue','123 Elm Street','Christian','2022-23','Active',1),
(2,'2022-01-16',NULL,'B-','01900000002','2003-06-22','jane.smith@example.com','01900000012','Robert Smith','Female','23002','01900000022','Emily Smith','Jane Smith','101 Maple Avenue','789 Pine Street','Muslim','2022-23','Active',1),
(3,'2022-01-17',NULL,'O+','01900000003','2002-07-30','michael.johnson@example.com','01900000013','David Johnson','Male','23003','01900000023','Linda Johnson','Michael Johnson','567 Cedar Avenue','234 Birch Street','Hindu','2022-23','Active',1),
(4,'2022-01-18',NULL,'AB+','01900000004','2001-08-11','emily.davis@example.com','01900000014','William Davis','Female','23004','01900000024','Sarah Davis','Emily Davis','678 Spruce Avenue','345 Fir Street','Buddhist','2022-23','Active',1),
(5,'2022-01-19',NULL,'A-','01900000005','2002-09-25','james.brown@example.com','01900000015','Charles Brown','Male','23005','01900000025','Laura Brown','James Brown','789 Willow Avenue','456 Maple Street','Sikh','2022-23','Active',1),
(6,'2022-02-01',NULL,'B+','01900000006','2002-10-05','alice.green@example.com','01900000016','James Green','Female','23006','01900000026','Olivia Green','Alice Green','890 Pine Avenue','567 Oak Street','Christian','2022-23','Active',2),
(7,'2022-02-02',NULL,'O-','01900000007','2003-11-12','robert.wilson@example.com','01900000017','Ethan Wilson','Male','23007','01900000027','Sophia Wilson','Robert Wilson','901 Cedar Avenue','678 Pine Street','Muslim','2022-23','Active',2),
(8,'2022-02-03',NULL,'A-','01900000008','2002-12-20','sophia.martinez@example.com','01900000018','Daniel Martinez','Female','23008','01900000028','Emma Martinez','Sophia Martinez','912 Birch Avenue','789 Maple Street','Hindu','2022-23','Active',2),
(9,'2022-02-04',NULL,'B+','01900000009','2001-01-15','daniel.garcia@example.com','01900000019','Mark Garcia','Male','23009','01900000029','Alice Garcia','Daniel Garcia','123 Willow Avenue','890 Elm Street','Buddhist','2022-23','Active',2),
(10,'2022-02-05',NULL,'AB-','01900000010','2002-02-25','rachel.lee@example.com','01900000020','Andrew Lee','Female','23010','01900000030','Linda Lee','Rachel Lee','234 Pine Avenue','901 Oak Street','Sikh','2022-23','Active',2),
(11,'2022-03-01',NULL,'A+','01900000011','2001-03-05','megan.taylor@example.com','01900000021','George Taylor','Female','23011','01900000031','Nancy Taylor','Megan Taylor','345 Oak Avenue','234 Elm Street','Christian','2022-23','Active',3),
(12,'2022-03-02',NULL,'B-','01900000012','2002-04-15','william.anderson@example.com','01900000022','Frank Anderson','Male','23012','01900000032','Karen Anderson','William Anderson','456 Pine Avenue','345 Maple Street','Muslim','2022-23','Active',3),
(13,'2022-03-03',NULL,'O+','01900000013','2002-05-20','emily.thomas@example.com','01900000023','Samuel Thomas','Female','23013','01900000033','Megan Thomas','Emily Thomas','567 Cedar Avenue','456 Oak Street','Hindu','2022-23','Active',3),
(14,'2022-03-04',NULL,'AB+','01900000014','2003-06-10','michael.lewis@example.com','01900000024','Mark Lewis','Male','23014','01900000034','Alice Lewis','Michael Lewis','678 Birch Avenue','567 Pine Street','Buddhist','2022-23','Active',3),
(15,'2022-03-05',NULL,'A-','01900000015','2002-07-25','olivia.robinson@example.com','01900000025','Philip Robinson','Female','23015','01900000035','Sandra Robinson','Olivia Robinson','789 Willow Avenue','678 Cedar Street','Sikh','2022-23','Active',3),
(16,'2022-04-01',NULL,'B+','01900000016','2002-08-05','ethan.walker@example.com','01900000026','Brian Walker','Male','23016','01900000036','Emily Walker','Ethan Walker','890 Oak Avenue','789 Elm Street','Christian','2022-23','Active',4),
(17,'2022-04-02',NULL,'O-','01900000017','2001-09-10','isabella.hall@example.com','01900000027','Albert Hall','Female','23017','01900000037','Alice Hall','Isabella Hall','901 Maple Avenue','890 Pine Street','Muslim','2022-23','Active',4),
(18,'2022-04-03',NULL,'A-','01900000018','2003-10-20','aiden.young@example.com','01900000028','Henry Young','Male','23018','01900000038','Lily Young','Aiden Young','912 Cedar Avenue','901 Oak Street','Hindu','2022-23','Active',4),
(19,'2022-04-04',NULL,'AB-','01900000019','2002-11-15','mia.hernandez@example.com','01900000029','David Hernandez','Female','23019','01900000039','Sarah Hernandez','Mia Hernandez','123 Willow Avenue','012 Birch Street','Buddhist','2022-23','Active',4),
(20,'2022-04-05',NULL,'B+','01900000020','2002-12-25','logan.martinez@example.com','01900000030','Charles Martinez','Male','23020','01900000040','Laura Martinez','Logan Martinez','234 Oak Avenue','123 Maple Street','Sikh','2022-23','Active',4),
(21,'2023-01-05',NULL,'O+','01900000021','2003-01-10','sophia.king@example.com','01900000031','James King','Female','24001','01900000041','Anna King','Sophia King','345 Oak Avenue','234 Elm Street','Christian','2023-24','Active',5),
(22,'2023-01-06',NULL,'A-','01900000022','2002-02-20','liam.scott@example.com','01900000032','Robert Scott','Male','24002','01900000042','Olivia Scott','Liam Scott','456 Pine Avenue','345 Maple Street','Muslim','2023-24','Active',5),
(23,'2023-01-07',NULL,'B+','01900000023','2002-03-25','mia.adams@example.com','01900000033','Daniel Adams','Female','24003','01900000043','Emma Adams','Mia Adams','567 Cedar Avenue','456 Oak Street','Hindu','2023-24','Active',5),
(24,'2023-01-08',NULL,'AB-','01900000024','2003-04-30','noah.carter@example.com','01900000034','Paul Carter','Male','24004','01900000044','Sophia Carter','Noah Carter','678 Birch Avenue','567 Pine Street','Buddhist','2023-24','Active',5),
(25,'2023-01-09',NULL,'A+','01900000025','2002-05-15','emma.turner@example.com','01900000035','Philip Turner','Female','24005','01900000045','Laura Turner','Emma Turner','789 Willow Avenue','678 Cedar Street','Sikh','2023-24','Active',5),
(26,'2023-02-01',NULL,'B-','01900000026','2002-06-05','lucas.nelson@example.com','01900000036','Andrew Nelson','Male','24006','01900000046','Lily Nelson','Lucas Nelson','890 Oak Avenue','789 Elm Street','Christian','2023-24','Active',6),
(27,'2023-02-02',NULL,'O+','01900000027','2001-07-10','olivia.parker@example.com','01900000037','David Parker','Female','24007','01900000047','Sarah Parker','Olivia Parker','901 Maple Avenue','890 Pine Street','Muslim','2023-24','Active',6),
(28,'2023-02-03',NULL,'A-','01900000028','2002-08-15','ethan.roberts@example.com','01900000038','James Roberts','Male','24008','01900000048','Emma Roberts','Ethan Roberts','912 Cedar Avenue','901 Oak Street','Hindu','2023-24','Active',6),
(29,'2023-02-04',NULL,'AB+','01900000029','2003-09-20','ava.campbell@example.com','01900000039','Robert Campbell','Female','24009','01900000049','Mia Campbell','Ava Campbell','123 Willow Avenue','012 Birch Street','Buddhist','2023-24','Active',6),
(30,'2023-02-05',NULL,'B+','01900000030','2002-10-25','mason.mitchell@example.com','01900000040','George Mitchell','Male','24010','01900000050','Laura Mitchell','Mason Mitchell','234 Oak Avenue','123 Maple Street','Sikh','2023-24','Active',6);

/*Table structure for table `ad_universities` */

DROP TABLE IF EXISTS `ad_universities`;

CREATE TABLE `ad_universities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `contact` varchar(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `established_year` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKghrx3c5fvxhg0jgbo7spq8ckw` (`contact`),
  UNIQUE KEY `UKe0mh2w988l7xfrjwgj8amohg5` (`email`),
  UNIQUE KEY `UK7qtoi1njyemr3j0y01y2a95ke` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `ad_universities` */

insert  into `ad_universities`(`id`,`address`,`contact`,`email`,`established_year`,`name`) values 
(1,'Patuakhali','01700000000','pstu@email.com',2000,'Patuakhali Science & Technology University'),
(2,'Dhaka','01900000000','du@email.com',1970,'Dhaka University');

/*Table structure for table `ad_users` */

DROP TABLE IF EXISTS `ad_users`;

CREATE TABLE `ad_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `cell` varchar(11) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `joining_date` date DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `role` enum('ROLE_ADMIN','ROLE_STAFF','ROLE_TEACHER') NOT NULL,
  `status` varchar(255) NOT NULL,
  `university_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6miwknughpcf5ux8q852vofb2` (`cell`),
  UNIQUE KEY `UK8ldcumy42y3px57a0wjbuofk6` (`email`),
  KEY `FK3twl7cieh7wo0svjyy4tcvt1c` (`university_id`),
  CONSTRAINT `FK3twl7cieh7wo0svjyy4tcvt1c` FOREIGN KEY (`university_id`) REFERENCES `ad_universities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `ad_users` */

insert  into `ad_users`(`id`,`address`,`avatar`,`blood_group`,`cell`,`date_of_birth`,`email`,`gender`,`joining_date`,`name`,`role`,`status`,`university_id`) values 
(1,'123 University Rd, Patuakhali',NULL,'O+','01700000001','1970-05-15','john.smith@email.com','Male','2024-01-01','Dr. John Smith','ROLE_ADMIN','ACTIVE',1),
(2,'456 University St, Patuakhali',NULL,'A-','01700000002','1985-09-22','alice.johnson@email.com','Female','2024-02-01','Alice Johnson','ROLE_STAFF','ACTIVE',1),
(3,'789 University Ave, Patuakhali',NULL,'B+','01700000003','1990-12-05','robert.brown@email.com','Male','2024-03-01','Robert Brown','ROLE_STAFF','ACTIVE',1),
(4,'101 University Blvd, Patuakhali',NULL,'AB-','01700000004','1980-11-11','emily.davis@email.com','Female','2024-04-01','Emily Davis','ROLE_TEACHER','ACTIVE',1),
(5,'202 University Park, Patuakhali',NULL,'O-','01700000005','1988-04-12','michael.wilson@email.com','Male','2024-05-01','Michael Wilson','ROLE_TEACHER','ACTIVE',1),
(6,'303 University Ln, Patuakhali',NULL,'B-','01700000006','1992-07-30','jessica.taylor@email.com','Female','2024-06-01','Jessica Taylor','ROLE_TEACHER','ACTIVE',1),
(7,'12 Main Rd, Dhaka',NULL,'A+','01900000001','1968-03-10','sarah.green@email.com','Female','2024-01-15','Dr. Sarah Green','ROLE_ADMIN','ACTIVE',2),
(8,'34 Main St, Dhaka',NULL,'O+','01900000002','1982-08-20','david.lee@email.com','Male','2024-02-15','David Lee','ROLE_STAFF','ACTIVE',2),
(9,'56 Main Ave, Dhaka',NULL,'AB+','01900000003','1987-11-05','laura.martinez@email.com','Female','2024-03-15','Laura Martinez','ROLE_STAFF','ACTIVE',2),
(10,'78 Main Blvd, Dhaka',NULL,'B-','01900000004','1975-04-22','james.clark@email.com','Male','2024-04-15','James Clark','ROLE_TEACHER','ACTIVE',2),
(11,'90 Main Ln, Dhaka',NULL,'O+','01900000005','1983-12-10','sophia.white@email.com','Female','2024-05-15','Sophia White','ROLE_TEACHER','ACTIVE',2),
(12,'101 Main Park, Dhaka',NULL,'A-','01900000006','1990-06-01','william.harris@email.com','Male','2024-06-15','William Harris','ROLE_TEACHER','ACTIVE',2),
(13,'404 University St, Patuakhali',NULL,'AB+','01700000007','1985-02-10','anna.williams@email.com','Female','2024-07-01','Anna Williams','ROLE_TEACHER','ACTIVE',1),
(14,'505 University Ave, Patuakhali',NULL,'B+','01700000008','1991-10-15','liam.adams@email.com','Male','2024-08-01','Liam Adams','ROLE_TEACHER','ACTIVE',1),
(15,'606 University Blvd, Patuakhali',NULL,'O+','01700000009','1988-06-22','olivia.scott@email.com','Female','2024-09-01','Olivia Scott','ROLE_TEACHER','ACTIVE',1),
(16,'707 University Ln, Patuakhali',NULL,'A-','01700000010','1994-04-30','ethan.harris@email.com','Male','2024-10-01','Ethan Harris','ROLE_TEACHER','ACTIVE',1),
(17,'808 University Park, Patuakhali',NULL,'B-','01700000011','1992-01-12','sophia.miller@email.com','Female','2024-11-01','Sophia Miller','ROLE_TEACHER','ACTIVE',1),
(18,'909 University Dr, Patuakhali',NULL,'AB-','01700000012','1990-07-08','jacob.wilson@email.com','Male','2024-12-01','Jacob Wilson','ROLE_TEACHER','ACTIVE',1),
(19,'112 Main Rd, Dhaka',NULL,'O-','01900000007','1983-05-16','ava.brown@email.com','Female','2024-07-15','Ava Brown','ROLE_TEACHER','ACTIVE',2),
(20,'223 Main St, Dhaka',NULL,'A+','01900000008','1989-09-24','mason.green@email.com','Male','2024-08-15','Mason Green','ROLE_TEACHER','ACTIVE',2),
(21,'334 Main Ave, Dhaka',NULL,'B+','01900000009','1990-11-30','isabella.miller@email.com','Female','2024-09-15','Isabella Miller','ROLE_TEACHER','ACTIVE',2),
(22,'445 Main Blvd, Dhaka',NULL,'AB+','01900000010','1984-04-18','james.smith@email.com','Male','2024-10-15','James Smith','ROLE_TEACHER','ACTIVE',2),
(23,'556 Main Ln, Dhaka',NULL,'O+','01900000011','1991-08-22','mia.davis@email.com','Female','2024-11-15','Mia Davis','ROLE_TEACHER','ACTIVE',2),
(24,'667 Main Park, Dhaka',NULL,'A-','01900000012','1987-12-05','benjamin.johnson@email.com','Male','2024-12-15','Benjamin Johnson','ROLE_TEACHER','ACTIVE',2);

/*Table structure for table `auth_tokens` */

DROP TABLE IF EXISTS `auth_tokens`;

CREATE TABLE `auth_tokens` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKgymtf40i213h1bo2f2aoiqdfg` (`username`),
  UNIQUE KEY `UK1cqfuii5jj5i9bqfwtq1i5x9w` (`user_id`),
  CONSTRAINT `FKdx4xxonjpxgjvsf7q9d04oiop` FOREIGN KEY (`user_id`) REFERENCES `ad_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `auth_tokens` */

insert  into `auth_tokens`(`id`,`password`,`username`,`user_id`) values 
(1,'$2y$12$9sRJ.Ne61.H4KjdWFRwgeubstn6BShshqDwckD95LJVCqcYRVUzRy','admin1',1),
(2,'$2y$12$9sRJ.Ne61.H4KjdWFRwgeubstn6BShshqDwckD95LJVCqcYRVUzRy','admin2',7);

/*Table structure for table `course_teachers` */

DROP TABLE IF EXISTS `course_teachers`;

CREATE TABLE `course_teachers` (
  `course_id` bigint(20) NOT NULL,
  `teacher_id` bigint(20) NOT NULL,
  KEY `FKka1ep0uutppr6ifmvurny69e0` (`teacher_id`),
  KEY `FK2cul0iwvm9cxgpsqy33h1nk3s` (`course_id`),
  CONSTRAINT `FK2cul0iwvm9cxgpsqy33h1nk3s` FOREIGN KEY (`course_id`) REFERENCES `acd_courses` (`id`),
  CONSTRAINT `FKka1ep0uutppr6ifmvurny69e0` FOREIGN KEY (`teacher_id`) REFERENCES `ad_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `course_teachers` */

insert  into `course_teachers`(`course_id`,`teacher_id`) values 
(1,1),
(1,2),
(2,3),
(3,4),
(4,5),
(4,6),
(5,7),
(6,8),
(7,2),
(8,3),
(9,9),
(10,10),
(11,11),
(12,12);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
