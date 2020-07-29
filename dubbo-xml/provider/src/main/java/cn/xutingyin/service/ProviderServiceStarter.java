package cn.xutingyin.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderServiceStarter {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext(new String[] {"classpath:/spring/dubbo-provider.xml"});
        ProviderService service = (ProviderService)context.getBean("providerService");
        System.out.println(service.sayHello("susan"));

    }
}
