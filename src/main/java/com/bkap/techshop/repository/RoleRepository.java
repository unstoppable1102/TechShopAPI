package com.bkap.techshop.repository;

import com.bkap.techshop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select r from Role r inner join UserRole ur on r.id = ur.user.id where ur.user.id =:userId")
    List<Role> findAllByUserId(Long userId);

    Optional<Role> findByName(String name);
    boolean existsByName(String name);
}