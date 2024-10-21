package com.bkap.techshop.service;

import com.bkap.techshop.dto.request.RoleRequest;
import com.bkap.techshop.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {

    List<RoleResponse> getAll();
    RoleResponse create(RoleRequest request);
    void delete(String role);

}
