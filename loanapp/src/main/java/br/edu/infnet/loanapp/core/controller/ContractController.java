package br.edu.infnet.loanapp.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.infnet.loanapp.business.model.Contract;
import br.edu.infnet.loanapp.business.model.ContractDTO;
import br.edu.infnet.loanapp.business.repository.ContractRepository;
import br.edu.infnet.loanapp.core.constants.URLConsts;

@Controller
@RequestMapping("/")
public class ContractController {

	private final ContractRepository contractRepository;

	@Autowired
	public ContractController(final ContractRepository contractRepository) {
		this.contractRepository = contractRepository;
	}

//	@GetMapping("/contract")
//	public ModelAndView listCustomer(@PathVariable("customerId") Integer customerId, final Model model) {
//		model.addAttribute("customerid", CustomerRepository.findAll());
//		return new ModelAndView(URLConsts.getContractPath());
//	}

	@PostMapping("/contract")
	public ModelAndView contractForm(@ModelAttribute("contractForm") final ContractDTO contract, final Model model) {
		// Validar os dados do DTO

		this.contractRepository.save(Contract.fromDTO(contract));
		model.addAttribute("message", "Contrato salvo com sucesso!");
		return new ModelAndView(URLConsts.getContractPath());
	}
}
