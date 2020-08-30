##康康社区



##资料
教程授权GITHUB：https://docs.github.com/en/developers/apps/authorizing-oauth-appshttps://docs.github.com/en/developers/apps/building-oauth-apps
https://developer.github.com/apps/building-github-apps/
https://docs.github.com/en/developers/apps/building-oauth-apps

[fastjson仓库]： https://mvnrepository.com/artifact/com.alibaba/fastjson/1.2.72
[阿里云仓库]https://maven.aliyun.com/mvn/search
[mybatis]https://blog.mybatis.org/
[spring-boot]https://spring.io/projects/spring-boot
[使用@Autowired注解 ]:https://blog.csdn.net/zhangjingao/article/details/81094529

##异常处理
使用OkHttp过程出现java.net.SocketException: Connection reset 异常
csdn解决：https://blog.csdn.net/pepper5/article/details/104743585/



  spring.datasource.other.jdbc-url=jdbc:h2:~/helloworld;AUTO_SERVER=TRUE
  spring.datasource.other.username=root
  spring.datasource.other.password=123
  spring.datasource.other.driver-class-name=org.h2.driver

spring.h2.console.settings.web-allow-others=true
spring.h2.console.path=/h2
spring.h2.console.enabled=true

'''
create table USER
(
    ID           INT auto_increment,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);
'''
