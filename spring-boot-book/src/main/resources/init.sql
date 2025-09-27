DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
                        `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                        `title` VARCHAR(200) NOT NULL COMMENT '书名',
                        `author` VARCHAR(128) COMMENT '作者',
                        `isbn` VARCHAR(32) COMMENT 'ISBN（唯一）',
                        `category` VARCHAR(64) COMMENT '分类名称/标签',
                        `stock` INT NOT NULL DEFAULT 0 COMMENT '库存数量',
                        `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删 1已删',
                        `version` INT NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
                        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_book_isbn` (`isbn`),
                        KEY `idx_book_title` (`title`),
                        KEY `idx_book_category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;