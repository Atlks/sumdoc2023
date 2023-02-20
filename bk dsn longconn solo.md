bk dsn longconn solo


<!-- TOC -->

- [http+rds lst blockapi](#httprds-lst-blockapi)
- [java socket api smp](#java-socket-api-smp)
- [netty mafe](#netty-mafe)

<!-- /TOC -->

# http+rds lst blockapi

bk dsn im send receiv ref

# java socket api smp

 public static void main(String[] args) throws  Exception {
        int port = 8099;
        ServerSocket connectionSocket = new ServerSocket(port);
        // 监听端口，accept为阻塞方法
        System.out.println("Get socket successfully, wait for request...");
        while(true)
        {
            Socket communicationSocket = connectionSocket.accept();
            new Thread( hadle(communicationSocket)).start();
        }


    }

    private static Runnable hadle(Socket communicationSocket) throws Exception {

        // 获取输入流，读取数据


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()));
        String message;
        while ((message = bufferedReader.readLine()) != null) {
            System.out.printf("get message from client : %s \r\n",message);
        }

        return null;
    }




public class TcpClient {


    public static void main(String[] args) throws IOException {
        // 指定ip 端口，创建socket
        String host = "127.0.0.1";
        int port = 8099;
        Socket communicationSocket = new Socket(host, port);
        // 获取输出流,写入数据
        OutputStream outputStream = communicationSocket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write("hello world\r\n");
        bufferedWriter.flush();
        communicationSocket.shutdownOutput();


# netty mafe