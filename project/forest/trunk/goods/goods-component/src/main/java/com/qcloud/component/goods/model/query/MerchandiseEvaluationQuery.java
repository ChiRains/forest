package com.qcloud.component.goods.model.query;

import com.qcloud.component.goods.exception.CommoditycenterException;
import com.qcloud.component.goods.model.key.TypeEnum.StarLevelType;

public class MerchandiseEvaluationQuery {

    private Long    merchandiseId;

    /**
     * 0,1,2,3:全部、好评、中评、差评
     */
    private Integer typeNum;

    public Long getMerchandiseId() {

        return merchandiseId;
    }

    public void setMerchandiseId(Long merchandiseId) {

        this.merchandiseId = merchandiseId;
    }

    public Integer getTypeNum() {

        return typeNum;
    }

    public void setTypeNum(Integer typeNum) {

        this.typeNum = typeNum;
    }

    public StarLevelType getStarLevelType() {

        StarLevelType starLevelType = null;
        if (typeNum == null) {
            typeNum = Integer.valueOf(0);
        }
        switch (typeNum) {
        case 0:
            starLevelType = StarLevelType.ALL;
            break;
        case 1:
            starLevelType = StarLevelType.HP;
            break;
        case 2:
            starLevelType = StarLevelType.ZP;
            break;
        case 3:
            starLevelType = StarLevelType.CP;
            break;
        default:
            throw new CommoditycenterException("非法操作!");
        }
        return starLevelType;
    }
}
