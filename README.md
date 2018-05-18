# my_elasticsearch
elasticsearch实践
#本地搭建环境
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





