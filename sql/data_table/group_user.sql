-- 增加管理组sql
insert into group_user(group_code, group_name) values ('001', '管理组');

-- 增加权限sql,还需修改用户表对应的用户组编码
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 27);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 28);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 29);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 30);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 31);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 33);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 35);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 36);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 38);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 39);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 40);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 41);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 53);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 70);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 71);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 72);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 78);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 80);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 81);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 82);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 83);
insert into group_permission(pk_group, group_code, group_name, pk_resource)
      values (1, '001', '管理员', 84);