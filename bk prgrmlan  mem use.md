bk prgrmlan > mem use



# 三大段   代码区   静态数据区   动态数据区堆栈区

 堆栈区 只针对堆栈机。。如果你是基于寄存器状态机，则不需要堆栈，使用寄存器

汇编的内存使用
数据段（静态变量 数据
代码段 方法区
堆栈（动态数据





⦁	　此程式执行时，关键的记忆体有三种，分別是：
　　1、Managed Heap：这是动态配置（Dynamic Allocation）的记忆体，由 Garbage Collector（GC）在执行时自动管理，整个Process 共用一个 Managed Heap。
　　2、Call Stack：这是由 .NET CLR 在执行时自动管理的记忆体，每个 Thread 都有自己专属的 Call Stack。每调用一次 method，就会使得Call Stack 上多了一个 Record Frame；调用完毕之后，此 Record Frame 会被丢弃。一般來说，Record Frame 內记录着 method 参数（Parameter）、返回位址（Return Address）、以及区域变数（Local Variable）。Java VM 和 .NET CLR 都是使用 0, 1, 2… 编号的方式來识別区别变数。
　　3、Evaluation Stack：这是由 .NET CLR 在执行时自动管理的记忆体，每个 Thread 都有自己专属的 Evaluation Stack。前面所谓的堆叠式虚拟机器，指的就是这个堆叠。



# 清理方式    移动复制法 标记法  引用计数法

引用计数法主要是调用方法 stack释放的时候使用

# java

元空间 代码区与    heap数据区域。。   stack区域堆栈区 