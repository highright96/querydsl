package dev.highright96.querydsl.repository;

import dev.highright96.querydsl.dto.MemberSearchCondition;
import dev.highright96.querydsl.dto.MemberTeamDto;
import dev.highright96.querydsl.entity.Member;
import dev.highright96.querydsl.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {
    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    void basicTest() {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> findList = memberJpaRepository.findAll();
        assertThat(findList).containsExactly(member);

        List<Member> findMemberByName = memberJpaRepository.findByName("member1");
        assertThat(findMemberByName).containsExactly(member);
    }

    @Test
    void basicQuerydslTest() {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        List<Member> findList = memberJpaRepository.findAll_Querydsl();
        assertThat(findList).containsExactly(member);

        List<Member> findMemberByName = memberJpaRepository.findByName_Querydsl("member1");
        assertThat(findMemberByName).containsExactly(member);
    }

    @Test
    public void searchTest() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("teamB");

        List<MemberTeamDto> result = memberJpaRepository.searchByWhere(condition);
        assertThat(result).extracting("username").containsExactly("member4");
    }
}