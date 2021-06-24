package com.example.victorbeck180289pooaf.repository;

import com.example.victorbeck180289pooaf.entity.Filial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FilialRepository extends JpaRepository<Filial, Integer> {
    
}