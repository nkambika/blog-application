package com.myprojects.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Permission extends CommonEntity{
    @Column(length = 50, nullable = false)
    private String name;
    private List<String> allowed_endpoints;
    private List<String> allowed_methods;
}
