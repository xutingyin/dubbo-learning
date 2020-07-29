package cn.xutingyin.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import cn.xutingyin.entity.Order;
import cn.xutingyin.service.AccountService;
import cn.xutingyin.service.OrderService;
import io.seata.core.context.RootContext;

/**
 * Please add the follow VM arguments:
 * 
 * <pre>
 *     -Djava.net.preferIPv4Stack=true
 * </pre>
 */
@Service(interfaceClass = OrderService.class, timeout = 10000)
@Component
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private AccountService accountService;

    private JdbcTemplate jdbcTemplate;

    @Override
    public Order create(String userId, String commodityCode, int orderCount) {
        LOGGER.info("Order Service Begin ... xid: " + RootContext.getXID());

        // 计算订单金额
        int orderMoney = calculate(commodityCode, orderCount);

        // 从账户余额扣款
        accountService.debit(userId, orderMoney);

        final Order order = new Order();
        order.userId = userId;
        order.commodityCode = commodityCode;
        order.count = orderCount;
        order.money = orderMoney;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        LOGGER.info(
            "Order Service SQL: insert into order_tbl (user_id, commodity_code, count, money) values ({}, {}, {}, {})",
            userId, commodityCode, orderCount, orderMoney);

        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pst = con.prepareStatement(
                    "insert into order_tbl (user_id, commodity_code, count, money) values (?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setObject(1, order.userId);
                pst.setObject(2, order.commodityCode);
                pst.setObject(3, order.count);
                pst.setObject(4, order.money);
                return pst;
            }
        }, keyHolder);

        order.id = keyHolder.getKey().longValue();

        LOGGER.info("Order Service End ... Created " + order);

        return order;
    }

    /**
     * Sets account service.
     *
     * @param accountService
     *            the account service
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Sets jdbc template.
     *
     * @param jdbcTemplate
     *            the jdbc template
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private int calculate(String commodityId, int orderCount) {
        return 200 * orderCount;
    }

}