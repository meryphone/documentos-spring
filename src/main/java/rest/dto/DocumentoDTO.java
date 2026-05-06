package rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.RepresentationModel;

import dominio.Documento;


public class DocumentoDTO extends RepresentationModel<DocumentoDTO> {
	
	@NotBlank(message = "El propietario no puede estar vacío")
	private String propietario;
	
	@NotNull(message = "El contenido no puede ser null")
	private String contenido;		

	public DocumentoDTO( String propietario, String contenido) {
		this.propietario = propietario;
		this.contenido = contenido;
	}
	
	public DocumentoDTO() {
		
	}
	
	public static DocumentoDTO fromEntity(Documento d) {
	    DocumentoDTO dto = new DocumentoDTO();
	    dto.propietario = d.getPropietario();
	    dto.contenido = d.getContenido();
	    return dto;
	}
	
	// No hace falta
	public static Documento toEntity(DocumentoDTO d) {
	    Documento documento = new Documento();
	    documento.setPropietario(d.getPropietario());
	    documento.setContenido(d.getContenido());
	    return documento;
	}


	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	

}
