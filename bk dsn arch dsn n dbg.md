bk dsn arch dsn  n dbg

<!-- TOC -->

- [------------arch smp](#------------arch-smp)
- [smp prgrm ,script lan](#smp-prgrm-script-lan)
- [smp lib.](#smp-lib)
- [smp  双层结构 ,invoke deep nomore 3](#smp--双层结构-invoke-deep-nomore-3)
- [smp db, rds mgdb  sqlite file](#smp-db-rds-mgdb--sqlite-file)
- [smp net io...http vs socket](#smp-net-iohttp-vs-socket)
    - [控制调用链深度3层 biz db](#控制调用链深度3层-biz-db)
    - [cs arch more easy](#cs-arch-more-easy)
    - [h5 rest mgdb rds](#h5-rest-mgdb-rds)
    - [script vs complit java](#script-vs-complit-java)
    - [process vs oo](#process-vs-oo)
    - [single vs mlt file mode](#single-vs-mlt-file-mode)
    - [biz+util mode..](#bizutil-mode)
    - [mlt lan mlt micrsvr  ..svrless mode](#mlt-lan-mlt-micrsvr--svrless-mode)
- [...... DBG.....](#-dbg)
    - [INVOKE process stack trace N cur varlst](#invoke-process-stack-trace-n-cur-varlst)
    - [debug mode  vs  log mode](#debug-mode--vs--log-mode)
        - [db log as log](#db-log-as-log)
        - [console print mode](#console-print-mode)
        - [sql as biz script can easy log in mysql open log file..](#sql-as-biz-script-can-easy-log-in-mysql-open-log-file)
        - [web acc log url as biz scrpt](#web-acc-log-url-as-biz-scrpt)
        - [vm lejye api write log...](#vm-lejye-api-write-log)
    - [使用拦截器来log  埋点](#使用拦截器来log--埋点)
        - [trigger  ,web filter ，db trigger](#trigger--web-filter-db-trigger)
        - [vm invoke 语句api接口](#vm-invoke-语句api接口)
        - [db driver 拦截埋点](#db-driver-拦截埋点)
- [。。。。dsl log mode](#dsl-log-mode)
    - [web accs log   url log and param](#web-accs-log---url-log-and-param)
    - [vm log medthod)(param)](#vm-log-medthodparam)
    - [sql log](#sql-log)

<!-- /TOC -->

编程中最困难   架构设计，紧随其后的是Debug（代码调试）


认为编程中最困难的是什么呢？处在不同岗位，各有各的看法。老外程序员对这件事也是争论不休，于是他们发起了一个投票，投票结果显示，排名第一的是程序初始的架构设计，紧随其后的是Debug（代码调试），而得票最低的是实现新功能排在最后一名。这个结果在外人看来不可思议，开发新功能怎么可能是最简单的呢？而在程序员看来，应该完全在情理之中，尤其是Debug的难度，看来中外程序员所见略同。
————————————————
第三  digging api 你需要的


# ------------arch smp

# smp prgrm ,script lan
# smp lib.
# smp  双层结构 ,invoke deep nomore 3
# smp db, rds mgdb  sqlite file
# smp net io...http vs socket

## 控制调用链深度3层 biz db

## cs arch more easy

## h5 rest mgdb rds

## script vs complit java
## process vs oo

## single vs mlt file mode

##  biz+util mode..
## mlt lan mlt micrsvr  ..svrless mode

# ......DBG.....    

##  INVOKE process stack trace N cur varlst

##  debug mode  vs  log mode
### db log as log
### console print mode
### sql as biz script can easy log in mysql open log file..
### web acc log url as biz scrpt
### vm lejye api write log...


## 使用拦截器来log  埋点
### trigger  ,web filter ，db trigger
### vm invoke 语句api接口
### db driver 拦截埋点


# 。。。。dsl log mode

## web accs log   url log and param
##    vm log medthod)(param)
##   sql log   