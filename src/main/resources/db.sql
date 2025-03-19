#用户表:用户ID、付费阶段、用户状态、金币（练习/挑战所得）、创建时间、修改时间
#知识库表:一级目录、二级目录、三级目录、四级目录、难度(1-5⭐️)、知识点图片URL、知识点的具体内容(文本、富文本等)、排序字段、状态：1-上架，0-下架、创建时间、修改时间
#学习进度表:主键ID、用户ID，关联用户表、知识ID，关联知识库表、学习状态：0-未开始,1-学习中,2-已掌握、累计学习次数、首次掌握该知识点的时间、记录创建时间、记录更新时间
#练习表:练习ID、练习标题、练习目标、注意事项、练习类型：1-基础, 2-进阶, 3-突破、已练习时长、奖励金币、创建时间、修改时间
#练习记录表:练习ID、发起练习的用户ID、开始时间、结束时间、耗时（单位：分钟)、路程（单位：公里)、平均速度（单位：公里/小时)、最高速度（单位：公里/小时)、急刹车次数、熟练度、练习表现、练习心得、练习照片、轨迹点、创建时间、更新时间
#挑战表:挑战ID、挑战标题、挑战描述、挑战条件类型：1-次数, 2-里程, 3-综合、挑战状态：0-待发布，1-进行中, 2-已完成, 3-取消、条件值、奖励金币、创建时间、修改时间
#奖励表:奖励ID、奖励标题、奖励描述、奖励图片、兑换条件、奖励类型：1-实物, 2-虚拟道具, 3-优惠券、有效性：1-上架, 0-下架、创建时间、修改时间
#兑换记录表:学生表ID、奖励表ID、金币消耗、兑换时间、创建时间、修改时间


# 用户表
CREATE TABLE `users`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `pay_stage`   TINYINT         NOT NULL DEFAULT 0 COMMENT '付费阶段：A-白户，B-知识，C-月付，D-买断',
    `status`      TINYINT         NOT NULL DEFAULT 1 COMMENT '用户状态：1-正常，0-禁用',
    `proficiency` INT             NOT NULL DEFAULT 10 COMMENT '熟练度',
    `gold`        INT             NOT NULL DEFAULT 0 COMMENT '金币(练习/挑战所得)',
    `created_at`  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

