package com.jwt.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post")
@Builder
@Data
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
}
