$(document).ready(function () {
    $("#test_btn").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        test2();   });

});


function adduserTypeToBox(data,length)
{
    var boxHTML = "";
    for(var i = 0;i<length;i++)
    {
        boxHTML+='<label><input type="checkbox" value="'+data[i].userID+'+'+data[i].username+'+'+data[i].phonenumber+'" id="comicTypeGroup_'+i+'" />'+data[i].userID+''+data[i].username+''+data[i].phonenumber+'</label>&nbsp;';
        if(i!=0 && i%6==0)
            boxHTML+='<br/>';
    }
    var groupdiv = document.getElementById('view_user');
    groupdiv.innerHTML = boxHTML;
    // checkboxOnClick(length);//必须加在这里
}

function test2(){
    alert("hey2");
    $.ajax({
        type: "post",
        url: "http://localhost:8081/meme/hello/data",    //向后端请求数据的url
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        data:{"userID":SVGAnimatedInteger['userID'],"username":String['username'],"password":String['password'],"address":String['address'],
            "phonenumber":String['phonenumber'],"avatar":String['avatar'],"qq":String['qq'],"introduction":String['introduction']},
        cache:false,
        success: function (data) {
            var length = data.length;
            adduserTypeToBox(data,length);
            alert("hey3");
        },
        error:function() {
            alert("listComicType error");
        }
    });
}


