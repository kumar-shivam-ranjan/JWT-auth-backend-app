package com.jwt.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer postId;

  @Column(nullable = false)
  private String title;

  @Column(length = 1000)
  private String content;

  private String image;
  private Date addedDate;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  private Set<Comment> comments=new HashSet<>();
}
