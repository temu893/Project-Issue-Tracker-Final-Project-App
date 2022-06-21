CREATE TABLE `project` (
  `project_id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `project_name` varchar(45) UNIQUE NOT NULL,
  `start_date` date NOT NULL,
  `target_end_date` date NOT NULL,
  `actual_end_date` date NOT NULL,
  `created_on` date NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `modified_on` date NOT NULL,
  `modified_by` varchar(45) NOT NULL
);

CREATE TABLE `users` (
  `person_id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `person_name` varchar(45) UNIQUE NOT NULL,
  `person_email` varchar(45)  NOT NULL,
  `person_role` varchar(45) NOT NULL,
  `username` varchar(45) UNIQUE NOT NULL,
  `assigned_project` varchar(45),
  `created_on` date NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `modified_on` date NOT NULL,
  `modified_by` varchar(45) NOT NULL
);

CREATE TABLE `issues` (
  `issue_id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `issue_summary` varchar(45) NOT NULL,
  `issue_description` varchar(45) NOT NULL,
  `identified_by_person_id` int NOT NULL,
  `identified_date` date NOT NULL,
  `assigned_to` int NOT NULL,
  `status` varchar(45) NOT NULL,
  `priority` varchar(45) NOT NULL,
  `target_resolution_date` date NOT NULL,
  `progress` varchar(45) NOT NULL,
  `actual_resolution_date` date NOT NULL,
  `resolution_summary` varchar(45) NOT NULL,
  `created_on` date NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `modified_on` date NOT NULL,
  `modified_by` varchar(45) NOT NULL
);

ALTER TABLE `project` ADD FOREIGN KEY (`created_on`) REFERENCES `users` (`created_on`);

ALTER TABLE `users` ADD FOREIGN KEY (`created_on`) REFERENCES `issues` (`created_on`);
