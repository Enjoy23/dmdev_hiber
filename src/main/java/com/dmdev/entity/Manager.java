package com.dmdev.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@DiscriminatorValue("manager")
@PrimaryKeyJoinColumn(name = "id")
public class Manager extends User {
    private String projectName;
    @Builder
    public Manager(Long id, String username, PersonalInfo personalInfo, Role role, Company company, List<UserChat> userChats, String projectName) {
        super(id, username, personalInfo, role, company, userChats);
        this.projectName = projectName;
    }
}
