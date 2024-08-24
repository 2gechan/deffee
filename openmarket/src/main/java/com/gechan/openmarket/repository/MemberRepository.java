package com.gechan.openmarket.repository;

import com.gechan.openmarket.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = "memberRoleList")
    @Query("select m from Member m where m.id = :id")
    Member getWithRoles(@Param("id") String id);
}
