package com.qcloud.component.personalcenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class UserUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/user/list.do");
        list.add("/admin/user/toAdd.do");
        list.add("/admin/user/toEdit.do");
        list.add("/admin/user/add.do");
        list.add("/admin/user/edit.do");
        list.add("/admin/user/enable.do");
        list.add("/admin/user/listBySelect.do");
        list.add("/admin/user/activate.do");
        list.add("/admin/user/resetPwd.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/user/list.do");
        list.add("/admin/user/toAdd.do");
        list.add("/admin/user/toEdit.do");
        list.add("/admin/user/add.do");
        list.add("/admin/user/edit.do");
        list.add("/admin/user/enable.do");
        list.add("/admin/user/activate.do");
        list.add("/admin/user/resetPwd.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/user/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/user/toAdd.do", list);
        map.put("/admin/user/add.do", list);
        map.put("/admin/user/toEdit.do", list);
        map.put("/admin/user/edit.do", list);
        map.put("/admin/user/enable.do", list);
        map.put("/admin/user/activate.do", list);
        map.put("/admin/user/resetPwd.do", list);
        return map;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/user/edit.do");
        list.add("/user/getUser.do");
        list.add("/user/changePwd.do");
        list.add("/user/editHeadImage.do");
        list.add("/user/editNickname.do");
        list.add("/user/editSex.do");
        list.add("/user/editMobile.do");
        list.add("/user/editEmail.do");
        list.add("/user/editUserHeadImage.do");
        list.add("/user/editBirthDay.do");
        list.add("/user/editName.do");
        list.add("/user/editAddress.do");
        list.add("/user/isSubscribe.do");
        list.add("/user/logout4Weixin.do");
        // ############################################
        list.add("/app/user/changePwd.do");
        list.add("/app/user/getUser.do");
        list.add("/app/user/edit.do");
        list.add("/app/user/logout.do");
        list.add("/app/user/checkLoginToken.do");
        list.add("/app/user/editHeadImage.do");
        list.add("/app/user/editNickname.do");
        list.add("/app/user/editSex.do");
        list.add("/app/user/editMobile.do");
        list.add("/app/user/editEmail.do");
        list.add("/app/user/editBirthDay.do");
        list.add("/app/user/editName.do");
        list.add("/app/user/editUserHeadImage.do");
        list.add("/app/user/editAddress.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/user/isLogin.do");
        list.add("/user/login.do");
        list.add("/user/logout.do");
        list.add("/user/sendMsgForRegister.do");
        list.add("/user/sendMsgForForgetPwd.do");
        list.add("/user/registerByMobile.do");
        list.add("/user/setPwdByCode.do");
        list.add("/user/loginByWeixin.do");
        list.add("/user/openIdStatus.do");
        // ############################################
        list.add("/app/user/registerByMobile.do");
        list.add("/app/user/sendMsgForRegister.do");
        list.add("/app/user/sendMsgForForgetPwd.do");
        list.add("/app/user/sendMsgForActivateMembershipCard.do");
        list.add("/app/user/setPwdByCode.do");
        list.add("/app/user/login.do");
        list.add("/app/user/activateUser.do");
        // list.add("/user/reactivated.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/user/sendMsgForRegister.do");
        list.add("/app/user/registerByMobile.do");
        list.add("/app/user/sendMsgForForgetPwd.do");
        list.add("/app/user/setPwdByCode.do");
        list.add("/app/user/login.do");
        // ############################################
        list.add("/app/user/changePwd.do");
        list.add("/app/user/getUser.do");
        list.add("/app/user/edit.do");
        list.add("/app/user/logout.do");
        list.add("/app/user/checkLoginToken.do");
        list.add("/app/user/sendMsgForActivateMembershipCard.do");
        list.add("/app/user/activateUser.do");
        list.add("/app/user/editHeadImage.do");
        list.add("/app/user/editNickname.do");
        list.add("/app/user/editSex.do");
        list.add("/app/user/editMobile.do");
        list.add("/app/user/editEmail.do");
        list.add("/app/user/editBirthDay.do");
        list.add("/app/user/editName.do");
        list.add("/app/user/editUserHeadImage.do");
        list.add("/app/user/editAddress.do");
        return list;
    }
}
