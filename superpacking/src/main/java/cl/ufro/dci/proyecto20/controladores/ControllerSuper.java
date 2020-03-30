package cl.ufro.dci.proyecto20.controladores;

import cl.ufro.dci.proyecto20.modelo.Supermercado;
import cl.ufro.dci.proyecto20.modelo.Usuario;
import cl.ufro.dci.proyecto20.repositorio.ISuperReposi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/supermercado")
public class ControllerSuper {

    @Autowired
    private ISuperReposi reposiSuper;
    @GetMapping("/registroSuper")
    public String mostrarSuper(Model model){

        model.addAttribute("supermerc",reposiSuper.findAll());
    return "/RegistroSuper";
    }
    @PostMapping("/registroSuper")
    public String guardarSupermer(@ModelAttribute Supermercado supermercado){
        reposiSuper.save(supermercado);
        return("/RegistroSuper");
    }
    


}
