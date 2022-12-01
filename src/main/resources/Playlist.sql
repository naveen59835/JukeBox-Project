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
-- inserted values in the song taable
insert into jukebox.song value(1,'adiye_azhage','Beiber','pop','6 mins');
insert into jukebox.song value(2,'minsara','gomez','pop','2 mins');
insert into jukebox.song value(3,'naan','shakira','treble','3 mins');
insert into jukebox.song value(4,'pirates','scoopdoo','solo','2 mins');
insert into jukebox.song value(5,'rolex1','rahmaan','peace','3 mins');
insert into jukebox.song value(6,'rolex2','rahmaan','solo','3 mins');
insert into jukebox.song value(7,'varanam','yuvan','boost','4 mins');
insert into jukebox.song value(8,'vikramvedha','Beiber','rock','1 mins');
insert into jukebox.song value(9,'yanji','Beiber','bass','3 mins');
-- insert values into playlist table
insert into jukebox.playlist value(12,'pop playlist',1);
insert into jukebox.playlist value(13,'pop playlist',2);
insert into jukebox.playlist value(14,'fusion playlist',3);
insert into jukebox.playlist value(15,'fusion playlist',4);