package com.gechan.devlog.repository;

import com.gechan.devlog.domain.Blog;
import com.gechan.devlog.domain.Member;

public interface BlogRepository {

    Blog findByMemberBlog(Member member);

    void createBlog(Blog blog);
}
