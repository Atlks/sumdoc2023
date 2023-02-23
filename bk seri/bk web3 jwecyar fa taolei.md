bk web3 jwecyar fa taolei

<!-- TOC -->

- [dimai gaomai](#dimai-gaomai)
- [diya daikwe zaici dimai gaomai](#diya-daikwe-zaici-dimai-gaomai)
- [buy more low preice ast...  eth chge apt like ths](#buy-more-low-preice-ast--eth-chge-apt-like-ths)
- [stake vs lp take](#stake-vs-lp-take)
- [dex taolei..lp take](#dex-taoleilp-take)
- [减少无常损失方法  配备镜像资产](#减少无常损失方法--配备镜像资产)
- [任意比例amm 防止汇率损失](#任意比例amm-防止汇率损失)

<!-- /TOC -->

# dimai gaomai
# diya daikwe zaici dimai gaomai 
#  buy more low preice ast...  eth chge apt like ths
# stake vs lp take
# dex taolei..lp take

这种现象称为「套利 损失」。每当交易价格发生变动，就会出现套利者窃取廉价资产，直到资金池的定价达到正确为止。 （这些损失是「暂时的」，因为如果后来真实交易价格恢复为 1：1，那么就好像你从未损失过这笔钱，和开始相比。）


你面对的还有套利者：他们是知情订单流。他们在选择价格错误的资金池。某种程度上说，他们其实是在帮助 Uniswap 将交易价格拉回正轨。但另一方面，他们是把 LP 的钱转移到他们自己的兜里。


但 Uniswap 无法区分这两种流！

Uniswap 不清楚一笔订单是来自懵懂无知的散户，还是某个套利者。无论市场条件是什么情况，它只遵循 x * y = k 的方程。


由于AMM不会自动调整汇率，因此套利者需要买入价格偏低的资产或卖出价格偏高的资产，直到AMM提供的价格与外部市场的市场价格相匹配。套利者获取的利润是从流动性提供者的口袋里抽走的，这将对流动性提供者造成损


在上面的示例中，由于可能会因为其他交易所的交易活动，或者ETH的市场价格发生变化，导致AMM内的暂时性亏损。


之所以称之为 "暂时性 "亏损，是因为只要AMM内的代币相对价格恢复到原来的价值，亏损就会消失，流动性提供者就会把赚取的费用作为利润保留下来



通过使用Chainlink oracle，Bancor V2池能够保持准确的汇率，即使代币的定价因外部市场价格变化而出现分歧。oracle不是由套利者固定汇率，而是提供价格更新，调整AMM的权重，使内部汇率与外部市场价格相匹配。这样做的好处是，套利者不再以暂时性亏损的形式从流动性提供者那里抽走价值。

 从去中心化的程度来看，DEX可以分为混合型DEX和完全去中心化的DEX，混合型DEX去中心化程度相对不高。而完全去中心化的DEX，目前行业当中主流的有订单簿和自动化做市商的模式。


# 减少无常损失方法  配备镜像资产

 这样代币价格波动毫无波澜，对冲了。。。

# 任意比例amm 防止汇率损失

Balancer 允许用户以任意比例创建多达八种不同资产的动态流动资金池，从而扩展了 Uniswap 的限制，从而扩展了 AMM 的灵活性

 image.png