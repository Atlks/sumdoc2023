bk prgrmlan编程语言概论 语句

<!-- TOC -->

- [语句（又称陈述式、叙述、述句、描述式、陈述句](#语句又称陈述式叙述述句描述式陈述句)
- [语句本身也具有内部结构（例如表达式）](#语句本身也具有内部结构例如表达式)
- [语句的分格与语言有关 sql lisp](#语句的分格与语言有关-sql-lisp)
- [简单和复合语句](#简单和复合语句)
- [语句和表达式区别](#语句和表达式区别)
- [分类 声明定义语句。赋值语句。。执行语句if块等](#分类-声明定义语句赋值语句执行语句if块等)

<!-- /TOC -->

# 语句（又称陈述式、叙述、述句、描述式、陈述句
电脑科学的编程中，语句（又称陈述式、叙述、述句、描述式、陈述句）是指令式编程语言中最小的独立元素，表达程序要执行的一些动作。 多数语句是以高级语言编写成一或多个语句的序列，用于命令电脑执行指定的一系列操作。 单一个语句本身也具有内部结构（例如表达式）

# 语句本身也具有内部结构（例如表达式）


# 语句的分格与语言有关 sql lisp
lap没有语句。全部函数链式连接起来

# 简单和复合语句

Python的语句分为两大类：简单和复合语句。 简单语句是指一逻辑行的代码。例如表达式语句、赋值语句和return语句等。 复合语句是指包含
简单语句（simple statement）编辑指定(赋值)C, Fortran: A = A + 5Pascal: A := A + 5调用C: CLEARSCREEN()回传：return 5;gotoC: goto 1断言C: assert(

复合语句（compound statement）编辑
• block：begin integer NUMBER; WRITE('Number? '); READLN(NUMBER); A:= A*NUMBER end
• if语句：if A > 3 then WRITELN(A) else WRITELN("NOT YET"); end
• Switch语句：switch (c) { case 'a'：alert(); break; case 'q'：quit(); break; }
• While循环：while NOT EOF DO begin READLN end
• Do-while循环：do { computation(&i); } while (i < 10);
• For循环：for A:=1 to 10 do WRITELN(A) end

# 语句和表达式区别

表示式编辑
在大多数编程语言中，语句与表达式互相对比，两者不同之处在于，语句是为了运作它们的副作用而执行；表达式则一定会传回评估后的结果，而且通常不产生副作用。在指令式编程中，Algol 68是语句可有回传值的少数几种语言。在混合指令式和函数式的编程语言（如Lisp）中，表达式和语句之间的分野并不存在：即段落中只为了副作用且不返回值，而依序执行的表达式，也被认为是“表达式”。在纯函数式编程中没有语句；一切都是可被评估的表达式。
在措辞中经常出现这样的区别：一个语句是被“执行”(execute)，而一个表达式是被“评估”或对其“求值”(evaluate)。一些语言中具备了exec和eval函数：比如在Python中，exec应用于语句，而eval应用于表达式

Python的语句分为两大类：简单和复合语句。
简单语句是指一逻辑行的代码。例如表达式语句、赋值语句和return语句等。
复合语句是指包含、影响或控制一组语句的代码。例如if、try和class语句等。
表达式本身可以作为表达式语句，也能作为赋值语句的右值或if语句的条件等，所以表达式可以作为语句的组成部分，但不是必须成分（例如continue语句）。
语句就是一条完整的指令，可以包含关键词、运算符、变量、常量以及表达式。语句一般可分为：

语句就是一条完整的指令，可以包含关键词、运算符、变量、常量以及表达式。语句一般可分为：

语句就是一条完整的指令，可以包含关键词、运算符、变量、常量以及表达式。语句一般可分为：
# 分类 声明定义语句。赋值语句。。执行语句if块等