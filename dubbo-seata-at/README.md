# 分布式事务-Seata-AT模式
1、目前存在问题：Business启动的时候，storage服务找不到
解决方案：
检查 registry.conf 的配置信息中的group、namespace等信息是否和nacos 注册中心上传的一致。