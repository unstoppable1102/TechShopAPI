package com.bkap.techshop.service.impl;

import com.bkap.techshop.dto.request.RoleRequest;
import com.bkap.techshop.dto.response.RoleResponse;
import com.bkap.techshop.entity.Role;
import com.bkap.techshop.exception.AppException;
import com.bkap.techshop.exception.ErrorCode;
import com.bkap.techshop.repository.RoleRepository;
import com.bkap.techshop.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RoleResponse> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map((role) -> modelMapper.map(role, RoleResponse.class))
                .collect(Collectors.toList());

    }

    @Override
    public RoleResponse create(RoleRequest request) {
        if (roleRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.ROLE_EXISTED);
        }

        Role role = modelMapper.map(request, Role.class);
        roleRepository.save(role);
        return modelMapper.map(role, RoleResponse.class);

    }

    @Override
    public void delete(String role) {
        Role existingRole = roleRepository.findByName(role)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        roleRepository.delete(existingRole);
    }
}
