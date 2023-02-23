bk prgrmlan> data type  



[toc]

# datatype ()int str bool date)



货币类型  Url类型

几何类型

枚举类型
枚举类型是一个包含静态和值的有序集合的数据类型。
PostgtesSQL中的枚举类型类似于 C 语言中的 enum 类型。

Eml类型
Cellphone numb

串类型
位串就是一串 1 和 0 的字符串。它们可以用于存储和直观化位掩码。 我们有两种 SQL 位类型：bit(n) 和bit varying(n)， 这里的n是一个正整数。
bit 类型的数据必须准确匹配长度 n， 试图存储短些或者长一些的数据都是错误的。bit varying 类型数据是最长 n 的变长类型；更长的串会被拒绝。 写一个没有长度的bit 等效于 bit(1)， 没有长度的 bit varying 意思是没有长度限制。

文本搜索类型

UUID 类型
 

XML 类型

JSON 类型
 

数组类型



范围类型
 

对象标识符类型
PostgreSQL 在内部使用对象标识符(OID)作为各种系统表的主键。
同时，系统不会给用户创建的表增加一个 OID 系统字段(除非在建表时声明了WITH OIDS 或者配置参数default_with_oids设置为开启)。oid 类型代表一个对象标识符。除此以外 oid 还有几个别名：regproc, regprocedure, regoper, regoperator, regclass, regtype, regconfig, 和regdictionary。
伪类型

table类型

# 简单类型 vs 复杂类型 复合类型

# datastruts

# other



Lua 的简单语法
Lua在Redis脚本中我个人建议只需要使用下面这几种类型：
nil 空
boolean 布尔值
number 数字
string 字符串
table 表



#  系统常用的数据类型 h5 db 对比

系统常用的数据类型
	H5 form	Postgre	Mysql
整数	Number
	intgreter	Int
小数	number	numeric	FLOAT
文本	。。。	Text	TEXT
自增整数		serial	4 字节		Autocrem
价格	number	money	DECIMAL
时间日期	Date picker	timestamp 	Datetime
时间戳	Date picker	timestamp 	TIMESTAMP
日期	Date picker	date	Date
email			
Cellphone numb			
url			
range			
search			
Color			
datalist			
UUID 			
Othr pattern (regexp)
			

# ref

Atitit 常用数据类型有哪些v2.docx