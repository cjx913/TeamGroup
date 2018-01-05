package com.cjx913.teamgroup.model.dao.impl;

import com.cjx913.teamgroup.model.dao.ICommonDao;

import java.io.Serializable;
import java.util.List;

public abstract class CommonDaoImpl<T extends Serializable> implements ICommonDao<T> {

    @Override
    public int create(T entity)   {
        return 0;
    }

    @Override
    public int[] create(List<T> entities) {
        return new int[0];
    }

    @Override
    public int delete(T entity) {
        return 0;
    }

    @Override
    public int[] delete(List<T> entities) {
        return new int[0];
    }

    @Override
    public int deleteByID(int id) {
        return 0;
    }

    @Override
    public int[] deleteByIDs(int... ids) {
        return new int[0];
    }

    @Override
    public int deleteByCondition(String condition) {
        return 0;
    }

    @Override
    public int[] deleteByConditions(String... conditions) {
        return new int[0];
    }

    @Override
    public int update(T entity) {
        return 0;
    }

    @Override
    public int[] update(List<T> eneities) {
        return new int[0];
    }

    @Override
    public int updateByID(int id) {
        return 0;
    }

    @Override
    public int[] updateByIDs(int... ids) {
        return new int[0];
    }

    @Override
    public int updateByCondition(String condition) {
        return 0;
    }

    @Override
    public int[] updateByConditions(String... conditions) {
        return new int[0];
    }

    @Override
    public T findByID(int id) {
        return null;
    }


    @Override
    public T findByCondition(String condition) {
        return null;
    }

    @Override
    public List<T> findAllByID(int id) {
        return null;
    }

    @Override
    public List<T> findAllByCondition(String condition) {
        return null;
    }
}
