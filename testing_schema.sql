INSERT INTO PUBLISHER VALUES (1, "Ahmed", "9.st.abdElsnaasas", "1232131241123");
INSERT INTO PUBLISHER VALUES (2, "H", "9.ssss", "1412512323");
INSERT INTO PUBLISHER VALUES (3, "Negro", "9.aaaaa", "1212312312");

INSERT INTO BOOK VALUES (1, "Good Doctor", 1, "2011",'Art', 1111, 20, 10);
INSERT INTO BOOK VALUES (2, "Good Doctor2", 1, "2012",'Science', 2222, 40, 10);
INSERT INTO BOOK VALUES (3, "Good Doctor3", 1, "2013", 'Art', 3333, 60, 10);
INSERT INTO BOOK VALUES (4, "Good Doctor4", 2, "2014", 'History', 4444, 30, 20);
INSERT INTO BOOK VALUES (5, "Good Doctor5", 2, "2015", 'Art', 5555, 40, 20);
INSERT INTO BOOK VALUES (6, "Good Doctor6", 3, "2016", 'History', 6666, 50, 30);
INSERT INTO BOOK VALUES (7, "Good Doctor7", 3, "2017", 'Science', 7777, 600, 25);

#Edited
INSERT INTO BOOK VALUES (8, "Good Doctor8", 2, "2015", 'Art', 5555, 600, 20);
INSERT INTO BOOK VALUES (9, "Good Doctor9", 3, "2016", 'History', 6666, 600, 30);
INSERT INTO BOOK VALUES (10, "Good Doctor10", 3, "2017", 'Science', 7777, 600, 25);
INSERT INTO BOOK VALUES (11, "Good Doctor11", 2, "2015", 'Art', 5555, 600, 20);
INSERT INTO BOOK VALUES (12, "Good Doctor2", 3, "2016", 'History', 6666, 600, 30);
INSERT INTO BOOK VALUES (13, "Good Doctor13", 3, "2017", 'Science', 7777, 600, 25);


INSERT INTO BOOK_AUTHORS VALUES (1, "A");
INSERT INTO BOOK_AUTHORS VALUES (1, "B");
INSERT INTO BOOK_AUTHORS VALUES (2, "C");
INSERT INTO BOOK_AUTHORS VALUES (2, "D");
INSERT INTO BOOK_AUTHORS VALUES (3, "A");
INSERT INTO BOOK_AUTHORS VALUES (4, "C");
INSERT INTO BOOK_AUTHORS VALUES (5, "A");
INSERT INTO BOOK_AUTHORS VALUES (6, "D");
INSERT INTO BOOK_AUTHORS VALUES (7, "E");
INSERT INTO BOOK_AUTHORS VALUES (7, "D");

INSERT INTO `USER` VALUES ( "ahmed@ahmed", "AhmedK", "aks", "khaled", "ahmed", "123123123", "100.st.inplasd", "Customer");
INSERT INTO `USER` VALUES ( "ahmed@ahmed1", "AhmedK1", "aks1", "khaled1", "ahmed1", "123123123", "101.st.inplasd", "Customer");
INSERT INTO `USER` VALUES ( "ahmed@ahmed2", "AhmedK2", "aks2", "khaled2", "ahmed2", "123123123", "102.st.inplasd", "Customer");
INSERT INTO `USER` VALUES ( "ahmed@ahmed3", "AhmedK3", "aks3", "2etshh", "2etsh", "123123123", "103.st.inplasd", "Manager");
INSERT INTO `USER` VALUES ( "edited@me", "AhmedK", "aks", "khaled", "ahmed", "123123123", "100.st.inplasd", "Customer");


INSERT INTO `ORDER` VALUES ( "ahmed@ahmed", 1, 5, 'IN_PROGRESS', '2018-05-20' );
INSERT INTO `ORDER` VALUES ( "ahmed@ahmed", 2, 40, 'IN_PROGRESS', '2018-03-20' );
INSERT INTO `ORDER` VALUES ( "ahmed@ahmed", 3, 12, 'IN_PROGRESS', '2018-02-20' );
INSERT INTO `ORDER` VALUES ( "ahmed@ahmed3", 4, 3, 'IN_PROGRESS', '2018-01-20' );
INSERT INTO `ORDER` VALUES ( "ahmed@ahmed3", 5, 6, 'IN_PROGRESS', '2018-02-20' );
INSERT INTO `ORDER` VALUES ( "ahmed@ahmed3", 6, 17, 'IN_PROGRESS', '2018-05-20' );

#Edited
INSERT INTO `ORDER` VALUES ( "edited@me", 7, 412, 'IN_PROGRESS', '2018-04-20' );
INSERT INTO `ORDER` VALUES ( "edited@me", 8, 311, 'IN_PROGRESS', '2018-05-07' );
INSERT INTO `ORDER` VALUES ( "edited@me", 9, 22, 'IN_PROGRESS', '2018-05-08' );
INSERT INTO `ORDER` VALUES ( "edited@me", 10, 333, 'IN_PROGRESS', '2018-05-30' );
INSERT INTO `ORDER` VALUES ( "edited@me", 11, 125, 'IN_PROGRESS', '2018-06-01' );
INSERT INTO `ORDER` VALUES ( "edited@me", 12, 92, 'IN_PROGRESS', '2018-05-06' );


SELECT * FROM `ORDER`;
SELECT * FROM `BOOK`;

SET SQL_SAFE_UPDATES = 0;

UPDATE `ORDER` SET State = 'COMPLETED' WHERE State = 'IN_PROGRESS';
SET SQL_SAFE_UPDATES = 1;
SELECT * FROM `ORDER`;

SELECT * FROM `BOOK`;
SELECT * FROM `USER`;
