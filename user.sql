DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL auto_increment,
  `USERNAME` varchar(50) not NULL,
  `PASSWD` varchar(200) not NULL,
  `NICKNAME` varchar(200) default '',
  `USERTYPE` int(1) default '2',
  `COMMENTS` varchar(200) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into user(USERNAME,PASSWD,NICKNAME,USERTYPE,COMMENTS)values('admin','96e79218965eb72c92a549dd5a330112','魏晋',2,'密码是6个1');
insert into user(USERNAME,PASSWD,NICKNAME,USERTYPE,COMMENTS)values('asianinfo','e3ceb5881a0a1fdaad01296d7554868d','魏晋',2,'密码6个2');
