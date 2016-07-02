package com.qcloud.component.sellercenter.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.publicservice.SmsClient;
import com.qcloud.component.sellercenter.dao.DistributeMembershipCardDao;
import com.qcloud.component.sellercenter.exception.SellerCenterException;
import com.qcloud.component.sellercenter.model.DistributeMembershipCard;
import com.qcloud.component.sellercenter.model.DistributeMembershipCardStat;
import com.qcloud.component.sellercenter.model.Merchant;
import com.qcloud.component.sellercenter.model.key.TypeEnum.DistributeMembershipCardStateType;
import com.qcloud.component.sellercenter.model.query.DistributeMembershipCardQuery;
import com.qcloud.component.sellercenter.model.query.DistributeMembershipCardStatQuery;
import com.qcloud.component.sellercenter.service.DistributeMembershipCardService;
import com.qcloud.component.sellercenter.service.MerchantService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class DistributeMembershipCardServiceImpl implements DistributeMembershipCardService {

    @Autowired
    private DistributeMembershipCardDao distributeMembershipCardDao;

    @Autowired
    private MerchantService             merchantService;

    @Autowired
    private AutoIdGenerator             autoIdGenerator;

    private static final String         ID_KEY = "sellercenter_distribute_membership_card";

    @Autowired
    private PersonalcenterClient        personalcenterClient;

    @Override
    public boolean add(DistributeMembershipCard distributeMembershipCard) {

        long id = autoIdGenerator.get(ID_KEY);
        distributeMembershipCard.setId(id);
        return distributeMembershipCardDao.add(distributeMembershipCard);
    }

    @Override
    public DistributeMembershipCard get(Long id) {

        return distributeMembershipCardDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return distributeMembershipCardDao.delete(id);
    }

    @Override
    public boolean update(DistributeMembershipCard distributeMembershipCard) {

        return distributeMembershipCardDao.update(distributeMembershipCard);
    }

    @Override
    public Page<DistributeMembershipCard> page(DistributeMembershipCardQuery query, int start, int count) {

        return distributeMembershipCardDao.page(query, start, count);
    }

    public List<DistributeMembershipCard> listAll() {

        return distributeMembershipCardDao.listAll();
    }

    @Override
    public DistributeMembershipCard getByCardNumber(String cardNumber) {

        return distributeMembershipCardDao.getByCardNumber(cardNumber);
    }

    @Transactional
    @Override
    public boolean initDistribute(Long merchantId, String startNumStr, String endNumStr) {

        Merchant merchant = merchantService.get(merchantId);
        AssertUtil.assertNotNull(merchant, "商家不存在." + merchantId);
        int length = startNumStr.length();
        Long start = Long.valueOf(startNumStr);
        Long end = Long.valueOf(endNumStr);
        for (long index = start; index <= end; index++) {
            String number = StringUtils.leftPad(String.valueOf(index), length, "0");
            DistributeMembershipCard distributeMembershipCard = getByCardNumber(number);
            if (distributeMembershipCard != null) {
                throw new SellerCenterException("卡号已经派发过." + number);
            }
            distributeMembershipCard = new DistributeMembershipCard();
            distributeMembershipCard.setCardNumber(number);
            distributeMembershipCard.setMemberId(-1);
            distributeMembershipCard.setMerchantId(merchantId);
            distributeMembershipCard.setTime(new Date());
            distributeMembershipCard.setState(DistributeMembershipCardStateType.SEND.getKey());
            distributeMembershipCard.setMerchantCode(merchant.getCode());
            distributeMembershipCard.setMerchantName(merchant.getName());
            add(distributeMembershipCard);
        }
        return true;
    }

    public static final String MERCHANT_DISTRIBUTE_CARD_SUCCESS_SMS_TEMPLATE_KEY = "merchant-distribute-success-card-sms-template";

    @Autowired
    private SmsClient          smsClient;

    @Transactional
    @Override
    public boolean distribute(Long memberId, String cardNumber, String mobile, String name, int sex) {

        String pwd = "";
        for (int index = 0; index < 6; index++) {
            pwd += String.valueOf(new Random().nextInt(10));
        }
        Long userId = personalcenterClient.addUser(mobile, cardNumber, name, pwd);
        personalcenterClient.editSex(userId, sex);
        //
        // 发送密码到短信上
        Map<String, String> map = new HashMap<String, String>();
        map.put("pwd", pwd);
        smsClient.send(MERCHANT_DISTRIBUTE_CARD_SUCCESS_SMS_TEMPLATE_KEY, mobile, map);
        //
        DistributeMembershipCard distributeMembershipCard = getByCardNumber(cardNumber);
        distributeMembershipCard.setState(DistributeMembershipCardStateType.SENDED.getKey());
        distributeMembershipCard.setMemberId(memberId);
        distributeMembershipCard.setTime(new Date());
        update(distributeMembershipCard);
        //
        return true;
    }

    @Override
    public Page<DistributeMembershipCardStat> statPage(DistributeMembershipCardStatQuery query, int start, int count) {

        return distributeMembershipCardDao.statPage(query, start, count);
    }

    @Override
    public Page<DistributeMembershipCard> page(Long merchantId, int start, int count) {

        return distributeMembershipCardDao.page(merchantId, start, count);
    }

    @Override
    public int countMerchantSended(long merchantId) {

        return distributeMembershipCardDao.countMerchantSended(merchantId);
    }
}
