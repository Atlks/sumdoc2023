bk db网状数据库

<!-- TOC -->

- [优点](#优点)
    - [简单直接，所有数据无需任何结构 hashmap保存即可](#简单直接所有数据无需任何结构-hashmap保存即可)
- [范例](#范例)
- [im](#im)
    - [node biztype (user msg  globe grp)](#node-biztype-user-msg--globe-grp)
    - [reg  login](#reg--login)
    - [frd lst](#frd-lst)
    - [msg lst](#msg-lst)
- [prblm](#prblm)
    - [rds net acc maybe slow ,,acc next node...](#rds-net-acc-maybe-slow-acc-next-node)
    - [loc rds betr..](#loc-rds-betr)
    - [so file mode node maybe more faster...](#so-file-mode-node-maybe-more-faster)
- [perf hance](#perf-hance)
    - [fuzhu cengci modl.... rds jsut cenci mdl...](#fuzhu-cengci-modl-rds-jsut-cenci-mdl)

<!-- /TOC -->

网状消息

# 优点

## 简单直接，所有数据无需任何结构 hashmap保存即可

这个比层次db还简单，怪不敌第一个出现的db



# 范例



User he msg是俩个实体。。他们之间的关系有收发关系。。Msg之间也有上下时间关联关系。。这样就构成了网状关系。。

查询用户的msgs。。。先定位到user根据唯一id，然后遍历收发消息关系。。。任何一个消息，上下诉求所有消息。。根据时间联系。。


定位唯一idkey，hash就很有用了。。。


如何加快速度，定位第一个msg ，就可以增加一个关系   first msg,,然后直接hash定位过去即可。。


# im

## node biztype (user msg  globe grp)

node shiji type just a json object str...

## reg  login

reg 需要回溯所有节点user，确定no same user...速度可能比较慢。。  建立一个globe节点，关系all user,,使用rds set实现即可。。。
login 根据uname定位到user节点，确定pwd正确与否。。


## frd lst

定位到user node。，，，find rlt frd, find one frd, then next rlt ,next ,,,,get  the lst...        
加快速度，缓存cache lst 到一个lst


## msg lst


定位到user node。，find msg rlt,,first msg rlt,,,find first msg,,,then sec msg...>>> refl >>reflf  ...
加快速度，缓存cache lst 到一个lst

# prblm

## rds net acc maybe slow ,,acc next node...
## loc rds betr..
## so file mode node maybe more faster...

one fldr,,inclued all nodefile,,just ok...fil name just node name...


# perf hance

## fuzhu cengci modl.... rds jsut cenci mdl...



5日 — 首先网状模型数据库有树形层次模型的思想，从父子关系的角度出发思考结点 ... 仍然要需要明确数据的存储结构，指出存取路径，而后来的关系型数据库较 

举例来说，层级数据模型和网状数据模型的数据访问路径受限制于底层的物理存储，只能基于节点的父子关系依次去遍历


结点可以有多个父节点。乍一看这定义，网状数据库与近年流行的图数据库颇相似，但是如果深入了解两者还是有很大的差别。首先网状模型数据库有树形层次模型的思想，从父子关系的角度出发思考结点之间的关系，这就意味着在网状模型数据库中无法在结点之间随意建立关系。同时，网状模型数据库对于结点的属性需要提前定义好，而图数据库允许随意向结点和边添加、编辑属性。


网状数据库模型对于层次和非层次结构的都能比较自然的模拟，在关系数据库出现之前，网状DBMS比层次DBMS更普遍和成功。在数据库发展史上，网状数据库占有重要地位。



关系型数据库
网状和层次数据库以及很好的解决了数据集中和共享问题，但是在易用性、数据独立性和抽象级别上仍有很大欠缺。如用户在对这两种数据库进行读写数据时，仍然要需要明确数据的存储结构，指出存取路径，而后来的关系型数据库较好的解决了这些问题。

进入80年代中后期，在经过十几年的发展和实际应用，关系型数据库在事务(transaction)、锁(lock)、并行(parallel)、并发(concurrence)、调度(schedule)、日志(log)等技术越来越成熟和完善，诞生了诸多产品，其中商业数据库有Oracle、IBM的DB2、微软的MSSQL，开源的Posgres等辉煌至今。