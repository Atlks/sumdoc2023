bk dsn 更加扁平化的数据库设计

命名根据ui。。访问路径一体化，更加的可读性。。
uid_biz  可以合在一起，，直接作为容器名称。。。记录分别在里面。。

uid_biz也可以单独节点记录，比如userinfo记录。。


uid_userinfo.json
uid_frids  容器里面放好友列表
uid_frdName_msg 容器里面放msg
uid_grps 放群组列表
grpname_msgs 放群组消息


grpname_msgs_202211 放月份msg记录。。或者单独一天作为记录容器
grpname_msgs_20221122


免索引折腾免折腾。。与ui一一对应，，方便设计存储。。

数据全部是json模式。


 NoSQL 胜在 schema 灵活

