package com.qcloud.component.brokerage.engine.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.brokerage.engine.BrokerageEngineService;
import com.qcloud.component.brokerage.exception.BrokerageException;
import com.qcloud.component.brokerage.model.AllocationScheme;
import com.qcloud.component.brokerage.model.CalculationFormula;
import com.qcloud.component.brokerage.model.DistributionBrokerage;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.FormulaCalculationRecord;
import com.qcloud.component.brokerage.model.FormulaCalculationResult;
import com.qcloud.component.brokerage.model.FormulaSqlResult;
import com.qcloud.component.brokerage.model.UserDistributionGrade;
import com.qcloud.component.brokerage.model.UserRelationship;
import com.qcloud.component.brokerage.model.key.TypeEnum.DistributionBrokerageStateType;
import com.qcloud.component.brokerage.model.key.TypeEnum.FormulaCalculationRecordStateType;
import com.qcloud.component.brokerage.model.key.TypeEnum.FormulaCalculationResultStateType;
import com.qcloud.component.brokerage.service.AllocationSchemeService;
import com.qcloud.component.brokerage.service.CalculationFormulaService;
import com.qcloud.component.brokerage.service.DataPoolService;
import com.qcloud.component.brokerage.service.DistributionBrokerageService;
import com.qcloud.component.brokerage.service.DistributionGradeService;
import com.qcloud.component.brokerage.service.FormulaCalculationRecordService;
import com.qcloud.component.brokerage.service.FormulaCalculationResultService;
import com.qcloud.component.brokerage.service.UserDistributionGradeService;
import com.qcloud.component.brokerage.service.UserRelationshipService;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.WealthType;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.component.template.core.util.string.StringUtils;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.StringUtil;

@Component
public class BrokerageEngineServiceImpl implements BrokerageEngineService {

    @Autowired
    private FormulaCalculationRecordService formulaCalculationRecordService;

    @Autowired
    private CalculationFormulaService       calculationFormulaService;

    @Autowired
    private AllocationSchemeService         allocationSchemeService;

    @Autowired
    private DataPoolService                 dataPoolService;

    @Autowired
    private FormulaCalculationResultService formulaCalculationResultService;

    @Autowired
    private DistributionBrokerageService    distributionBrokerageService;

    @Autowired
    private UserDistributionGradeService    userDistributionGradeService;

    @Autowired
    private UserRelationshipService         userRelationshipService;

    @Autowired
    private DistributionGradeService        distributionGradeService;

    @Autowired
    private PersonalcenterClient            personalcenterClient;

    private Log                             logger = LogFactory.getLog(getClass());

    @Override
    public boolean calculate() {

        List<CalculationFormula> list = calculationFormulaService.listAll();
        for (CalculationFormula calculationFormula : list) {
            if (EnableType.ENABLE.getKey() == calculationFormula.getState()) {
                FormulaCalculationRecord formulaCalculationRecord = formulaCalculationRecordService.getLast(calculationFormula.getId());
                // 一个月数据
                Date begin = DateUtil.addDate(new Date(), -31);
                Date end = new Date();
                // 不到五分钟
                if (formulaCalculationRecord != null) {
                    begin = formulaCalculationRecord.getEndTime();
                }
                FormulaCalculationRecord currentFormulaCalculationRecord = new FormulaCalculationRecord();
                currentFormulaCalculationRecord.setBeginTime(begin);
                currentFormulaCalculationRecord.setCalculateTime(new Date());
                currentFormulaCalculationRecord.setEndTime(end);
                currentFormulaCalculationRecord.setFormulaId(calculationFormula.getId());
                currentFormulaCalculationRecord.setState(FormulaCalculationRecordStateType.START.getKey());
                formulaCalculationRecordService.add(currentFormulaCalculationRecord);
                logger.info(calculationFormula.getName() + " " + calculationFormula.getFormula());
                String sql = calculationFormula.getFormula();
                Map<String, String> param = new HashMap<String, String>();
                param.put("formulaId", String.valueOf(calculationFormula.getId()));
                param.put("beginTime", DateUtil.date2String(begin));
                param.put("endTime", DateUtil.date2String(end));
                sql = formatVariableValue(formatVariableKey(sql, param), param);
                logger.info("formula format: " + sql + " " + param);
                //
                List<FormulaSqlResult> result = dataPoolService.query(sql);
                for (FormulaSqlResult formulaSqlResult : result) {
                    logger.info(formulaSqlResult);
                    FormulaCalculationResult formulaCalculationResult = new FormulaCalculationResult();
                    formulaCalculationResult.setBeginTime(currentFormulaCalculationRecord.getBeginTime());
                    formulaCalculationResult.setBrokerage(formulaSqlResult.getBrokerage());
                    formulaCalculationResult.setCalculateTime(currentFormulaCalculationRecord.getCalculateTime());
                    formulaCalculationResult.setDataPoolId(formulaSqlResult.getId());
                    formulaCalculationResult.setEndTime(currentFormulaCalculationRecord.getEndTime());
                    formulaCalculationResult.setFormulaId(calculationFormula.getId());
                    formulaCalculationResult.setImage(formulaSqlResult.getImage());
                    formulaCalculationResult.setMerchantId(formulaSqlResult.getMerchantId());
                    formulaCalculationResult.setName(formulaSqlResult.getName());
                    formulaCalculationResult.setOrderTime(formulaSqlResult.getOrderTime());
                    formulaCalculationResult.setState(FormulaCalculationResultStateType.INIT.getKey());
                    formulaCalculationResult.setType(formulaSqlResult.getType());
                    formulaCalculationResult.setUserId(formulaSqlResult.getUserId());
                    formulaCalculationResultService.add(formulaCalculationResult);
                }
                //
                currentFormulaCalculationRecord.setState(FormulaCalculationRecordStateType.FINISH.getKey());
                formulaCalculationRecordService.update(currentFormulaCalculationRecord);
            }
        }
        for (CalculationFormula calculationFormula : list) {
            allocate(calculationFormula);
        }
        return true;
    }

