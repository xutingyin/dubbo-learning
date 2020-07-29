package cn.xutingyin.starter;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.xutingyin.ApplicationKeeper;

/**
 * The type Dubbo account service starter.
 */
public class DubboAccountServiceStarter {
    /**
     * 2. Account service is ready . A buyer register an account: U100001 on my e-commerce platform
     *
     * @param args
     *            the input arguments
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext accountContext =
            new ClassPathXmlApplicationContext(new String[] {"spring/dubbo-account-service.xml"});
        accountContext.getBean("accountService");
        JdbcTemplate accountJdbcTemplate = (JdbcTemplate)accountContext.getBean("jdbcTemplate");
        accountJdbcTemplate.update("delete from account_tbl where user_id = 'U100001'");
        accountJdbcTemplate.update("insert into account_tbl(user_id, money) values ('U100001', 999)");

        new ApplicationKeeper(accountContext).keep();
    }
}