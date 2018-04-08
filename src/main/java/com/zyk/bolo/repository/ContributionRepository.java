package com.zyk.bolo.repository;

import com.zyk.bolo.entity.Tb_Contribution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributionRepository extends JpaRepository<Tb_Contribution, String> {
}
