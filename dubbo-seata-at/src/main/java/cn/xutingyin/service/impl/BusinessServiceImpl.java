package cn.xutingyin.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.xutingyin.service.BusinessService;
import cn.xutingyin.service.OrderService;
import cn.xutingyin.service.StorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;

/**
 * Please add the follow VM arguments:
 * 
 * <pre>
 *     -Djava.net.preferIPv4Stack=true
 * </pre>
 */
@Component
@Service(interfaceClass = BusinessService.class, timeout = 10000)
public class BusinessServiceImpl implements BusinessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessService.class);
    private StorageService storageService;
    private OrderService orderService;

    @GlobalTransactional(timeoutMills = 400000, name = "dubbo-demo-tx")
    @Override
    public void purchase(String userId, String commodityCode, int orderCount) {
        LOGGER.info("purchase begin ... xid: " + RootContext.getXID());
        storageService.deduct(commodityCode, orderCount);
        orderService.create(userId, commodityCode, orderCount);
        // throw new RuntimeException("xxx");

    }

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

}