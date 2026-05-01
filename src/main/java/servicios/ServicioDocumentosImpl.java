package servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dominio.Documento;
import repositorios.EntidadNoEncontrada;
import repositorios.RepositorioDocumentos;
import repositorios.RepositorioDocumentosMongo;

@Service
public class ServicioDocumentosImpl implements ServicioDocumentos {

	private RepositorioDocumentos repoDocumentos;

	@Autowired
	public ServicioDocumentosImpl(RepositorioDocumentos repoDocumentos) {
		this.repoDocumentos = repoDocumentos;
	}

	@Override
	public String saveDocumento(String propietario, String contenido) {
		
		if(propietario == null || propietario.isEmpty() || contenido == null) {
			throw new IllegalArgumentException();			
		}
		
		Documento documento = new Documento(propietario,contenido);
		
		return repoDocumentos.save(documento).getId();
		
	}

	@Override
	public Documento getDocumento(String id) throws ServicioExcepcion, EntidadNoEncontrada {
		try {

			Documento documento = repoDocumentos.findById(id)
					.orElseThrow(() -> new EntidadNoEncontrada("El documento con id: " + id + "no existe."));

			return documento;

		} catch (IllegalArgumentException e) {
			throw new ServicioExcepcion("Identificador no válido: " + id + ".");
		}

	}

	@Override
	public void deleteDocumento(String id) throws ServicioExcepcion, EntidadNoEncontrada {
		try {

			repoDocumentos.findById(id)
					.orElseThrow(() -> new EntidadNoEncontrada("El documento con id: " + id + " no existe."));
			
			repoDocumentos.deleteById(id);

		} catch (IllegalArgumentException e) {
			throw new ServicioExcepcion("Identificador no válido: " + id + ".");
		}

	}

	@Override
	public void addColaborardor(String id, String colaborardor) throws EntidadNoEncontrada, ServicioExcepcion {
		try {

			Documento documento = repoDocumentos.findById(id)
					.orElseThrow(() -> new EntidadNoEncontrada("El documento con id: " + id + "no existe."));
			
			documento.addColaborador(colaborardor);
			repoDocumentos.save(documento);

		} catch (IllegalArgumentException e) {
			throw new ServicioExcepcion("Identificador no válido: " + id + ".");
		}

	}

	@Override
	public void deleteColaborador(String id, String colaborador) throws EntidadNoEncontrada, ServicioExcepcion {
		try {

			Documento documento = repoDocumentos.findById(id)
					.orElseThrow(() -> new EntidadNoEncontrada("El documento con id: " + id + "no existe."));
			
			documento.deleteColaborador(colaborador);
			repoDocumentos.save(documento);

		} catch (IllegalArgumentException e) {
			throw new ServicioExcepcion("Identificador no válido: " + id + ".");
		}
	}

	@Override
	public List<Documento> getDocumentosPropietario(String propietario) throws EntidadNoEncontrada, ServicioExcepcion{
		try {

			List<Documento> documentos = repoDocumentos.findByPropietario(propietario);
						
			return documentos;

		} catch (IllegalArgumentException e) {
			throw new ServicioExcepcion("Identificador no válido: " + propietario + ".");
		}
	}

}
