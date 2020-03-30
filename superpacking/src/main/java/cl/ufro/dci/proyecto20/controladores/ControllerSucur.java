package cl.ufro.dci.proyecto20.controladores;

import cl.ufro.dci.proyecto20.modelo.Sucursal;
import cl.ufro.dci.proyecto20.modelo.Supermercado;
import cl.ufro.dci.proyecto20.repositorio.ISucursReposi;
import cl.ufro.dci.proyecto20.repositorio.ISuperReposi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/sucursal")
public class ControllerSucur {

    @Autowired
    private ISucursReposi reposiSucursal;
    @Autowired
    private ISuperReposi reposiSuper;
    @GetMapping("/registroSucur") //relacion con el navegdor
    public String mostrarDato(Model model){
        ArrayList<Supermercado>supermercado= (ArrayList<Supermercado>) reposiSuper.findAll();
        model.addAttribute("supermercados",supermercado);
        //model.addAttribute(reposiSucursal.findAll());
        return "/RegistroSucurs"; //relacion con el html
    }
    @PostMapping("/registroSucur")
    public String guardarSucurs(@ModelAttribute Sucursal sucursal){
        reposiSucursal.save(sucursal);
        return ("/RegistroSucurs");
    }

}
