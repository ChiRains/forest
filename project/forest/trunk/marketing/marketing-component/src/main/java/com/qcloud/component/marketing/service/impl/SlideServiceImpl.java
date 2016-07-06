package com.qcloud.component.marketing.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.marketing.dao.SlideDao;
import com.qcloud.component.marketing.exception.MarketingException;
import com.qcloud.component.marketing.model.Slide;
import com.qcloud.component.marketing.model.query.SlideQuery;
import com.qcloud.component.marketing.service.SlideService;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.publicdata.IntKeyValue;
import com.qcloud.pirates.data.Page;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    private SlideDao            slideDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY           = "marketing_slide";

    @Autowired
    private ParameterClient     parameterClient;

    @Autowired
    private FileSDKClient       fileSDKClient;

    //
    private static final String SLIDE_SENCE_TYPE = "marketing-slide-sence-type";

    @PostConstruct
    public void init() {

        final String type = parameterClient.get(SLIDE_SENCE_TYPE);
        if (StringUtils.isEmpty(type)) {
            throw new MarketingException("请初始化参数：" + SLIDE_SENCE_TYPE);
        }
        List<IntKeyValue> list = getSenceTypes(type);
        if (CollectionUtils.isEmpty(list)) {
            throw new MarketingException("请初始化参数：" + SLIDE_SENCE_TYPE);
        }
    }

    public List<IntKeyValue> getSenceTypes() {

        final String typeStr = parameterClient.get(SLIDE_SENCE_TYPE);
        return getSenceTypes(typeStr);
    }

    public List<IntKeyValue> getSenceTypes(String typeStr) {

        if (StringUtils.isEmpty(typeStr)) {
            throw new MarketingException("请初始化参数：" + SLIDE_SENCE_TYPE);
        }
        String[] strs = typeStr.split(";");
        List<IntKeyValue> list = new ArrayList<IntKeyValue>();
        for (String str : strs) {
            final String[] items = str.split(":");
            if (items == null || items.length != 2) {
                throw new MarketingException("初始化参数,格式不正确：" + SLIDE_SENCE_TYPE);
            }
            final int key = Integer.valueOf(items[0]);
            final String value = items[1];
            list.add(new IntKeyValue() {

                @Override
                public long getKey() {

                    return key;
                }

                @Override
                public String getValue() {

                    return value;
                }
            });
        }
        return list;
    }

    @Override
    public boolean add(Slide slide) {

        String url = fileSDKClient.uidToUrl(slide.getImage());
        slide.setImage(url);
        long id = autoIdGenerator.get(ID_KEY);
        slide.setId(id);
        return slideDao.add(slide);
    }

    @Override
    public Slide get(Long id) {

        return slideDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return slideDao.delete(id);
    }

    @Override
    public boolean update(Slide slide) {

        Slide slide1 = get(slide.getId());
        if (!slide.getImage().equals(slide1.getImage())) {
            String url = fileSDKClient.uidToUrl(slide.getImage());
            slide.setImage(url);
        }
        return slideDao.update(slide);
    }

    @Override
    public Page<Slide> page(SlideQuery query, int start, int count) {

        return slideDao.page(query, start, count);
    }

    public List<Slide> listAll() {

        return slideDao.listAll();
    }

    @Override
    public List<Slide> listBySence(int sence) {

        return slideDao.listBySence(sence);
    }
}
