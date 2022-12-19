CREATE DATABASE `heima_movie` CHARACTER SET utf8;
USE `heima_movie`;

DROP TABLE IF EXISTS `tb_user`;
create table tb_user(
    id int PRIMARY KEY COMMENT '主键',
    username varchar(30) null comment '账户',
    password varchar(32) null comment '密码'
);
insert  into `tb_user`(`id`,`username`,`password`) values (1,'admin','e10adc3949ba59abbe56e057f20f883e');


DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
   `id` int(11) PRIMARY KEY COMMENT '主键',
   `name` varchar(20) DEFAULT NULL COMMENT '栏目名'
);
insert  into `tb_category`(`id`,`name`) values (1,'电视剧');
insert  into `tb_category`(`id`,`name`) values (2,'电影');
insert  into `tb_category`(`id`,`name`) values (3,'综艺');
insert  into `tb_category`(`id`,`name`) values (4,'动漫');
insert  into `tb_category`(`id`,`name`) values (5,'网剧');
insert  into `tb_category`(`id`,`name`) values (6,'纪录片');



DROP TABLE IF EXISTS `tb_member`;
CREATE TABLE `tb_member` (
     `id` int(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
     `username` varchar(30) DEFAULT NULL COMMENT '用户名',
     `password` varchar(30) DEFAULT NULL COMMENT '密码',
     `phone` varchar(11) DEFAULT NULL COMMENT '手机号'
);
insert  into `tb_member`(`id`,`username`,`password`,`phone`) values (1,'tom','123456','13800000000');
insert  into `tb_member`(`id`,`username`,`password`,`phone`) values (2,'jerry','123456','13800000001');


DROP TABLE IF EXISTS `tb_movie`;
CREATE TABLE `tb_movie` (
    `id` int(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `name` varchar(100) DEFAULT NULL COMMENT '电影名称',
    `cid` int(11) DEFAULT NULL COMMENT '分类id',
    `description` varchar(512) DEFAULT NULL COMMENT '内容描述',
    `keyword` varchar(50) DEFAULT NULL COMMENT '关键字，用来搜索',
    `director` varchar(100) DEFAULT NULL COMMENT '导演姓名',
    `actor` varchar(200) DEFAULT NULL COMMENT '主演',
    `image` varchar(200) DEFAULT NULL COMMENT '缩略图',
    `type` varchar(2) DEFAULT '2' COMMENT '视频类型:1.免费视频  2.付费视频',
    `url` varchar(256) DEFAULT NULL COMMENT '播放连接',
    `play_id` varchar(100) DEFAULT NULL COMMENT '播放id,阿里云提供'
);
insert  into `tb_movie`(`id`,`name`,`cid`,`description`,`keyword`,`director`,`actor`,`image`,`type`,`url`,`play_id`) values (1,'荞麦疯长',2,'20世纪90年代的小城市里，云荞(马思纯饰)想要“活成一部电影”；李麦(钟楚曦饰)期待着看得见希望的未来；吴风(黄景瑜饰)要在平凡的日子里背水一战。每个人都有着对未来的憧憬，并带着憧憬与不可知开始了异乡漂泊之路。“云彼有荞麦，无风亦可长”，底层年轻人背井离乡，逆风成长','背水一战 憧憬 漂泊','徐展雄','马思纯、钟楚曦、黄景瑜','/images/qiaomaifengzhang.jpg','2','','');
insert  into `tb_movie`(`id`,`name`,`cid`,`description`,`keyword`,`director`,`actor`,`image`,`type`,`url`,`play_id`) values (2,'花木兰',2,'中国的皇帝颁布法令，要求每家出一名男子服兵役，抵御北方侵略者。作为一名受人尊敬的战士的长女，花木兰（刘亦菲饰）站出来替生病的父亲应征入伍。她女扮男装，化名为花军，经历了种种考验，同时必须利用内在的力量，接纳自己真正的潜能。这是一场史诗般的旅程，让木兰蜕变为一名受人尊敬的战士，也赢得来自国家与骄傲的父亲的尊重','服兵役 尊重  神秘','妮基·卡罗','刘亦菲、甄子丹、巩俐、李连杰、安柚鑫、李截、郑佩佩、赵家玲、马志、唐莎娜','/images/huamulan.jpg','2','','');
insert  into `tb_movie`(`id`,`name`,`cid`,`description`,`keyword`,`director`,`actor`,`image`,`type`,`url`,`play_id`) values (3,'一出好戏',2,'《一出好戏》是由上海瀚纳影视文化传媒有限公司制作的喜剧片，由黄渤执导，黄渤、王宝强、舒淇、张艺兴、于和伟、王迅联袂主演。该片于2018年8月10日在中国内地上映，2020年4月2日在韩国上映。该片讲述了公司员工团建出游遭遇海难，众人流落在荒岛之上，为了生存他们共同生活，并面对一系列人性问题的寓言故事','喜剧 荒岛','黄渤','黄渤、张冀、郭俊立、查慕春、崔斯韦、邢爱娜、黄湛中','/images/yichuhaoxi.jpg','2','','');
insert  into `tb_movie`(`id`,`name`,`cid`,`description`,`keyword`,`director`,`actor`,`image`,`type`,`url`,`play_id`) values (4,'极线杀手',2,'别名「黑色凯萨」的世界顶尖杀手邓肯·维兹拉（麦斯·米科尔森饰）在享受退休生活之际，前雇主突然指控他不利于公司。他在身不由己的情况下重回本行，与一群杀手正面对决。这群心狠手辣的杀手不仅在年纪与体力占尽优势，更不计一切要取他性命。','复仇特工 机密','乔纳斯·阿克伦德','麦斯·米科尔森、凡妮莎·哈金斯、凯瑟琳·温妮克、罗伯特·梅耶、马特·卢卡斯','/images/jisushashou.jpg','2','','');
insert  into `tb_movie`(`id`,`name`,`cid`,`description`,`keyword`,`director`,`actor`,`image`,`type`,`url`,`play_id`) values (5,'东北往事：我叫刘海柱',2,'《东北往事：我叫刘海柱》讲述的是上世纪80年代初的东北，爱慕同事周萌的工人刘海柱因帮扶弱者，爱打抱不平，招惹上了不良分子并给自己的家人朋友带来危险。在经历被坏人打伤，被工厂开除等人生打击低谷后，刘海柱更面临危及生命的挑战。但此时，这个神奇的男人竟然还能重新站起来，面对一个个惊天的挑战，令世人震惊的完成着属于他的精彩传奇','男人 挑战','周润泽','许君聪、杨蕊嘉、刘奔、龚小钧、田璐','/images/dongbeiwangshi.jpg','2','','');
insert  into `tb_movie`(`id`,`name`,`cid`,`description`,`keyword`,`director`,`actor`,`image`,`type`,`url`,`play_id`) values (6,'最美逆行',2,'电影《最美逆行》是一部反映广东援鄂医务人员广州武汉高铁乘警们在本次疫情中坚难困苦，努力奋斗，可歌可泣的英雄故事','新冠病毒 疫情 ','范雨林  、邢博、胡树华','程琤、启杰领衔主演，王奎荣，迟志强，沙溢、黄一山、陈龙，刘烨，柳海龙、梁梓滔、肖午、田甜等','/images/zuimeinixing.jpg','2','','');
insert  into `tb_movie`(`id`,`name`,`cid`,`description`,`keyword`,`director`,`actor`,`image`,`type`,`url`,`play_id`) values (7,'复仇者联盟4：终局之战',2,'来自泰坦星的灭霸为了解决宇宙资源匮乏、人口暴增的问题，集齐了所有无限宝石，一个响指成功地使全宇宙生命随机减半。\r\n宇宙由于灭霸的行动而变得满目疮痍，但是五年之后，被困在量子领域的蚁人意外回到现实世界，他的出现为幸存的复仇者点燃了希望。无论前方将遭遇怎样的后果，幸存的复仇者都必须在剩余盟友的帮助下再一次集结，以逆转灭霸的所作所为，彻底恢复宇宙的秩序','复联4 漫威 机器人 地球 宇宙','安东尼·罗素、乔·罗素','小罗伯特·唐尼，克里斯·埃文斯，马克·鲁法洛，克里斯·海姆斯沃斯，斯嘉丽·约翰逊，杰瑞米·雷纳','/images/fulian4.jpg','2','','');
insert  into `tb_movie`(`id`,`name`,`cid`,`description`,`keyword`,`director`,`actor`,`image`,`type`,`url`,`play_id`) values (8,'蜘蛛侠：英雄归来',2,'彼得帕克（汤姆·赫兰德 Tom Holland 饰）在内战后受到了钢铁侠托尼斯塔克（小罗伯特·唐尼 Robert Downey Jr. 饰）的赏识，表面上，彼得进入托尼的公司成为了一名实习生，实际上，他和复仇者联盟的成员们一起接受了各种各样的训练。托尼虽然欣赏彼得的勇敢和正直，却并不认为他目前已经拥有加入复联的实力，他派出了特工哈皮（乔恩·费儒 Jon Favreau 饰）暗中观察，这让十分想证明自己的彼得感到万分焦躁。\r\n　　在对付两个银行劫匪的过程中，彼得发现劫匪们使用的是一种前所未见的新型武器，他孤身一人深入敌后顺藤摸瓜找到了幕后主使秃鹰（迈克尔·基顿 Michael Keaton 饰），让他彼得没有想到的是，秃鹰竟然是他爱慕的女生利兹（劳拉·海瑞尔 Laura Harrier 饰）的父亲','蜘蛛侠  回归 返校日','乔·沃茨',' 汤姆·赫兰德 / 小罗伯特·唐尼 / 玛丽莎·托梅 / 迈克尔·基顿 / 雅各布·巴特朗','/images/zhizhuxia.jpg','2','','');
insert  into `tb_movie`(`id`,`name`,`cid`,`description`,`keyword`,`director`,`actor`,`image`,`type`,`url`,`play_id`) values (9,'哥斯拉2：怪兽之王',2,'动物基因组学（帝王计划）研究机构的一个小组在一座庙里研究着魔斯拉。她从幼体中孵出，在一群雇佣兵袭击这个机构时，魔斯拉逃了出去。然后她在一个瀑布包了茧，随后破茧而出，完成了全形态生长。在2014年，哥斯拉被整个世界看到后，“帝王组织”在水下建了一个基地，在这个位于水下好几英里的水中基地中，他们研究着哥斯拉和其它可能存在的生物。那是他们真正的研究核心','基因 哥斯拉 怪兽','迈克尔·道赫蒂','凯尔·钱德勒，维拉·法梅加，米莉·博比·布朗，渡边谦，章子怡，布莱德利·惠特福德，莎莉·霍金斯','/images/gesila2.jpg','2','','');

