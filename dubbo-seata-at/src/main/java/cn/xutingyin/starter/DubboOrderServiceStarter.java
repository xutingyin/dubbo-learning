package cn.xutingyin.starter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xutingyin.ApplicationKeeper;

public class DubboOrderServiceStarter {
    /**
     * The entry point of application.
     *
     * @param args
     *            the input arguments
     */
    public static void main(String[] args) {
        /**
         * 3. Order service is ready . Waiting for buyers to order
         */
        ClassPathXmlApplicationContext orderContext =
            new ClassPathXmlApplicationContext(new String[] {"spring/dubbo-order-service.xml"});
        orderContext.getBean("orderService");
        new ApplicationKeeper(orderContext).keep();
    }
}
