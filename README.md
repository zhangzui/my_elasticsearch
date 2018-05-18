# my_elasticsearch
elasticsearch实践
#一.windows搭建环境
#安装插件plugin-head
#集成javaAPI
#elasticsearch－head插件安装后，启动问题！
1.修改 elasticsearch/config/elasticsearch.yml
    添加
http.cors.enabled: true
http.cors.allow-origin: "*"
2.下载 elasticsearch-head 或者 git clone 到随便一个文件夹
3.安装nodejs
4.cd /path/to/elasticsearch-head
    npm install -g grunt-cli
    npm install
    grunt server
5.http://localhost:9100/

#一.ubuntu搭建环境
None of the configured nodes are available：
集群名字是否对，配置文件的IP是否对
安装插件head

1.git clone https://github.com/mobz/elasticsearch-head.git
2.进到/home/zhangzuizui/myresource/elasticsrearch/elasticsearch-head目录
    安装node：sudo apt-get install nodejs-legacy
    安装npm： sudo apt-get install npm
    安装grunt：npm install -g grunt-cli
    构建：npm install
    启动：grunt server










