package com.proyecto.springboot.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.springboot.proyecto.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}