1. Installation
----------------

1.1 Software (Runtime)

MySQL
- Install MySQL Community Server (>=version 5.6)
- Copy /conf/my.cnf to location depending on platform (e.g., /etc/)
- Import SQL files in sql folder:
  mysql -u root < xdb.sql
  mysql -u root < catalog.sql
  mysql -u root < test.sql
  mysql -u root < tpch.sql (unzip tpch.zip before)


1.2 Software (Devel)

Graphviz:
- Install graphviz and graphviz-gui to look at XDB plans

Antlr:
- Install newest version of Antlworks for generating the compiler

2. Startup
---------------
- Start mysqld_safe at least with option federated (which should be given by the my.cnf file)
- Execute test suite (TestSuiteXDB)
  
