


<!-- TOC -->

- [--------------------------bsic arch](#--------------------------bsic-arch)
- [ui h5 chrome app mode](#ui-h5-chrome-app-mode)
- [prgrm js python](#prgrm-js-python)
- [api rmt api rest spark.java](#api-rmt-api-rest-sparkjava)
- [db store (rds mgdb)](#db-store-rds-mgdb)
- [-------------------other](#-------------------other)
- [mainProcessMode+dbLog+timerChildProcess](#mainprocessmodedblogtimerchildprocess)
- [im long conn  (http+block queue ,rds list +block api)](#im-long-conn--httpblock-queue-rds-list-block-api)
- [uid key 发表方案](#uid-key-发表方案)
- [auth   aes(id-expireDate) mode... not need usertable](#auth---aesid-expiredate-mode-not-need-usertable)
- [session keep ...use cookie token moshi no session mode](#session-keep-use-cookie-token-moshi-no-session-mode)
- [sync first vs async](#sync-first-vs-async)
- [自动化浏览器 webdriver Selenium打开了完整的Chrome实例](#自动化浏览器-webdriver-selenium打开了完整的chrome实例)
- [client  desktop mode  -app mode](#client--desktop-mode---app-mode)
- [use startrnd thing](#use-startrnd-thing)
    - [secur https](#secur-https)
    - [perf http2 http3](#perf-http2-http3)
    - [ftp flashfxp](#ftp-flashfxp)
- [--------------HTTP 的基本性质](#--------------http-的基本性质)
- [HTTP 是简单的  报文可读](#http-是简单的--报文可读)
- [HTTP 是可扩展的 header）让协议扩展变得非常容易](#http-是可扩展的-header让协议扩展变得非常容易)

<!-- /TOC -->



# --------------------------bsic arch
# ui h5 chrome app mode
# prgrm js python 
# api rmt api rest spark.java
# db store (rds mgdb)


# -------------------other

# mainProcessMode+dbLog+timerChildProcess
regProcess  ,,,childProcess( timer,,add score like this)
just like mq fltr mode...
or use fltre mode also ok...
# im long conn  (http+block queue ,rds list +block api)

，SSE（server-sent events also good

服务器端如何发送事件流
服务器端发送的响应内容应该使用值为text/event-stream的 MIME 类型。每个通知以文本块形式发送，并以一对换行符结尾。有关事件流的格式的详细信息，请参见事件流格式。

演示的PHP代码如下：

 
header("Content-Type: text/event-stream");

 
while (true) {
   //////  output html
     echo "event: ping\n";
  
  echo 'data: {"time": "' . $curDate . '"}';
 
 
  flush();
  

# uid key 发表方案

数据迁移，直接全量迁移，多余的作为垃圾数据无所谓了。不要按照key去choose数据挑出麻烦的


#   auth   aes(id-expireDate) mode... not need usertable
#  session keep ...use cookie token moshi no session mode

# sync first vs async

# 自动化浏览器 webdriver Selenium打开了完整的Chrome实例

。换句话说，这是一个自动化的解决方案


# client  desktop mode  -app mode


chrome以窗口模式运行(无地址栏与标签栏 - CSDN博客

csdn.net
https://blog.csdn.net › wlphyl › article › details
2016年9月5日 — 目前Chrome有1000个左右的命令行参数。 一、使用方法：. 1. 更改快捷方式，使用此法须要用此快捷方式启动chrome才会带参数 ..

full screen app ....  --kiosk mode

chrome  --app=d:/i.htm


# use startrnd thing

##  secur https
## perf http2 http3

HTTP 是一种简单可扩展的协议，其 Client-Server 的结构以及轻松扩展标头信息的能力使得 HTTP 可以和 Web 共同发展。

在 2015 年 5 月正式标准化后，HTTP/2 取得了极大的成功，在 2022 年 1 月达到峰值，占所有网站的 46.9%（见这些统计数据）。高流量的站点最迅速的普及，在数据传输上节省了可观的成本和支出。

这种迅速的普及率很可能是因为 HTTP2 不需要站点和应用做出改变：使用 HTTP/1.1 和 HTTP/2 对他们来说是透明的。拥有一个最新的服务器和新点的浏览器进行交互就足够了。只有一小部分群体需要做出改变，而且随着陈旧的浏览器和服务器的更新，而不需 Web 开发者做什么，用的人自然就增加了。


HTTP/3——基于 QUIC 的 HTTP
HTTP 的下一个主要版本，HTTP/3 有这与 HTTP 早期版本的相同语义，但在传输层部分使用 QUIC (en-US) 而不是 TCP。到2022年10月，26% 的网站正在使用 HTTP/3。

QUIC 旨在为 HTTP 连接设计更低的延迟。类似于 HTTP/2，它是一个多路复用协议，但是 HTTP/2 通过单个 TCP 连接运行，所以在 TCP 层处理的数据包丢失检测和重传可以阻止所有流。QUIC 通过 UDP 运行多个流，并为每个流独立实现数据包丢失检测和重传，因此如果发生错误，只有该数据包中包含数据的流才会被阻止。


HTTP/2 简介
2015 年，HTTP/2 发布。HTTP/2 是现行 HTTP 协议（HTTP/1.x）的替代，但它不是重写，HTTP 方法/状态码/语义都与 HTTP/1.x 一样。HTTP/2 基于 SPDY，专注于性能，最大的一个目标是在用户和网站间只用一个连接（connection）。从目前的情况来看，国内外一些排名靠前的站点基本都实现了 HTTP/2 的部署，使用 HTTP/2 能带来 20%~60%的效率提升。

## ftp flashfxp

# --------------HTTP 的基本性质
# HTTP 是简单的  报文可读
虽然下一代 HTTP/2 协议将 HTTP 消息封装到了帧（frame）中，HTTP 大体上还是被设计得简单易读。HTTP 报文能够被人读懂，还允许简单测试，降低了门槛，对新人很友好。

# HTTP 是可扩展的 header）让协议扩展变得非常容易
在 HTTP/1.0 中出现的 HTTP 标头（header）让协议扩展变得非常容易。只要服务端和客户端就新标头达成语义一致，新功能就可以被轻松加入进来。

HTTP 是一种简单可扩展的协议，其 Client-Server 的结构以及轻松扩展标头信息的能力使得 HTTP 可以和 Web 共同发展。