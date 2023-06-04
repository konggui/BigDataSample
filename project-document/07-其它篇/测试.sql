CREATE TABLE mappoi.mappoi_operation_log
(
`id`            VARCHAR(20) NOT NULL
,`type`          VARCHAR(20) NOT NULL
,`step`          VARCHAR(40) NOT NULL
,`raw_data`      VARCHAR(24) default ''
,`src`           VARCHAR(20) NOT NULL
,`category`      VARCHAR(20) NOT NULL
,`traceid`       VARCHAR(20) NOT NULL
,`timestamp`     DateTime NOT NULL
,`errorcode`     VARCHAR(20) default ''
,`error_message` VARCHAR(255) default ''
,`process_ext`   VARCHAR(255) default ''
,`extra_text`    VARCHAR(255) default ''
,PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET=utf8;

