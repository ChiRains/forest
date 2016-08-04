package com.qcloud.project.forest.web.handler;

import java.util.List;
import com.qcloud.project.forest.model.ShareGift;
import com.qcloud.project.forest.web.vo.ShareGiftVO;

public interface ShareGiftHandler {

    List<ShareGiftVO> toVOList(List<ShareGift> list);

    ShareGiftVO toVO(ShareGift shareGift);
}
