package pe.edu.cibertec.appwebformularios2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.cibertec.appwebformularios2.model.PromedioModel;

@Controller
public class FormPromedioController {
    @GetMapping("/CalcularPromedioNotas")
    public String index(Model model){
        model.addAttribute("promedioModel", new PromedioModel());
        model.addAttribute("verResultado", false);
        return "formPromedio";
    }

    @PostMapping("/CalcularPromedioNotas")
    public String calcularpromedio(@ModelAttribute("promedioModel") PromedioModel promedioModel, Model model){
        Double promedio = (promedioModel.getCL1()*0.15)+ (promedioModel.getCL2()*0.2)+ (promedioModel.getCL3()*0.35)+ (promedioModel.getSP()*0.3);
        String condicion = (promedio > 10.5) ? "Aprobado" : "Desaprobado";
        model.addAttribute("resultado", "El promedio de notas es: " +promedio+" usted se encuentra: "+ condicion);
        model.addAttribute("verResultado", true);
        model.addAttribute("promedioModel", new PromedioModel());
        return "formPromedio";
    }
}
