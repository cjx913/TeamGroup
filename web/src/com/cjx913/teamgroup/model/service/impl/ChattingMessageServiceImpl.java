package com.cjx913.teamgroup.model.service.impl;

import com.cjx913.teamgroup.model.dao.DaoFactory;
import com.cjx913.teamgroup.model.dao.impl.ChattingMessageDaoImpl;
import com.cjx913.teamgroup.model.entity.ChattingMessage;

import java.util.List;

public class ChattingMessageServiceImpl extends CommonServiceImpl<ChattingMessage> {
    ChattingMessageDaoImpl dao = (ChattingMessageDaoImpl) DaoFactory.getDao("ChattingMessage");
    @Override
    public int create(ChattingMessage entity) {
        return dao.create(entity);
    }

    @Override
    public List<ChattingMessage> findAllByCondition(String condition) {
        return dao.findAllByCondition(condition);
    }
}
