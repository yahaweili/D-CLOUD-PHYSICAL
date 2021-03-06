CREATE TABLE `t_book`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `reader`      varchar(255),
    `isbn`        varchar(255),
    `title`       varchar(255),
    `author`      varchar(255),
    `description` varchar(255),
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for seata
-- ----------------------------
CREATE TABLE `undo_log`
(
    `id`            bigint(20)   NOT NULL AUTO_INCREMENT COMMENT 'increment id',
    `branch_id`     bigint(20)   NOT NULL COMMENT 'branch transaction id',
    `xid`           varchar(100) NOT NULL COMMENT 'global transaction id',
    `context`       varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` longblob     NOT NULL COMMENT 'rollback info',
    `log_status`    int(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   datetime     NOT NULL COMMENT 'create datetime',
    `log_modified`  datetime     NOT NULL COMMENT 'modify datetime',
    `ext`           varchar(100) DEFAULT NULL COMMENT 'reserved field',
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='AT transaction mode undo table';
