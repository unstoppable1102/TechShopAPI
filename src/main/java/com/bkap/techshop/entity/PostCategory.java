package com.bkap.techshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post_categories")
public class PostCategory extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean active;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Transient
    private int postCount;

    @OneToMany(mappedBy = "postCategory", fetch = FetchType.LAZY)
    private List<Post> posts;
}
