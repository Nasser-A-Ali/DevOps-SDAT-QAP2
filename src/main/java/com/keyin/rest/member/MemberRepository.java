package com.keyin.rest.member;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    public List<Member> findByNameContainingIgnoreCase(String name);
    public List<Member> findByPhone(String phone);
    public List<Member> findByEmail(String email);
    public List<Member> findByStartDate(Date startDate);
    public List<Member> findByMembershipDuration(int duration);

}