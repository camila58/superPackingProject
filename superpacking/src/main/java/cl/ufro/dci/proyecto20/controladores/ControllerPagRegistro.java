package cl.ufro.dci.proyecto20.controladores;

import cl.ufro.dci.proyecto20.modelo.BlocNota;
import cl.ufro.dci.proyecto20.modelo.Supermercado;
import cl.ufro.dci.proyecto20.modelo.Usuario;
import cl.ufro.dci.proyecto20.modelo.numertion.TiposSupermer;
import cl.ufro.dci.proyecto20.repositorio.IBlocRepos;
import cl.ufro.dci.proyecto20.repositorio.ISuperReposi;
import cl.ufro.dci.proyecto20.repositorio.IUsuarioReposi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/registro")
public class ControllerPagRegistro {
    @Autowired
    private IUsuarioReposi userRepo;

    @Autowired
    ISuperReposi supermercados;

    @GetMapping("/regis")
    public String mostrarFormulari(Model model){
        Iterable<Supermercado> supermers =supermercados.findAll();
        model.addAttribute("supermercs",supermers);
        model.addAttribute(userRepo.findAll());
        model.addAttribute("error_mensaje","");
        return "Registrarse";
    }
    @PostMapping("/regis")
    public String GuargarUser(Model model,@ModelAttribute Usuario user, @RequestParam Long supermer){
        Usuario usuarioBd=userRepo.findByCorreo(user.getCorreo());
        if(usuarioBd != null){
            model.addAttribute("error_mensaje","Este correo ya se encuentra registrado.");
            return "Registrarse";
        }
        Optional<Supermercado> superTemp =supermercados.findById(supermer.intValue());
        if (superTemp.isPresent()){
            Supermercado supTemp =superTemp.get();
            user.setSupermercado(supTemp);
            supTemp.getEmpaques().add(user);
            userRepo.save(user);
            return"redirect:/login";
        }
        return"redirect:/registro/regis";

    }


}
