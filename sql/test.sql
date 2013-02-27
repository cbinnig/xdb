USE xdb_test;

CREATE TABLE `R` (
  `a` int(11) DEFAULT NULL,
  `b` text,
  `c` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `R` VALUES (1,'Test',1);

CREATE TABLE `S` (
  `d` int(11) DEFAULT NULL,
  `e` text,
  `f` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `S` VALUES (1,'Test',1);
