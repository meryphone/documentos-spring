package servicios;

import java.util.List;

import dominio.Documento;
import repositorios.EntidadNoEncontrada;

public interface ServicioDocumentos {
	
	/**
	 * 
	 * @param propietario
	 * @param contenido
	 * @return Id del documento creado.
	 */
	 String saveDocumento(String propietario, String contenido);
	 	 
	 Documento getDocumento(String id) throws EntidadNoEncontrada, ServicioExcepcion;
	 
	 void deleteDocumento(String id) throws ServicioExcepcion, EntidadNoEncontrada;
	 
	 List<Documento> getDocumentosPropietario(String propietario) throws EntidadNoEncontrada, ServicioExcepcion;

	void addColaborardor(String id, String colaborardor) throws EntidadNoEncontrada, ServicioExcepcion;

	void deleteColaborador(String id, String colaborador) throws EntidadNoEncontrada, ServicioExcepcion;
	 
		 

}

