package pe.edu.cibertec.appwebformularios2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.cibertec.appwebformularios2.model.primoModel;

@Controller
public class formPrimoController {

    private Boolean esPrimo(Integer numero){
        Integer contador = 0;
        for(int i = 1; i <= numero; i++){
            if((numero % i) == 0){
                contador++;
            }
        }
        return contador <= 2;
    }

    @GetMapping("/CalcularPrimos")
    public String index(Model model){
        model.addAttribute("verResultado", false);
        model.addAttribute("primoModel", new primoModel());
        return "formPrimo";
    }

    @PostMapping("/CalcularPrimos")
    public String calcularPrimo(@ModelAttribute("primoModel") primoModel primo, Model  model){
        String resultado = esPrimo(primo.getNumero())
                ? "El número " + primo.getNumero() + " es Primo"
                : "El número " + primo.getNumero() + " No es Primo";
        model.addAttribute("verResultado", true);
        model.addAttribute("resultado", resultado);
        return "formPrimo";
    }
}
