-- vue3_admin_template.system_menu definition

CREATE TABLE `system_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int NOT NULL DEFAULT '0' COMMENT '目录id',
  `button_id` int NOT NULL DEFAULT '0' COMMENT '按钮id',
  `type` int NOT NULL DEFAULT '0' COMMENT '菜单类型 0目录，1菜单，2按钮',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '目录 ｜ 菜单 | 按钮名称',
  `icon` varchar(255) NOT NULL DEFAULT '' COMMENT '目录或菜单图标',
  `hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0：显示 1：隐藏 （当设置为隐藏时，将不显示当前菜单或目录',
  `show` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0：隐藏 1：显示 （当设置为显示时，当前目录下只有一个菜单，那么将显示目录',
  `cache` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0：不缓存  1：缓存 （当设置为缓存时，将缓存当前页面的数据',
  `breadcrumb` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0：隐藏 1：显示 （当设置为隐藏时，将不会出现在面包屑中【仅在经典布局中】',
  `affix` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0：false 1：true （如果设置为true，它则会固定在tags-view中(默认 false,只在经典布局中展示)',
  `auth` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0：false 1：true（当设置为false时，将不认证权限直接返回当前菜单或目录',
  `active_menu` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '如果设置了path，侧边栏会高亮显示你设置的路径',
  `layout` varchar(255) NOT NULL DEFAULT '' COMMENT '布局路由名称',
  `catalogue_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '目录路径',
  `path` varchar(255) NOT NULL DEFAULT '' COMMENT '菜单路径',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态 0：正常 1：禁用',
  `creator` int NOT NULL DEFAULT '0' COMMENT '创建用户id',
  `updater` int NOT NULL DEFAULT '0' COMMENT '更新用户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统菜单表';


-- vue3_admin_template.system_role definition

CREATE TABLE `system_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `type` int NOT NULL DEFAULT '0' COMMENT '角色类型 0普通角色，1超级管理员',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '角色状态 0：正常 1：禁用',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态 0：正常 1：禁用',
  `creator` int NOT NULL DEFAULT '0' COMMENT '创建用户id',
  `updater` int NOT NULL DEFAULT '0' COMMENT '更新用户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色表';


-- vue3_admin_template.system_role_menu definition

CREATE TABLE `system_role_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL DEFAULT '0' COMMENT '角色id',
  `menu_id` int NOT NULL DEFAULT '0' COMMENT '菜单id',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '角色状态 0：正常 1：禁用',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态 0：正常 1：禁用',
  `creator` int NOT NULL DEFAULT '0' COMMENT '创建用户id',
  `updater` int NOT NULL DEFAULT '0' COMMENT '更新用户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色菜单关系表';


-- vue3_admin_template.`system_user` definition

CREATE TABLE `system_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名称',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `role_id` int NOT NULL DEFAULT '0' COMMENT '角色id',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户状态 0：正常 1：禁用',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态 0：正常 1：禁用',
  `creator` int NOT NULL DEFAULT '0' COMMENT '创建用户id',
  `updater` int NOT NULL DEFAULT '0' COMMENT '更新用户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户表';


-- vue3_admin_template.system_user_menu definition

CREATE TABLE `system_user_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL DEFAULT '0' COMMENT '用户id',
  `menu_id` int NOT NULL DEFAULT '0' COMMENT '菜单id',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '角色状态 0：正常 1：禁用',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态 0：正常 1：禁用',
  `creator` int NOT NULL DEFAULT '0' COMMENT '创建用户id',
  `updater` int NOT NULL DEFAULT '0' COMMENT '更新用户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色菜单关系表';