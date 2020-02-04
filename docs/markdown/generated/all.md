# Tips


<a name="overview"></a>
## 概览
Tips


### 版本信息
*版本* : 8.0


### 许可信息
*服务条款* : xx


### URI scheme
*域名* : localhost:8086  
*基础路径* : /


### 标签

* article-controller : Article Controller
* carousel-controller : Carousel Controller
* cart-controller : Cart Controller
* comment-controller : Comment Controller
* file-upload-controller : File Upload Controller
* love-controller : Love Controller
* order-controller : Order Controller
* oss-controller : Oss Controller
* post-controller : Post Controller
* user-controller : User Controller




<a name="paths"></a>
## 资源

<a name="article-controller_resource"></a>
### Article-controller
Article Controller


<a name="articleusingpost"></a>
#### article
```
POST /articles/
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**userId**  <br>*可选*|userId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `application/json`


##### HTTP请求示例

###### 请求 path
```
/articles/
```


###### 请求 query
```
json :
{
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="findanarticleusingget"></a>
#### findAnArticle
```
GET /articles/findArticle
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**articleId**  <br>*可选*|articleId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ArticleDTO](#articledto)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/articles/findArticle
```


###### 请求 query
```
json :
{
  "articleId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "articleId" : "string",
  "comments" : [ {
    "apiRootId" : "string",
    "children" : [ {
      "apiRootId" : "string",
      "children" : [ "..." ],
      "commentId" : "string",
      "content" : "string",
      "createTime" : "string",
      "enabled" : true,
      "parentId" : "string",
      "thumbers" : [ {
        "createTime" : "string",
        "faceIcon" : "string",
        "id" : "string",
        "nickname" : "string",
        "status" : true,
        "username" : "string"
      } ],
      "type" : 0,
      "updateTime" : "string",
      "userId" : "string"
    } ],
    "commentId" : "string",
    "content" : "string",
    "createTime" : "string",
    "enabled" : true,
    "parentId" : "string",
    "thumbers" : [ {
      "createTime" : "string",
      "faceIcon" : "string",
      "id" : "string",
      "nickname" : "string",
      "status" : true,
      "username" : "string"
    } ],
    "type" : 0,
    "updateTime" : "string",
    "userId" : "string"
  } ],
  "coverPicture" : "string",
  "createTime" : "string",
  "deleted" : true,
  "enabled" : true,
  "faceIcon" : "string",
  "headPicture" : "string",
  "htmlContent" : "string",
  "nickname" : "string",
  "tagVOS" : [ {
    "name" : "string",
    "tagId" : "string"
  } ],
  "thumbers" : [ {
    "createTime" : "string",
    "faceIcon" : "string",
    "id" : "string",
    "nickname" : "string",
    "status" : true,
    "username" : "string"
  } ],
  "title" : "string",
  "updateTime" : "string",
  "userId" : "string",
  "username" : "string",
  "viaCitiesVOS" : [ {
    "cityId" : "string",
    "name" : "string"
  } ]
}
```


<a name="articleusingpost_1"></a>
#### article
```
POST /articles/no
```


##### 参数

|类型|名称|说明|类型|默认值|
|---|---|---|---|---|
|**Query**|**keywords**  <br>*可选*|keywords|string||
|**Query**|**page**  <br>*可选*|page|integer (int32)|`1`|
|**Query**|**pageSize**  <br>*可选*|pageSize|integer (int32)|`3`|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `application/json`


##### HTTP请求示例

###### 请求 path
```
/articles/no
```


###### 请求 query
```
json :
{
  "keywords" : "string",
  "page" : 0,
  "pageSize" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="carousel-controller_resource"></a>
### Carousel-controller
Carousel Controller


<a name="carouselsusingpost"></a>
#### carousels
```
POST /carousels/
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `application/json`


##### HTTP请求示例

###### 请求 path
```
/carousels/
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="cart-controller_resource"></a>
### Cart-controller
Cart Controller


<a name="addusingpost"></a>
#### add
```
POST /cart/add
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**count**  <br>*可选*|count|integer (int32)|
|**Query**|**souvenirId**  <br>*可选*|souvenirId|integer (int32)|
|**Query**|**userId**  <br>*可选*|userId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/cart/add
```


###### 请求 query
```
json :
{
  "count" : 0,
  "souvenirId" : 0,
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="deleteusingdelete"></a>
#### delete
```
DELETE /cart/delete
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**souvenirIds**  <br>*可选*|souvenirIds|string|
|**Query**|**userId**  <br>*可选*|userId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/cart/delete
```


###### 请求 query
```
json :
{
  "souvenirIds" : "string",
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="getcartsouvenircountusingget"></a>
#### getCartSouvenirCount
```
GET /cart/get_cart_souvenir_count
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**userId**  <br>*可选*|userId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/cart/get_cart_souvenir_count
```


###### 请求 query
```
json :
{
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="isselectallusingget"></a>
#### isSelectAll
```
GET /cart/isSelect
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**isChecked**  <br>*可选*|isChecked|boolean|
|**Query**|**souvenirId**  <br>*可选*|souvenirId|integer (int32)|
|**Query**|**userId**  <br>*可选*|userId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/cart/isSelect
```


###### 请求 query
```
json :
{
  "isChecked" : true,
  "souvenirId" : 0,
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="listusingget"></a>
#### list
```
GET /cart/list
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**userId**  <br>*可选*|userId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/cart/list
```


###### 请求 query
```
json :
{
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="updateusingput"></a>
#### update
```
PUT /cart/update
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**count**  <br>*可选*|count|integer (int32)|
|**Query**|**souvenirId**  <br>*可选*|souvenirId|integer (int32)|
|**Query**|**userId**  <br>*可选*|userId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/cart/update
```


###### 请求 query
```
json :
{
  "count" : 0,
  "souvenirId" : 0,
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="comment-controller_resource"></a>
### Comment-controller
Comment Controller


<a name="commentforcommentusingget"></a>
#### commentForComment
```
GET /comments/
```


##### 参数

|类型|名称|说明|类型|默认值|
|---|---|---|---|---|
|**Query**|**apiRootId**  <br>*可选*|apiRootId|string||
|**Query**|**commentId**  <br>*可选*|commentId|string||
|**Query**|**content**  <br>*可选*|content|string||
|**Query**|**type**  <br>*可选*|type|integer (int32)|`6`|
|**Query**|**userId**  <br>*可选*|userId|string||


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|integer (int32)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/comments/
```


###### 请求 query
```
json :
{
  "apiRootId" : "string",
  "commentId" : "string",
  "content" : "string",
  "type" : 0,
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
0
```


<a name="commentforrootusingget"></a>
#### commentForRoot
```
GET /comments/root
```


##### 参数

|类型|名称|说明|类型|默认值|
|---|---|---|---|---|
|**Query**|**apiRootId**  <br>*可选*|apiRootId|string||
|**Query**|**content**  <br>*可选*|content|string||
|**Query**|**type**  <br>*可选*|type|integer (int32)|`6`|
|**Query**|**userId**  <br>*可选*|userId|string||


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|integer (int32)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/comments/root
```


###### 请求 query
```
json :
{
  "apiRootId" : "string",
  "content" : "string",
  "type" : 0,
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
0
```


<a name="file-upload-controller_resource"></a>
### File-upload-controller
File Upload Controller


<a name="fileuploadusingpost"></a>
#### fileUpload
```
POST /uploads/imgs
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**FormData**|**files**  <br>*必填*|files|< file > array(multi)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/uploads/imgs
```


###### 请求 formData
```
json :
"file"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="love-controller_resource"></a>
### Love-controller
Love Controller


<a name="addloveusingpost"></a>
#### addLove
```
POST /loves/
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**apiRootId**  <br>*必填*|apiRootId|string|
|**Query**|**userId**  <br>*必填*|userId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|boolean|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/loves/
```


###### 请求 query
```
json :
{
  "apiRootId" : "string",
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
true
```


<a name="order-controller_resource"></a>
### Order-controller
Order Controller


<a name="buynowusingpost"></a>
#### buyNow
```
POST /order/buyNow
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**count**  <br>*可选*|count|integer (int32)|
|**Query**|**shippingId**  <br>*可选*|shippingId|integer (int32)|
|**Query**|**souvenirId**  <br>*可选*|souvenirId|integer (int32)|
|**Query**|**userId**  <br>*可选*|userId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/order/buyNow
```


###### 请求 query
```
json :
{
  "count" : 0,
  "shippingId" : 0,
  "souvenirId" : 0,
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="createusingpost"></a>
#### create
```
POST /order/createFromCart
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**shippingId**  <br>*可选*|shippingId|integer (int32)|
|**Query**|**userId**  <br>*可选*|userId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/order/createFromCart
```


###### 请求 query
```
json :
{
  "shippingId" : 0,
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="findoneusingget"></a>
#### findOne
```
GET /order/find
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**orderId**  <br>*可选*|orderId|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[OrderDTO](#orderdto)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/order/find
```


###### 请求 query
```
json :
{
  "orderId" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "closeTime" : "string",
  "createTime" : "string",
  "endTime" : "string",
  "orderDetailList" : [ {
    "createTime" : "string",
    "currentUnitPrice" : 0.0,
    "detailId" : 0,
    "quantity" : 0,
    "souvenirId" : 0,
    "souvenirImage" : "string",
    "souvenirName" : "string",
    "totalPrice" : 0.0,
    "userId" : "string"
  } ],
  "orderId" : 0,
  "payment" : 0.0,
  "paymentTime" : "string",
  "paymentType" : 0,
  "paymentTypeDesc" : "string",
  "postage" : 0,
  "sendTime" : "string",
  "shipping" : {
    "createTime" : "string",
    "receiverAddress" : "string",
    "receiverCity" : "string",
    "receiverDistrict" : "string",
    "receiverMobile" : "string",
    "receiverName" : "string",
    "receiverPhone" : "string",
    "receiverProvince" : "string",
    "receiverZip" : "string",
    "shippingId" : 0
  },
  "status" : 0,
  "statusDesc" : "string",
  "userId" : "string"
}
```


<a name="listusingpost"></a>
#### list
```
POST /order/list
```


##### 参数

|类型|名称|说明|类型|默认值|
|---|---|---|---|---|
|**Query**|**num**  <br>*可选*|num|integer (int32)|`1`|
|**Query**|**size**  <br>*可选*|size|integer (int32)|`3`|
|**Query**|**userId**  <br>*必填*|userId|string||


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/order/list
```


###### 请求 query
```
json :
{
  "num" : 0,
  "size" : 0,
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="oss-controller_resource"></a>
### Oss-controller
Oss Controller


<a name="getpictureusingpost"></a>
#### getPicture
```
POST /oss/imgs/{parentId}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**parentId**  <br>*必填*|parentId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/oss/imgs/string
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="fileuploadusingpost_1"></a>
#### fileUpload
```
POST /oss/insert
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**parentId**  <br>*可选*|parentId|string|
|**FormData**|**files**  <br>*必填*|files|< file > array(multi)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/oss/insert
```


###### 请求 query
```
json :
{
  "parentId" : "string"
}
```


###### 请求 formData
```
json :
"file"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="post-controller_resource"></a>
### Post-controller
Post Controller


<a name="sendusingpost"></a>
#### send
```
POST /posts/
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|integer (int32)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/posts/
```


##### HTTP响应示例

###### 响应 200
```
json :
0
```


<a name="findusingpost"></a>
#### find
```
POST /posts/detail
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**userId**  <br>*可选*|userId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/posts/detail
```


###### 请求 query
```
json :
{
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="findallcommentsnewusingget"></a>
#### findAllCommentsNew
```
GET /posts/find
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**parentId**  <br>*可选*|parentId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|< [Comment](#comment) > array|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/posts/find
```


###### 请求 query
```
json :
{
  "parentId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
[ {
  "apiRootId" : "string",
  "children" : [ {
    "apiRootId" : "string",
    "children" : [ "..." ],
    "commentId" : "string",
    "content" : "string",
    "createTime" : "string",
    "enabled" : true,
    "parentId" : "string",
    "thumbers" : [ {
      "createTime" : "string",
      "faceIcon" : "string",
      "id" : "string",
      "nickname" : "string",
      "status" : true,
      "username" : "string"
    } ],
    "type" : 0,
    "updateTime" : "string",
    "userId" : "string"
  } ],
  "commentId" : "string",
  "content" : "string",
  "createTime" : "string",
  "enabled" : true,
  "parentId" : "string",
  "thumbers" : [ {
    "createTime" : "string",
    "faceIcon" : "string",
    "id" : "string",
    "nickname" : "string",
    "status" : true,
    "username" : "string"
  } ],
  "type" : 0,
  "updateTime" : "string",
  "userId" : "string"
} ]
```


<a name="findapostusingpost"></a>
#### findAPost
```
POST /posts/findPost
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**postId**  <br>*可选*|postId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/posts/findPost
```


###### 请求 query
```
json :
{
  "postId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="user-controller_resource"></a>
### User-controller
User Controller


<a name="findmycommentsofanarticleusingget"></a>
#### findMyCommentsOfAnArticle
```
GET /users/
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**articleId**  <br>*可选*|articleId|string|
|**Query**|**userId**  <br>*可选*|userId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|< [CommentContent](#commentcontent) > array|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/users/
```


###### 请求 query
```
json :
{
  "articleId" : "string",
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
[ {
  "commentId" : "string",
  "commenters" : [ {
    "commentContents" : [ {
      "commentId" : "string",
      "commenters" : [ {
        "commentContents" : [ "..." ],
        "faceIcon" : "string",
        "nickname" : "string",
        "userId" : "string"
      } ],
      "content" : "string",
      "contentId" : "string",
      "createTime" : "string",
      "fromId" : "string",
      "id" : "string",
      "loveId" : "string"
    } ],
    "faceIcon" : "string",
    "nickname" : "string",
    "userId" : "string"
  } ],
  "content" : "string",
  "contentId" : "string",
  "createTime" : "string",
  "fromId" : "string",
  "id" : "string",
  "loveId" : "string"
} ]
```


<a name="logoutusingpost"></a>
#### logout
```
POST /users/logout
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**userId**  <br>*可选*|userId|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/users/logout
```


###### 请求 query
```
json :
{
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="modifyuserinfousingpost"></a>
#### modifyUserInfo
```
POST /users/modifyUserInfo
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**mpwxUserInfoDTO**  <br>*必填*|mpwxUserInfoDTO|[MPWXUserInfoDTO](#mpwxuserinfodto)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/users/modifyUserInfo
```


###### 请求 body
```
json :
{
  "birthday" : "string",
  "city" : "string",
  "country" : "string",
  "faceIcon" : "string",
  "gender" : 0,
  "language" : "string",
  "nickname" : "string",
  "province" : "string",
  "sessionKey" : "string",
  "skey" : "string",
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="loginusingpost"></a>
#### 微信小程序登录
```
POST /users/mpWXLogin/{code}
```


##### 说明
code需要通过wx.login获取


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**code**  <br>*必填*|wx.login得到的code|string|
|**Body**|**mpwxUserInfo**  <br>*必填*|mpwxUserInfo|[MPWXUserInfo](#mpwxuserinfo)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/users/mpWXLogin/string
```


###### 请求 body
```
json :
{
  "avatarUrl" : "string",
  "city" : "string",
  "country" : "string",
  "gender" : 0,
  "language" : "string",
  "nickName" : "string",
  "province" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="loginusingpost_1"></a>
#### login
```
POST /users/registerOrLogin
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**user**  <br>*必填*|user|[UserInfo](#userinfo)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/users/registerOrLogin
```


###### 请求 body
```
json :
{
  "password" : "string",
  "userId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="thirdpartyloginusingpost"></a>
#### thirdPartyLogin
```
POST /users/thirdPartyLogin/{logintype}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**logintype**  <br>*必填*|logintype|string|
|**Body**|**thirdPartyUser**  <br>*必填*|thirdPartyUser|[ThirdPartyUser](#thirdpartyuser)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/users/thirdPartyLogin/string
```


###### 请求 body
```
json :
{
  "city" : "string",
  "country" : "string",
  "faceIcon" : "string",
  "gender" : 0,
  "language" : "string",
  "loginType" : "string",
  "nickName" : "string",
  "province" : "string",
  "telephone" : 0,
  "thirdPartyId" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```


<a name="uploadfaceusingpost"></a>
#### uploadFace
```
POST /users/uploadFace
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**userId**  <br>*可选*|userId|string|
|**FormData**|**file**  <br>*必填*|file|file|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResultVO](#resultvo)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `multipart/form-data`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/users/uploadFace
```


###### 请求 query
```
json :
{
  "userId" : "string"
}
```


###### 请求 formData
```
json :
"file"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string"
}
```




<a name="definitions"></a>
## 定义

<a name="articledto"></a>
### ArticleDTO

|名称|说明|类型|
|---|---|---|
|**articleId**  <br>*可选*|**样例** : `"string"`|string|
|**comments**  <br>*可选*|**样例** : `[ "[comment](#comment)" ]`|< [Comment](#comment) > array|
|**coverPicture**  <br>*可选*|**样例** : `"string"`|string|
|**createTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**deleted**  <br>*可选*|**样例** : `true`|boolean|
|**enabled**  <br>*可选*|**样例** : `true`|boolean|
|**faceIcon**  <br>*可选*|**样例** : `"string"`|string|
|**headPicture**  <br>*可选*|**样例** : `"string"`|string|
|**htmlContent**  <br>*可选*|**样例** : `"string"`|string|
|**nickname**  <br>*可选*|**样例** : `"string"`|string|
|**tagVOS**  <br>*可选*|**样例** : `[ "[tagvo](#tagvo)" ]`|< [TagVO](#tagvo) > array|
|**thumbers**  <br>*可选*|**样例** : `[ "[thumber](#thumber)" ]`|< [Thumber](#thumber) > array|
|**title**  <br>*可选*|**样例** : `"string"`|string|
|**updateTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**userId**  <br>*可选*|**样例** : `"string"`|string|
|**username**  <br>*可选*|**样例** : `"string"`|string|
|**viaCitiesVOS**  <br>*可选*|**样例** : `[ "[cityvo](#cityvo)" ]`|< [CityVO](#cityvo) > array|


<a name="cityvo"></a>
### CityVO

|名称|说明|类型|
|---|---|---|
|**cityId**  <br>*可选*|**样例** : `"string"`|string|
|**name**  <br>*可选*|**样例** : `"string"`|string|


<a name="comment"></a>
### Comment

|名称|说明|类型|
|---|---|---|
|**apiRootId**  <br>*可选*|**样例** : `"string"`|string|
|**children**  <br>*可选*|**样例** : `[ "[comment](#comment)" ]`|< [Comment](#comment) > array|
|**commentId**  <br>*可选*|**样例** : `"string"`|string|
|**content**  <br>*可选*|**样例** : `"string"`|string|
|**createTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**enabled**  <br>*可选*|**样例** : `true`|boolean|
|**parentId**  <br>*可选*|**样例** : `"string"`|string|
|**thumbers**  <br>*可选*|**样例** : `[ "[thumber](#thumber)" ]`|< [Thumber](#thumber) > array|
|**type**  <br>*可选*|**最小值** : `-128`  <br>**最大值** : `127`**样例** : `0`|integer (int32)|
|**updateTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**userId**  <br>*可选*|**样例** : `"string"`|string|


<a name="commentcontent"></a>
### CommentContent

|名称|说明|类型|
|---|---|---|
|**commentId**  <br>*可选*|**样例** : `"string"`|string|
|**commenters**  <br>*可选*|**样例** : `[ "[commenter](#commenter)" ]`|< [Commenter](#commenter) > array|
|**content**  <br>*可选*|**样例** : `"string"`|string|
|**contentId**  <br>*可选*|**样例** : `"string"`|string|
|**createTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**fromId**  <br>*可选*|**样例** : `"string"`|string|
|**id**  <br>*可选*|**样例** : `"string"`|string|
|**loveId**  <br>*可选*|**样例** : `"string"`|string|


<a name="commenter"></a>
### Commenter

|名称|说明|类型|
|---|---|---|
|**commentContents**  <br>*可选*|**样例** : `[ "[commentcontent](#commentcontent)" ]`|< [CommentContent](#commentcontent) > array|
|**faceIcon**  <br>*可选*|**样例** : `"string"`|string|
|**nickname**  <br>*可选*|**样例** : `"string"`|string|
|**userId**  <br>*可选*|**样例** : `"string"`|string|


<a name="inputstream"></a>
### InputStream
*类型* : object


<a name="mpwxuserinfo"></a>
### MPWXUserInfo

|名称|说明|类型|
|---|---|---|
|**avatarUrl**  <br>*可选*|**样例** : `"string"`|string|
|**city**  <br>*可选*|**样例** : `"string"`|string|
|**country**  <br>*可选*|**样例** : `"string"`|string|
|**gender**  <br>*可选*|**样例** : `0`|integer (int32)|
|**language**  <br>*可选*|**样例** : `"string"`|string|
|**nickName**  <br>*可选*|**样例** : `"string"`|string|
|**province**  <br>*可选*|**样例** : `"string"`|string|


<a name="mpwxuserinfodto"></a>
### MPWXUserInfoDTO

|名称|说明|类型|
|---|---|---|
|**birthday**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**city**  <br>*可选*|**样例** : `"string"`|string|
|**country**  <br>*可选*|**样例** : `"string"`|string|
|**faceIcon**  <br>*可选*|**样例** : `"string"`|string|
|**gender**  <br>*可选*|**样例** : `0`|integer (int32)|
|**language**  <br>*可选*|**样例** : `"string"`|string|
|**nickname**  <br>*可选*|**样例** : `"string"`|string|
|**province**  <br>*可选*|**样例** : `"string"`|string|
|**sessionKey**  <br>*可选*|**样例** : `"string"`|string|
|**skey**  <br>*可选*|**样例** : `"string"`|string|
|**userId**  <br>*可选*|**样例** : `"string"`|string|


<a name="orderdto"></a>
### OrderDTO

|名称|说明|类型|
|---|---|---|
|**closeTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**createTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**endTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**orderDetailList**  <br>*可选*|**样例** : `[ "[orderdetailvo](#orderdetailvo)" ]`|< [OrderDetailVO](#orderdetailvo) > array|
|**orderId**  <br>*可选*|**样例** : `0`|integer (int64)|
|**payment**  <br>*可选*|**样例** : `0.0`|number|
|**paymentTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**paymentType**  <br>*可选*|**样例** : `0`|integer (int32)|
|**paymentTypeDesc**  <br>*可选*|**样例** : `"string"`|string|
|**postage**  <br>*可选*|**样例** : `0`|integer (int32)|
|**sendTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**shipping**  <br>*可选*|**样例** : `"[shippingvo](#shippingvo)"`|[ShippingVO](#shippingvo)|
|**status**  <br>*可选*|**样例** : `0`|integer (int32)|
|**statusDesc**  <br>*可选*|**样例** : `"string"`|string|
|**userId**  <br>*可选*|**样例** : `"string"`|string|


<a name="orderdetailvo"></a>
### OrderDetailVO

|名称|说明|类型|
|---|---|---|
|**createTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**currentUnitPrice**  <br>*可选*|**样例** : `0.0`|number|
|**detailId**  <br>*可选*|**样例** : `0`|integer (int64)|
|**quantity**  <br>*可选*|**样例** : `0`|integer (int32)|
|**souvenirId**  <br>*可选*|**样例** : `0`|integer (int32)|
|**souvenirImage**  <br>*可选*|**样例** : `"string"`|string|
|**souvenirName**  <br>*可选*|**样例** : `"string"`|string|
|**totalPrice**  <br>*可选*|**样例** : `0.0`|number|
|**userId**  <br>*可选*|**样例** : `"string"`|string|


<a name="resultvo"></a>
### ResultVO

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `"object"`|object|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="shippingvo"></a>
### ShippingVO

|名称|说明|类型|
|---|---|---|
|**createTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**receiverAddress**  <br>*可选*|**样例** : `"string"`|string|
|**receiverCity**  <br>*可选*|**样例** : `"string"`|string|
|**receiverDistrict**  <br>*可选*|**样例** : `"string"`|string|
|**receiverMobile**  <br>*可选*|**样例** : `"string"`|string|
|**receiverName**  <br>*可选*|**样例** : `"string"`|string|
|**receiverPhone**  <br>*可选*|**样例** : `"string"`|string|
|**receiverProvince**  <br>*可选*|**样例** : `"string"`|string|
|**receiverZip**  <br>*可选*|**样例** : `"string"`|string|
|**shippingId**  <br>*可选*|**样例** : `0`|integer (int64)|


<a name="tagvo"></a>
### TagVO

|名称|说明|类型|
|---|---|---|
|**name**  <br>*可选*|**样例** : `"string"`|string|
|**tagId**  <br>*可选*|**样例** : `"string"`|string|


<a name="thirdpartyuser"></a>
### ThirdPartyUser

|名称|说明|类型|
|---|---|---|
|**city**  <br>*可选*|**样例** : `"string"`|string|
|**country**  <br>*可选*|**样例** : `"string"`|string|
|**faceIcon**  <br>*可选*|**样例** : `"string"`|string|
|**gender**  <br>*可选*|**样例** : `0`|integer (int32)|
|**language**  <br>*可选*|**样例** : `"string"`|string|
|**loginType**  <br>*可选*|**样例** : `"string"`|string|
|**nickName**  <br>*可选*|**样例** : `"string"`|string|
|**province**  <br>*可选*|**样例** : `"string"`|string|
|**telephone**  <br>*可选*|**样例** : `0`|integer (int32)|
|**thirdPartyId**  <br>*可选*|**样例** : `"string"`|string|


<a name="thumber"></a>
### Thumber

|名称|说明|类型|
|---|---|---|
|**createTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**faceIcon**  <br>*可选*|**样例** : `"string"`|string|
|**id**  <br>*可选*|**样例** : `"string"`|string|
|**nickname**  <br>*可选*|**样例** : `"string"`|string|
|**status**  <br>*可选*|**样例** : `true`|boolean|
|**username**  <br>*可选*|**样例** : `"string"`|string|


<a name="userinfo"></a>
### UserInfo

|名称|说明|类型|
|---|---|---|
|**password**  <br>*可选*|**样例** : `"string"`|string|
|**userId**  <br>*可选*|**样例** : `"string"`|string|





