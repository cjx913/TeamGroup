package com.cjx913.teamgroup.model.service.impl;

import com.cjx913.teamgroup.model.dao.DaoFactory;
import com.cjx913.teamgroup.model.dao.impl.SchedulesDaoImpl;
import com.cjx913.teamgroup.model.entity.Schedules;

import java.io.Serializable;
import java.util.List;

public class SchedulesServiceImpl extends CommonServiceImpl<Schedules> {
    private SchedulesDaoImpl dao = (SchedulesDaoImpl) DaoFactory.getDao("Schedules");

    @Override
    public int create(Schedules entity) {
        return dao.create(entity);
    }

    @Override
    public List<Schedules> findAllByID(int id) {
        return dao.findAllByID(id);
    }

    @Override
    public List<Schedules> findAllByCondition(String condition) {
        return dao.findAllByCondition(condition);
    }

    @Override
    public int[] deleteByIDs(int... ids) {
        return dao.deleteByIDs(ids);
    }
}
