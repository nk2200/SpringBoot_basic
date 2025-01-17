package com.example.myapp.model;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.myapp.controller.MemberController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class MemberModelAssembler implements RepresentationModelAssembler<Member, EntityModel<Member>>{

	@Override
	public EntityModel<Member> toModel(Member member) {
		//현재 리소스의 링크 생성
		return EntityModel.of(member,
				linkTo(methodOn(MemberController.class)
						.getMemberById(member.getId())).withSelfRel(),
				linkTo(methodOn(MemberController.class)
						.getAllMembers()).withRel("member"),
				linkTo(methodOn(MemberController.class)
						.updateMember(member.getId(), member)).withRel("update"),
				linkTo(methodOn(MemberController.class)
						.deleteMember(member.getId())).withRel("delete")
				);
	}

}
