package com.example.myapp.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.model.Member;
import com.example.myapp.model.MemberModelAssembler;
import com.example.myapp.service.MemberService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@RequestMapping("/api/members")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberModelAssembler assembler;
	
	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<Member>>> getAllMembers(){
		List<EntityModel<Member>> members = memberService.getAllMembers().stream().map(assembler::toModel)
				.collect(Collectors.toList());
		return ResponseEntity.ok(CollectionModel.of(members,
				linkTo(methodOn(MemberController.class).getAllMembers()).withSelfRel()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<Member>> getMemberById(@PathVariable Long id){
		Optional<Member> member = memberService.getMemberById(id);
		if(member.isPresent()) {
			EntityModel<Member> entityModel = assembler.toModel(member.get());
			return ResponseEntity.ok(entityModel);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public Member createMember(@RequestBody Member member) {
		return memberService.createMember(member);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails){
		Optional<Member> updateMember = memberService.updateMember(id, memberDetails);
		return updateMember.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMember(@PathVariable Long id){
		if(memberService.deleteMember(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	 
}
