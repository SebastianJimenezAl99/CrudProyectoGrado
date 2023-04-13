package com.example.servicio;

import java.util.List;

import com.example.modelo.Profesor;

public interface ProfesorServicio {
	
	public List<Profesor> findAll(); 
	
	public Profesor guardar(Profesor pro);	
	
	public Profesor encontrarPorId(Long id);
	
	public void eliminar(Profesor pro);
	
	public long contarRegistro();
	
	
}
