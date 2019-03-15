CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `marks` int(11) DEFAULT NULL,
  `roll_num` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


INSERT INTO `student` (`id`,`marks`,`roll_num`) VALUES 
 (1,500,'111');