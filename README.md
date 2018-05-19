#my_elasticsearch
elasticsearch入门与实践
、#一.Elasticsearch是什么
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
搭建环境-Ubuntu
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

搭建环境-windows
1.下载zip包，解压，启动bat脚本即可;
#安装插件plugin-head
2.下载 elasticsearch-head 或者 git clone 到随便一个文件夹
3.安装nodejs
4.elasticsearch-head
    npm install -g grunt-cli
    npm install
    grunt server
5.访问http://localhost:9100/


#三.了解基础数据类型和模式：
对比关系型数据库：

支持的数据类型：
es使用json作为数据的交互格式，因此可以说，只要json支持的数据类型，es都是支持的。
主要有：
string、byte、short、integer、long、float、double、boolean、date
高版本的String替换成text了;
然后就是复合数据类型，主要就是两个：array、object

#四.运用ES自带的restful API来进行简单CURD

1.创建索引：
http://localhost:9200/my_index/ 直接put就可以创建成功；
2.设置分片和索引类型
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
                    "type":"text",
                    "index":"false"
                },
                "username":{
                    "type":"text",
                    "index":"false"
                }
            }
        },
        "order":{
                    "properties":{
                        "orderId":{
                            "type":"text",
                            "index":"false"
                        },
                        "orderInfo":{
                            "type":"text",
                            "index":"false"
                        }
                    }
                }
    }
}
```
新增类型字段：
http://localhost:9200/test/_mapping/user1/
{"properties":{"userid":{"type":"text","index":"false"},"username":{"type":"text","index":"false"},"userage":{"type":"text","index":"false"}}}
#2.插入数据：
put
```
http://localhost:9200/my_index/userid/3/
{"userid":"000003","username":"zhangzuizui"}
```
put
http://localhost:9200/test/user/1/
{"userid":"000001","username":"zhangzuizui","userage":"24"}
#3.查询：
get
http://localhost:9200/my_index/_search

#4.设置setting,制定副本和分片和分词器
http://192.168.79.131:9200/test/_settings
{"index":{"number_of_replicas":2}}








