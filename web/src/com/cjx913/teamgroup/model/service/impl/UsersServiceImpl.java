package com.cjx913.teamgroup.model.service.impl;

import com.cjx913.teamgroup.model.dao.DaoFactory;
import com.cjx913.teamgroup.model.dao.impl.UsersDaoImpl;
import com.cjx913.teamgroup.model.entity.Users;

public class UsersServiceImpl extends CommonServiceImpl<Users> {
    private UsersDaoImpl dao = (UsersDaoImpl)DaoFactory.getDao("Users");

    @Override
    public int create(Users entity) {
            return dao.create(entity);
    }

    @Override
    public int update(Users entity) {
        return dao.update(entity);
    }

    @Override
    public Users findByCondition(String condition) {
        return dao.findByCondition(condition);
    }
}
