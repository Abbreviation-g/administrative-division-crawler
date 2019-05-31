# administrative-division-crawler  
从中华人民共和国民政部爬虫获取省市县行政区划并保存到数据库  \
[民政部](http://xzqh.mca.gov.cn/map "民政部")  

## 如果要重新爬虫获取省市县行政区划数据并初始化数据库，则按以下方式执行
如果使用命令行直接运行jar文件，则使用java -jar administrative-division-crawler-0.0.1-SNAPSHOT.jar --spring.profiles.active=init.  
如果使用开发工具，运行ServiceMainApplication.java文件启动，则增加参数--spring.profiles.active=test 
