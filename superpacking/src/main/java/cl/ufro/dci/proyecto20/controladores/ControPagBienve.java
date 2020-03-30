package cl.ufro.dci.proyecto20.controladores;

import cl.ufro.dci.proyecto20.modelo.BlocNota;
import cl.ufro.dci.proyecto20.modelo.Horario;
import cl.ufro.dci.proyecto20.modelo.Usuario;
import cl.ufro.dci.proyecto20.modelo.numertion.Dia;
import cl.ufro.dci.proyecto20.modelo.numertion.Periodo;
import cl.ufro.dci.proyecto20.repositorio.IBlocRepos;
import cl.ufro.dci.proyecto20.repositorio.IHorariRepos;
import cl.ufro.dci.proyecto20.repositorio.IUsuarioReposi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller()
@RequestMapping(value = "/principal")
public class    ControPagBienve {
    Usuario sessionUser;

    @Autowired
    private IBlocRepos blocRepos;

    @Autowired
    IUsuarioReposi repositorio;

    @Autowired
    IHorariRepos horariRepos;


    @GetMapping("/pagPrinci")
    public String mostrarPag(Model model, HttpServletRequest request){
        sessionUser = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        if(sessionUser == null){
            return ("redirect:/login");
        }
        Optional<Usuario> userTemp=repositorio.findById(sessionUser.getIdUsuario());
        Map<String,String> asignad = getMapHorario();
        for(Horario hor :userTemp.get().getHorario()){
            String attribute=hor.getDia().getDiaFinal()+hor.getPeriodo().getDiaFinal();
            asignad.put(attribute,"Turno");
        }
        model.addAttribute("asign",asignad);
        model.addAttribute("notas",userTemp.get().getBlocnota());
        model.addAttribute("sueldo",("Sueldo: $"+userTemp.get().getSueldo()));
        return("PaginaPrincipal");
    }


    @GetMapping("/agregar/{dia}/{period}")
    public String Guardarhora(@PathVariable("dia") int dia,@PathVariable("period") int periodo, HttpServletRequest request){
        Optional<Usuario> userTemp=repositorio.findById(sessionUser.getIdUsuario());
        Horario ho = horariRepos.findByPeriodoAndDia(Periodo.getPeriodoByNum(periodo),Dia.getDiaByNum(dia));
        if(userTemp.isPresent() && ho==null){
            Usuario ut =userTemp.get();
            Horario hor = new Horario();
            hor.setDia(Dia.getDiaByNum(dia));
            hor.setUsuario(ut);
            hor.setPeriodo(Periodo.getPeriodoByNum(periodo));
            Horario tempHor = horariRepos.save(hor);
            ut.addHorario(tempHor);
            repositorio.save(ut);
        }
        return"redirect:/principal/pagPrinci";
    }

    @PostMapping("/pagBloc")
    public String GuardarNota(@ModelAttribute BlocNota blocNota){
        BlocNota blocTemp=blocRepos.save(blocNota);
        Optional<Usuario> userTemp=repositorio.findById(sessionUser.getIdUsuario());
        if(userTemp.isPresent()){
            Usuario ut =userTemp.get();
            ut.getBlocnota().add(blocTemp);
            repositorio.save(ut);
        }
        return"redirect:/principal/pagPrinci";
    }

    @PostMapping("/pagSueldo")
    public String GuardarSueldo(@RequestParam("ganancia") long ganancia){
        Optional<Usuario> userTemp=repositorio.findById(sessionUser.getIdUsuario());
        if(userTemp.isPresent()){
            Usuario ut =userTemp.get();
            ut.setSueldo(ut.getSueldo()+ganancia);
            repositorio.save(ut);
        }
        return"redirect:/principal/pagPrinci";
    }

    private Map<String, String> getMapHorario() {
        Map<String,String> asignad = new HashMap<>();
        asignad.put("lunesNO","+");
        asignad.put("lunesOO","+");
        asignad.put("lunesOF","+");
        asignad.put("lunesFS","+");
        asignad.put("lunesSN","+");
        asignad.put("lunesNE","+");
        asignad.put("lunesNI","+");
        asignad.put("lunesNT","+");

        asignad.put("martesNO","+");
        asignad.put("martesOO","+");
        asignad.put("martesOF","+");
        asignad.put("martesFS","+");
        asignad.put("martesSN","+");
        asignad.put("martesNE","+");
        asignad.put("martesNI","+");
        asignad.put("martesNT","+");

        asignad.put("miercolesNO","+");
        asignad.put("miercolesOO","+");
        asignad.put("miercolesOF","+");
        asignad.put("miercolesFS","+");
        asignad.put("miercolesSN","+");
        asignad.put("miercolesNE","+");
        asignad.put("miercolesNI","+");
        asignad.put("miercolesNT","+");

        asignad.put("juevesNO","+");
        asignad.put("juevesOO","+");
        asignad.put("juevesOF","+");
        asignad.put("juevesFS","+");
        asignad.put("juevesSN","+");
        asignad.put("juevesNE","+");
        asignad.put("juevesNI","+");
        asignad.put("juevesNT","+");

        asignad.put("viernesNO","+");
        asignad.put("viernesOO","+");
        asignad.put("viernesOF","+");
        asignad.put("viernesFS","+");
        asignad.put("viernesSN","+");
        asignad.put("viernesNE","+");
        asignad.put("viernesNI","+");
        asignad.put("viernesNT","+");

        asignad.put("sabadoNO","+");
        asignad.put("sabadoOO","+");
        asignad.put("sabadoOF","+");
        asignad.put("sabadoFS","+");
        asignad.put("sabadoSN","+");
        asignad.put("sabadoNE","+");
        asignad.put("sabadoNI","+");
        asignad.put("sabadoNT","+");

        asignad.put("domingoNO","+");
        asignad.put("domingoOO","+");
        asignad.put("domingoOF","+");
        asignad.put("domingoFS","+");
        asignad.put("domingoSN","+");
        asignad.put("domingoNE","+");
        asignad.put("domingoNI","+");
        asignad.put("domingoNT","+");

        return asignad;
    }

}