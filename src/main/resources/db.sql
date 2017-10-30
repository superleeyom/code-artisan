create table `t_user` (
	`u_id` INTEGER,
	`u_name` varchar (300),
	`password` varchar (300),
	`birthday` date ,
	`sex` INTEGER ,
	`age` INTEGER 
); 
insert into `t_user` (`u_id`, `u_name`, `password`, `birthday`, `sex`, `age`) values('1','leeyom','123','1993-08-26','1','24');
insert into `t_user` (`u_id`, `u_name`, `password`, `birthday`, `sex`, `age`) values('2','Tom','5566','1995-01-01','1','18');
insert into `t_user` (`u_id`, `u_name`, `password`, `birthday`, `sex`, `age`) values('3','Lucy','123456','1991-08-21','0','25');