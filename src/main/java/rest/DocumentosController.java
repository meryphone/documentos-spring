package rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dominio.Documento;
import repositorios.EntidadNoEncontrada;
import jakarta.validation.Valid;
import rest.dto.DocumentoDTO;
import servicios.ServicioDocumentos;
import servicios.ServicioExcepcion;

@RestController
@RequestMapping("/documentos")

public class DocumentosController {

    private ServicioDocumentos servicio;

    @Autowired
    public DocumentosController(ServicioDocumentos servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Void> createDocumento(
            @Valid @RequestBody
            DocumentoDTO nuevoDocumento) throws ServicioExcepcion {

        String id = servicio.saveDocumento(
                nuevoDocumento.getPropietario(),
                nuevoDocumento.getContenido());

        URI nuevaURL = ServletUriComponentsBuilder.fromCurrentRequest()
        		.path("/{id}")
        		.buildAndExpand(id)
        		.toUri();

        return ResponseEntity.created(nuevaURL).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> getDocumento(
            @PathVariable("id")  String id) throws ServicioExcepcion, EntidadNoEncontrada {
    	
    	Documento d = servicio.getDocumento(id);
    	
        return ResponseEntity.ok(DocumentoDTO.fromEntity(d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumento(
            @PathVariable("id")  String id) throws ServicioExcepcion, EntidadNoEncontrada {

        servicio.deleteDocumento(id);
        
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/colaboradores/{colaborador}")
    public ResponseEntity<Void> addColaborador(
            @PathVariable("id")
            String id,
            @PathVariable("colaborador")
            String colaborador) throws ServicioExcepcion, EntidadNoEncontrada {

        servicio.addColaborardor(id, colaborador);
        
        return ResponseEntity.noContent().build();
    }
}