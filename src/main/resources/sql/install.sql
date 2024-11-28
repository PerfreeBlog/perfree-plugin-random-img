INSERT INTO `p_menu` (
    `id`,`pid`,`name`,`url`,`icon`,
    `menuType`,`pluginId`,`component`,`componentName`,`moduleName`,`type`,`seq`
)
VALUES(
          '2cadaa1f51f0423356f0d64e5cc7b76','-1','随机图管理','/admin/randomImg','fa-solid fa-image',
          1,  'perfree-plugin-random-img'  ,'/view/RandomImgView','RandomImg','RandomImg', 1, 99
      );

DROP TABLE IF EXISTS `p_random_img`;
CREATE TABLE `p_random_img`  (
                                 `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `name` varchar(256) CHARACTER SET utf8mb4 NOT NULL COMMENT '名称',
                                 `slug` varchar(256) CHARACTER SET utf8mb4 NOT NULL COMMENT '访问slug',
                                 `remark` varchar(512) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '备注',
                                 `attachLibraryId` int NOT NULL COMMENT '附件库id',
                                 `status` int NOT NULL DEFAULT 0 COMMENT '是否开启0:开启,1关闭',
                                 `visitCount` int NOT NULL DEFAULT 0 COMMENT '访问次数',
                                 `createUserId` int NULL DEFAULT NULL COMMENT '添加人',
                                 `updateUserId` int NULL DEFAULT NULL COMMENT '更新人',
                                 `createTime` datetime NOT NULL COMMENT '创建时间',
                                 `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '随机图' ROW_FORMAT = Dynamic;
