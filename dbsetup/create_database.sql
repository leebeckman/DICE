create database forum_db;
create user dbuser identified by 'dbpassword';
grant all on forum_db.* to dbuser@localhost identified by 'dbpassword';
grant all on forum_db.* to dbuser@'%' identified by 'dbpassword';
