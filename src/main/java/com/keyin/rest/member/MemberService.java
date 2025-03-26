package com.keyin.rest.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return (List<Member>) memberRepository.findAll();
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public Member updateMember(Long id, Member memberDetails) {
        Member member = memberRepository.findById(id).orElseThrow();
        member.setName(memberDetails.getName());
        member.setAddress(memberDetails.getAddress());
        member.setEmail(memberDetails.getEmail());
        member.setPhone(memberDetails.getPhone());
        member.setStartDate(memberDetails.getStartDate());
        member.setMembershipDuration(memberDetails.getMembershipDuration());
        return memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public List<Member> searchByName(String name) {
        return memberRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Member> searchByPhone(String phone) {
        return memberRepository.findByPhone(phone);
    }

    public List<Member> searchByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public List<Member> searchByStartDate(Date startDate) {
        return memberRepository.findByStartDate(startDate);
    }

    public List<Member> searchByMembershipDuration(int duration) {
        return memberRepository.findByMembershipDuration(duration);
    }
}