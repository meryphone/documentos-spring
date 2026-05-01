package dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "documentos")
public class Documento {
	
	@Id
	private String id;
	private String propietario;
	private ArrayList<String> colaboradores;
	private String contenido;
	
	public Documento(String propietario, String contenido) {
		this.propietario = propietario;
		this.colaboradores = new ArrayList<String>();
		this.contenido = contenido;
	}		
		
	public Documento() {	
	}


	public void addColaborador(String colaborador) {
		colaboradores.add(colaborador);
	}
	
	public void deleteColaborador(String colaborador) {
		colaboradores.remove(colaborador);
	}
	
	public String getId() {
		return id;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public List<String> getColaboradores() {
		return  Collections.unmodifiableList(this.colaboradores);
	}

	public void setColaboradores(List<String> colaboradores) {
		this.colaboradores = new ArrayList<>(colaboradores);
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	
	
	
	

}
