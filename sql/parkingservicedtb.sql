# Dumping database structure for parkingservicedtb
DROP DATABASE IF EXISTS `parkingservicedtb`;
CREATE DATABASE IF NOT EXISTS `parkingservicedtb` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `parkingservicedtb`;

# Dumping structure for spring security tables

create table users(
id int not null auto_increment,
username varchar(50) not null,
password varchar(100) not null,
enabled boolean not null,
primary key(id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

create table authorities (
id int not null auto_increment primary key,
username varchar(50) not null,
authority varchar(50) not null,
constraint fk_authorities_users foreign key(id) references users(id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

create unique index ix_auth_username on authorities (username,authority);

# Example insert into spring security tables (encrypted password is: user)

insert into users(id,username,password,enabled)
	values(null,'user','$2a$10$b2SzVGgMUgtSQlrQ1Ht1puOB3A4MM98wrq9eboDnH.GUNAdxqbB7i',true);
insert into authorities(id, username,authority) 
	values(null,'user','ROLE_USER');
	
# Example insert into spring security tables (encrypted password is: admin)

insert into users(id,username,password,enabled)
	values(null,'admin','$2a$10$4VNlXv/TDHAzYGyu7caxWuOvpmn05UJ8aO5Sk4psbnhJ7wa8qeccy',true);
insert into authorities(id,username,authority) 
	values(null,'admin','ROLE_ADMIN');
	
# Example insert into spring security tables (encrypted password is: superadmin)

insert into users(id,username,password,enabled)
	values(null,'superadmin','$2a$10$uTxsM6mTsjdYfWuuOtrFkOjG4zGCfJQqkIjpFRQ3MfUVEHHZhVfP.',true);
insert into authorities(id,username,authority) 
	values(null,'superadmin','ROLE_SUPERADMIN');
	
# Dumping structure for table parkingservicedtb.objects

drop table if exists `objects`;
create table if not exists `objects`(
	`id` int(10) NOT NULL AUTO_INCREMENT, 
	`name` varchar(200) NOT NULL,
	`address` varchar(500) NOT NULL,
	`contact` varchar(500),
	`description` text,
	PRIMARY KEY (`id`)
)	ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT='Constains information about parking objects';

# Dumping structure for table parkingservicedtb.devices

drop table if exists `devices`;
create table if not exists `devices`(
	`id` int(10) NOT NULL AUTO_INCREMENT,
	`serial` int(20) NOT NULL,
	`devicetype` varchar(100) NOT NULL,
	`manufacturername` varchar(200) NOT NULL,
	`manufactureddate` date NOT NULL,
	`testeddate` date NOT NULL,
	`testername` varchar(100),
	`additionalinfo` varchar(500) NOT NULL,
	`parkingobjectid` int(10) NOT NULL,
	PRIMARY KEY(`id`)
)	ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT='Constains information about parking devices';

# Creating foreign key for tab devices.parkingobjectid -> objects.id

alter table devices
add constraint parkingobjectid_fk foreign key (parkingobjectid) references objects(id);