    private boolean allocate(CalculationFormula calculationFormula) {

        List<AllocationScheme> saList = allocationSchemeService.listByFormula(calculationFormula.getId());
        List<FormulaCalculationResult> resultList = formulaCalculationResultService.listToAllocation(calculationFormula.getId());
        for (FormulaCalculationResult formulaCalculationResult : resultList) {
            formulaCalculationResult.setState(FormulaCalculationResultStateType.ALLOCATING.getKey());
            formulaCalculationResultService.update(formulaCalculationResult);
            allocate(calculationFormula, saList, formulaCalculationResult);
            formulaCalculationResult.setState(FormulaCalculationResultStateType.ALLOCATED.getKey());
            formulaCalculationResultService.update(formulaCalculationResult);
        }
        return true;
    }

    private boolean allocate(CalculationFormula calculationFormula, List<AllocationScheme> saList, FormulaCalculationResult formulaCalculationResult) {

        int proportionTotal = 0;
        if (new Double(formulaCalculationResult.getBrokerage() * 100).longValue() <= 0) {
            return true;
        }
        String route = "";
        long owner = formulaCalculationResult.getUserId();
        int poundageRate = calculationFormula.getPoundageRate();
        poundageRate = poundageRate <= 0 ? 0 : poundageRate;
        if (calculationFormula.getTradeUserDistribution() == EnableType.ENABLE.getKey()) {
            int proportion = calculationFormula.getProportion();
            if (proportion > 100) {
                proportion = 100;
            }
            proportionTotal += proportion;
            UserDistributionGrade userDistributionGrade = userDistributionGradeService.getByUser(owner);
            long gradeId = userDistributionGrade == null ? -1L : userDistributionGrade.getGradeId();
            route += String.valueOf(owner) + ";";
            allocate(formulaCalculationResult, owner, formulaCalculationResult.getBrokerage() * proportion / 100, poundageRate, gradeId, proportion, route);
        }
        for (AllocationScheme allocationScheme : saList) {
            long gradeId = allocationScheme.getAllocationGradeId();
            int proportion = allocationScheme.getProportion();
            if (proportionTotal + proportion > 100) {
                proportion = 100 - proportionTotal;
            }
            proportionTotal += proportion;
            logger.info("计算上线 开始 " + owner + " " + gradeId);
            owner = getOwner(owner, gradeId);
            logger.info("计算上线结束  " + owner + " " + gradeId);
            if (owner == -1L) {
                break;
            }
            route += String.valueOf(owner) + ";";
            allocate(formulaCalculationResult, owner, formulaCalculationResult.getBrokerage() * proportion / 100, poundageRate, gradeId, proportion, route);
            if (proportionTotal == 100) {
                break;
            }
        }
        return true;
    }

