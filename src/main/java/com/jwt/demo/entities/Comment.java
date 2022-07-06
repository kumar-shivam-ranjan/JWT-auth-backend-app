package com.jwt.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String content;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;
}
