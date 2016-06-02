package com.qcloud.component.sellercenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.sellercenter.model.DistributeMembershipCard;
import com.qcloud.component.sellercenter.model.DistributeMembershipCardStat;
import com.qcloud.component.sellercenter.model.query.DistributeMembershipCardQuery;
import com.qcloud.component.sellercenter.model.query.DistributeMembershipCardStatQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface DistributeMembershipCardDao extends ISimpleDao<DistributeMembershipCard, Long> {

    public boolean add(DistributeMembershipCard distributeMembershipCard);

    public DistributeMembershipCard get(Long id);

    public boolean delete(Long id);

    public boolean update(DistributeMembershipCard distributeMembershipCard);

    public List<DistributeMembershipCard> list(List<Long> idList);

    public Map<Long, DistributeMembershipCard> map(Set<Long> idSet);

    public Page<DistributeMembershipCard> page(DistributeMembershipCardQuery query, int start, int size);

    public List<DistributeMembershipCard> listAll();

    DistributeMembershipCard getByCardNumber(String cardNumber);

    Page<DistributeMembershipCard> page(Long merchantId, int start, int count);

    Page<DistributeMembershipCardStat> statPage(DistributeMembershipCardStatQuery query, int start, int count);
    
    int countMerchantSended(long merchantId);
}
