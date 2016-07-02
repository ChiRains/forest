package com.qcloud.project.forest.dao.outside;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import com.qcloud.project.forest.dao.ForestToOmsOutSideDao;

@Repository
public class ForestToOmsOutSideDaoImpl implements ForestToOmsOutSideDao {

    Log logger = LogFactory.getLog(getClass());

    @Override
    public boolean userPayNotify() {

        // TODO Auto-generated method stub
        return false;
    }
}