    private boolean allocate(FormulaCalculationResult formulaCalculationResult, long owner, double brokerage, int poundageRate, long gradeId, int proportion, String route) {

        double poundage = brokerage * poundageRate / 100;
        brokerage = brokerage - poundage;
        DistributionBrokerage distributionBrokerage = new DistributionBrokerage();
        distributionBrokerage.setPoundage(poundage);
        distributionBrokerage.setBrokerage(brokerage);
        distributionBrokerage.setPoundageRate(poundageRate);
        distributionBrokerage.setOrderTime(formulaCalculationResult.getOrderTime());
        distributionBrokerage.setFormulaId(formulaCalculationResult.getFormulaId());
        distributionBrokerage.setGradeId(gradeId);
        distributionBrokerage.setImage(formulaCalculationResult.getImage());
        distributionBrokerage.setName(formulaCalculationResult.getName());
        distributionBrokerage.setOwner(owner);
        distributionBrokerage.setProportion(proportion);
        distributionBrokerage.setResultId(formulaCalculationResult.getId());
        distributionBrokerage.setRoute(route);
        distributionBrokerage.setState(DistributionBrokerageStateType.TO_AUDIT.getKey());
        distributionBrokerage.setType(formulaCalculationResult.getType());
        distributionBrokerage.setUserId(formulaCalculationResult.getUserId());
        distributionBrokerageService.add(distributionBrokerage);
        // TODO 先给钱
        QUser user = personalcenterClient.getUser(distributionBrokerage.getOwner());
        if (user != null) {
            // 可以提现部分
            personalcenterClient.calculateMyWealth(user.getId(), WealthType.COMMISSION, brokerage, false, "会员升级 " + distributionBrokerage.getName());
        } else {
            logger.info("用户不存在." + distributionBrokerage.getOwner());
        }
        distributionBrokerage.setState(DistributionBrokerageStateType.PASS.getKey());
        distributionBrokerageService.update(distributionBrokerage);
        // TODO 先给钱
        return true;
    }

    private long getOwner(long userId, long gradeId) {

        Long recommedId = userId;
        UserRelationship userRelationship = userRelationshipService.getByUserId(recommedId);
        if (userRelationship != null) {
            recommedId = userRelationship.getRecommedId();
            UserDistributionGrade userDistributionGrade = userDistributionGradeService.getByUser(recommedId);
            DistributionGrade currentDistributionGrade = null;
            if (userDistributionGrade == null) {
                currentDistributionGrade = distributionGradeService.getDefault();
            } else {
                currentDistributionGrade = distributionGradeService.get(userDistributionGrade.getGradeId());
            }
            DistributionGrade distributionGrade = distributionGradeService.get(gradeId);
            if (distributionGrade.getId() == currentDistributionGrade.getId()) {
                return recommedId;
            }
        }
        return -1L;
    }

    private static String formatVariableKey(String formula, Map<String, String> param) {

        if (StringUtils.isEmpty(formula)) {
            return StringUtil.nullToEmpty(formula);
        }
        if (param == null) {
            return formula;
        }
        while (true) {
            int symbolFirstIndex = formula.indexOf("#{", 0);
            int symbolLastIndex = formula.indexOf("}#", 0);
            boolean noMatch = symbolFirstIndex == -1 && symbolLastIndex != -1 || symbolFirstIndex != -1 && symbolLastIndex == -1;
            if (noMatch || symbolFirstIndex > symbolLastIndex) {
                throw new BrokerageException("公式配置有误,约定符号#{}#必须匹对出现,并且#{需要在}#的前面。。。");
            }
            if (symbolFirstIndex == -1 && symbolLastIndex == -1) {
                break;
            }
            String key = formula.substring(symbolFirstIndex + 2, symbolLastIndex);
            String dyString = "#\\{" + key + "\\}#";
            String value = param.get(key);
            formula = formula.replaceAll(dyString, "'" + StringUtil.nullToEmpty(value) + "'");
        }
        return formula;
    }

    private static String formatVariableValue(String formula, Map<String, String> param) {

        if (StringUtils.isEmpty(formula)) {
            return StringUtil.nullToEmpty(formula);
        }
        if (param == null) {
            return formula;
        }
        while (true) {
            int symbolFirstIndex = formula.indexOf("${", 0);
            int symbolLastIndex = formula.indexOf("}$", 0);
            boolean noMatch = symbolFirstIndex == -1 && symbolLastIndex != -1 || symbolFirstIndex != -1 && symbolLastIndex == -1;
            if (noMatch || symbolFirstIndex > symbolLastIndex) {
                throw new BrokerageException("公式配置有误,约定符号${}$必须匹对出现,并且${需要在}$的前面。。。");
            }
            if (symbolFirstIndex == -1 && symbolLastIndex == -1) {
                break;
            }
            String key = formula.substring(symbolFirstIndex + 2, symbolLastIndex);
            String dyString = "\\$\\{" + key + "\\}\\$";
            String value = param.get(key);
            formula = formula.replaceAll(dyString, StringUtil.nullToEmpty(value));
        }
        return formula;
    }
}
