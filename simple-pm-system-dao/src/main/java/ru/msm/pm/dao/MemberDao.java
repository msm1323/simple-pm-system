package ru.msm.pm.dao;

import ru.msm.pm.model.abstractEntities.Member;

import java.math.BigDecimal;
import java.util.List;

public interface MemberDao<T extends Member> {

    T create(T member) throws Exception;

    T update(T member) throws Exception;

    T getById(BigDecimal id) throws Exception;

    T deleteById(BigDecimal id) throws Exception;

    List<T> getAll() throws Exception;

}
