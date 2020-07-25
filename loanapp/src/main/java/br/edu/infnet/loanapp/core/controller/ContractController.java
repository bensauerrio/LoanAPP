package br.edu.infnet.loanapp.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.infnet.loanapp.business.model.Contract;
import br.edu.infnet.loanapp.business.repository.ContractRepository;
import br.edu.infnet.loanapp.business.service.ContractService;
import br.edu.infnet.loanapp.core.constants.URLConsts;
import br.edu.infnet.loanapp.core.dto.ContractDTO;

@Controller
@RequestMapping("/")
public class ContractController {

	private final ContractRepository contractRepository;
	private final ContractService contractService;

	@Autowired
	public ContractController(ContractRepository contractRepository, ContractService contractService) {
		this.contractRepository = contractRepository;
		this.contractService = contractService;
	}
	


	@PostMapping("/contract")
	public ModelAndView contractForm(@ModelAttribute("contractForm") final ContractDTO contractDto, final Model model) {
		Contract contract = Contract.fromDTO(contractDto);
		this.contractService.registerContract(contract);
		model.addAttribute("message", "Contrato salvo com sucesso!");
		return new ModelAndView(URLConsts.getContractPath());
	}
}
