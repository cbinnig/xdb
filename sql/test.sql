USE xdb_test;

CREATE TABLE `r` (
  `a` int(11) DEFAULT NULL,
  `b` text,
  `c` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `r` VALUES (1,'Test',1);

CREATE TABLE `s` (
  `d` int(11) DEFAULT NULL,
  `e` text,
  `f` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `s` VALUES (1,'Test',1);
