package com.bkap.techshop.service;


import com.bkap.techshop.common.util.DBUtil;
import com.bkap.techshop.entity.BaseModel;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class BaseService {
    protected final DBUtil dbUtil;

    public void update(BaseModel o, String username) {
        o.setUpdatedAt(dbUtil.getDatabaseTimestamp());
        o.setUpdatedBy(username);
    }

    public void create(BaseModel o, String username) {
        o.setCreatedAt(dbUtil.getDatabaseTimestamp());
        o.setCreatedBy(username);
    }
}
