# 启动项目

运行本地上传文件程序之前确保本地已经搭建好Java环境，关于搭建java环境，windows系统可参考链接<https://zhuanlan.zhihu.com/p/368873695>。搭建完系统进入jar包所在目录双击restart.bat程序

# 设置读取采集文件的目录地址
1. 在浏览器输入http://localhost:8083/index/index
2. 在页面中设置读取的文件路径，例如两个csv文件放在D:/desktop/demandAnalysis/data/下，就输入D:/desktop/demandAnalysis/data/即可。
3. 点击提交即可完成设置

# 注意

1. 请勿随意杀死本程序的进程，以防定时上传文件出错导致的数据污染
2. 上传的文件夹下固定只有两个文件，每周定时上传之后都会删除这周的数据，一个是SensorArray1.csv文件，另一个是SensorArray2.csv文件，请勿随意更改文件名

