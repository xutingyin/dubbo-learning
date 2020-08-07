# 分布式事务-Seata-AT模式
1、目前存在问题：Business启动的时候，storage服务找不到
解决方案：
1、检查 registry.conf 的注册中心的group、namespace等信息是否和nacos注册中心上传的一致。
2、检查使用的seata jar包和服务端使用的seata版本是否一致