# 知识库表
CREATE TABLE `knowledge_library`
(
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '知识ID',
    `level1_dir` VARCHAR(20)              DEFAULT NULL COMMENT '一级目录',
    `level2_dir` VARCHAR(20)              DEFAULT NULL COMMENT '二级目录',
    `level3_dir` VARCHAR(20)              DEFAULT NULL COMMENT '三级目录',
    `level4_dir` VARCHAR(20)              DEFAULT NULL COMMENT '四级目录',
    `difficulty` TINYINT         NOT NULL DEFAULT 1 COMMENT '难度(1-5星)',
    `image_url`  VARCHAR(255)             DEFAULT NULL COMMENT '知识点图片URL',
    `content`    VARCHAR(300) COMMENT '知识点的具体内容',
    `sort_order` INT             NOT NULL DEFAULT 0 COMMENT '排序字段',
    `status`     TINYINT         NOT NULL DEFAULT 1 COMMENT '状态：1-上架, 0-下架',
    `created_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='知识库表';

# 练习表
CREATE TABLE `practice`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '练习ID',
    `title`       VARCHAR(100)    NOT NULL COMMENT '练习标题',
    `target`      json COMMENT '练习目标',
    `notes`       varchar(200) COMMENT '注意事项',
    `type`        TINYINT         NOT NULL DEFAULT 1 COMMENT '练习类型：1-基础, 2-进阶, 3-突破',
    `duration`    INT UNSIGNED    NOT NULL DEFAULT 0 COMMENT '已练习时长(单位:分钟)',
    `reward_gold` INT             NOT NULL DEFAULT 0 COMMENT '奖励金币',
    `created_at`  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='练习表';

#挑战表
CREATE TABLE `challenge`
(
    `id`              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '挑战ID',
    `title`           VARCHAR(36)     NOT NULL COMMENT '挑战标题',
    `description`     VARCHAR(128) COMMENT '挑战描述',
    `condition_type`  TINYINT         NOT NULL DEFAULT 1 COMMENT '挑战条件类型：1-次数, 2-里程, 3-综合',
    `status`          TINYINT         NOT NULL DEFAULT 0 COMMENT '挑战状态：0-待发布,1-进行中,2-已完成,3-取消',
    `condition_value` INT             NOT NULL DEFAULT 0 COMMENT '条件值(次数/里程等)',
    `reward_gold`     INT             NOT NULL DEFAULT 0 COMMENT '奖励金币',
    `sort_order`      INT             NOT NULL DEFAULT 0 COMMENT '排序字段',
    `created_at`      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='挑战表';


#奖励表
CREATE TABLE `reward`
(
    `id`                 BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '奖励ID',
    `title`              VARCHAR(36)     NOT NULL COMMENT '奖励标题',
    `description`        VARCHAR(128) COMMENT '奖励描述',
    `image`              VARCHAR(255)             DEFAULT NULL COMMENT '奖励图片',
    `exchange_condition` INT             NOT NULL DEFAULT 0 COMMENT '兑换条件(如所需金币数)',
    `reward_type`        TINYINT         NOT NULL DEFAULT 1 COMMENT '奖励类型：1-实物,2-虚拟道具,3-优惠券',
    `sort_order`         INT             NOT NULL DEFAULT 0 COMMENT '排序字段',
    `is_active`          TINYINT         NOT NULL DEFAULT 1 COMMENT '有效性：1-上架,0-下架',
    `created_at`         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='奖励表';

#学习进度表
CREATE TABLE `learning_progress`
(
    `id`                BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`           BIGINT UNSIGNED NOT NULL COMMENT '用户ID，关联users表',
    `knowledge_id`      BIGINT UNSIGNED NOT NULL COMMENT '知识ID，关联knowledge_library表',
    `learning_status`   TINYINT         NOT NULL DEFAULT 0 COMMENT '学习状态：0-未开始,1-学习中,2-已掌握',
    `study_count`       INT UNSIGNED    NOT NULL DEFAULT 0 COMMENT '累计学习次数',
    `first_mastered_at` DATETIME                 DEFAULT NULL COMMENT '首次掌握该知识点的时间',
    `created_at`        DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    `updated_at`        DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_knowledge_id` (`knowledge_id`),
    CONSTRAINT `fk_learning_progress_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_learning_progress_knowledge` FOREIGN KEY (`knowledge_id`) REFERENCES `knowledge_library` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户学习进度表';

#练习记录表
CREATE TABLE `practice_record`
(
    `id`                 BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `practice_id`        BIGINT UNSIGNED NOT NULL COMMENT '练习ID，关联practice表',
    `user_id`            BIGINT UNSIGNED NOT NULL COMMENT '用户ID，关联users表',
    `start_time`         DATETIME        NOT NULL COMMENT '开始时间',
    `end_time`           DATETIME        NOT NULL COMMENT '结束时间',
    `duration`           time            NOT NULL DEFAULT 0 COMMENT '耗时(单位:分钟)',
    `distance`           DECIMAL(8, 2)   NOT NULL DEFAULT 0 COMMENT '路程(单位:公里)',
    `avg_speed`          DECIMAL(5, 2)   NOT NULL DEFAULT 0 COMMENT '平均速度(公里/小时)',
    `max_speed`          DECIMAL(5, 2)   NOT NULL DEFAULT 0 COMMENT '最高速度(公里/小时)',
    `sudden_brake_count` TINYINT         NOT NULL DEFAULT 0 COMMENT '急刹车次数',
    `proficiency`        TINYINT         NOT NULL DEFAULT 0 COMMENT '熟练度',
    `performance`        json COMMENT '练习表现',
    `insights`           varchar(200) COMMENT '练习心得(200字内)',
    `photos`             varchar(200) COMMENT '练习照片(可存JSON或逗号分隔URL)',
    `trajectory`         json COMMENT '轨迹点(可存JSON)',
    `created_at`         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_practice_id` (`practice_id`),
    KEY `idx_user_id` (`user_id`),
    CONSTRAINT `fk_practice_record_practice` FOREIGN KEY (`practice_id`) REFERENCES `practice` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_practice_record_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='练习记录表';

#兑换记录表
CREATE TABLE `redemption_record`
(
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `student_id` BIGINT UNSIGNED NOT NULL COMMENT '学生表ID，关联users表',
    `reward_id`  BIGINT UNSIGNED NOT NULL COMMENT '奖励表ID，关联reward表',
    `gold_cost`  INT             NOT NULL DEFAULT 0 COMMENT '金币消耗',
    `created_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间(兑换时间)',
    `updated_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_student_id` (`student_id`),
    KEY `idx_reward_id` (`reward_id`),
    CONSTRAINT `fk_redemption_student` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_redemption_reward` FOREIGN KEY (`reward_id`) REFERENCES `reward` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='兑换记录表';
