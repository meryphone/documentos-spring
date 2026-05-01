package repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import dominio.Documento;


public interface RepositorioDocumentosMongo extends RepositorioDocumentos, MongoRepository<Documento, String> {

}
