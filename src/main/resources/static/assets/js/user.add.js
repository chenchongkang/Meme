
document.getElementById('file').onchange = function() {
    var imgFile = this.files[0];
    var fr = new FileReader();
    fr.onload = function() {
        document.getElementById('image').getElementsByTagName('img')[0].src = fr.result;
    };
    fr.readAsDataURL(imgFile);
};

function add() {
    if(document.getElementById("password1").value==document.getElementById("password2").value&&document.getElementById("user-name").value.length!=0
        &&document.getElementById("password1").value.length!=0&&document.getElementById("user-email").value.length!=0&&document.getElementById("user-phone").value.length!=0&&document.getElementById("user-QQ").value.length!=0
        &&document.getElementById("user-intro").value.length!=0){
        alert("添加成功");
      }
    else if(document.getElementById("password1").value!=document.getElementById("password2").value)
        alert("两次密码输入不一致");
    else if(document.getElementById("user-name").value.length==0)
        alert("昵称不能为空");
    else if(document.getElementById("password1").value.length==0)
        alert("密码不能为空");
    else if(document.getElementById("user-email").value.length==0)
        alert("邮箱不能为空");
    else if(document.getElementById("user-phone").value.length==0)
        alert("手机号不能为空");
    else if(document.getElementById("user-QQ").value.length==0)
        alert("QQ号不能为空");
    else if(document.getElementById("user-intro").value.length==0)
        alert("简介不能为空");
    else
        alert("填写格式错误")
};

// function addEntityuser() {
// var mysql  = require('mysql');  //调用MySQL模块
// //创建一个connection
// var connection = mysql.createConnection({
//     host     : '106.14.201.240',       //主机
//     user     : 'chenck',               //MySQL认证用户名
//     password : 'Chenck123@',        //MySQL认证用户密码
//     port: '3306',                   //端口号
// });
// //创建一个connection
// connection.connect(function(err){
//     if(err){
//         console.log('[query] - :'+err);
//         return;
//     }
//     console.log('[connection connect]  succeed!');
// });
// //执行SQL语句
// connection.query('SELECT 1 + 1 AS solution', function(err, rows, fields) {
//     if (err) {
//         console.log('[query] - :'+err);
//         return;
//     }
//     console.log('The solution is: ', rows[0].solution);
// });
// //关闭connection
// connection.end(function(err){
//     if(err){
//         return;
//     }
//     console.log('[connection end] succeed!');
// });
//
// var mysql  = require('mysql');
// var connection = mysql.createConnection({
//     host     : '106.14.201.240',       //主机
//     user     : 'chenck',               //MySQL认证用户名
//     password : 'Chenck123@',        //MySQL认证用户密码
//     port: '3306',                   //端口号
//     database: 'memedb',
// });
//
// connection.connect();
//
// var  userAddSql = 'INSERT INTO Entity_user(userID,userName,password,address,phoneNumber,avatar,qq,introduction) VALUES(1,?,?,?,?,"img",?,?)';
// var  userAddSql_Params = [document.getElementById("user-name").value,document.getElementById("password1").value,
// document.getElementById("user-email").value,document.getElementById("user-phone").value,document.getElementById("user-QQ").value,
// document.getElementById("user-intro").value];
// //增
// connection.query(userAddSql,userAddSql_Params,function (err, result) {
//     if(err){
//         console.log('[INSERT ERROR] - ',err.message);
//         return;
//     }
//
//     console.log('--------------------------INSERT----------------------------');
//     //console.log('INSERT ID:',result.insertId);
//     console.log('INSERT ID:',result);
//     console.log('-----------------------------------------------------------------\n\n');
// });
// connection.end();
// }