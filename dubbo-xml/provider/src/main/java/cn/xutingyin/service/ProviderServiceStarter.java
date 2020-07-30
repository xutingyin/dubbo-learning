package cn.xutingyin.service;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderServiceStarter {
    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"classpath:/spring/dubbo-provider.xml"});
            context.start();
            ProviderService service = (ProviderService)context.getBean("providerService");
            // System.out.println(service.sayHello("susan"));
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
