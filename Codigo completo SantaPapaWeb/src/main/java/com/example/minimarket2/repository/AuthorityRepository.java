package com.example.minimarket2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.minimarket2.entity.Authority;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
