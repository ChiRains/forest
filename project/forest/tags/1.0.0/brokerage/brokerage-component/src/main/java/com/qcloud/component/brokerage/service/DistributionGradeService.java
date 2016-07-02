package com.qcloud.component.brokerage.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.query.DistributionGradeQuery;
import com.qcloud.component.publicdata.model.Classify;

public interface DistributionGradeService {

    public boolean add(DistributionGrade distributionGrade);

    public boolean add(DistributionGrade distributionGrade, Classify classify);

    public DistributionGrade get(Long id);

    public boolean delete(Long id);

    public boolean update(DistributionGrade distributionGrade);

    public boolean update(DistributionGrade distributionGrade, Classify classify);

    public Page<DistributionGrade> page(DistributionGradeQuery query, int start, int count);

    public List<DistributionGrade> listAll();

    DistributionGrade getDefault();

    List<DistributionGrade> listUpgrade(Long id);

    List<Long> gradeFormulaList();

    boolean editGradeFormula(String formulaList);

    String gradeRules();
}
