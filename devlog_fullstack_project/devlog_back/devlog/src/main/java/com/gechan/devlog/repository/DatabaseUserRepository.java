package com.gechan.devlog.repository;

import com.gechan.devlog.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseUserRepository extends JpaRepository<Member, Long> {

}
