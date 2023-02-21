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
