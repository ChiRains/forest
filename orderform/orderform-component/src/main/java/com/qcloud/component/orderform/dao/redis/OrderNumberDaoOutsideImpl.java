package com.qcloud.component.orderform.dao.redis;

import java.util.Date;
import java.util.Random;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.OrderNumberDao;
import com.qcloud.component.orderform.exception.OrderformException;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.pirates.util.DateUtil;

@Repository
public class OrderNumberDaoOutsideImpl implements OrderNumberDao {

    @Resource(name = "redis-orderform")
    private Redis            redis;

    private byte[]           lock  = new byte[0];

    private static final int TIMES = 10;

    private Log              log   = LogFactory.getLog(getClass());

    @Override
    public String getNextNumber() {

        // 尝试 10 次
        for (int index = 0; index < TIMES; index++) {
            String number = generateOrderNumber();
            log.info("尝试第" + (index + 1) + "次获取订单号.");
            if (StringUtils.isNotEmpty(number)) {
                return number;
            }
        }
        throw new OrderformException("尝试了" + TIMES + "次,获取订单号。");
    }

    private String generateOrderNumber() {

        synchronized (lock) {
            String number = generate();
            String val = redis.get(number);
            if (StringUtils.isEmpty(val)) {
                redis.setex(number, 7200, "ok");
                return number;
            } else {
                return null;
            }
        }
    }

    //
    private String generate() {

        int a1 = new Random().nextInt(26);
        int a2 = new Random().nextInt(26);
        int a3 = new Random().nextInt(26);
        int a4 = new Random().nextInt(26);
        int e = new Random().nextInt(100);
        char c1 = UPPERCHAR[a1];
        char c2 = UPPERCHAR[a2];
        char c3 = UPPERCHAR[a3];
        char c4 = UPPERCHAR[a4];
        String timeStr = DateUtil.date2String(new Date(), "yyyyMMddHH");
        return String.valueOf(c1) + String.valueOf(c2) + String.valueOf(c3) + String.valueOf(c4) + timeStr + StringUtils.leftPad(String.valueOf(e), 2, "0");
    }

    private static final char[] UPPERCHAR = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
}
