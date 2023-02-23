bk perf write perf FileChannel和mmap bufferio



FileChannel和mmap性能测试（翻译）

1. FileChannel 的用途
JDK 提供三种文件 I/O 方式，如下：

普通 I/O：存在于 java.io 包中的 FileWriter 与 FileReader 类；
FileChannel（文件通道）：存在于 java.nio 包中的 FileChannel 类；
MMAP（内存映射）：此方法较为特殊，由 FileChannel#map 方法衍生出的一种特殊的读写文件方