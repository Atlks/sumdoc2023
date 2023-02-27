bk dsn dsl choose struts implt

<!-- TOC -->

- [asm write too trouble but parse is smple](#asm-write-too-trouble-but-parse-is-smple)
- [maybe sql if else struts is ok...](#maybe-sql-if-else-struts-is-ok)
- [java smp but parse trouble](#java-smp-but-parse-trouble)

<!-- /TOC -->

# asm write too trouble but parse is smple


# maybe sql if else struts is ok...
not very rouble wrt,,also parse smp...


IF search_condition THEN statement_list
    [ELSEIF search_condition THEN statement_list] ...
    [ELSE statement_list]
END IF


# java smp but parse trouble



LEAVE label
此语句用于退出具有给定标签的流程控制构造。如果标签用于最外层存储的程序块，LEAVE则退出程序。


LOOP实现一个简单的循环结构，允许重复执行语句列表，该语句列表由一个或多个语句组成，每个语句以分号 ( );语句分隔符终止。重复循环内的语句，直到循环终止。通常，这是通过 LEAVE语句完成的。在存储的函数中，RETURN也可以使用，它完全退出函数。

忽略包含循环终止语句会导致无限循环。

语句LOOP可以被标记。有关标签使用的规则，请参阅 第 13.6.2 节，“声明标签”。


REPEAT重复语句 中的语句列表 ，直到search_condition表达式为真。因此，aREPEAT总是至少进入循环一次。 statement_list由一个或多个语句组成，每个语句以分号 ( ;) 语句分隔符终止。

语句REPEAT可以被标记。有关标签使用的规则，请参阅 第 13.6.2 节，“声明标签”。


WHILE 只要表达式 search_condition为真， 就会重复语句 中的语句列表。statement_list由一个或多个 SQL 语句组成，每个语句以分号 ( ;) 语句分隔符终止。

语句WHILE可以被标记。有关标签使用的规则，请参阅 第 13.6.2 节，“声明标签”。