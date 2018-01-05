package com.cjx913.teamgroup.model.service;

import com.cjx913.teamgroup.model.service.impl.*;

import java.util.WeakHashMap;

public class ServiceFactory {
    private final static WeakHashMap<String, ICommonService> map = new WeakHashMap<>();

    public static ICommonService getService(String name) {
        ICommonService service = map.get(name);
        if(service==null) {
            return createService(name);
        }
        return service;
    }

    private synchronized static ICommonService createService(String name) {
        ICommonService service = null;
        if (name.equals("Users")) {
            service = new UsersServiceImpl();
            map.put(name, service);
        }
        if(name.equals("Schedules")){
            service = new SchedulesServiceImpl();
            map.put("Schedules",service);
        }
        if(name.equals("Contacts")){
            service = new ContactsServiceImpl();
            map.put("Contacts",service);
        }
        if(name.equals("Groups")){
            service = new GroupsServiceImpl();
            map.put("Groups",service);
        }
        if(name.equals("ChattingMessage")){
            service = new ChattingMessageServiceImpl();
            map.put("ChattingMessage",service);
        }
        return service;
    }
}
