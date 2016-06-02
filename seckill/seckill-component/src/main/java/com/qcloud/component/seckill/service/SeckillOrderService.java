package com.qcloud.component.seckill.service;

import java.util.List;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.seckill.model.SeckillOrder;
import com.qcloud.component.seckill.model.query.SeckillOrderQuery;
import com.qcloud.pirates.data.Page;

public interface SeckillOrderService {

    boolean canKill(Long seckillMerchandiseId);

    QOrder orderSeckill(QUser user, Long seckillMerchandiseId);

    public boolean add(SeckillOrder seckillOrder);

    public SeckillOrder get(Long id);

    public boolean delete(Long id);

    public boolean update(SeckillOrder seckillOrder);

    public Page<SeckillOrder> page(SeckillOrderQuery query, int start, int count);

    public List<SeckillOrder> listAll();
}
