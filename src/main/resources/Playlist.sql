-- created database
create database `jukebox`;
--used jukebox
use `jukebox`;

create table `jukebox`.`playlist`(
playlistId int,
playlistName varchar(50),
songId int
);

create table `jukebox`.`song`(
songId int,
songName varchar(50),
artistName varchar(50),
genre varchar(50),
duration varchar(50)
);

create table `jukebox`.`user`(
userId varchar(50),
password varchar(50)
);