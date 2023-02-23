


use script or java all cant exe mlt sql,,,set mltiquery ya busin......
zwihou use mysql cmd is ok......but cant use in prgrmlan exec...


prgrm gene ***.bat shell..then ok


    //    String cmd="C:\xampp\mysql\bin\mysql.exe -uroot -proot -Dim_admin <  D:\sqls\admin\im_admin_admin_log.sql"
                        String cmd="C:\\xampp\\mysql\\bin\\mysql.exe -uroot -proot -D@dbname <  "+f;
                        cmd=cmd.replaceAll("@dbname",db);
                        System.out.println(cmd);
                        FileUtils.writeStringToFile(new File("imp.bat"),cmd+"\r\n","utf8",true);
                        Runtime.getRuntime().exec(cmd);
               


               








btch imp sql file to mysql


nodejs mysql 执行多条sql语句


执行多条查询语句

为了安全起见，默认情况下是不允许执行多条查询语句的。要使用多条查询语句的功能，就需要在创建数据库连接的时候打开这一功能：

var connection =  mysql.createConnection( { multipleStatements: true } );  
这一功能打开以后，你就可以像下面的例子一样同时使用多条查询语句：

 

除了使用shell和mysql搭配外，再就是脚本了，，
使用nodejs 来遍历文件夹，读取文件，执行sql。。。

console.log("111");



walkSync('D:\\归档\\褰掓。\\user', function(filePath, stat) {
    console.log(filePath);

    var fs = require("fs");
      var data = fs.readFileSync(filePath);
       console.log("同步读取: " + data.toString());  //// 同步读取
       sqls=data.toString();
       try {
        btchimp('',sqls)
       } catch (error) {
         console.log(error);
       }
      
  });



function walkSync(currentDirPath, callback) {
    var fs = require('fs'),
      path = require('path');
    // http://nodejs.cn/api/fs.html#fsreaddirsyncpath-options
    // http://nodejs.cn/api/fs.html#class-fsdirent 新增于: v10.10.0
    fs.readdirSync(currentDirPath, { withFileTypes: true }).forEach(function(dirent) {
      var filePath = path.join(currentDirPath, dirent.name);
      if (dirent.isFile()) {
        callback(filePath, dirent);
      } else if (dirent.isDirectory()) {
        walkSync(filePath, callback);
      }
    });
  }



  
  function btchimp(db,sqls)
  {

    const mysql = require("mysql");
    var connParam = {
      host     : 'localhost',
      user     : 'root',
      password : 'root',
      port       : '3306',          
      database : 'im_user',
      multipleStatements: true
  };
  //var connection =  mysql.createConnection( { multipleStatements: true } );
  connection =  mysql.createConnection( connParam );
  connection.query(sqls, function(err, result){  
      if(err){  
        throw err;  
      }else{  
        console.log(result[0]);       // Column1 as a result  
        console.log(result[1]);       // Column2 as a result  
        console.log(result[2]);       // Column3 as a result  
      }  
    });


  }






// connection.query('select 1;select 2;select 3', function(err, result){  
//     if(err){  
//       throw err;  
//     }else{  
//       console.log(result[0]);       // Column1 as a result  
//       console.log(result[1]);       // Column2 as a result  
//       console.log(result[2]);       // Column3 as a result  
//     }  
//   });


C:\Users\AA286\fld\sumdoc2023\btch impt sql.js

