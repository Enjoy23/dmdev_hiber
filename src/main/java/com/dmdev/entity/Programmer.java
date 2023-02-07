package com.dmdev.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@DiscriminatorValue("programmer")
@PrimaryKeyJoinColumn(name = "id")
public class Programmer extends User{
    @Enumerated(value = EnumType.STRING)
    private Language language;
    @Builder
    public Programmer(Long id, String username, PersonalInfo personalInfo, Role role, Company company, List<UserChat> userChats, Language language) {
        super(id, username, personalInfo, role, company, userChats);
        this.language = language;
    }
}
