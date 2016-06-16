package com.qcloud.component.publicdata.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.component.publicdata.dao.ClassifyDao;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.publicdata.model.query.ClassifyQuery;
import com.qcloud.component.publicdata.service.ClassifyBsidMaxNumberService;
import com.qcloud.component.publicdata.service.ClassifyService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class ClassifyServiceImpl implements ClassifyService {

    @Autowired
    private ClassifyDao                  classifyDao;

    @Autowired
    private AutoIdGenerator              autoIdGenerator;

    private static final String          ID_KEY = "publicdata_classify";

    @Autowired
    private FileSDKClient                fileSDKClient;

    @Autowired
    private ClassifyBsidMaxNumberService classifyBsidMaxNumberService;

    @Override
    public boolean add(Classify classify) {

        // Classify c = classifyDao.getByName(classify.getType(), classify.getName());
        // if (c != null) {
        // // throw new PublicdataException("分类名称已经使用." + classify.getName());
        // }
        List<Classify> list = classifyDao.listChildren(classify.getType(), classify.getParentId());
        Classify p = classifyDao.get(classify.getParentId());
        if (p == null) {
            String bsid = calculateBsid(classify, null);
            classify.setParentId(-1);
            String typeStr = StringUtils.leftPad(String.valueOf(classify.getType()), 2, "0");
            classify.setBsid(typeStr + bsid);
        } else {
            String bsid = calculateBsid(classify, list);
            classify.setBsid(p.getBsid() + bsid);
        }
        long id = autoIdGenerator.get(ID_KEY);
        classify.setId(id);
        if (StringUtils.isNotEmpty(classify.getImage())) {
            classify.setImage(fileSDKClient.uidToUrl(classify.getImage()));
        }
        classify.setEnable(EnableType.ENABLE.getKey());
        return classifyDao.add(classify);
    }

    private String calculateBsid(Classify classify, List<Classify> list) {

        int bsid = 0;
        while (true) {
            bsid = classifyBsidMaxNumberService.calculateNextBsid(classify.getParentId(), classify.getType());
            if (CollectionUtils.isEmpty(list)) {
                break;
            }
            boolean exist = false;
            for (Classify c : list) {
                if (c.getBsid().endsWith(String.valueOf(bsid))) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                break;
            }
        }
        return StringUtils.leftPad(String.valueOf(bsid + 1), 5, "0");
    }

    @Override
    public Classify get(Long id) {

        return classifyDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return classifyDao.delete(id);
    }

    @Override
    public boolean update(Classify classify) {

        // Classify temp = classifyDao.getByName(classify.getType(), classify.getName());
        // if (temp != null&&temp.getId()!=classify.getId()) {
        // throw new PublicdataException("分类名称已经使用." + classify.getName());
        // }
        Classify c = classifyDao.get(classify.getId());
        if (c.getParentId() != classify.getParentId()) {
            List<Classify> list = classifyDao.listChildren(classify.getType(), classify.getParentId());
            Classify p = classifyDao.get(classify.getParentId());
            String bsid = calculateBsid(classify, list);
            if (p == null) {
                classify.setParentId(-1);
                String typeStr = StringUtils.leftPad(String.valueOf(classify.getType()), 2, "0");
                classify.setBsid(typeStr + bsid);
            } else {
                classify.setBsid(p.getBsid() + bsid);
            }
            List<Classify> children = classifyDao.listAllChildren(c.getBsid());
            for (Classify cc : children) {
                cc.setBsid(cc.getBsid().replaceFirst(c.getBsid(), classify.getBsid()));
                classifyDao.update(cc);
            }
        } else {
            classify.setBsid(c.getBsid());
        }
        classify.setImage(fileSDKClient.uidToUrl(classify.getImage()));
        return classifyDao.update(classify);
    }

    @Override
    public Page<Classify> page(ClassifyQuery query, int start, int count) {

        return classifyDao.page(query, start, count);
    }

    public List<Classify> listAll(long type, boolean includeDisable) {

        Set<String> disableBsidSet = new HashSet<String>();
        List<Classify> list = classifyDao.listAll(type);
        List<Classify> result = new ArrayList<Classify>();
        for (Classify classify : list) {
            if (!includeDisable && !canShow(classify, disableBsidSet)) {
                disableBsidSet.add(classify.getBsid());
                continue;
            }
            result.add(classify);
        }
        return result;
    }

    private boolean canShow(Classify classify, Set<String> disableBsidSet) {

        if (classify.getEnable() == EnableType.DISABLE.getKey()) {
            return false;
        }
        for (String str : disableBsidSet) {
            if (classify.getBsid().indexOf(str) > -1) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    @Override
    public boolean sort(Long id, int sort) { // 0 1 -1

        Classify classify = classifyDao.get(id);
        AssertUtil.assertNotNull(classify, "指定分类不存在." + id);
        List<Classify> sameLevel = classifyDao.listChildrenToSort(classify.getType(), classify.getParentId());
        boolean flag = true;
        for (int i = 0; i < sameLevel.size(); i++) {
            if (sameLevel.get(i).getId() == classify.getId()) {
                sameLevel.remove(i);
            }
        }
        if (sort == 0) {// 置顶
            for (int i = 0; i < sameLevel.size(); i++) {
                Classify temp = sameLevel.get(i);
                temp.setSort(i + 1);
                classifyDao.sort(temp.getId(), temp.getSort());
            }
            flag = classifyDao.sort(classify.getId(), 0);
        } else if (sort == -1) {// 向上
            Classify upward = new Classify();
            for (Classify temp : sameLevel) {
                if (temp.getSort() == classify.getSort() - 1) {
                    temp.setSort(temp.getSort() + 1);
                    upward = temp;
                }
            }
            classifyDao.sort(upward.getId(), upward.getSort());
            flag = classifyDao.sort(classify.getId(), classify.getSort() > 0 ? classify.getSort() - 1 : 0);
        } else {// 向下
            Classify down = null;
            for (Classify temp : sameLevel) {
                if (temp.getSort() == classify.getSort() + 1) {
                    temp.setSort(temp.getSort() - 1);
                    down = temp;
                }
            }
            if (down != null) {
                classifyDao.sort(down.getId(), down.getSort());
                flag = classifyDao.sort(classify.getId(), classify.getSort() + 1);
            }
        }
        return flag;
    }

    @Transactional
    @Override
    public boolean enable(Long id, int enable) {

        Classify classify = classifyDao.get(id);
        AssertUtil.assertNotNull(classify, "指定分类不存在." + id);
        List<Classify> childrenList = classifyDao.listAllChildrenToEnable(classify.getBsid());
        for (Classify c : childrenList) {
            classifyDao.enable(c.getId(), enable);
        }
        boolean flag = classifyDao.enable(id, enable);
        return flag;
    }

    @Override
    public boolean withoutDelete(Long id) {

        Classify classify = classifyDao.get(id);
        AssertUtil.assertNotNull(classify, "指定分类不存在." + id);
        return false;
    }
}
