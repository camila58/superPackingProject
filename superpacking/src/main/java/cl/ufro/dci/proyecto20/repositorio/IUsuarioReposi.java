package cl.ufro.dci.proyecto20.repositorio;

import cl.ufro.dci.proyecto20.modelo.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUsuarioReposi extends CrudRepository<Usuario,Long> {
    @Override
    public List <Usuario> findAll();

    public Usuario findByCorreoAndContra(String user,String contra);
    
    public Usuario findByNombreAndCorreo(String nombre, String correo);

    public Usuario findByCorreo(String correo);

}
