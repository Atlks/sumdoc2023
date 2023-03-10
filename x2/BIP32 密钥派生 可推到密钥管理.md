BIP32 密钥派生 可推导密钥管理

<!-- TOC -->

- [JBOK密钥管理法(一堆私钥)](#jbok密钥管理法一堆私钥)
- [可推导密钥bip32模式](#可推导密钥bip32模式)
- [秘钥路径及BIP44](#秘钥路径及bip44)
- [密钥的备份，可以采用助记词（BIP39）模式来备份](#密钥的备份可以采用助记词bip39模式来备份)
- [全程无需交换密钥的动态加密法](#全程无需交换密钥的动态加密法)
- [通过公钥推导出私钥](#通过公钥推导出私钥)
- [如何推导出自己的历史上用过的私钥](#如何推导出自己的历史上用过的私钥)

<!-- /TOC -->

# JBOK密钥管理法(一堆私钥) 
每个密钥都是从不同的随机数独立生成的，密钥彼此之间没有任何关系，这种模式也被称为   （Just a Bunch Of Keys）； 但密钥一旦丢失很难恢复了。。

# 可推导密钥bip32模式

为了解决这种麻烦，就有了BIP32 提议： 根据一个种子通过分层确定性推导的方式得到n个私钥，，私钥可以推导出来，如图：

其中所有的密钥都是从一个主种子（seed）生成而来，所有的密钥之间都是相互关联的，如果有原始种子，就可以再次生成全部的密钥；生成确定性的密钥，可以使用不同的密钥推导方式。目前最常用的推导方法是 树状结构，。。也可以按其他方式来推导




# 秘钥路径及BIP44

一句话概括下BIP44就是：给BIP32的分层路径定义规范
通过这种分层（树状结构）推导出来的秘钥，通常用路径来表示，每个级别之间用斜杠 / 来表示，由主私钥衍生出的私钥起始以“m”打头。因此，第一个母密钥生成的子私钥是m/0。第一个公共钥匙是M/0。第一个子密钥的子密钥就是m/0/1，以此类推。

BIP44则是为这个路径约定了一个规范的含义，BIP0044指定了包含5个预定义树状层级的结构：
m / purpose' / coin' / account' / change / address_index
m是固定的, Purpose也是固定的，值为44（或者 0x8000002C）


# 密钥的备份，可以采用助记词（BIP39）模式来备份

也可以直接备份密钥

# 全程无需交换密钥的动态加密法

根据对方uid推导出应该使用的公钥用来消息加密

比如可以利用对方uid作为原始种子，利用算法推导出二级种子。再次推导出可以生成公钥，然后用此公钥加密发给对方。。无需网络交换

对方收到后，利用自己的uid，生成种子，再次推导出解密的私钥，解密信息

这样全程无需交换密钥

甚至可以每次可以随机选取某个公钥 去加密，动态加密达到消息级别


# 通过公钥推导出私钥

在知道种子的情况下，可以从公钥推导出私钥。。可以生成若干公钥私钥对，反向查询，从而可以从公钥推导出要解密的私钥。用来解密消息。。
在各种丢失私钥场合，可以用来恢复私钥

# 如何推导出自己的历史上用过的私钥

可以利用一个算法推导出自己的seed种子， 。然后推导出自己的历史上私钥，可以用来应对自己私钥丢失 而需要恢复的情况。。解密历史信息



