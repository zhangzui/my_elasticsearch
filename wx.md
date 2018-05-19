#Elasticsearch入门与实践
#一.Elasticsearch是什么
##1.简单介绍？
Elasticsearch是一个基于Apache Lucene(TM)的开源搜索引擎。无论在开源还是专有领域，Lucene可以被认为是迄今为止最先进、性能最好的、功能最全的搜索引擎库。但是，Lucene只是一个库。
想要使用它，你必须使用Java来作为开发语言并将其直接集成到你的应用中，更糟糕的是，Lucene非常复杂，你需要深入了解检索的相关知识来理解它是如何工作的。
Elasticsearch也使用Java开发并使用Lucene作为其核心来实现所有索引和搜索的功能，但是它的目的是通过简单的RESTful API来隐藏Lucene的复杂性，从而让全文搜索变得简单。
Elasticsearch不仅仅是Lucene和全文搜索，我们还能这样去描述它：分布式的实时文件存储，每个字段都被索引并可被搜索，分布式的实时分析搜索引擎，可以扩展到上百台服务器，处理PB级结构化或非结构化数据;

##2.可以用来干啥？
    1.分库分表，一些运营数据查询，没有路由字段，其他的条件查询，范围查询，分页和排序都变得十分复杂，
    2.基于它的特点，对全文进行分词,然后进行快速的分词搜索，实现一个简易的搜索引擎功能;
    3.除了搜索之外，es还可以快速的实现聚合运算，快速基于关键信息进行统计和分析。

#二.QuickStart
###1.搭建环境-Ubuntu
1.官网下载tar包，我这里下载的是一个6.1.2版本;
配置文件：修改 elasticsearch/config/elasticsearch.yml
```
cluster.name: my-application
network.host: 127.0.0.1
transport.tcp.port: 9300
http.port: 9200

```
2.bin目录启动elasticsearch脚本;
3安装插件elasticsearch-head
```
1.git clone https://github.com/mobz/elasticsearch-head.git
2.进到elasticsearch-head目录
    安装node：sudo apt-get install nodejs-legacy
    安装npm： sudo apt-get install npm
    安装grunt：npm install -g grunt-cli
    构建：npm install
    启动：grunt server
```

###2.搭建环境-windows
```
1.下载zip包，解压，启动bat脚本即可;
3.安装插件plugin-head
2.下载 elasticsearch-head 或者 git clone 到随便一个文件夹
3.安装nodejs
4.elasticsearch-head
    npm install -g grunt-cli
    npm install
    grunt server
5.访问http://localhost:9100/
```

#三.了解基础数据类型和模式：
###1.对比关系型数据库：

支持的数据类型：
es使用json作为数据的交互格式，因此可以说，只要json支持的数据类型，es都是支持的。
主要有：string、byte、short、integer、long、float、double、boolean、date，
ES更新到5版本后，取消了 string 数据类型，代替它的是 keyword 和 text 数据类型;然后就是复合数据类型，主要就是两个：array、object
Keyword 数据类型用来建立电子邮箱地址、姓名、邮政编码和标签等数据，不需要进行分词。可以被用来检索过滤、排序和聚合。keyword 类型字段只能用本身来进行检索。


#四.运用ES自带的restful API来进行简单CURD

###1.创建索引：
http://localhost:9200/my_test/ 直接put就可以创建成功；
settings：可以设置分片和副本数
mappings：设置类型，下面相当于两张表的user和order的字段信息
```
{
    "settings":{
        "number_of_shards":1,
        "number_of_replicas":2
    },
    "mappings":{
        "user":{
            "properties":{
                "userid":{
                    "type":"keyword",
                    "index":"true"
                },
                "username":{
                    "type":"keyword",
                    "index":"true"
                }
            }
        }
    }
}
```
###2.新增类型字段：
新增user表的字段：用户年龄：userage
http://localhost:9200/my_test/_mapping/user/
```
{
    "properties":{
        "userage":{
            "type":"integer",
            "index":"true"
        },
         "useraddress":{
                    "type":"text",
                    "index":"true"
                }
    }
}
```
#3.插入数据：
例子：类比往mytest库中的user表中插入一条数据：
注意，后面不加1,2,则自己会成成一个id，这里可以插入的时候带一个id，方便后边查询和删除。
更新直接针对id进行覆盖即可。
post：http://localhost:9200/my_test/user/1/
```
{
    "userid":"000001",
    "username":"zhangsan",
    "userage":20,
    "useraddress":"中国-北京"
}
```
post：http://localhost:9200/my_test/user/2/
```
{
    "userid":"000002",
    "username":"lisi",
    "userage":21,
    "useraddress":"中国-上海-黄浦区"
}
```
#4.查询：
a.全量查询
get http://localhost:9200/my_test/_search
```
{
    "took":1,
    "timed_out":false,
    "_shards":{
        "total":1,
        "successful":1,
        "skipped":0,
        "failed":0
    },
    "hits":{
        "total":2,
        "max_score":1,
        "hits":[
            {
                "_index":"my_test",
                "_type":"user",
                "_id":"2",
                "_score":1,
                "_source":{
                    "userid":"000002",
                    "username":"lisi",
                    "userage":21
                }
            },
            {
                "_index":"my_test",
                "_type":"user",
                "_id":"1",
                "_score":1,
                "_source":{
                    "userid":"000001",
                    "username":"zhangsan",
                    "userage":20
                }
            }
        ]
    }
}
```
b.条件查询：term是代表完全匹配，match单个字段匹配查询，multi_match可以多个字段匹配;
这里查询名字为lisi的用户
```
{
  "query": {
    "term": {
      "username": "lisi"
    }
  }
}
```
c.过滤查询：查询年纪大于20的用户
```
{
  "query": {
    "bool": {
      "filter": {
        "range": {
          "userage": {
            "gt": 20
          }
        }
      }
    }
  }
}
```

5.删除index 和删除一个document
```
DELETE index：http://localhost:9200/my_index/
DELETE document：http://localhost:9200/my_test/user/2/
```
##五.总结
Elasticsearch不仅提供了RestApi，restApi方便我们可视化的查询和创建数据，更好的了解ES。而且提供了全面的java 服务端的API，大家可以通过连接es-client，进行索引创建，以及数据复杂查询和封装。
参考代码：https://github.com/zhangzui/my_elasticsearch.git，后面会介绍Java的API的封装和使用。








