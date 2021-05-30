package dev.highright96.querydsl.repository;

import dev.highright96.querydsl.dto.MemberSearchCondition;
import dev.highright96.querydsl.dto.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition cond);

    Page<MemberTeamDto> searchPageSimple(MemberSearchCondition cond, Pageable pageable );

    Page<MemberTeamDto> searchPageComplex(MemberSearchCondition cond, Pageable pageable);

}
