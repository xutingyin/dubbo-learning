package cn.xutingyin.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xutingyin.BusinessService;
import cn.xutingyin.OrderService;
import cn.xutingyin.StorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;

/**
 * Please add the follow VM arguments:
 * 
 * <pre>
 *     -Djava.net.preferIPv4Stack=true
 * </pre>
 */
public class BusinessServiceImpl implements BusinessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessServiceImpl.class);
    private StorageService storageService;
    private OrderService orderService;

    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * Sets order service.
     *
     * @param orderService
     *            the order service
     */
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GlobalTransactional(timeoutMills = 400000, name = "dubbo-demo-tx")
    @Override
    public void purchase(String userId, String commodityCode, int orderCount) {
        LOGGER.info("purchase begin ... xid: " + RootContext.getXID());
        try {
            storageService.deduct(commodityCode, orderCount);
            orderService.create(userId, commodityCode, orderCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 出现异常时，全局事务回滚
        // throw new RuntimeException("异常了...");

    }

}