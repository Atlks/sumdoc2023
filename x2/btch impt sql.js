console.log("111");

walkSync("D:\\归档\\褰掓。\\user", function (filePath, stat) {
  console.log(filePath);

  try {
    var fs = require("fs")
    var data = fs.readFileSync(filePath);
    console.log("同步读取: " + data.toString()); //// 同步读取
    sqls = data.toString();
    console.log(filePath);
    var fs = require("fs");
    fname = stat.name;
    idx = fname.indexOf("_");
    idx = fname.indexOf("_", idx + 1);
    dbname = fname.substring(0, idx);
    console.log(dbname);
    btchimp(dbname, sqls);
  } catch (error) {
    console.log(error);
  }
});

function walkSync(currentDirPath, callback) {
  var fs = require("fs"),
    path = require("path");
  // http://nodejs.cn/api/fs.html#fsreaddirsyncpath-options
  // http://nodejs.cn/api/fs.html#class-fsdirent 新增于: v10.10.0
  fs.readdirSync(currentDirPath, { withFileTypes: true }).forEach(function (
    dirent
  ) {
    var filePath = path.join(currentDirPath, dirent.name);
    if (dirent.isFile()) {
      callback(filePath, dirent);
    } else if (dirent.isDirectory()) {
      walkSync(filePath, callback);
    }
  });
}

function btchimp(db, sqls) {
  console.log("====>db:" + db);
  const mysql = require("mysql");
  var connParam = {
    host: "localhost",
    user: "root",
    password: "root",
    port: "3306",
    database: db,
    multipleStatements: true,
  };
  //var connection =  mysql.createConnection( { multipleStatements: true } );
  connection = mysql.createConnection(connParam);
  //db.connect(handleError);
  connection.on("error", handleError);
  connection.query(sqls, function (err, result) {
    if (err) {
      console.log(err);
    } else {
    }
  });
}

function handleError(err) {
  if (err) {
    // 如果是连接断开，自动重新连接
    if (err.code === "PROTOCOL_CONNECTION_LOST") {
      console.log(err);
    } else {
      console.error(err.stack || err);
    }
  }
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
