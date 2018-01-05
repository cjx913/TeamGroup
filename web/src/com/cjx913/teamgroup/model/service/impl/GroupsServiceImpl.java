package com.cjx913.teamgroup.model.service.impl;

import com.cjx913.teamgroup.model.dao.DaoFactory;
import com.cjx913.teamgroup.model.dao.ICommonDao;
import com.cjx913.teamgroup.model.dao.impl.GroupsDaoImpl;
import com.cjx913.teamgroup.model.entity.Groups;

import java.util.List;

public class GroupsServiceImpl extends CommonServiceImpl<Groups>{
    GroupsDaoImpl dao = (GroupsDaoImpl) DaoFactory.getDao("Groups");

    @Override
    public List<Groups> findAllByID(int id) {
        return dao.findAllByID(id);
    }
}
