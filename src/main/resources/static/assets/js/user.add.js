
document.getElementById('file').onchange = function() {
    var imgFile = this.files[0];
    var fr = new FileReader();
    fr.onload = function() {
        document.getElementById('image').getElementsByTagName('img')[0].src = fr.result;
    };
    fr.readAsDataURL(imgFile);
};
function Location() {
    if(document.getElementById("password1").value=="1"&&document.getElementById("password2").value=="1")
        window.location.href = "admin-index.html";
    else if(document.getElementById("username").value!="1")
        alert("用户名不正确");
    else if(document.getElementById("username").value=="1"&&document.getElementById("password").value!="1")
        alert("密码错误");
    else if(document.getElementById("username").value==null)
        alert("用户不能为空");

};