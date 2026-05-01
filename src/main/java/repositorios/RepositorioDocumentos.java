package repositorios;

import dominio.Documento;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositorioDocumentos extends CrudRepository<Documento, String> {
	
	 List<Documento> findByPropietario(String propietario);

}
