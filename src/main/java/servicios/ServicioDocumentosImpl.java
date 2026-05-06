package servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dominio.Documento;
import puertos.PuertoEntrada;
import repositorios.EntidadNoEncontrada;
import repositorios.RepositorioDocumentos;

@Service
public class ServicioDocumentosImpl implements ServicioDocumentos, PuertoEntrada {

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
	public Documento getDocumento(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("El identificador no puede ser nulo ni vacío.");

		return repoDocumentos.findById(id)
				.orElseThrow(() -> new EntidadNoEncontrada("El documento con id: " + id + "no existe."));
	}

	@Override
	public void deleteDocumento(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("El identificador no puede ser nulo ni vacío.");

		repoDocumentos.findById(id)
				.orElseThrow(() -> new EntidadNoEncontrada("El documento con id: " + id + " no existe."));

		repoDocumentos.deleteById(id);
	}

	@Override
	public void addColaborardor(String id, String colaborardor) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("El identificador no puede ser nulo ni vacío.");

		Documento documento = repoDocumentos.findById(id)
				.orElseThrow(() -> new EntidadNoEncontrada("El documento con id: " + id + "no existe."));

		documento.addColaborador(colaborardor);
		repoDocumentos.save(documento);
	}

	@Override
	public void deleteColaborador(String id, String colaborador) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("El identificador no puede ser nulo ni vacío.");

		Documento documento = repoDocumentos.findById(id)
				.orElseThrow(() -> new EntidadNoEncontrada("El documento con id: " + id + "no existe."));

		documento.deleteColaborador(colaborador);
		repoDocumentos.save(documento);
	}

	@Override
	public List<Documento> getDocumentosPropietario(String propietario) throws EntidadNoEncontrada {
		return repoDocumentos.findByPropietario(propietario);
	}

	@Override
	public void manejarUsuarioEliminado(String nombreUsuario) {
		
		if (nombreUsuario == null || nombreUsuario.isEmpty())
			throw new IllegalArgumentException("El identificador no puede ser nulo ni vacío.");
		
		List<Documento> documentosAeliminar = repoDocumentos.findByPropietario(nombreUsuario);
													
		if(documentosAeliminar.isEmpty()) {
			throw new EntidadNoEncontrada("El usuario no tenía documentos disponubles.")
		}
		
		repoDocumentos.deleteAll(documentosAeliminar);
		
	}

}
