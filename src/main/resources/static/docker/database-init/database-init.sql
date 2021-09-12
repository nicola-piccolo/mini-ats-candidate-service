CREATE DATABASE candidate;

CREATE USER 'candidate_user'@'%' identified by 'SuPeRsEcReTpWd';

GRANT ALL PRIVILEGES ON candidate.* TO 'candidate_user'@'%';

USE candidate

CREATE TABLE candidate (
	id INT(10) UNSIGNED AUTO_INCREMENT,
	uid VARCHAR(36) NOT NULL,
	company_uid VARCHAR(36) NOT NULL,
	first_name VARCHAR(128) NOT NULL,
	last_name VARCHAR(128) NOT NULL,
	email VARCHAR(256) NOT NULL,
	mobile_phone VARCHAR(32),
	current_salary INT(10) UNSIGNED,
	desired_salary INT(10) UNSIGNED,
	deleted TINYINT(1),
	PRIMARY KEY (id)
) ENGINE=InnoDB;