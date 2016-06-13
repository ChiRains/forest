package com.qcloud.project.forest.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.MedicationRemindersThemeDao;
import com.qcloud.project.forest.model.MedicationRemindersTheme;
import com.qcloud.project.forest.model.query.MedicationRemindersThemeQuery;

@Repository
public class MedicationRemindersThemeDaoCacheImpl implements MedicationRemindersThemeDao {

    @Autowired
    private MedicationRemindersThemeDao medicationRemindersThemeDaoMysqlImpl;

    @Autowired
    private MedicationRemindersThemeDao medicationRemindersThemeDaoRedisImpl;

    @Override
    public boolean add(MedicationRemindersTheme medicationRemindersTheme) {

        return medicationRemindersThemeDaoMysqlImpl.add(medicationRemindersTheme);
    }

    @Override
    public MedicationRemindersTheme get(Long id) {

        return medicationRemindersThemeDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return medicationRemindersThemeDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MedicationRemindersTheme medicationRemindersTheme) {

        return medicationRemindersThemeDaoMysqlImpl.update(medicationRemindersTheme);
    }

    @Override
    public List<MedicationRemindersTheme> list(List<Long> idList) {

        return CacheLoader.list(medicationRemindersThemeDaoRedisImpl, medicationRemindersThemeDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MedicationRemindersTheme> map(Set<Long> idSet) {

        return CacheLoader.map(medicationRemindersThemeDaoRedisImpl, medicationRemindersThemeDaoMysqlImpl, idSet);
    }

    public List<MedicationRemindersTheme> listByUserId(Long userId) {

        return medicationRemindersThemeDaoMysqlImpl.listByUserId(userId);
    }

    @Override
    public Page<MedicationRemindersTheme> page(int start, int count) {

        return medicationRemindersThemeDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MedicationRemindersTheme> page(MedicationRemindersThemeQuery query, int start, int count) {

        return medicationRemindersThemeDaoMysqlImpl.page(query, start, count);
    }

    public List<MedicationRemindersTheme> listAll() {

        return medicationRemindersThemeDaoMysqlImpl.listAll();
    }
}
