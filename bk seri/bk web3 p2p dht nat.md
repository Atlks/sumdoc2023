bk web3 p2p dht nat


技术简介编辑 播报
纯点对点网络没有客户端或服务器的概念，只有平等的同级节点，同时对网络上的其它节点充当客户端和服务器。这种网络设计模型不同于客户端-服务器模型，在客户端-服务器模型中通信通常来往于一个中央服务器。

<!-- TOC -->

- [【网络技术】P2P技术原理浅析](#网络技术p2p技术原理浅析)
- [第三代 p2p dht vs 广播模式 vs 中心节点模式](#第三代-p2p-dht-vs-广播模式-vs-中心节点模式)
- [P2P 基本原理](#p2p-基本原理)
- [BT 工作原理简介   BT 是第三代的 P2P，](#bt-工作原理简介---bt-是第三代的-p2p)
- [dht的局势实现   Chord 算法 Kademlia 算法](#dht的局势实现---chord-算法-kademlia-算法)
- [简单实现](#简单实现)

<!-- /TOC -->

# 【网络技术】P2P技术原理浅析
 2021-04-22 | 阅读：1650次
1. 概述
1.1. P2P的概念
1.2. P2P产生的背景
1.3. P2P的优劣势
2. P2P分类
2.1. 根据中央化程度
2.2. 根据网络拓扑结构
3. NAT技术详解
3.1. 概述
3.2. NAT的优劣势
3.3. NAT穿透常见解决方案
3.4. 探针打洞基本原理
3.4.1. 基本原理
3.4.2. 同一个NAT网关内
3.4.3. 不通NAT网关内
3.4.4. 多层不通NAT网关内
3.4.5. 保活
3.4.6. TCP打洞
3.5. 常用的打洞协议框架
4. P2P下载技术原理
4.1. 概述
4.2. 两种P2P下载技术方案
4.2.1. BitTorrent技术方案
4.2.2. Kademlia技术方案


# 第三代 p2p dht vs 广播模式 vs 中心节点模式
 
　以“分散式杂凑表”(distributed hash tables )为诉求的Neonet技术，改变了P2P网络上的搜索方式，理论上可以更有效率的搜索更多的电脑，以及更容易找出少见的文件。同样的技术也用在eDonkey上──已经快要追上P2P服务龙头Kazaa的另一竞争对手。


　第一代依赖中心服务器

第一代、Napster
这一代的 p2p 只是把资源从服务器上拿掉了。中央服务器上只有一个目录。这个目录记录着哪个用户有哪些资源。要建立这样的一个目录，要求网络中的每个人都要告诉这个服务器：他有什么资源。

Kaka 发一个资源请求向中央服务器。
这个服务器然后检索目录，并告诉 Kaka，日天有他要的东西。
Kaka 直接去找日天下东西。
这样做，解决了高带宽需求（服务器只需要转发一些消息，而不用真格的提供资源），低资源利用率的问题（网络里谁都可以发挥作用了）。但是，他仍然面临严重的‘单一故障点’风险


第二代广播模式
第三代dht

更强的第三代

　　第三代的P2P 网络则是以eDonkey 及Morpheus为代表，此外还有一些较小的独立软件开发商，他们让这个工具比以前更为分散化。

　　“分散式杂凑表”的方法，基本上是对网络上某一特定时刻的文件进行快照(snapshot)，然后将这些信息分散到整个网络里。

　　为了找到特定的文件，搜索的要求先到达网络上的任何一台电脑上，然后这台电脑就会再将它转到另一台有更多文件信息的电脑。第三台电脑可能就拥有文件本身──或者也可能再继续转到其他有正确信息的电脑。


　整个过程有点像在照线索循序问路而找到正确方向，而不是路上随便到处抓人在问路。而每个地方里的网络相关信息，则会随着电脑及文件的加入而持续更新。



　StreamCast去年向哈佛学生Ben Wilkin及Francis Crick 买下这项技术，这两位前哈佛学生表示，他们的技术只要跳三至四次就可以在几百万台电脑的网络里找到任何文件，不管这个文件多么稀有。

　　这种技术也让一些应用有了新的前景，例如网络电话。Neonet与eDonkey 专注的都是文件交换，但是这种有效率的网络路由技术也可用于快速连接网络电话，他们表示。

　　“这可以用于所有的分散电脑工具上，而这也正是我们想走的。”Wilken表示，“这真的让我们再也不需要什么集中化的基础建设了。”


# P2P 基本原理
在 P2P 技术之前，网络中的所有人大多都是通过一种叫做“客户端 / 服务器”的模式被组织在一起。一个服务器向很多客户提供服务。

举个例子吧： kaka 有一台机子，里面存有全套的央视春晚，他想把他的挚爱分享给所有人。于是不论是通过广告还是别的什么方式，很多人知道了 kaka 有这个资源的消息。通过网络，他们就可以向 kaka 建立连接并从他的机子上下载。这样就形成


这个网络如何工作呢？比如 kaka 想要找一首叫‘走进新时代’的歌。一个基本交易要经过如下几步：

kaka 给他的邻居们每人发一个请求消息，‘你们丫谁有走进新时代？’
日天，威，curt 吐血，‘谁 tm 听这歌啊，我没有’。于是把 kaka 的消息转发给他们的邻居：日天发给小胖，威发给候泡，curt 发给路总。
当 kaka 邻居的邻居们收到这个消息后，同样，先看看自己有没有。比如，候泡有这个歌，他就可以给 kaka 发个消息：‘爷这有，来下吧’
卡卡于是可以直接去候泡那下载。

第一代、Napster  中央服务器上只有一个目录
这一代的 p2p 只是把资源从服务器上拿掉了。中央服务器上只有一个目录。这个目录记录着哪个用户有哪些资源。要建立这样的一个目录，要求网络中的每个人都要告诉这个服务器：他有什么资源。

Kaka 发一个资源请求向中央服务器。
这个服务器然后检索目录，并告诉 Kaka，日天有他要的东西。
Kaka 直接去找日天下东西。
这样做，解决了高带宽需求（服务器只需要转发一些消息，而不用真格的提供资源），低资源利用率的问题（网络里谁都可以发挥作用了）。但是，他仍然面临严重的‘单一故障点’风险。

第二代、非结构化 P2P

，还基本上以地毯式为基础。我介绍两种最常见的：
K-walkers: 第一个人先把消息发给 k 个邻居，具体多少都自己定。 然后从第一轮邻居开始都只把消息转发给他们的一个邻居。
水纹式（涟漪式）：先定一个限制。第一个人先发给第一层的邻居。如果第一层不灵，他们再发给他们的邻居。一旦找到资源就不再继续了。这样的话最坏的情况就是原始的地毯式。好点的情况就是，消息没到限制就找到了相应的资源，就停了。



# BT 工作原理简介   BT 是第三代的 P2P，

因为 P2P 又逐渐变成了客户端 / 服务器模式了。

于是，不论是学术界还是产业界都意识到，如果没有一种鼓励机制来刺激提供者，P2P 将失去其天生的魅力。在众多解决方案中 BT 脱颖而出，迅速发展了起来。BT 是简称，在国外可能会产生歧义，如 British Telecommunications 等等。所以大家尽量还是用全称，尤其是和外国人说的时候 Bit-Torrent。有人说 BT 是第三代的 P2P，我认为这是因为他把 P2P 的理念更加深入的实现了。

还有一点很重要就是，bittorrent 有自己的鼓励机制，就是说，你做的贡献大就会被鼓励，你不做贡献就会被惩罚。具体的操作是，每个人在下载的同时也上传。上传给谁呢？谁给我给的多，我就传给谁。而且我只传个前 4 名的（视具体软件具体分析，也可能是前 8 名或其他）。


bittorrent 大概的工作原理就是这样了，还有些细节这里不多说了。我个人认为 Bittorrent 的设计还是很不错的，但是其只适用于文件共享，或视频共享。然而网络中的资源又何止电影音乐？？绝大多数的资源是不可分的，bittorrent 的局限性还是相当大！ 另一点可悲的是，大多数宽带用户是 ADSL，也就是说他们的下载上传的带宽上限差别很大，比如，下载最大 2m/s，上传却只有 100k/s。这也限制了 bittorrent 的发挥。


2.2 朴素的 P2P 网络
P2P 网络需要解决的一个最重要的问题就是, 如何知道用户请求的资源位于哪个节点上. 在第一代 P2P 网络中, 人们设置了一台中央服务器来管理资源所处的位置. 当一个用户想要发布资源, 他需要告诉中央服务器它发布的资源信息和自己的节点信息; 当其他用户请求资源的时候, 需要先请求中央服务器以获取资源发布者的节点信息, 再向资源发布者请求资源.这种 P2P 网络的好处是效率高, 只需要请求一次中央服务器就可以发布或获取资源. 然而它的缺点也很明显


早期的另外一种 P2P 网络采取了不同的策略, 它不设置中央服务器; 当用户请求资源时, 它会请求它所有的邻接节点, 邻接节点再依次请求各自的邻接节点, 并使用一些策略防止重复请求, 直到找到拥有资源的节点. 也就是说, 这是一种泛洪搜索(Flooding Search).


这种 P2P 网络去除了中央服务器, 它的稳定性就强多了. 然而它太慢了. 一次查找可能会产生大量的请求, 可能会有大量的节点卷入其中. 一旦整个系统中的的节点过多, 性能就会变得很差.

2.3 分布式哈希表
为了解决这些问题, 分布式哈希表应运而生. 在一个有 
n
 个节点的分布式哈希表中, 每个节点仅需存储 
O
(
log
n
)
 个其他节点, 查找资源时仅需请求 
O
(
log
n
)
 个节点, 并且无需中央服务器, 是一个完全自组织的系统. 分布式哈希表有很多中实现算法, 第 3 节和第 4 节会详细介绍其中的两种. 这里我们先来看看它们共通的思想.


 路由算法
上面我们简述了地址系统, 以及如何发布和取回资源. 但是现在还有一个大问题: 如何找到负责某个特定 Key 的节点呢? 这里就要用到路由算法了. 不同的分布式哈希表实现有不同的路由算法, 但它们的思路是一致的.

首先每个节点会由若干个其他节点的联系方式(IP 地址, 端口), 称之为路由表. 一般来说一个有着 
n
 个节点的分布式哈希表中, 一个节点的路由表的长度为 
O
(
log
n
)
. 每个节点都会按照特定的规则构建路由表, 最终所有的节点会形成一张网络. 从一个节点发出的消息会根据特定的路由规则, 沿着网络逐步接近目标节点, 最终达到目标节点. 在有着 
n
 个节点的分布式哈希表中, 这个过程的转发次数通常为 
  自我组织(self-organization)
分布式哈希表中的节点都是由各个用户组成, 随时有用户加入, 离开或失效; 并且分布式哈希表没有中央服务器, 也就是说着这个系统完全没有管理者. 这意味着分配地址, 构建路由表, 节点加入, 节点离开, 排除失效节点等操作都要靠自我组织策略实现.

要发布或获取资源, 首先要有节点加入. 一个节点加入通常有以下几步. 首先, 一个新节点需要通过一些外部机制联系分布式哈希表中的任意一个已有节点; 接着新节点通过请求这个已有节点构造出自己的路由表, 并且更新其他需要与其建立连接的节点的路由表; 最后这个节点还需要取回它所负责的资源.

此外我们必须认为节点的失效是一件经常发生的事, 必须能够正确处理它们. 例如, 在路由的过程中遇到失效的节点, 会有能够替代它的其他节点来完成路由操作; 会定期地检查路由表中的节点是否有效; 将资源重复存储在多个节点上以对抗节点失效等. 另外分布式哈希表中的节点都是自愿加入的, 也可以自愿离开. 节点离开的处理与节点失效类似, 不过还可以做一些更多的操作, 比如说立即更新其他节点的路由表, 将自己的资源转储到其他节点等.


# dht的局势实现   Chord 算法 Kademlia 算法


3. Chord 算法
上一节简单介绍了 P2P 网络和分布式哈希表, 现在我们来讨论它的一个具体实现. 分布式哈希表有很多中实现, Ion Stoica 和 Robert Morris 等人在 2001 年的一篇论文中提出了 Chord 算法, 原论文已在文章末尾的参考资料中给出. Chord 算法比较简洁, 也很漂亮, 这里我们先介绍它.


4. Kademlia 算法
Petar Maymounkov 和 David Mazières 在 2002 年的一篇论文中提出了 Kademlia 算法, 原论文已在文章末尾的参考资料中给出. 相比与 Chord 算法, Kademlia 算法容错度更高, 效率也高于 Chord, 也更巧妙合理.

4.1 地址管理
Kademlia 同样使用 m 位整数作为节点和资源的唯一标识. 与 Chord 中的 "区间负责制" 不同, Kademlia 中的资源都是被离它最近的节点负责. 出于容




分布式哈希表 (DHT) 和 P2P 技术	1
1. 引言	2
2. P2P 网络的概述	2
2.1 传统 CS 网络和 P2P 网络	2
2.2 朴素的 P2P 网络	3
2.3 分布式哈希表	4
3. Chord 算法	5
3.1 地址管理	5
3.2 路由算法	6
3.3 自我组织	7
4. Kademlia 算法	9
4.1 地址管理	9
4.2 路由算法	10
4.3 自我组织	12


# 简单实现


 client 请求所有找到的节点群，3个把。。发送消息。。。
 请求节点列表。。预先设置一些节点列表。三五个把。。
 配置化写入。。



 简单的说，Kad是一种分布式哈希表（DHT）技术