package com.example.victorbeck180289pooaf.repository;

import com.example.victorbeck180289pooaf.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}