package pe.edu.cibertec.appwebformularios2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.cibertec.appwebformularios2.model.ImcModel;

@Controller
public class FormImcController {

    @GetMapping("/CalcularIMC")
    public String index(Model model){
        model.addAttribute("imcModel", new ImcModel());
        model.addAttribute("verResultado", false);
        return "formimc";
    }

    @PostMapping("/CalcularIMC")
    public String calcularIMC(@ModelAttribute("imcModel") ImcModel imc, Model model){

        Double tallaM= imc.getTalla() / 100;
        Double valorImc = imc.getPeso() / (tallaM * tallaM);
        String condicion = "";
        String colorAlert="alert-danger";
        if(valorImc < 18.5) {
            condicion = "Peso inferior al normal";
            colorAlert = "alert-dark";
        }
        else if (valorImc <= 25) {
            condicion = "Peso normal";
            colorAlert = "alert-primary";
        }
        else if(valorImc <= 30) {
            condicion = "Peso superior al normal";
            colorAlert = "alert-warning";
        }
        else if(valorImc <= 35)
            condicion = "Con obesidad leve";
        else if(valorImc <= 39)
            condicion = "Con obesidad media";
        else
            condicion = "Con obesidad mórbida";

        model.addAttribute("verResultado", true);
        model.addAttribute("resultado", "Su valor de IMC es: " + String.format("%.2f", valorImc) + "\n" + "Su condición es: " + condicion);
        model.addAttribute("colorAlert", colorAlert);

        return "formimc";
    }
}
