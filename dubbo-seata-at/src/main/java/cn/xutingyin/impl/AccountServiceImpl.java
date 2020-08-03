package cn.xutingyin.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

import cn.xutingyin.AccountService;
import io.seata.core.context.RootContext;

@Service
@Component
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private JdbcTemplate jdbcTemplate;

    /**
     * Sets jdbc template.
     *
     * @param jdbcTemplate
     *            the jdbc template
     */
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void debit(String userId, int money) {
        LOGGER.info("Account Service ... xid: " + RootContext.getXID());
        LOGGER.info("Deducting balance SQL: update account_tbl set money = money - {} where user_id = {}", money,
            userId);

        jdbcTemplate.update("update account_tbl set money = money - ? where user_id = ?", new Object[] {money, userId});
        LOGGER.info("Account Service End ... ");
    }
}
