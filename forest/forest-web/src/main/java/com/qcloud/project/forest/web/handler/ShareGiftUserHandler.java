package com.qcloud.project.forest.web.handler;

import java.util.List;
import com.qcloud.project.forest.model.ShareGiftUser;
import com.qcloud.project.forest.web.vo.ShareGiftUserVO;

public interface ShareGiftUserHandler {

    List<ShareGiftUserVO> toVOList(List<ShareGiftUser> list);

    ShareGiftUserVO toVO(ShareGiftUser shareGiftUser);
}
