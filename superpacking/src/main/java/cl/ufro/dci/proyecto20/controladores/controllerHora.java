package cl.ufro.dci.proyecto20.controladores;

import cl.ufro.dci.proyecto20.modelo.Horario;
import cl.ufro.dci.proyecto20.modelo.Usuario;
import cl.ufro.dci.proyecto20.repositorio.IHorariRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/agregar")
public class controllerHora {
    Usuario sessionUser;
    @Autowired
    private IHorariRepos reposiHorario;

    @GetMapping("/hora")
    public String mostrarfor(){
        return "PaginaPrincipal";
    }

    @PostMapping("/guardar")
    public String guardarDatos(Horario horario, HttpServletRequest request){
        sessionUser = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        horario.setUsuario(sessionUser);
        reposiHorario.save(horario);
        System.out.print("holssss");
        return "PaginaPrincipal";
    }
    @DeleteMapping("/borraHorario")
    public String borrarPeriodo(@ModelAttribute Horario horario,HttpServletRequest request){
        sessionUser = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        //horario =reposiHorario.findAllById(horario.getIdHorario());
        reposiHorario.delete(horario);

        return "PaginaPrincipal";
    }



}
