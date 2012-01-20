CREATE TABLE jrf_ban_type (
       id INT(12) NOT NULL DEFAULT 0
     , type_name VARCHAR(80) NOT NULL
)TYPE=InnoDB;

CREATE TABLE jrf_constants (
       c_name VARCHAR(32) NOT NULL
     , c_value VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX c_name ON jrf_constants (c_name ASC);

CREATE TABLE jrf_group (
       groupid INT(12) NOT NULL DEFAULT 0
     , group_name VARCHAR(255)
     , group_sort CHAR(2)
)TYPE=InnoDB;

CREATE TABLE jrf_key_keeper (
       table_name VARCHAR(255) NOT NULL
     , next_key INT(11) NOT NULL DEFAULT 0
);

CREATE TABLE jrf_pending_user (
       user_name VARCHAR(32) NOT NULL
     , user_mail VARCHAR(64) NOT NULL
     , confirm_code VARCHAR(128) NOT NULL
     , intime DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'
);
CREATE INDEX jrf_pending_user_idx ON jrf_pending_user (user_name ASC, confirm_code ASC);

CREATE TABLE jrf_rank (
       id INT(12) NOT NULL DEFAULT 0
     , rank_count INT(4) NOT NULL DEFAULT 0
     , rank_name VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX rank_count ON jrf_rank (rank_count ASC);

CREATE TABLE jrf_skins (
       skinid INT(12) NOT NULL DEFAULT 0
     , skin_name VARCHAR(128) NOT NULL
)TYPE=InnoDB;

CREATE TABLE jrf_user_status (
       status INT(2) NOT NULL DEFAULT 0
     , status_name VARCHAR(255)
)TYPE=InnoDB;

CREATE TABLE jrf_whois (
       id INT(12) NOT NULL DEFAULT 0
     , ip VARCHAR(16) NOT NULL
     , sessionid VARCHAR(255) NOT NULL
     , user_name VARCHAR(32)
);

CREATE TABLE jrf_audit_log (
       log_date VARCHAR(80) NOT NULL
     , logger VARCHAR(255) NOT NULL
     , log_level VARCHAR(32) NOT NULL
     , message TEXT NOT NULL
     , remote_ip VARCHAR(16)
     , user_name VARCHAR(32)
     , session_id VARCHAR(255)
);
CREATE INDEX jrf_audit_log_idx ON jrf_audit_log (log_date ASC, logger ASC, log_level ASC);

CREATE TABLE jrf_forum (
       forumid INT(12) NOT NULL DEFAULT 0
     , forumtitle VARCHAR(255)
     , forumdesc VARCHAR(255)
     , groupid INT(12) DEFAULT 0
     , locked INT(1) DEFAULT 0
     , forum_sort CHAR(2) DEFAULT 'aa'
)TYPE=InnoDB;

CREATE TABLE jrf_thread (
       threadid INT(12) NOT NULL DEFAULT 0
     , forumid INT(12) DEFAULT 1
     , lintime DATETIME
     , locked INT(1) DEFAULT 0
     , sortby INT(1) DEFAULT 9
)TYPE=InnoDB;

CREATE TABLE jrf_message (
       id INT(12) NOT NULL DEFAULT 0
     , sender VARCHAR(32) NOT NULL
     , centents TEXT NOT NULL
     , intime DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'
     , heading VARCHAR(255)
     , threadid INT(12) DEFAULT 1
     , ip VARCHAR(15) DEFAULT 'N/A'
)TYPE=InnoDB;

CREATE TABLE jrf_user (
       id INT(12) NOT NULL DEFAULT 0
     , user_name VARCHAR(32) NOT NULL
     , user_pass VARCHAR(100) NOT NULL
     , user_mail VARCHAR(64) NOT NULL
     , user_status INT(2)
     , user_hp VARCHAR(128)
     , user_icq VARCHAR(32)
     , user_dob DATE
     , user_city VARCHAR(32)
     , user_occupation VARCHAR(32)
     , auto_login CHAR(1) DEFAULT '1'
     , show_user_mail CHAR(1) DEFAULT '1'
     , mes_per_page INT(2) DEFAULT 20
     , user_signature TEXT
     , last_intime DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00'
)TYPE=InnoDB;

CREATE TABLE jrf_ban (
       type_id INT(12) NOT NULL DEFAULT 0
     , ban_mask VARCHAR(255) NOT NULL
)TYPE=InnoDB;
CREATE UNIQUE INDEX ban_mask ON jrf_ban (ban_mask ASC);

CREATE TABLE jrf_mod (
       user_name VARCHAR(32) NOT NULL
     , forum_id INT(12)
     , id INT(12) NOT NULL DEFAULT 0
)TYPE=InnoDB;

CREATE TABLE jrf_skin_params (
       skinid INT(12) NOT NULL DEFAULT 0
     , param_name VARCHAR(128) NOT NULL
     , param_value VARCHAR(255) NOT NULL
)TYPE=InnoDB;

CREATE TABLE jrf_subscribe (
       threadid INT(12) NOT NULL DEFAULT 0
     , user_mail VARCHAR(128) NOT NULL
     , user_name VARCHAR(32) NOT NULL
)TYPE=InnoDB;

CREATE TABLE jrf_attach (
       id INT(12) NOT NULL DEFAULT 0
     , message_id INT(12) NOT NULL DEFAULT 0
     , attach_name VARCHAR(255) NOT NULL
     , attach_description VARCHAR(255) NOT NULL
     , attach_content_type VARCHAR(255) NOT NULL
     , attach_size INT(12)
)TYPE=InnoDB;

ALTER TABLE jrf_ban_type
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (id);

ALTER TABLE jrf_group
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (groupid);

ALTER TABLE jrf_key_keeper
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (next_key, table_name);

ALTER TABLE jrf_pending_user
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (confirm_code, user_name);

ALTER TABLE jrf_rank
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (id);

ALTER TABLE jrf_skins
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (skinid);

ALTER TABLE jrf_user_status
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (status);

ALTER TABLE jrf_whois
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (id);

ALTER TABLE jrf_forum
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (forumid);

ALTER TABLE jrf_thread
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (threadid);

ALTER TABLE jrf_message
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (id);

ALTER TABLE jrf_user
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (id);

ALTER TABLE jrf_skin_params
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (param_name, skinid);

ALTER TABLE jrf_attach
  ADD CONSTRAINT PRIMARY
      PRIMARY KEY (id);

ALTER TABLE jrf_whois
  ADD CONSTRAINT UQ_jrf_whois_1
      UNIQUE (user_name);

ALTER TABLE jrf_user
  ADD CONSTRAINT UQ_jrf_user_1
      UNIQUE (user_name);

ALTER TABLE jrf_forum
  ADD CONSTRAINT `0_791`
      FOREIGN KEY (groupid)
      REFERENCES jrf_group (groupid)
   ON DELETE SET NULL
   ON UPDATE NO ACTION;

ALTER TABLE jrf_thread
  ADD CONSTRAINT `0_797`
      FOREIGN KEY (forumid)
      REFERENCES jrf_forum (forumid)
   ON DELETE SET NULL
   ON UPDATE NO ACTION;

ALTER TABLE jrf_message
  ADD CONSTRAINT `0_793`
      FOREIGN KEY (threadid)
      REFERENCES jrf_thread (threadid)
   ON DELETE SET NULL
   ON UPDATE NO ACTION;

ALTER TABLE jrf_user
  ADD CONSTRAINT `0_801`
      FOREIGN KEY (user_status)
      REFERENCES jrf_user_status (status)
   ON DELETE SET NULL
   ON UPDATE NO ACTION;

ALTER TABLE jrf_ban
  ADD CONSTRAINT `0_805`
      FOREIGN KEY (type_id)
      REFERENCES jrf_ban_type (id)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

ALTER TABLE jrf_mod
  ADD CONSTRAINT `0_799`
      FOREIGN KEY (forum_id)
      REFERENCES jrf_forum (forumid)
   ON DELETE SET NULL
   ON UPDATE NO ACTION;

ALTER TABLE jrf_mod
  ADD CONSTRAINT FK_jrf_mod_2
      FOREIGN KEY (user_name)
      REFERENCES jrf_user (user_name);

ALTER TABLE jrf_skin_params
  ADD CONSTRAINT FK_jrf_skin_params_1
      FOREIGN KEY (skinid)
      REFERENCES jrf_skins (skinid);

ALTER TABLE jrf_subscribe
  ADD CONSTRAINT `0_795`
      FOREIGN KEY (threadid)
      REFERENCES jrf_thread (threadid)
   ON DELETE CASCADE
   ON UPDATE NO ACTION;

ALTER TABLE jrf_attach
  ADD CONSTRAINT `0_803`
      FOREIGN KEY (message_id)
      REFERENCES jrf_message (id)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;

