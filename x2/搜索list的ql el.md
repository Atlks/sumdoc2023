搜索list的ql el




Aviator 的基本过程是将表达式直接翻译成对应的 java 字节码执行，整个过程最多扫两趟（开启执行优先模式，如果是编译优先模式下就一趟），这样就保证了它的性能超越绝大部分解释性的表达式引擎，测试也证明如此；其次，除了依赖 commons-beanutils 这个库之外（用于做反射）不依赖任何第三方库，因此整体非常轻量级，整个 jar 包大小哪怕发展到现在 5.0 这个大版本，也才 430K。

回到上面的风控规则引擎，如果我们想实现订单金额大于100元并且用户属于vip这个规则在aviator中应该怎么做呢？

public static void main(String[] args) {
        //首先构造参数
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("orderAmount", 101);
        env.put("vip", true);
        // 执行表达式逻辑
        Boolean result = (Boolean) AviatorEvaluator.execute("orderAmount > 100 && vip", env);
        System.out.println(result);
    }
    // 输出true
复制
可以看见首先我们构造用户是否是vip和订单金额这两个属性，接下来只需要定义 orderAmount>100&&vip 这句表达式，就可以得到我们想到的结果。所以只要运营人员或者产品想到不同的规则，我们这边都可以马上进行配置，可以将这一条规则存到数据库里面，然后进行读取，执行。对于有界面的需求话需要和前端进行配合，让前端的一些控件能自动转换成这种表达式语言，就能完成自动化。

要不放入内存表，sql查询，embdb作为嵌入库。。
