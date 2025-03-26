package com.keyin.rest.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping("/member")
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    @GetMapping("/member/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id).orElse(null);
    }

    @PutMapping("/member/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
        return memberService.updateMember(id, memberDetails);
    }

    @DeleteMapping("/member/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }

    @GetMapping("/member_search")
    public List<Member> searchMembers(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "start_date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(value = "duration", required = false) Integer duration) {

        if (name != null) {
            return memberService.searchByName(name);
        } else if (phone != null) {
            return memberService.searchByPhone(phone);
        } else if (email != null) {
            return memberService.searchByEmail(email);
        } else if (startDate != null) {
            return memberService.searchByStartDate(startDate);
        } else if (duration != null) {
            return memberService.searchByMembershipDuration(duration);
        }

        return memberService.getAllMembers();
    }
}