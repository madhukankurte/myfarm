CREATE TABLE `myfram`.`Members` (
  `userdId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `mobileNumber` VARCHAR(45) NOT NULL,
  `emailId` VARCHAR(250) NULL,
  `addarNumber` VARCHAR(50) NOT NULL,
  `address` VARCHAR(250) NULL,
  `date` long NULL,
  `status` TINYINT NULL,
  `image` VARCHAR(500) NULL,
  `description` VARCHAR(250) NULL,
  UNIQUE INDEX `userdId_UNIQUE` (`userdId` ASC),
  PRIMARY KEY (`userdId`));