/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informatica.fac4.demoConferencia.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author tecnologiaf4
 */

/**
 * La anotación @Controller indica al compilador 
 * que la clase es un controlador de Spring
 */
@Controller
public class Controlador {
    /**
     * La anotación @GetMapping indica que cuando 
     * desde el navegador web se haga una petición 
     * vía get, se ejecutará el método que esté 
     * anotado con ella
     * 
     * En este caso si se realiza desde el navegador 
     * web la siguiente petición http://localhost:8080/formulario 
     * se va a ejecutar el método formulario
     * 
     * Este método es el encargado de procesar y responder la petición 
     * del cleinte
     * 
     * En este caso la respuesta es mostrar al usuario la página 
     * formulario.html que se encuentra en la carpeta templates del proyecto
     * 
     * @return 
     */
    @GetMapping("/formulario")
    public String formulario() {
        return "formulario";
    }
    
    /**
     * La anotación @PostMapping indica que cuando desde el navegador se haga
     * una petición vía post, se ejecutará el método que esté anotado con ella
     * 
     * En este caso cuando se presione el botón enviar del formulario 
     * (ver Figura 2), se ejecuta el método procesar
     * 
     * Note que este método recibe tres parámetros, el nombre y apellidos que 
     * son los datos que vienen desde el formulario y un atributo de tipo Model
     * 
     * El atributo model, es un atributo que estará presente en todas las 
     * páginas de nuestra aplicación y podremos acceder a sus valores desde las
     * páginas html a través del motor de plantilla thymeleaf
     * 
     * Cuando el nombre del parámetro es diferente al valor del atributo name 
     * en el componente del formulario html debemos usar la anotación
     * 
     
     * @param genero
     * @RequestParam, como es el caso del parámetro ape que se refiere al valor
     * que trae el campo apellidos del formulario
     * 
     * @param nombre
     * @param ape
     * @param fecha
     * @param model
     * @return 
     */
    @PostMapping("/enviar")
    public String procesar(String nombre, 
                          @RequestParam(name = "apellidos")String ape,
                          @RequestParam(name = "genero")String genero,
                          @RequestParam(name = "fecha_nacimiento")String fecha,
                          Model model) {
               
        
        String anno = fecha.substring(0, 4);
        int anio;
        anio = Integer.parseInt(anno);
        String biciesto = " ";
        if(anio % 4 != 0 || anio % 100 == 0 && (anio % 100 != 0 || anio % 400 != 0)){
            biciesto = "El año ingresado '" + anno + "' no es biciesto";
        }
        else {
            biciesto = "El año ingresado '" + anno + "' es biciesto";
        }

               
       

        model.addAttribute("nombre", nombre);
        model.addAttribute("apellidos", ape);
        model.addAttribute("genero", genero);
        model.addAttribute("fecha_nacimiento", biciesto);
       
        
        return "formulario";
    }
}
