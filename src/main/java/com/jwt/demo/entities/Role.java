package com.jwt.demo.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name="roles")
@Builder
public class Role {
    @Id
    private Integer id;
    private String name;
}
