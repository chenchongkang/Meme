
document.getElementById('file').onchange = function() {
    if(typeof FileReader != 'undefined'){
        var file = document.getElementById("file").files[0];
        if((file.type).indexOf("image/")==-1){
            alert("请上传图片文件!");
        }
    }else{
        var fileName=document.getElementById("file").value;
        var suffixIndex=fileName.lastIndexOf(".");
        var suffix=fileName.substring(suffixIndex+1).toUpperCase();
        if(suffix!="BMP"&&suffix!="JPG"&&suffix!="JPEG"&&suffix!="PNG"&&suffix!="GIF"){
            alert( "请上传图片（格式BMP、JPG、JPEG、PNG、GIF等）!");
        }
    }
    // maxSize = 200*1024;//200KB
    var imgFile = this.files[0];
    var fr = new FileReader();
    fr.onload = function() {
        $.ajaxFileUpload({
            url: './file/up', //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 'file', //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            data:{param:'license'},//file 标签的名称
            type:'post',
            success: function (data, status)  //服务器成功响应处理函数
            {
                if(data.result == "true"){
                    alert("上传成功！");
                    $("#id_license_value").val(data.srckey);

                    var $file = $(this);
                    var fileObj = $file[0];
                    var windowURL = window.URL || window.webkitURL;
                    var dataURL;
                    var $img = $("#id_license_img");

                    if (fileObj && fileObj.files && fileObj.files[0]) {
                        dataURL = windowURL.createObjectURL(fileObj.files[0]);
                        $img.attr('src', dataURL);
                    } else {
                        dataURL = $file.val();
                        var imgObj = document.getElementById("preview");
                        imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                        imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;
                    }

                }else {
                    alert("上传失败！");
                }
            },error: function (data, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        });
// var result=this.result;
        // img=new Image(),
        //     img.src=result;
        // if(result.length<maxSize){
        //
        //     //     imgUpload(result);
        // }else {
        //     // var data=comparss(img);
        // //     imgUpload(data);
        // }
        document.getElementById('image').getElementsByTagName('img')[0].src = fr.result;
    };
    fr.readAsDataURL(imgFile);
    };

    // var canvas = document.createElement('canvas'),
    //     ctx = canvas.getContext('2d');
    // function compress(img) {
    //     canvas.width = img.width;
    //     canvas.height = img.height;
    //     //利用canvas进行绘图
    //     //将原来图片的质量压缩到原先的0.2倍。
    //     var data = canvas.toDataURL('image/jpeg/gif/jpe/bmp', 0.2); //data url的形式
    //     return data;
    // }
    // var ndata = compress(img);
    // ndata = window.atob(ndata); //将base64格式的数据进行解码
    // //新建一个buffer对象，用以存储图片数据
    // var buffer = new Uint8Array(ndata.length);
    // for(var i = 0; i < text.length; i++) {
    //     buffer[i] = ndata.charCodeAt(i);
    // }
    // //将buffer对象转化为Blob数据类型
    // var blob = getBlob([buffer]);
    // var fd = new FormData(),
    //     xhr = new XMLHttpRequest();
    // fd.append('file', blob);
    // xhr.open('post', url);
    // xhr.onreadystatechange = function() {
    //     //do something
    // }
    // xhr.send(fd);


function add() {

    if(document.getElementById("meme_name").value.length!=0 && document.getElementById("meme_classes").value!=0 && document.getElementById("meme_author").value.length!=0
        && document.getElementById("meme_src").value.length!=0 && document.getElementById("meme_intro").value.length!=0){

        var a=document.getElementById("meme_name").value;
        var memeName= JSON.stringify(a);
        var b=document.getElementById("meme_classes").value;
        var classis=JSON.stringify(b);
        var c=document.getElementById("meme_author").value;
        var author=JSON.stringify(c);
        var d=document.getElementById("meme_src").value;
        var src=JSON.stringify(d);
        var e=document.getElementById("meme_intro").value;
        var memeIntro =JSON.stringify(e);
        alert(a+b+c+d+e);
        $.ajax({       //用ajax来实现不刷新网页的基础上更新数据
            type:"post", //请求方式
            url:"./addmeme", //路径
            dataType:"json",
            contentType: "application/json; charset=utf-8",
            data:'{"memeName":'+memeName+',"classis":'+classis+',"author":'+author+',"src":'+src+',"cover":"2.img","memeIntro":'+memeIntro+',"upTime":"","downloads":"0"}',

            success:function(){
                window.location.href= "http://localhost:8081/meme/admin-meme.html"; //添加成功就跳转到login.html
                alert("添加成功");
            },
            error:function() {
                alert("listComicType error");
            }
        });
        // alert(JSON.stringify(data));


    }

    else if(document.getElementById("meme_name").value.length==0)
        alert("名称不能为空");
    else if(document.getElementById("meme_classes").value.length==0)
        alert("分类不能为空");
    else if(document.getElementById("meme_author").value.length==0)
        alert("作者不能为空");
    else if(document.getElementById("meme_src").value.length==0)
        alert("路径不能为空");
    else if(document.getElementById("meme_intro").value.length==0)
        alert("描述不能为空");
    else
        alert("填写格式错误")
};
