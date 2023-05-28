package ru.msm.pm.dao;

import ru.msm.pm.model.abstract_entities.Member;

import java.util.List;

public interface MemberDao<T extends Member, K> {

    T create(T member) throws Exception;

    T update(T member) throws Exception;

    T getById(K id) throws Exception;

    T deleteById(K id) throws Exception;

    List<T> getAll() throws Exception;

}
