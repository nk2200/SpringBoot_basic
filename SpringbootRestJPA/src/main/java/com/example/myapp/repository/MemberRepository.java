package com.example.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myapp.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	
}
