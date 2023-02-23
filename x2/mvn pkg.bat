
d:
cd D:\im-server-core\im-biz
Mvn.cmd     -s   d:/settings.xml  package





del C:\xampp\tomcat\webapps\ROOT\im-biz.war.rar
copy D:\im-server-core\im-biz\target\im-biz.war  C:\xampp\tomcat\webapps\ROOT\im-biz.war.rar

cd C:\xampp\tomcat\webapps\ROOT\
c:
C:\prgrm\7-Zip\7z.exe x  im-biz.war.rar /y






