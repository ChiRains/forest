package com.qcloud.component.brokerage.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.brokerage.dao.DistributionGradeDao;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.query.DistributionGradeQuery;
import com.qcloud.component.brokerage.service.DistributionGradeService;
import com.qcloud.component.parameter.ParamType;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;

@Service
public class DistributionGradeServiceImpl implements DistributionGradeService {

    @Autowired
    private DistributionGradeDao distributionGradeDao;

    @Autowired
    private AutoIdGenerator      autoIdGenerator;

    private static final String  ID_KEY        = "brokerage_distribution_grade";

    private static final String  grade_formula = "brokerage_grade_formula";

    private static final String  grade_rules   = "brokerage_grade_rules";

    @Autowired
    private PublicdataClient     publicdataClient;

    @Autowired
    private ParameterClient      parameterClient;

    @Override
    public boolean add(DistributionGrade distributionGrade) {

        long id = autoIdGenerator.get(ID_KEY);
        distributionGrade.setId(id);
        return distributionGradeDao.add(distributionGrade);
    }

    @Transactional
    @Override
    public boolean add(DistributionGrade distributionGrade, Classify classify) {

        publicdataClient.addClassify(classify);
        //
        distributionGrade.setClassifyId(classify.getId());
        distributionGrade.setBsid(classify.getBsid());
        distributionGrade.setType(1);// 1用户配置 2系统内置
        return add(distributionGrade);
    }

    @Override
    public DistributionGrade get(Long id) {

        return distributionGradeDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return distributionGradeDao.delete(id);
    }

    @Override
    public boolean update(DistributionGrade distributionGrade) {

        return distributionGradeDao.update(distributionGrade);
    }

    @Override
    public Page<DistributionGrade> page(DistributionGradeQuery query, int start, int count) {

        return distributionGradeDao.page(query, start, count);
    }

    public List<DistributionGrade> listAll() {

        return distributionGradeDao.listAll();
    }

    @Override
    public boolean update(DistributionGrade distributionGrade, Classify classify) {

        publicdataClient.update(classify);
        return update(distributionGrade);
    }

    @Override
    public DistributionGrade getDefault() {

        return distributionGradeDao.getDefault();
    }

    @Override
    public List<DistributionGrade> listUpgrade(Long id) {

        DistributionGrade dg = get(id);
        List<DistributionGrade> list = listAll();
        Collections.sort(list, new Comparator<DistributionGrade>() {

            @Override
            public int compare(DistributionGrade o1, DistributionGrade o2) {

                return o2.getBsid().indexOf(o1.getBsid()) > -1 ? -1 : 1;
            }
        });
        List<DistributionGrade> result = new ArrayList<DistributionGrade>();
        for (DistributionGrade distributionGrade : list) {
            if (!distributionGrade.getBsid().equals(dg.getBsid()) && distributionGrade.getBsid().indexOf(dg.getBsid()) > -1) {
                result.add(distributionGrade);
            }
        }
        return result;
    }

    @Override
    public List<Long> gradeFormulaList() {

        List<Long> formulaList = new ArrayList<Long>();
        String formulaString = parameterClient.get(grade_formula);
        if (null != formulaString) {
            for (String val : formulaString.split(",")) {
                if (!val.equals("")) {
                    formulaList.add(Long.parseLong(val));
                }
            }
        }
        return formulaList;
    }

    @Override
    public String gradeRules() {

        String rules = parameterClient.get(grade_rules);
        return rules;
    }

    @Override
    public boolean editGradeFormula(String formulaList) {

        return parameterClient.reg(grade_formula, formulaList, ParamType.STRING);
    }
}
