package com.example.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.modelo.Profesor;
import com.example.servicio.ProfesorServicio;


@RestController
@RequestMapping("/api/v1/")
public class ProfesorControlador {
	
	@Autowired
	private ProfesorServicio profesorServicio;
	
	@GetMapping(value = "profesor")
	public ResponseEntity<Object> get(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Profesor> list = profesorServicio.findAll();
			return new ResponseEntity<Object>(list,HttpStatus.OK);
		} catch (Exception e) {
			map.put("Mensaje de error: ", e.getMessage());
			return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "profesor/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id){
		try {
			Profesor data = profesorServicio.encontrarPorId(id);
			return new ResponseEntity<Object>(data,HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Mensaje de error: ", e.getMessage());
			return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "profesor")
	public ResponseEntity<Object> create(@RequestBody Profesor Profesor){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Profesor res = profesorServicio.guardar(Profesor);
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		} catch (Exception e) {
			map.put("Mensaje de error: ", e.getMessage());
			return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping(value = "profesor/{id}")
	public ResponseEntity<Object> actualizar(@RequestBody Profesor profesor, @PathVariable Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			Profesor currentProfe = profesorServicio.encontrarPorId(id);
			//currentProfe.setId(profesor.getId());
			currentProfe.setNombre(profesor.getNombre());
			currentProfe.setApellido(profesor.getApellido());
			currentProfe.setEmail(profesor.getEmail());
			currentProfe.setTelefono(profesor.getTelefono());
			currentProfe.setTipo_documento(profesor.getTipo_documento());
			currentProfe.setId_carrera(profesor.getId_carrera());
			
			Profesor res = profesorServicio.guardar(profesor);
			
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		} catch (Exception e) {
			map.put("Mensaje de error: ", e.getMessage());
			return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "profesor/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Profesor currentProfe = profesorServicio.encontrarPorId(id);
			profesorServicio.eliminar(currentProfe);
			map.put("Deleted: ", true);
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		} catch (Exception e) {
			
			map.put("Mensaje de error: ", e.getMessage());
			return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "profesor/cantidad")
	public ResponseEntity<Object> contar(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Long cantidad = profesorServicio.contarRegistro();
			map.put("Cantidad de resgistros de profesores: ", cantidad);
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		} catch (Exception e) {
			map.put("Mensaje de error: ", e.getMessage());
			return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
