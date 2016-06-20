package com.qcloud.component.sellercenter.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.publicdata.ClassifyType;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.publicdata.util.ClassifyUtils;
import com.qcloud.component.publicservice.SmsClient;
import com.qcloud.component.sellercenter.OutdatedSellercenterClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.entity.MerchantEntity;
import com.qcloud.component.sellercenter.model.Merchant;
import com.qcloud.component.sellercenter.model.MerchantClassify;
import com.qcloud.component.sellercenter.model.MerchantEvaluation;
import com.qcloud.component.sellercenter.model.MerchantMember;
import com.qcloud.component.sellercenter.model.MerchantMerchandiseClassify;
import com.qcloud.component.sellercenter.model.MerchantSpecClassify;
import com.qcloud.component.sellercenter.model.StoreMember;
import com.qcloud.component.sellercenter.model.key.TypeEnum.NotifyType;
import com.qcloud.component.sellercenter.model.query.MerchantQuery;
import com.qcloud.component.sellercenter.model.query.StoreMemberQuery;
import com.qcloud.component.sellercenter.service.MerchantClassifyService;
import com.qcloud.component.sellercenter.service.MerchantEvaluationService;
import com.qcloud.component.sellercenter.service.MerchantMemberService;
import com.qcloud.component.sellercenter.service.MerchantMerchandiseClassifyService;
import com.qcloud.component.sellercenter.service.MerchantOrderFormService;
import com.qcloud.component.sellercenter.service.MerchantService;
import com.qcloud.component.sellercenter.service.MerchantSpecClassifyService;
import com.qcloud.component.sellercenter.service.StoreMemberService;
import com.qcloud.component.sellercenter.service.StoreService;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class OutdatedSellercenterClientImpl implements OutdatedSellercenterClient {

    @Autowired
    protected MerchantMemberService            merchantMemberService;

    @Autowired
    protected MerchantService                  merchantService;

    @Autowired
    protected MerchantClassifyService          merchantClassifyService;

    @Autowired
    protected PublicdataClient                 publicdataClient;

    @Autowired
    protected MerchantOrderFormService         merchantOrderFormService;

    @Autowired
    protected StoreService                     storeService;

    @Autowired
    protected StoreMemberService               storeMemberService;

    @Autowired
    private MerchantEvaluationService          merchantEvaluationService;

    @Autowired
    private SmsClient                          smsClient;

    @Autowired
    private MerchantMerchandiseClassifyService merchantMerchandiseClassifyService;

    @Autowired
    private MerchantSpecClassifyService        merchantSpecClassifyService;

    @Autowired
    private SellercenterClient                 sellercenterClient;

    @Override
    public List<QMerchant> listMerchant(long memberId) {

        List<MerchantMember> mmList = merchantMemberService.listByMember(memberId);
        List<Long> keys = new ArrayList<Long>();
        for (MerchantMember merchantMember : mmList) {
            keys.add(merchantMember.getMerchantId());
        }
        List<Merchant> merchantList = merchantService.list(keys);
        List<QMerchant> result = new ArrayList<QMerchant>();
        for (Merchant merchant : merchantList) {
            result.add(toQMerchant(merchant));
        }
        return result;
    }

    private QMerchant toQMerchant(Merchant merchant) {

        if (merchant == null) {
            return null;
        }
        MerchantEntity qMerchant = new MerchantEntity(merchant);
        return qMerchant;
    }

    @Override
    public List<Classify> listMerchantClassify(long merchantId) {

        Merchant merchant = merchantService.get(merchantId);
        AssertUtil.assertNotNull(merchant, "商家不存在");
        List<MerchantClassify> list = merchantClassifyService.listByMerchant(merchantId);
        List<Classify> classifyList = new ArrayList<Classify>();
        List<Classify> allList = publicdataClient.listClassify(ClassifyType.MERCHANDISE);
        Set<Long> keysSet = new HashSet<Long>();
        for (MerchantClassify merchantClassify : list) {
            List<Classify> pList = ClassifyUtils.getParentList(allList, merchantClassify.getClassifyId());
            for (Classify classify : pList) {
                if (keysSet.add(classify.getId())) {
                    classifyList.add(classify);
                }
            }
        }
        classifyList = ClassifyUtils.sort(classifyList);
        return classifyList;
    }

    @Override
    public StoreMember getMemberStore(StoreMemberQuery query) {

        HashMap map = (HashMap) BeanUtils.transBean2Map(query);
        return storeMemberService.get(map);
    }

    @Override
    public Page<QMerchant> list4select(MerchantQuery query, int start, int count) {

        Page<QMerchant> pages = new Page<QMerchant>();
        Page<Merchant> merchantList = merchantService.page(query, start, count);
        List<QMerchant> qMerchants = new ArrayList<QMerchant>();
        for (Merchant merchant : merchantList.getData()) {
            MerchantEntity qMerchant = new MerchantEntity(merchant);
            qMerchants.add(qMerchant);
        }
        pages.setData(qMerchants);
        pages.setCount(merchantList.getCount());
        return pages;
    }

    @Override
    public boolean sendSmsToMerchant(long merchantId, String content) {

        Merchant merchant = merchantService.get(merchantId);
        if (NotifyType.Yes.getKey() == merchant.getNotify() && StringUtils.isNotEmpty(merchant.getReceiveMobile())) {
            smsClient.send(content, merchant.getReceiveMobile());
            return true;
        }
        return false;
    }

    @Override
    public boolean sendSmsToStore(long storeId, String content) {

        QStore store = sellercenterClient.getStore(storeId);
        Merchant merchant = merchantService.get(store.getMerchantId());
        if (NotifyType.Yes.getKey() == merchant.getNotify() && StringUtils.isNotEmpty(store.getSmsMobile())) {
            smsClient.send(content, store.getSmsMobile());
            return true;
        }
        return false;
    }

    @Override
    public MerchantEvaluation getMerchantEvaluation(long evaluationId, long merchantId) {

        return merchantEvaluationService.get(evaluationId, merchantId);
    }

    @Override
    public boolean deleteMerchantEvaluation(long evaluationId, long merchantId) {

        return merchantEvaluationService.delete(evaluationId, merchantId);
    }

    @Override
    public List<Classify> listMerchantMerchandiseClassify(long merchantId) {

        Merchant merchant = merchantService.get(merchantId);
        AssertUtil.assertNotNull(merchant, "商家不存在");
        List<MerchantMerchandiseClassify> list = merchantMerchandiseClassifyService.listByMerchantId(merchantId);
        List<Classify> classifyList = new ArrayList<Classify>();
        List<Classify> allList = publicdataClient.listClassify(ClassifyType.MERCHANDISE,true);
        for (MerchantMerchandiseClassify merchantMerchandiseClassify : list) {
            Classify classify = publicdataClient.getClassify(merchantMerchandiseClassify.getClassifyId());
            String path = ClassifyUtils.calculationPath(allList, classify);
            classify.setName(path);
            classifyList.add(classify);
        }
        classifyList = ClassifyUtils.sort(classifyList);
        //
        return classifyList;
    }

    @Override
    public List<Classify> listMerchantSpecClassify(long merchantId) {

        Merchant merchant = merchantService.get(merchantId);
        AssertUtil.assertNotNull(merchant, "商家不存在");
        List<MerchantSpecClassify> list = merchantSpecClassifyService.listByMerchant(merchantId);
        List<Classify> classifyList = new ArrayList<Classify>();
        List<Classify> allList = publicdataClient.listClassify(ClassifyType.SPECIFICATIONS,true);
        //
        for (MerchantSpecClassify merchantSpecClassify : list) {
            Classify classify = publicdataClient.getClassify(merchantSpecClassify.getClassifyId());
            String path = ClassifyUtils.calculationPath(allList, classify);
            classify.setName(path);
            classifyList.add(classify);
        }
        classifyList = ClassifyUtils.sort(classifyList);
        return classifyList;
    }
}