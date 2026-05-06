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
	 	 
	 Documento getDocumento(String id) throws EntidadNoEncontrada;

	 void deleteDocumento(String id) throws EntidadNoEncontrada;

	 List<Documento> getDocumentosPropietario(String propietario) throws EntidadNoEncontrada;

	void addColaborardor(String id, String colaborardor) throws EntidadNoEncontrada;

	void deleteColaborador(String id, String colaborador) throws EntidadNoEncontrada;
	 
		 

}

