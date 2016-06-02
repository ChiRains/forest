package com.qcloud.component.seckill.web.vo;

import java.util.ArrayList;
import java.util.List;

public class ScreeningsMerchandiseVO extends ScreeningsVO {

    private List<MerchandiseSeckillVO> list = new ArrayList<MerchandiseSeckillVO>();

    public List<MerchandiseSeckillVO> getList() {

        return list;
    }

    public void setList(List<MerchandiseSeckillVO> list) {

        this.list = list;
    }
}
