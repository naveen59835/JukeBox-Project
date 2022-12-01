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
-- inserted values in the song table
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

--insert values in the user table
insert into jukebox.user value('bob',123);
insert into jukebox.user value('smith',123);
insert into jukebox.user value('charles',123);
--made song id in song table as primary key
ALTER TABLE `jukebox`.`song`
CHANGE COLUMN `songId` `songId` INT NOT NULL ,
ADD PRIMARY KEY (`songId`);
;
--made playlist id in the playlist table as primary key
ALTER TABLE `jukebox`.`playlist`
CHANGE COLUMN `playlistId` `playlistId` INT NOT NULL ,
ADD PRIMARY KEY (`playlistId`);
;
--Alter table and added song path to it
ALTER TABLE `jukebox`.`song`
ADD COLUMN `songPath` VARCHAR(45) NULL AFTER `duration`;

--Added column to the jukebox database
ALTER TABLE `jukebox`.`playlist`
ADD COLUMN `songName` VARCHAR(45) NULL AFTER `songId`;
-- added values to the column
update `jukebox`.`playlist` set `songName`='varanam' where `songid`=1;
update `jukebox`.`playlist` set `songName`='naan' where `songid`=2;
update `jukebox`.`playlist` set `songName`='yanji' where `songid`=3;
update `jukebox`.`playlist` set `songName`='rolex' where `songid`=4;
-- changed column size of song varchar to 100
ALTER TABLE `jukebox`.`song`
CHANGE COLUMN `songPath` `songPath` VARCHAR(100) NULL DEFAULT NULL ;
--created column of songpath in song table and added path of the song to it
update `jukebox`.`song` set `songpath`='src/main/resources/SongsFolder/adiye_azhage.wav' where `songid`=1;
update `jukebox`.`song` set `songpath`='src/main/resources/SongsFolder/minsarakanavu.wav' where `songid`=2;
update `jukebox`.`song` set `songpath`='src/main/resources/SongsFolder/naan.wav' where `songid`=3;
update `jukebox`.`song` set `songpath`='src/main/resources/SongsFolder/Pirates of the Caribbean.wav' where `songid`=4;
update `jukebox`.`song` set `songpath`='src/main/resources/SongsFolder/rolex-58120.wav' where `songid`=5;
update `jukebox`.`song` set `songpath`='src/main/resources/SongsFolder/rolex-bgm.wav' where `songid`=6;
update `jukebox`.`song` set `songpath`='src/main/resources/SongsFolder/vaarnam_aairam_bgm.wav' where `songid`=7;
update `jukebox`.`song` set `songpath`='src/main/resources/SongsFolder/vikram_vedha.wav' where `songid`=8;
update `jukebox`.`song` set `songpath`='src/main/resources/SongsFolder/yanji.wav' where `songid`=9;
--updated song list and playlist name in the playlist table
update `jukebox`.`playlist` set `songName`='adiye_azhage' where `songid`=1;
update `jukebox`.`playlist` set `songName`='minsara' where `songid`=2;
update `jukebox`.`playlist` set `songName`='naan' where `songid`=3;
update `jukebox`.`playlist` set `songName`='pirates' where `songid`=4;
update `jukebox`.`playlist` set `songName`='rolex1' where `songid`=5;
--added values to the playlist table
insert into jukebox.playlist value(17,'ArtistPlaylist',6,'rolex2');
insert into jukebox.playlist value(18,'on Repeat playlist',7,'varanam');
insert into jukebox.playlist value(19,'spot playlist',8,'vikramvedha');
insert into jukebox.playlist value(20,'discover playlist',9,'yanji');