package cl.ufro.dci.proyecto20.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControlleSuperPa {

    @GetMapping("/")
    public String mostrasPag(){
        return "SuperPacking";
    }

}
