
function memeinfor() {
    var result;
    var url = window.location.search;
    if (url.indexOf("?") != -1) {
        result = url.substr(url.indexOf("=") + 1);
    }
    $.ajax({
        type: "post",
        url: "http://localhost:8081/meme/entitymemesrc/"+result,    //向后端请求数据的url
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        cache:false,
        success: function (data) {
            var length = data.length;
            fuzhi(data);
        },
        error:function() {
            alert("listComicType error");
        }
    });
}
    function fuzhi(data) {
        // aa=data.memeID;
        // bb=data.memeSrc;
        document.getElementById("meme_id").value=data.memeID;
        document.getElementById("meme_src").value=data.memeSrc;
}

function tijiao(){
    var formdata = new FormData();
    var file = document.getElementById('file1');
    var fileArray = file.files;
//	alert(fileArray.length);
    if (!(fileArray && fileArray[0])){
        alert("请选择图片");
        return;
    }
    for(var i = 0;i<fileArray.length;i++)
        formdata.append("fileArray",fileArray[i]);
    var b=document.getElementById("meme_id").value;
    var a=document.getElementById("meme_src").value;
    formdata.append("meme_src",a);
    formdata.append("meme_id",b);

    $.ajax({
        data:formdata,
        type: "post",
        url:"http://localhost:8081/meme/dataFiles",
        processData:false,//用于对data参数进行序列化处理 这里传文件必须false
        contentType:false,
        success:function(){
            alert('添加成功');
            window.location.href= "http://localhost:8081/meme/admin-meme.html"

        },
        error:function(){
            alert("testBatchUpload error");
        }
    });
}
//预览图片
function preview(j) {
    var files = document.getElementById("file1").files;
    if(j>=files.length)
        return;
    var imgFile = files[j];
    var fr = new FileReader();
    var a=j+1;
    fr.onload = function() {
        document.getElementById("id_license_img"+a).src= fr.result;
    };
    fr.readAsDataURL(imgFile);
    preview(j+1);
};
