package com.keyin.rest.member;

import com.keyin.rest.tournament.Tournament;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Member {
    @Id
    @SequenceGenerator(
            name = "member_sequence",
            sequenceName = "member_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(generator = "member_sequence")
    private long id;

    private String name;
    private String address;
    private String email;
    private String phone;

    private Date startDate;

    private int membershipDuration;

    @ManyToMany(mappedBy = "participatingMembers")
    private List<Tournament> tournaments;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getMembershipDuration() {
        return membershipDuration;
    }

    public void setMembershipDuration(int membershipDuration) {
        this.membershipDuration = membershipDuration;
    }
}