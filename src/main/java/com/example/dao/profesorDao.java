package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.modelo.Profesor;

@Repository
public interface profesorDao extends JpaRepository<Profesor, Long>{
	


	
}
