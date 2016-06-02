package com.qcloud.component.sellercenter.service;

import java.util.List;
import com.qcloud.component.sellercenter.model.DistributeMembershipCard;
import com.qcloud.component.sellercenter.model.DistributeMembershipCardStat;
import com.qcloud.component.sellercenter.model.query.DistributeMembershipCardQuery;
import com.qcloud.component.sellercenter.model.query.DistributeMembershipCardStatQuery;
import com.qcloud.pirates.data.Page;

public interface DistributeMembershipCardService {

    public boolean add(DistributeMembershipCard distributeMembershipCard);

    public DistributeMembershipCard get(Long id);

    public DistributeMembershipCard getByCardNumber(String cardNumber);

    public boolean initDistribute(Long merchantId, String startNumStr, String endNumStr);

    public boolean distribute(Long memberId, String cardNumber, String mobile, String name, int sex);

    public boolean delete(Long id);

    public boolean update(DistributeMembershipCard distributeMembershipCard);

    public Page<DistributeMembershipCard> page(DistributeMembershipCardQuery query, int start, int count);

    public Page<DistributeMembershipCard> page(Long merchantId, int start, int count);

    public Page<DistributeMembershipCardStat> statPage(DistributeMembershipCardStatQuery query, int start, int count);

    public int countMerchantSended(long merchantId);

    public List<DistributeMembershipCard> listAll();
}
