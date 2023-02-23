bk prgrmlan->istrct  zilin 



[toc]

<!-- TOC -->

- [idx](#idx)
- [分类法1  全局命令 未认证状态命令 未认证状态命令 选中状态指令](#分类法1--全局命令-未认证状态命令-未认证状态命令-选中状态指令)
- [----常见分类法2 by fuc](#----常见分类法2-by-fuc)
- [数据传送指令 。移动 赋值类](#数据传送指令-移动-赋值类)
- [运算指令  (算术   逻辑  移位运算)](#运算指令--算术---逻辑--移位运算)
- [控制指令 。。流程跳转比较指令](#控制指令-流程跳转比较指令)
- [Oo指令 (oo lan need)](#oo指令-oo-lan-need)
- [other instkt](#other-instkt)
- [comm lan inst amt](#comm-lan-inst-amt)
- [ref](#ref)

<!-- /TOC -->



指令集（IA：InstructionSet）是指CPU指令系统所能识别（翻译）执行的全部指令的集合



# idx

[TOC]

# 分类法1  全局命令 未认证状态命令 未认证状态命令 选中状态指令

指令的作用的权限吧。 全局命令 未认证状态命令 未认证状态命令 选中状态指令
1.在任何状态下都有效的指令（全局命令）：CAPALIBILY，NOOP，LOGOUT。
2.未认证状态下有效的指令（未认证状态命令）：STARTTLS,AUTHENTICATE，LOGIN和全局命令。
3.认证状态下有效的指令（未认证状态命令）：SELECT，EXAMINE，CREATE，DELETE，RENAME，SUBSCRIBE，UNSUBSCRIBE，LIST，LSUB，STATUS，APPEND和全局命令
4.在选中状态下有效的指令（选中状态指令）：CHECK，CLOSE，EXPUNGE，SEARCH，FETCH，STORE，COPY，UID和全局命令、认证状态命令

# ----常见分类法2 by fuc

# 数据传送指令 。移动 赋值类

栈顶元素数学操作及移位操作系列

# 运算指令  (算术   逻辑  移位运算)

# 控制指令 。。流程跳转比较指令

# Oo指令 (oo lan need)

5.1. 十六、域操作指令系列	14
5.2. 十七、方法操作命令系列	14
5.3. 十九、new及数组系列	15
5.4. 二十、异常抛出指令	15
5.5. 二十一、对象操作指令	



# other instkt

比较特殊的指令，如特权指令、多处理器控制指令和等待、停机、空操作等指令。

类型转换指令

# comm lan inst amt



目前5.3.2版本的PHP中，opcode一共有154种，可以在

JavaVM 有約 200 个指令（Instruction），每个指令都是 1 byte 的 opcode（操作码），后面接不等数目的参数；.NET CLR 有超过220个指令，但是有些指令使用相同的 opcode，所以 opcode 的数目比指令数略少。特



Php的静静136条指令，jvm clr都二百多了。。



# ref

Atitti  指令集 与操作符 运算符 与函数

Atitit 汇编语言指令集合

Atitit..net clr il指令集

Atitit. Php opcode 指令集合

Atitit .jvm 虚拟机指令详细解释