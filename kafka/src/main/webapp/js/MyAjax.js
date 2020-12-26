/**
 * type 请求方式,
 * url 请求路径,
 * data 发送数据
 * */
function MyAjax(url,data,type,success){
    //1.创建对象
    var xmlHttpRequest;
    try{
        xmlHttpRequest = new XMLHttpRequest();
    }catch (e) {
        try{
            xmlHttpRequest = new ActiveXObject('Msxml2.XMLHTTP');
        }catch (e) {
            try{
                xmlHttpRequest = new ActiveXObject('Microsoft.XMLHTTP');
            }catch (e) {
                alert("你的浏览器版本太低,请及时更新!");
                return false;
            }
        }
    }
    if ("GET" == type.toUpperCase()){
        //2.发送请求,并设置 请求方式(type),请求参数(data)和请求地址(url)
        xmlHttpRequest.open("get",url+"?"+data);
        xmlHttpRequest.send();
    }else if ("POST" == type.toUpperCase()){
        xmlHttpRequest.open("post",url);
        xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlHttpRequest.send(data);
    }else {
        alert("你的请求方式错误,请重新请求!");
        return false;
    }
    //3.监听响应  xmlHttpRequest.onreadystatechange
    /**
     * 事件：在readyState属性改变时触发
     * */
    /* xhr.readyState
     属性值：0  刚新建xhr，没有open()
     1  xhr调用send(),建立连接
     2  开始接收到响应
     3  正在处理接收的数据
     4  接收处理数据完毕*/
    xmlHttpRequest.onreadystatechange = function () {
        if (xmlHttpRequest.readyState == 4){
            //获取服务器响应数据
            //xmlHttpRequest.status获取服务器响应状态码
            /*200 表示成功
              302 表示重定向
              404 资源找不到
              500 服务器内部异常*/
            if (xmlHttpRequest.status == 200){
                //xmlHttpRequest.responseText;//获取响应的字符串
                //通过dom操作，将结果显示到页面
                success(xmlHttpRequest.responseText);
            }else {
                console.log(xmlHttpRequest.responseText);//获取到异常信息
            }
        }
    }
};