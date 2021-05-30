package dev.highright96.querydsl.controller;

import dev.highright96.querydsl.dto.MemberSearchCondition;
import dev.highright96.querydsl.dto.MemberTeamDto;
import dev.highright96.querydsl.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJpaRepository memberJpaRepository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> search(MemberSearchCondition cond) {
        return memberJpaRepository.searchByWhere(cond);
    }
}
