package com.zyk.bolo.repository;

import com.zyk.bolo.entity.Tb_BoloC;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoloCircleRepository extends JpaRepository<Tb_BoloC, String> {
    List<Tb_BoloC> findAllByIdIsNotNullOrderByUpdatetimeDesc(Pageable pageable);
}
