package cl.ufro.dci.proyecto20.controladores;

import cl.ufro.dci.proyecto20.modelo.Usuario;
import cl.ufro.dci.proyecto20.repositorio.IUsuarioReposi;
import cl.ufro.dci.proyecto20.repositorio.ISuperReposi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@Controller
@RequestMapping(value="/cuenta")
public class ContrRecuCuenta {

    @Autowired
    IUsuarioReposi repositorio;

    @Autowired
    private IUsuarioReposi userReposi;

    Usuario sessionUser;

    @GetMapping("/recuperar")
    public String mostrarForm(Model model){
        model.addAttribute("message", "");
        return("RecupeCuenta");
    }

    //private JavaMailSender mailSender;
    //public void setMailSender(JavaMailSender mailSender) {
   //     this.mailSender = mailSender;
   // }
//    @Service("emailSenderService")
//    public class EmailSenderService {
//
//        public JavaMailSender javaMailSender;
//
//        @Autowired
//        public EmailSenderService(JavaMailSender javaMailSender) {
//            this.javaMailSender = javaMailSender;
//        }
//
//        @Async
//        public void sendEmail(SimpleMailMessage email) {
//            javaMailSender.send(email);
//        }
//    }

//    @PostMapping("/recuperar")
//    public String recuperCuent(@ModelAttribute Usuario user, Model model, HttpServletRequest request){
//        Usuario usuarioBd=userReposi.findByNombreAndCorreo(user.getNombre(),user.getCorreo());
//        if(usuarioBd != null){
//            sessionUser = (Usuario) request.getSession().getAttribute("usuarioLogueado");
//            return "redirect:/login";
//        }else{
//            return "redirect:/princial/bienvenido";
//        }
//
//    }




    @RequestMapping(value="/recuperar", method=RequestMethod.POST)
    public String forgotUserPassword(Model model, Usuario user) {
        Usuario existingUser = repositorio.findByCorreo(user.getCorreo());
        if (existingUser != null) {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);
            mailSender.setUsername("superpackingcorpo@gmail.com");
            mailSender.setPassword("z");

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getCorreo());
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setFrom("superpackingcorpo@gmail.com");
            mailMessage.setText("Tu contrasena es: "+ existingUser.getContra());
            mailSender.send(mailMessage);
            model.addAttribute("message", "Tu contraseña ha sido enviada al correo.");
            return "RecupeCuenta";
        } else {
            model.addAttribute("message", "El correo ingresado no esta registrado");
            return "RecupeCuenta";
        }
    }

}

