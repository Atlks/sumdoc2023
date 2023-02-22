bk perf db perf enhance.md

<!-- TOC -->

- [节流 去除不必要的消耗](#节流-去除不必要的消耗)
    - [nosql,, no trx,no iso lev](#nosql-no-trxno-iso-lev)
    - [减少io使用内存表模式](#减少io使用内存表模式)
    - [减少io消耗 持久化机制， 每秒定时机制 vs 每次机制](#减少io消耗-持久化机制-每秒定时机制-vs-每次机制)
    - [去除网络消耗 socket 可以提升50倍](#去除网络消耗-socket-可以提升50倍)
    - [减少io消耗 去除结构消耗使用层次文件模式](#减少io消耗-去除结构消耗使用层次文件模式)
- [多元化存储，优先最简单模式 file](#多元化存储优先最简单模式-file)
    - [file>zip file>>embed db](#filezip-fileembed-db)
    - [netty + sqlt](#netty--sqlt)
    - [rds mgdb](#rds-mgdb)
    - [pgsql mysql](#pgsql-mysql)
- [db mode ,分层网状 vs rds.nosql vs sqldb](#db-mode-分层网状-vs-rdsnosql-vs-sqldb)
- [cache调优](#cache调优)
    - [内存表 query cache](#内存表-query-cache)
    - [使用timer把附属业务剥离，小分田谷类似mq](#使用timer把附属业务剥离小分田谷类似mq)
    - [异步消息 应用解耦，](#异步消息-应用解耦)
- [开源](#开源)
    - [集群与多元化存储](#集群与多元化存储)

<!-- /TOC -->


# 节流 去除不必要的消耗


## nosql,, no trx,no iso lev

## 减少io使用内存表模式
## 减少io消耗 持久化机制， 每秒定时机制 vs 每次机制
改为每秒一次或更久

## 去除网络消耗 socket 可以提升50倍
## 减少io消耗 去除结构消耗使用层次文件模式



# 多元化存储，优先最简单模式 file

## file>zip file>>embed db
## netty + sqlt
## rds mgdb 
## pgsql mysql


# db mode ,分层网状 vs rds.nosql vs sqldb

# cache调优

## 内存表 query cache
## 使用timer把附属业务剥离，小分田谷类似mq
## 异步消息 应用解耦，


# 开源
## 集群与多元化存储



