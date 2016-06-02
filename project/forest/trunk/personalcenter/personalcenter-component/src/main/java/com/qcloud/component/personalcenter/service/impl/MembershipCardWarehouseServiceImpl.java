package com.qcloud.component.personalcenter.service.impl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.personalcenter.dao.MembershipCardWarehouseDao;
import com.qcloud.component.personalcenter.model.CardNumberConfig;
import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.model.key.TypeEnum.MembershipCardWarehouseStateType;
import com.qcloud.component.personalcenter.model.query.MembershipCardWarehouseQuery;
import com.qcloud.component.personalcenter.service.MembershipCardWarehouseService;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;

@Service
public class MembershipCardWarehouseServiceImpl implements MembershipCardWarehouseService {

    @Autowired
    private MembershipCardWarehouseDao membershipCardWarehouseDao;

    @Autowired
    private AutoIdGenerator            autoIdGenerator;

    private static final String        ID_KEY = "personalcenter_membership_card_warehouse";

    @Override
    public boolean add(MembershipCardWarehouse membershipCardWarehouse) {

        long id = autoIdGenerator.get(ID_KEY);
        membershipCardWarehouse.setId(id);
        membershipCardWarehouse.setState(MembershipCardWarehouseStateType.NEW.getKey());
        return membershipCardWarehouseDao.add(membershipCardWarehouse);
    }

    @Override
    public MembershipCardWarehouse get(Long id) {

        return membershipCardWarehouseDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return membershipCardWarehouseDao.delete(id);
    }

    @Override
    public boolean update(MembershipCardWarehouse membershipCardWarehouse) {

        return membershipCardWarehouseDao.update(membershipCardWarehouse);
    }

    @Override
    public Page<MembershipCardWarehouse> page(MembershipCardWarehouseQuery query, int start, int count) {

        return membershipCardWarehouseDao.page(query, start, count);
    }

    public List<MembershipCardWarehouse> listAll() {

        return membershipCardWarehouseDao.listAll();
    }

    @Override
    public void init(String startNumStr, String endNumStr) {

        int length = startNumStr.length();
        CardNumberConfig config = getConfig();
        AssertUtil.assertTrue(config.getMinLength() <= length && length <= config.getMaxLength(), "卡号长度不在系统允许范围内.");
        Long start = Long.valueOf(startNumStr);
        Long end = Long.valueOf(endNumStr);
        for (long index = start; index <= end; index++) {
            String number = StringUtils.leftPad(String.valueOf(index), length, "0");
            MembershipCardWarehouse membershipCardWarehouse = getByCardNumber(number);
            if (membershipCardWarehouse != null) {
                continue;
            }
            membershipCardWarehouse = new MembershipCardWarehouse();
            membershipCardWarehouse.setCardNumber(number);
            add(membershipCardWarehouse);
        }
    }

    @Override
    public MembershipCardWarehouse getByCardNumber(String number) {

        return membershipCardWarehouseDao.getByCardNumber(number);
    }

    @Override
    public CardNumberConfig getConfig() {

        CardNumberConfig config = new CardNumberConfig();
        Xml xml = XmlFactory.get("personalcenter_user_membership_card_init_number");
        if (xml != null) {
            for (XmlItem xmlItem : xml.getItemList()) {
                if ("start".equals(xmlItem.getAttrMap().get("key"))) {
                    config.setStart(StringUtil.nullToEmpty(xmlItem.getText()).trim());
                }
                if ("end".equals(xmlItem.getAttrMap().get("key"))) {
                    config.setEnd(StringUtil.nullToEmpty(xmlItem.getText()).trim());
                }
                if ("minLength".equals(xmlItem.getAttrMap().get("key"))) {
                    config.setMinLength(Integer.valueOf(StringUtil.nullToEmpty(xmlItem.getText()).trim()));
                }
                if ("maxLength".equals(xmlItem.getAttrMap().get("key"))) {
                    config.setMaxLength(Integer.valueOf(StringUtil.nullToEmpty(xmlItem.getText()).trim()));
                }
                if ("defaultPwd".equals(xmlItem.getAttrMap().get("key"))) {
                    config.setDefaultPwd(StringUtil.nullToEmpty(xmlItem.getText()).trim());
                }
            }
        }
        return config;
    }
}
