package com.gechan.devlog.repository;

import com.gechan.devlog.domain.Blog;
import com.gechan.devlog.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SimpleBlogRepository implements BlogRepository {

    private Map<Member, Blog> blogStore = new ConcurrentHashMap<>();

    @Override
    public Blog findByMemberBlog(Member member) {
        Blog blog = blogStore.get(member);
        return blog;
    }

    @Override
    public void createBlog(Blog blog) {
        blogStore.put(blog.getOwner(), blog);
    }
}
