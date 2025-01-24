package com.example_vj4.demo4.repository;

import com.example_vj4.demo4.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}