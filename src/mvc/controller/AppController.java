package mvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.model.Jogador;
import mvc.model.JogadorDAO;


@Controller
public class AppController {


    @RequestMapping("/")
    public String execute() {
        return "login";
    }
    
    @RequestMapping("adicionaUsuario")
    public String adiciona(Jogador jogador) {
    	System.out.println(jogador.getUsuario());
    	JogadorDAO dao = new JogadorDAO();
    	dao.adicionaUsuario(jogador);
    	return "roleta";
    }


}