package cl.ufro.dci.proyecto20.repositorio;

import cl.ufro.dci.proyecto20.modelo.Horario;
import cl.ufro.dci.proyecto20.modelo.Usuario;
import cl.ufro.dci.proyecto20.modelo.numertion.Dia;
import cl.ufro.dci.proyecto20.modelo.numertion.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface IHorariRepos extends JpaRepository<Horario,Long> {

     public Horario findByPeriodoAndDia(Periodo periodo, Dia dia);

     }
