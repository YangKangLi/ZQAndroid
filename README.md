# AMTS接口设计说明书

版本号：V1.0

------
> ***目录***
> [1. 用户登录](#UserLogin)
> [2. 获取任务](#QueryTasks)
> [3. 领取任务](#AssignTask)
> [4. 提交任务结果](#SubmitTask)

==================================
<h2 id="UserLogin">1. 用户登录</h2>
接口地址：`http://api_url:port/login`
访问方式：`POST`
数据格式：`JSON`

**请求参数说明：**
|参数名称|类型|必选|说明|
|:----:|:---:|:---:|:----------:|
|userCode|String|`是`|工号 |
|password|String|`是`|密码|
**返回参数说明：**
|参数名称|类型|必选|说明|
|:----:|:---:|:---:|:----------:|
|userCode|String|`是`|工号 |
|password|String|`是`|密码|
**返回示例：**
```json
{
    "retCode": 0,
    "retMsg": "成功",
    "retData": {
        "burea": {
            "bureaCode": "H", 
            "bureaName": "上海铁路局"
        },
        "dwseg": {
            "dwsegCode": 10603,
            "dwsegName": "合肥电务段"
        },
        "workArea": {
            "workAreaCode": 202313580,
            "workAreaName": "合肥南动车车载设备维护工区"
        }
}
```

<h2 id="QueryTasks">2. 获取任务</h2>




<h2 id="AssignTask">3. 领取任务</h2>
|一个标题|哈哈|哈哈|
------|:------:|:------:|
|接口地址|base_url/api/login|
|访问方式|post|

<h2 id="SubmitTask">3. 提交任务结果</h2>
|一个标题|哈哈|哈哈|
------|:------:|:------:|
|接口地址|base_url/api/login|
|访问方式|post|
