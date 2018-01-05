package com.cjx913.teamgroup.model.dao;

import com.cjx913.teamgroup.model.dao.impl.*;

import java.util.WeakHashMap;

public final class DaoFactory {
    private final static WeakHashMap<String, ICommonDao> map = new WeakHashMap<>();

    public static ICommonDao getDao(String name) {
        ICommonDao dao = map.get(name);
        if (dao == null) {
            return createDao(name);
        }
        return dao;
    }


    private synchronized static ICommonDao createDao(String name) {
        ICommonDao dao = null;
        if (name.equals("Users")) {
            dao = new UsersDaoImpl();
            map.put(name, dao);
        }
        if(name.equals("Schedules")){
            dao = new SchedulesDaoImpl();
            map.put("Schedules",dao);
        }
        if(name.equals("Contacts")){
            dao = new ContactsDaoImpl();
            map.put("Contacts",dao);
        }
        if(name.equals("Groups")){
            dao = new GroupsDaoImpl();
            map.put("Groups",dao);
        }
        if(name.equals("ChattingMessage")){
            dao = new ChattingMessageDaoImpl();
            map.put("ChattingMessage",dao);
        }
        return dao;
    }
}
