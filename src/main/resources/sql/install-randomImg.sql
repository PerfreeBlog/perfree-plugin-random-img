
INSERT INTO `p_menu` (
`id`,`pid`,`name`,`url`,`icon`,
`menuType`,`pluginId`,`component`,`componentName`,`moduleName`,`type`
)
VALUES(
'076fd97f40954e618cc7ebd4bbd1d080','-1','随机图管理','/admin/randomImg','fa-solid fa-feather-alt',
1,  'perfree-plugin-random-img'  ,'/view/RandomImgView','randomImg','RandomImg',1
);

INSERT INTO `p_menu` (`id`,`pid`,`name`,`menuType`,`pluginId`,`perms`,`type`)
VALUES('e118b656d69c4cd281c6d83f9bfea81d','076fd97f40954e618cc7ebd4bbd1d080','随机图查询',2,  'perfree-plugin-random-img'  ,'admin:randomImg:query', 1);

INSERT INTO `p_menu` (`id`,`pid`,`name`,`menuType`,`pluginId`,`perms`,`type`)
VALUES('efca6a5043154adeb5fff6afde8a1566','076fd97f40954e618cc7ebd4bbd1d080','随机图创建',2,  'perfree-plugin-random-img'  ,'admin:randomImg:create', 1);

INSERT INTO `p_menu` (`id`,`pid`,`name`,`menuType`,`pluginId`,`perms`,`type`)
VALUES('f4a077d69bf842ce963afb0d6c40469f','076fd97f40954e618cc7ebd4bbd1d080','随机图编辑',2,  'perfree-plugin-random-img'  ,'admin:randomImg:update', 1);

INSERT INTO `p_menu` (`id`,`pid`,`name`,`menuType`,`pluginId`,`perms`,`type`)
VALUES('49b586cc28a24291836ccfcdfa9fadfa','076fd97f40954e618cc7ebd4bbd1d080','随机图删除',2,  'perfree-plugin-random-img'  ,'admin:randomImg:delete', 1);

INSERT INTO `p_menu` (`id`,`pid`,`name`,`menuType`,`pluginId`,`perms`,`type`)
VALUES('8fac1599a2a34331b49c5b29d09d35a4','076fd97f40954e618cc7ebd4bbd1d080','随机图导出',2,  'perfree-plugin-random-img'  ,'admin:randomImg:export', 1);
