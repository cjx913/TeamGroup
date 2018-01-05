package com.cjx913.teamgroup.model.service.impl;

import com.cjx913.teamgroup.model.dao.DaoFactory;
import com.cjx913.teamgroup.model.dao.impl.ContactsDaoImpl;

import java.util.List;

public class ContactsServiceImpl extends CommonServiceImpl {
    ContactsDaoImpl dao =(ContactsDaoImpl) DaoFactory.getDao("Contacts");

    @Override
    public List findAllByID(int id) {
        return dao.findAllByID(id);
    }
}
