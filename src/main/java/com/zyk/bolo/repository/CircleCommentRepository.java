package com.zyk.bolo.repository;

import com.zyk.bolo.entity.Tb_CircleComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CircleCommentRepository extends JpaRepository<Tb_CircleComment, String> {
    List<Tb_CircleComment> findByBolocidEqualsOrderByCreatetimeAsc(String boloCId);
}
