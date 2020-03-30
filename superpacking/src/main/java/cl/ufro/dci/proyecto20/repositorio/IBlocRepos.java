package cl.ufro.dci.proyecto20.repositorio;

import cl.ufro.dci.proyecto20.modelo.BlocNota;
import cl.ufro.dci.proyecto20.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBlocRepos extends JpaRepository<BlocNota,Long> {

    public List<BlocNota> findByUsuario(Usuario usuario);
}
