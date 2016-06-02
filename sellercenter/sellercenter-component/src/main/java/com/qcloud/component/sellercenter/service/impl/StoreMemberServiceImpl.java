package com.qcloud.component.sellercenter.service.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.permission.AccountClient;
import com.qcloud.component.permission.PermissionClient;
import com.qcloud.component.permission.model.Account;
import com.qcloud.component.sellercenter.dao.StoreMemberDao;
import com.qcloud.component.sellercenter.model.Member;
import com.qcloud.component.sellercenter.model.Merchant;
import com.qcloud.component.sellercenter.model.StoreMember;
import com.qcloud.component.sellercenter.model.query.StoreMemberQuery;
import com.qcloud.component.sellercenter.service.MemberService;
import com.qcloud.component.sellercenter.service.MerchantService;
import com.qcloud.component.sellercenter.service.StoreMemberService;
import com.qcloud.pirates.data.Page;

@Service
public class StoreMemberServiceImpl implements StoreMemberService {

    @Autowired
    private StoreMemberDao      storeMemberDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    @Autowired
    private MemberService       memberService;

    private static final String ID_KEY = "sellercenter_store_member";

    @Autowired
    private AccountClient       accountClient;

    @Autowired
    private PermissionClient    permissionClient;

    @Value("${pirates.seller.storeMemberRoleId}")
    private String              storeMemberRoleId;

    @Autowired
    private MerchantService     merchantService;

    @Override
    public boolean add(StoreMember storeMember) {

        long id = autoIdGenerator.get(ID_KEY);
        storeMember.setId(id);
        return storeMemberDao.add(storeMember);
    }

    @Override
    public StoreMember get(Long id) {

        return storeMemberDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return storeMemberDao.delete(id);
    }

    @Override
    public boolean update(StoreMember storeMember) {

        return storeMemberDao.update(storeMember);
    }

    @Override
    public Page<StoreMember> page(StoreMemberQuery query, int start, int count) {

        return storeMemberDao.page(query, start, count);
    }

    public List<StoreMember> listAll() {

        return storeMemberDao.listAll();
    }

    @Transactional
    @Override
    public boolean add(Member member, Long merchantId, Long storeId) {

        boolean result = memberService.add(member, merchantId);
        if (result) {
            StoreMember storeMember = new StoreMember();
            storeMember.setMemberId(member.getId());
            storeMember.setMerchantId(merchantId);
            storeMember.setStoreId(storeId);
            result = add(storeMember);
            Merchant merchant = merchantService.get(merchantId);
            if (StringUtils.isNotEmpty(storeMemberRoleId) && merchant.getMerchantType() == 1) {
                String perAccountCode = memberService.getMemberPermissionAccountCode(member);
                Account account = accountClient.getAccount(perAccountCode);
                if (account != null) {
                    permissionClient.grant(account.getId(), Long.parseLong(storeMemberRoleId));
                }
            }
        }
        return result;
    }

    @Override
    public StoreMember get(HashMap where) {

        return storeMemberDao.get(where);
    }

    @Override
    public List<StoreMember> list(HashMap where) {

        return storeMemberDao.list(where);
    }

    @Override
    public Page<StoreMember> page(HashMap where, int start, int size) {

        return storeMemberDao.page(where, start, size);
    }
}
