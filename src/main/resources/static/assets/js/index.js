function Location() {
    if(document.getElementById("username").value=="1"&&document.getElementById("password").value=="1")
    window.location.href = "admin-index.html";

    else if(document.getElementById("username").value=="1"&&document.getElementById("password").value!="1")
        alert("密码错误");
    else if(document.getElementById("username").value.length==0)
        alert("用户不能为空");
    else if(document.getElementById("username").value!="1")
        alert("此用户名不存在，请填写正确用户名");
};