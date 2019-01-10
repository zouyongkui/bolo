package com.zyk.bolo.repository;

import com.zyk.bolo.entity.Tb_User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Tb_User, String> {

    Tb_User findDistinctById(String usrId);

    Tb_User findByPhoneEquals(String phone);

    boolean existsByPhoneEquals(String phone);

}
