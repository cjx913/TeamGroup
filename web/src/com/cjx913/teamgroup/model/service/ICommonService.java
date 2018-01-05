package com.cjx913.teamgroup.model.service;

import java.io.Serializable;
import java.util.List;

public interface ICommonService<T extends Serializable> {
    //Create
    int create(T entity);
    int[] create(List<T> entities);
    //Delete
    int delete(T entity);
    int[] delete(List<T> entities);
    int deleteByID(int id);
    int[] deleteByIDs(int...ids);
    int deleteByCondition(String condition);
    int[] deleteByConditions(String...conditions);
    //Update
    int update(T entity);
    int[] update(List<T> eneities);
    int updateByID(int id);
    int[] updateByIDs(int...ids);
    int updateByCondition(String condition);
    int[] updateByConditions(String...conditions);
    //Retrieve
    T findByID(int id);
    List<T> findAllByID(int id);
    T findByCondition(String condition);
    List<T> findAllByCondition(String condition);
}
