package com.example.outsourcing_project.member.repository;

import com.example.outsourcing_project.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    //회원가입
    boolean existsByEmail(String email);// 회원가입 중복 체크

    //로그인
    Optional<Member> findByEmail(String email);// 로그인




}
