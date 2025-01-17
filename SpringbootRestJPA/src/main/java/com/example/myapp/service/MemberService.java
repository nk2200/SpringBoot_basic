package com.example.myapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.model.Member;
import com.example.myapp.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	//해당 메서드는 transaction에서 제외됨
	@Transactional(readOnly=true)
	public List<Member> getAllMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> getMemberById(Long id){
		return memberRepository.findById(id);
	}
	
	public Member createMember(Member member) {
		return memberRepository.save(member);
	}
	
	@Transactional
	public Optional<Member> updateMember(Long id, Member memberDetails){
		return memberRepository.findById(id).map(member -> {
			member.setName(memberDetails.getName());
			member.setEmail(memberDetails.getEmail());
			return memberRepository.save(member);
		});
	}
	
	public boolean deleteMember(Long id) {
		if(memberRepository.existsById(id)) {
			memberRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
