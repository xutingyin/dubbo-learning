package cn.xutingyin.entity;

import java.io.Serializable;

/**
 * The type Order.
 */
public class Order implements Serializable {
    /**
     * The Id.
     */
    public long id;
    /**
     * The User id.
     */
    public String userId;
    /**
     * The Commodity code.
     */
    public String commodityCode;
    /**
     * The Count.
     */
    public int count;
    /**
     * The Money.
     */
    public int money;

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", userId='" + userId + '\'' + ", commodityCode='" + commodityCode + '\''
            + ", count=" + count + ", money=" + money + '}';
    }
}