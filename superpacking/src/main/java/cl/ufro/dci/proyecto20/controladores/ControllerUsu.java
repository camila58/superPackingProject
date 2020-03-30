package cl.ufro.dci.proyecto20.controladores;

import cl.ufro.dci.proyecto20.modelo.Supermercado;
import cl.ufro.dci.proyecto20.modelo.Usuario;
import cl.ufro.dci.proyecto20.repositorio.ISuperReposi;
import cl.ufro.dci.proyecto20.repositorio.IUsuarioReposi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import cl.ufro.dci.proyecto20.modelo.Usuario.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@Controller
public class ControllerUsu {
    @Autowired
    IUsuarioReposi repositorio;

    @Autowired
    ISuperReposi supers;

    @GetMapping("/login")
    public String mostrarFormula(Model model){
        //loadSuperMarquets();
        model.addAttribute("usuario",new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Usuario user, HttpServletRequest request,Model model) {
        user.getNombre();
        Usuario usuarioBd = repositorio.findByCorreoAndContra(user.getCorreo(), user.getContra());
        if (usuarioBd != null) {
            request.getSession().setAttribute("usuarioLogueado", usuarioBd);
            return "redirect:/principal/pagPrinci";
        } else {
            model.addAttribute("usuario", new Usuario());
            model.addAttribute("error", true);
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(Model model,HttpServletRequest request){
        request.getSession().setAttribute("usuarioLogueado", null);
        return "redirect:/login";
    }

    private void loadSuperMarquets() {
        String[] names = {"Jumbo","Santa Isabel","Lider","Trebol","Walmarck"};
        for (String name: names){
            supers.save(new Supermercado(name));
        }
    }

}
