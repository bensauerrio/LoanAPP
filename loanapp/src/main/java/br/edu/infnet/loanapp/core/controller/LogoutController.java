package br.edu.infnet.loanapp.core.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.infnet.loanapp.core.constants.URLConsts;

@Controller
public class LogoutController {
	 
	@RequestMapping("logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView(URLConsts.getLoginPath());
    }
	
}

