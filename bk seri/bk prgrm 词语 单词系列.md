bk prgrm 词语 单词系列 标识符 分隔符 关键词 运算符 字面值

<!-- TOC -->

- [1. 词法标记序列（具有分配和识别含义的字符串）](#1-词法标记序列具有分配和识别含义的字符串)
- [2. 俩分法分类为  标识符单词 + 分隔符](#2-俩分法分类为--标识符单词--分隔符)
- [3. token标记 分类 ：  标识符单词  系统关键词 分隔符号  数学运算符号  字面值 注释](#3-token标记-分类---标识符单词--系统关键词-分隔符号--数学运算符号--字面值-注释)
    - [4. 书面语言通常将记号分类为名词、动词、形容词或标点符号。](#4-书面语言通常将记号分类为名词动词形容词或标点符号)
    - [各大语言关键词数量与列表](#各大语言关键词数量与列表)
- [bk prgrm 编程语言和自然语言的对应关系](#bk-prgrm-编程语言和自然语言的对应关系)
- [运算符 和操作符  算数关系逻辑赋值运算符](#运算符-和操作符--算数关系逻辑赋值运算符)
    - [运算符operator vs 操作符 operator](#运算符operator-vs-操作符-operator)
    - [运算符 vs 函数](#运算符-vs-函数)
    - [用户定义的运算符 vs 内置运算符](#用户定义的运算符-vs-内置运算符)
    - [运算符的编译实现](#运算符的编译实现)
    - [运算符重载](#运算符重载)
- [词法分析](#词法分析)
    - [双case分析法](#双case分析法)
    - [扫描器](#扫描器)
    - [token 生成器](#token-生成器)
    - [评估器（Evaluator）。将记号进行分类](#评估器evaluator将记号进行分类)
    - [正则表达式搜索split](#正则表达式搜索split)
    - [使用普通的splt模式来实现，比如url这样一类](#使用普通的splt模式来实现比如url这样一类)
    - [7.2 直接扫描法](#72-直接扫描法)

<!-- /TOC -->

# 1. 词法标记序列（具有分配和识别含义的字符串）

在计算机科学中，词法分析、词法分析或标记化是将字符序列（例如在计算机程序或网页中）转换为词法标记序列（具有分配和识别含义的字符串）的过程。执行词法分析的程序可以称为lexer、tokenizer、[1]或scanner


# 2. 俩分法分类为  标识符单词 + 分隔符

# 3. token标记 分类 ：  标识符单词  系统关键词 分隔符号  数学运算符号  字面值 注释


标识符：程序员选择的名称；（变量  函数名 
关键字：编程语言中已有的名称；
分隔符（也称为标点符号）：标点符号和成对分隔符； 空格 
运算符：对参数进行运算并产生结果的符号；
literal文字字面值：数字，逻辑，文本，参考文字；
注释   行，块（取决于编译器，如果编译器将注释实现为标记，否则它将被删除）


语



标记由空白字符（如空格或换行符）或标点符号分隔。


两个重要的常见词法类别是空格和注释。这些也在语法中定义并由词法分析器处理，但可能会被丢弃（不产生任何标记）并被认为是不重要的，最多分隔两个标记（如 in if xinstead of ifx）。这有两个重要的例外。首先，在使用缩进分隔块的越位规则语言中，初始空格很重要，因为它决定块结构，并且通常在词法分析器级别处理；


##  4. 书面语言通常将记号分类为名词、动词、形容词或标点符号。
编程语言通常将标记分类为标识符、运算符、分组符号或按数据类型分类。书面语言通常将记号分类为名词、动词、形容词或标点符号。类别用于由解析器或程序中的其他函数对标记进行后处理。

词法分析器通常不处理标记组合，这是留给解析器的任务。例如，一个典型的词法分析器将括号识别为记号，但不确保每个“(”与“)”相匹配。

## 各大语言关键词数量与列表


# bk prgrm 编程语言和自然语言的对应关系

单词 token单词
短语，表达式
语句 语句
段落  block区块
文章  代码文件

篇章  pkg

部分   一级pkg


# 运算符 和操作符  算数关系逻辑赋值运算符


运算符是一种告诉编译器执行特定的数学或逻辑操作的符号。C 语言内置了丰富的运算符，并提供了以下类型的运算符：

算术运算符
关系运算符
逻辑运算符
位运算符
赋值运算符
杂项运算符

常见的简单示例包括算术（例如加法 with ）、比较（例如“大于” with >）和逻辑运算（例如AND，也用&&某些语言编写）。更多涉及的示例包括赋值（通常是=or :=）、
记录或对象中的字段访问（通常是句号符号 .  ）
以及范围解析运算符（通常是 双冒号：： 或者句号。 ）



## 运算符operator vs 操作符 operator
egls 都是oprtr


1. 操作符是嵌在每一条指令中的，换句话说，指令系统的每一条指令都有一个操作符。我理解是计算机系统中的术语。
如=（赋值），==（判断相当），+，- 等等。
2. 运算符是数学中的术语。主要是指数之间的运算方式。如 + - * / %，但不包括 = （赋值）这样的操作符。
## 运算符 vs 函数

运算符一般为特殊符号 数学符号来源于

运算符是在编程语言中定义的构造，它们的行为通常类似于函数，但在句法或语义上有所不同。

在大多数语言中，函数可被视为具有固定优先级和结合性的特殊形式的前缀运算符

运算符可以看作是具有不同调用符号和有限数量参数（通常为 1 或 2）的函数的特殊形式。
在简单的情况下，这与通常的函数调用相同；例如，加法x + y通常等同于函数调用add(x, y)

作为函数，“大于”通常由标识符命名，例如gtorgreater_than并作为函数调用，如gt(x, y). 相反，该操作使用特殊字符（在词法分析>期间单独标记化）和中缀表示法，如. x > y


## 用户定义的运算符 vs 内置运算符
一种语言可能包含固定数量的内置运算符（例如C 和 C++、PHP中的+、-、*、<、<=、!、=等），或者它可能允许创建程序员定义的运算符（例如Prolog、[5] Seed7、[6] F#、OCaml、Haskell）。一些编程语言将运算符符号限制为特殊字符，如+或:=，而其他语言也允许使用类似的名称（例如Pascal）。 div

大多数语言都有一组内置的运算符，但不允许用户定义运算符，因为这会使解析变得非常复杂。[b]许多语言只允许运算符用于内置类型，但其他语言允许现有运算符用于用户定义的类型；这称为运算符重载。但是，某些语言允许在编译时或运行时定义新的运算符。这可能涉及元编程（在单独的语言中指定运算符），或在语言本身内


不太常见的运算符包括：

逗号运算符：e, f
取消引用运算符：*p和地址运算符：&x
?:或三元运算符：number = spell_out_numbers ? "forty-two" : 42
猫王运营商：x ?: y
空合并运算符：x ?? y
宇宙飞船算子（用于三向比较）：x <=> y


## 运算符的编译实现
编译器可以使用子例程调用或内联代码来实现运算符和函数。一些语言支持的内置运算符直接映射到中央处理器上常见的少量指令，尽管其他运算符（例如用于表示字符串连接的“+” ）可能有复杂的实现。


## 运算符重载

 
在计算机编程中，运算符重载（有时称为运算符即席多态性）是多态性的一种特殊情况，其中不同的运算符根据其参数具有不同的实现。运算符重载通常由编程语言、程序员或两者定义。

+号可以表示数字相加或字符串连接


# 词法分析

## 双case分析法
递归下降法

分词器 字典模式法

fsm有限状态自动机

最长一致原则
很多情况下，根据第一个非空白字符便可以推导出该记号的类型，于是便可逐个处理之后的字符，直到出现不属于该类型记号字符集中的字符（即最长一致原则）。



记号化（tokenization）即将输入字符串分割为记号、进而将记号进行分类的过程。生成的记号随后便被用来进行语法分析。

例如对于如下字符串： 
The quick brown fox jumps over the lazy dog

计算机并不知道这是以空格分隔的九个英语单词，只知道这是普通的43个字符构成的字符串。可以通过一定的方法（这里即使用空格作为分隔符）将语素（这里即英语单词）从输入字符串中分割出来。



## 扫描器
编辑
词法分析的第一阶段即扫描器，通常基于有限状态自动机。扫描器能够识别其所能处理的记号中可能包含的所有字符序列（单个这样的字符序列即前面所说的“语素”）


## token 生成器
编辑
记号化（tokenization）即将输入字符串分割为记号、进而将记号进行分类的过程。生成的记号随后便被用来进行语法分析。
然而，语素只是一类字符构成的字符串（字符序列），要构建记号，语法分析器需要第二阶段的

## 评估器（Evaluator）。将记号进行分类
评估器根据语素中的字符序列生成一个“值”，这个“值”和语素的类型便构成了可以送入语法分析器的记号。一些诸如括号的语素并没有“值”，评估器函数便可以什么都不返回。整数、标识符、字符串的评估器则要复杂的多。评估器有时会抑制语素，被抑制的语素（例如空白语素和注释语素）随后不会被送入语法分析器。

尽管在某些情况下需要手工编写词法分析器，一般情况下词法分析器都用自动化工具生成。


简单正则法生成词法分析
使用字典法生成token列表 评估分类器

## 正则表达式搜索split

因为字符串可能包含双引号和空格，所以使用普通的空格splt就有问题了额。。需要使用regexprs来分割。。

## 使用普通的splt模式来实现，比如url这样一类
但这样要求没有特殊符号，需要转义
方便解析这样。。
转义的模式俩种，一种url模式%xx 更好解析
一种使用反斜杠转义，比较麻烦些，但可使用正则识别。。



## 7.2 直接扫描法
直接扫描法的思路非常简单，每轮扫描，根据第一个字符判断属于哪种类型的 token ，然后采取不同的策略扫描出一个完整的 token ，再接着进行下一轮扫描。例如 TinyC 中，若仅考虑一些简单的情况，按 token 的第一个字符，可以将所有类别的 token 分为以下 7 大类：

（1）A型单字符运算符

包括：+, -, *, /, %， 这种 token 只有一个字符，若本轮扫描的第一个字符为上述字符，则立即返回此字符所代表的 token ，然后移到下一个字符开始下一轮扫描。

（2）B型单字符运算符和双字符运算符

B型单字符运算符包括： < > = ! ，双字符运算符包括： <=, >=, ==, != 。 若本轮扫描的第一个字符为B型单字符运算符时，先查看下一个字符是否是 “=” ，如果是，则返回这两个字符代表的 token ，如果否，则返回这个字符代表的 token 。例如，如果扫描到 “>” ，则查看下一个字符是否是 “=” ，是则返回 T_GREATEEQUAL ，否则返回 T_GREATTHAN 。

（3）关键词和标识符

关键词和标识符都是以字母或下划线开始、且只有字母、下划线或数字组成。若本轮扫描的第一个字符为字母或下划线时，则一直向后扫描，直到遇到第一个既不是字母、也不是下划线或数字的字符，此时一个完整的词就被扫描出来了，然后，查看这个词是不是为关键字，如果是，则返回关键字代表的 token ，如果不是，则返回 T_IDENTIFIER 以及这个词的字面值。

（4）整数常量

整数常量以数字开始，若本轮扫描的第一个字符为数字，则一直向后扫描，直到遇到第一个非数字字符，然后返回 T_INTEGERCONSTANT 和这个数字。

（5）字符串常量

字符串常量以双引号开始和结束，若本轮扫描的第一个字符为双引号，则一直向后扫描，直到遇到第一个双引号，然后返回 T_STRINGCONSTANT 和这个字符串。