bk dsn secury


消息安全性
#  通信安全 https
即消息传输层面的安全，在传输过程中，通过协议和加密算法进行保证，使传输过程中的消息是不可逆的。恶意用户即使抓到网络传输的包也没有办法破译出来。

# 会话安全级别安全    时间级别安全
加密级别最好做到会话级别，即一个用户的一次长链接中使用固定的密钥，即使同一个用户多次登录，或者在不同时间点登录，加密的密钥都是不一样的。

数据存储安全
这里主要涉及：

如何实现数据的高效存储和访问 涉及到序列化和反序列化的算法和数据结构
如何实现尽量小的存储空间 可以考虑配合一定的压缩算法进一步减少序列化后的数据的大小
数据的加密存储 需要有统一的密钥管理体系来保证数据的加密存储以及密钥的安全性，保证被拖库后数据仍然是安全的

# 数据脱敏 不要只是 自增ID
一般的IM中的用户账号体系都是采用自增ID来设计的，这样的坏处是可以被猜测已经容易受到攻击。 可以通过在业务层做一个mapping来避免暴露用户的核心信息，如uid、手机号等。具体算法可以使用混淆算法或自定义混淆数据库里的数据做映射，一般都是使用无法猜测的定长字符串的形式生成映射库里的数据。

防止重放
数据在整个传输的过程中可能存在安全风险，比方第三方的攻击，以及数据在网络流转过程中被拷贝和重放的潜在安全风险，这些在设计过程中都需要被规避掉。