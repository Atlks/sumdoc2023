 
      需要查看sql日志。。。。看它是否是同一条数据sql 。。。

 
# 查看日志状态和sql日志路径
 SHOW VARIABLES LIKE "general_log%";

# 打开mysql的sql日志
 SET GLOBAL general_log = 'ON';

 
在我本机测试同样报 DUPLICATE KEY的时候是可以查看到sql日志的


230210 14:17:28	    23 Query	SET PROFILING=1
		    23 Query	SHOW STATUS
		    23 Query	SHOW STATUS
		    23 Query	insert uid set id=22
		    23 Query	insert lg set t=new.id
		    23 Query	SHOW STATUS
		    23 Query	SELECT QUERY_ID, SUM(DURATION) AS SUM_DURATION FROM INFORMATION_SCHEMA.PROFILING GROUP BY QUERY_ID
		    23 Query	SELECT STATE AS `状态`, ROUND(SUM(DURATION),7) AS `期间`, CONCAT(ROUND(SUM(DURATION)/0.000265*100,3), '%') AS `百分比` FROM INFORMATION_SCHEMA.PROFILING WHERE QUERY_ID=105 GROUP BY STATE ORDER BY SEQ
230210 14:17:33	    23 Query	SET PROFILING=1
		    23 Query	SHOW STATUS
		    23 Query	SHOW STATUS
		    23 Query	insert uid set id=22
		    23 Query	insert lg set t=new.id