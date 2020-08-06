package cn.xutingyin;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerServiceStarter {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-consumer.xml");
        ProviderService providerService = (ProviderService)context.getBean("providerService");
        System.out.println(providerService.sayHello("dd"));
    }
}
