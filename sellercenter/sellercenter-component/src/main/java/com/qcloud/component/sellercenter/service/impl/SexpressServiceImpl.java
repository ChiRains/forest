package com.qcloud.component.sellercenter.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.SexpressDao;
import com.qcloud.component.sellercenter.exception.SellerCenterException;
import com.qcloud.component.sellercenter.model.Sexpress;
import com.qcloud.component.sellercenter.service.SexpressService;
import com.qcloud.component.sellercenter.model.query.SexpressQuery;

@Service
public class SexpressServiceImpl implements SexpressService {

    @Autowired
    private SexpressDao         sexpressDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    @Autowired
    private FileSDKClient       fileSDKClient;

    private static final String ID_KEY = "sellercenter_sexpress";

    @Override
    public Long add(Sexpress sexpress) {
        if(ifExist(sexpress.getCode(),sexpress.getMerchandId())){
            throw new SellerCenterException("物流公司."+sexpress.getCode()+"已经设置过,不能重复设置");
        }
        //
        long id = autoIdGenerator.get(ID_KEY);
        sexpress.setId(id);
        sexpress.setLogo(fileSDKClient.uidToUrl(sexpress.getLogo()));
        if (sexpressDao.add(sexpress)) {
            return id;
        } else {
            return -1L;
        }
    }

    @Override
    public Sexpress get(Long id) {

        return sexpressDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return sexpressDao.delete(id);
    }

    @Override
    public boolean update(Sexpress sexpress) {

        sexpress.setLogo(fileSDKClient.uidToUrl(sexpress.getLogo()));
        return sexpressDao.update(sexpress);
    }

    @Override
    public Page<Sexpress> page(SexpressQuery query, int start, int count) {

        return sexpressDao.page(query, start, count);
    }

    public List<Sexpress> listAll() {

        return sexpressDao.listAll();
    }

    @Override
    public List<Sexpress> listByMerchant(Long merchantId) {

        return sexpressDao.listByMerchant(merchantId);
    }
    
    private boolean ifExist(String code,long merchandId){
        List<Sexpress> list=listByMerchant(merchandId);
        for (Sexpress sexpress : list) {
            if(sexpress.getCode().equalsIgnoreCase(code)){
                return true;
            }
        }
        return false;
        
    }
}
