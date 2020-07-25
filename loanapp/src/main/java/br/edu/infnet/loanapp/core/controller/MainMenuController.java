package br.edu.infnet.loanapp.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.edu.infnet.loanapp.business.model.Client;
import br.edu.infnet.loanapp.business.model.Customer;
import br.edu.infnet.loanapp.business.repository.CustomerRepository;
import br.edu.infnet.loanapp.business.service.FunctionalitySingleton;
import br.edu.infnet.loanapp.core.constants.URLConsts;

@Controller
@RequestMapping(value = "/mainmenu")
@SessionAttributes({ "clientSession", "sessionFunctionalities" })
public class MainMenuController {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping
	public ModelAndView doGet(//
			@RequestParam(value = "path", required = true) final String path, //
			final Model model) {

		final Client client = (Client) model.getAttribute("clientSession");

		String logMessage = "";

		try {

			if (FunctionalitySingleton.getInstance().isPathValid(client, path)) {
				return this.beforeGo(path);
			} else {
				logMessage = "Funcionalidade não permitida para o usuário";
			}

		} catch (final RuntimeException e) {
			logMessage = e.getMessage();
		}
		model.addAttribute("message", logMessage);
		return new ModelAndView(URLConsts.getMainMenuPath());
	}

	private ModelAndView beforeGo(final String path) {
		final ModelAndView modelAndView = new ModelAndView();
		if (URLConsts.getContractPath().equalsIgnoreCase(path)) {
			this.loadContractAttributes(modelAndView);
		}

		modelAndView.setViewName(path);
		return modelAndView;
	}

	private void loadContractAttributes(final ModelAndView modelAndView) {
		final List<Customer> customers = this.customerRepository.findAll();
		modelAndView.addObject("customers", customers);
	}

}
