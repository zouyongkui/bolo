package com.zyk.bolo.repository;

import com.zyk.bolo.entity.Tb_Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Tb_Comment, String> {
}
