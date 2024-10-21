package com.bkap.techshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    private String image;

    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_category_id", updatable = false)
    private PostCategory postCategory;



}
