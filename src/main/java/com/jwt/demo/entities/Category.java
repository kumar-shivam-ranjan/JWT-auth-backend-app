package com.jwt.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer categoryId;

  @Column(name = "title")
  private String categoryTitle;

  @Column(name = "description")
  private String categoryDescription;
}
