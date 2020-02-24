CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `loginName` varchar(50) NOT NULL COMMENT '用户名',
  `loginPwd` varchar(150) NOT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `loginName_UNIQUE` (`loginName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `articles` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) NOT NULL COMMENT '文章标题',
  `content` TEXT NOT NULL COMMENT '文章内容',
  `authorId` int NOT NULL COMMENT '文章作者',
  `categoryId` int NOT NULL COMMENT '文章所属分类',
  `status` int NOT NULL COMMENT '文章状态',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tags` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '标签名称',
  `authorId` int NOT NULL COMMENT '标签作者',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` TEXT NOT NULL COMMENT '评论的内容',
  `authorId` int NOT NULL COMMENT '评论的作者',
  `articleId` int NOT NULL COMMENT '文章id',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '文章类别名称',
  `authorId` int NOT NULL COMMENT '文章类别的作者',
  `parentId` int NOT NULL COMMENT '父类别id',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `article_tag` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `articleId` int NOT NULL COMMENT '文章id',
  `tagId` int NOT NULL COMMENT '标签id',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `token_invalid` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int NOT NULL COMMENT '用户id',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `userId_UNIQUE` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `privileges` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(50) NOT NULL COMMENT '权限代码',
  `description` varchar(50) NOT NULL COMMENT '描述',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `description` varchar(50) NOT NULL COMMENT '描述',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_groups` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '用户组名称',
  `description` varchar(50) NOT NULL COMMENT '描述',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `role_privilege` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `roleId` int NOT NULL COMMENT '角色id',
  `privilegeId` int NOT NULL COMMENT '权限id',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int NOT NULL COMMENT '用户id',
  `roleId` int NOT NULL COMMENT '角色id',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_group_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userGroupId` int NOT NULL COMMENT '用户组id',
  `userId` int NOT NULL COMMENT '用户id',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `departments` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `parentId` int NOT NULL COMMENT '父id',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `department_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `departmentId` int NOT NULL COMMENT '部门id',
  `userId` int NOT NULL COMMENT '用户id',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rest_resources` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uri` varchar(200) NOT NULL COMMENT 'http uri',
  `httpMethod` varchar(10) NOT NULL COMMENT 'http method',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rest_resource_privilege` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `restResourceId` int NOT NULL COMMENT 'rest resource id',
  `privilegeId` int NOT NULL COMMENT '权限id',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `createdBy` int NOT NULL COMMENT '创建该记录的用户',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新的时间',
  `updatedBy` int NOT NULL COMMENT '最近一次更新该记录的用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

