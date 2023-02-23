bk web3  拜占庭容错（BFT）共识机制


拜占庭容错（BFT）共识机制


钱包
BIP32：定义 Hierarchical Deterministic wallet (简称 “HD Wallet”)，是一个系统可以从单一个 seed 产生一树状结构储存多组 keypairs（私钥和公钥）。好处是可以方便的备份、转移到其他相容装置（因为都只需要 seed），以及分层的权限控制等。
BIP39：将 seed 用方便记忆和书写的单字表示。一般由 12 个单字组成，称为 mnemonic code(phrase)，中文称为助记词或助记码。例如：


BIP44：基于 BIP32 的系统，赋予树状结构中的各层特殊的意义。让同一个 seed 可以支援多币种、多帐户等。各层定义如下：
m / purpose' / coin_type' / account' / change / address_index

  //purporse': 固定值44', 代表是BIP44
  //coin_type': 这个代表的是币种, 可以兼容很多种币, 比如BTC是0, ETH是60,TRX是195
  //account: 代表这个币的账户索引，从0开始
  //change: 常量0用于外部(收款地址)，常量1用于内部（也称为找零地址）。外部用于在钱包外可见的地址（例如，用于接收付款）。内部链用于在钱包外部不可见的地址，用于返回交易变更。 (所以一般使用0)
  //address_index: 地址索引，从0开始，代表生成第几个地址
  //btc一般是 m/44'/0'/0'/0
  //eth一般是 m/44'/60'/0'/0
  //trx一般是 m/44'/195'/0'/0