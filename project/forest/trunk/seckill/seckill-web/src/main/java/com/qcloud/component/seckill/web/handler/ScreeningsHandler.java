package com.qcloud.component.seckill.web.handler;

import java.util.List;
import com.qcloud.component.seckill.model.Screenings;
import com.qcloud.component.seckill.web.vo.MerchandiseSeckillVO;
import com.qcloud.component.seckill.web.vo.ScreeningsClassifyVO;
import com.qcloud.component.seckill.web.vo.ScreeningsIndexVO;
import com.qcloud.component.seckill.web.vo.ScreeningsListVO;
import com.qcloud.component.seckill.web.vo.ScreeningsMerchandiseVO;
import com.qcloud.component.seckill.web.vo.admin.AdminScreeningsVO;

public interface ScreeningsHandler {

    List<ScreeningsListVO> toVOList(List<Screenings> list);

    ScreeningsClassifyVO toVO4Classify(Screenings screenings);

    ScreeningsMerchandiseVO toVO4Merchandise(Screenings screenings);

    ScreeningsIndexVO toVO4Index(Screenings screenings);

    List<MerchandiseSeckillVO> getCrazySeckill(Screenings screenings);

    List<AdminScreeningsVO> toVOList4Admin(List<Screenings> list);

    AdminScreeningsVO toVO4Admin(Screenings screenings);
}
