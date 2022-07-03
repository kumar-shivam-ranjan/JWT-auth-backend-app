package com.jwt.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "username", nullable = false , length = 100)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    private String password;
    private String about;
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Post> posts=new ArrayList<>();
}
