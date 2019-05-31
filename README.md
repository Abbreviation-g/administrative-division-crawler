# administrative-division-crawler  
从中华人民共和国民政部爬虫获取省市县行政区划并保存到数据库  \
[民政部](http://xzqh.mca.gov.cn/map "民政部")  

## 如果要重新爬虫获取省市县行政区划数据并初始化数据库，则按以下方式执行
如果使用命令行直接运行jar文件，则使用java -jar administrative-division-crawler-0.0.1-SNAPSHOT.jar --spring.profiles.active=init.  
如果使用开发工具，运行ServiceMainApplication.java文件启动，则增加参数--spring.profiles.active=test 

## 提供三个combox接口，用于获取省市县三级联动的下拉框数据
省级下拉框 http://localhost:8763/province/combox \
地级下拉框 http://localhost:8763/prefecture/combox?pid=3 (当参数为空时返回所有地级行政区) \
县级下拉框 http://localhost:8763/county/combox?pid=1 (当参数为空时返回所有县级行政区) 

## 爬虫方法如下图
**获取省级行政区数据**
![avatar](/log/get_province.png)
**获取地级行政区数据**
![avatar](/log/get_prefecture.png)
**获取县级行政区数据**
![avatar](/log/get_county.png)
