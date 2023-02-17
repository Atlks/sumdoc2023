read spec line from large file


能够逐行读取文件使我们能够仅查找相关信息并在找到所需内容后停止搜索。它还允许我们将数据分解成逻辑部分，就像文件是 CSV 格式的一样。

当您需要逐行读取文件时，有几个不同的选项可供选择。


<!-- TOC -->

- [Scanner扫描器](#scanner扫描器)
- [读取特定行从大文件  Stream.skip(n)](#读取特定行从大文件--streamskipn)
- [BufferedReader reaa then skip some line](#bufferedreader-reaa-then-skip-some-line)

<!-- /TOC -->

# Scanner扫描器
使用Scanner类可以实现在 Java 中逐行读取文件的最简单方法之一。扫描仪使用定界符模式将其输入分解为标记，在我们的例子中是换行符：

Scanner scanner = new Scanner(new File("filename"));
while (scanner.hasNextLine()) {
   String line = scanner.nextLine();
   // process the line
}
如果此扫描仪的输入中有另一行，则该hasNextLine()方法返回true，但此时扫描仪本身不会前进超过任何输入或读取任何数据。

要阅读该行并继续前进，我们应该使用该nextLine()方法。此方法使扫描器前进到当前行并返回最初未到达的输入。此方法返回当前行的其余部分，不包括行尾的任何行分隔符。然后将读取位置设置为下一行的开头，将在再次调用该方法时读取并返回。

由于此方法继续在输入中搜索行分隔符，因此如果不存在行分隔符，它可能会在搜索行尾时缓冲所有输入。


# 读取特定行从大文件  Stream.skip(n)

Reading the Nth line from a file in Java

try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
        line = lines.skip(n).findFirst().get();
        System.out.println(line);
      }


      In the code above, lines.skip is used to jump n lines from the start of the file. Just as before, the get() method returns a string.

# BufferedReader reaa then skip some line
      ava 7 users, the BufferedReader class can be used to retrieve a particular line of text.


        try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
          for (int i = 0; i < n; i++)
              br.readLine();
          line = br.readLine();
          System.out.println(line);
      }


      缓冲阅读器
BufferedReader类代表了一种从字符输入流中读取字符、数组和行的有效方法。

如命名中所述，此类使用缓冲区。缓冲的默认数据量为 8192 字节，但出于性能原因可以将其设置为自定义大小：

BufferedReader br = new BufferedReader(new FileReader(file), bufferSize);
该文件，或者更确切地说是一个File类的实例，不是 的合适数据源BufferedReader，因此我们需要使用一个FileReader，它扩展了InputStreamReader。它是从文本文件中读取信息的便利类，不一定适合读取原始字节流：