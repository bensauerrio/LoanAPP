package br.edu.infnet.loanapp.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.edu.infnet.loanapp.business.model.Collector;
import br.edu.infnet.loanapp.business.model.Contract;
import br.edu.infnet.loanapp.business.repository.ContractRepository;
import br.edu.infnet.loanapp.business.service.ContractService;
import br.edu.infnet.loanapp.business.service.InstallmentService;
import br.edu.infnet.loanapp.core.constants.URLConsts;
import br.edu.infnet.loanapp.core.dto.ContractDTO;

@Controller
@RequestMapping("/contract")
@SessionAttributes({ "clientSession" })
public class ContractController {

	private final ContractRepository contractRepository;

	private final ContractService contractService;

	private final InstallmentService installmentService;

	@Autowired
	public ContractController(//
			final ContractRepository contractRepository, //
			final ContractService contractService, //
			final InstallmentService installmentService) {
		this.contractRepository = contractRepository;
		this.contractService = contractService;
		this.installmentService = installmentService;
	}

	@PostMapping
	public ModelAndView contractForm(//
			@ModelAttribute("contractForm") final ContractDTO contractDto, //
			final Model model, //
			@RequestParam(value = "formButton") final String buttonType) {

		final Collector collector = (Collector) model.getAttribute("clientSession");
		contractDto.setCollectorId(collector.getId());

		final Contract contract = Contract.fromDTO(contractDto);
		if (buttonType != null && buttonType.equalsIgnoreCase("simular")) {
			model.addAttribute("showModal", "showModal");
			model.addAttribute("installmentsSimulation",
					this.installmentService.simulateInstallmentsFromContract(contract));
		} else {
			this.contractService.registerContract(contract);
			model.addAttribute("message", "Contrato salvo com sucesso!");
		}

		return new ModelAndView(URLConsts.getContractPath());
	}

}
