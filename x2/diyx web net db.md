

<!-- TOC -->

- [net socket http的impt](#net-socket-http的impt)
- [svlt svr implt](#svlt-svr-implt)
- [long conn socket netty](#long-conn-socket-netty)
- [db implt](#db-implt)

<!-- /TOC -->

# net socket http的impt
mvc url mapper对应关系implt
web server
filter implet



# svlt svr implt

xsp tech implt....
if url is endwith xxjsp..thdn invoke cgi svr  run....script

# long conn socket netty

# db implt

db store
idx implt  hash,,   
btree idx,,rang query imple....lst 二分查找法

分片
分布式计算


rang query txt file lines... should reds add idx..then 
txt as heap table,,then query..


读取特定行从大文件  Stream.skip(n)

Reading the Nth line from a file in Java

try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
        line = lines.skip(n).findFirst().get();
        System.out.println(line);
      }


      In the code above, lines.skip is used to jump n lines from the start of the file. Just as before, the get() method returns a string.


      ava 7 users, the BufferedReader class can be used to retrieve a particular line of text.


        try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
          for (int i = 0; i < n; i++)
              br.readLine();
          line = br.readLine();
          System.out.println(line);
      }