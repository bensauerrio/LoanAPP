package br.edu.infnet.loanapp.core.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.edu.infnet.loanapp.business.model.Collector;
import br.edu.infnet.loanapp.business.model.Contract;
import br.edu.infnet.loanapp.business.model.Customer;
import br.edu.infnet.loanapp.business.model.Payment;
import br.edu.infnet.loanapp.business.repository.ContractRepository;
import br.edu.infnet.loanapp.business.repository.PaymentRepository;
import br.edu.infnet.loanapp.business.service.ContractService;
import br.edu.infnet.loanapp.business.service.InstallmentService;
import br.edu.infnet.loanapp.core.constants.URLConsts;
import br.edu.infnet.loanapp.core.dto.ContractDTO;

@Controller
@RequestMapping("/contract")
@SessionAttributes({ "clientSession", "customers" })
public class ContractController {

	private final ContractRepository contractRepository;

	private final ContractService contractService;

	private final InstallmentService installmentService;

	@Autowired
	private PaymentRepository paymentRepository;

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
			model.addAttribute("contractForm", contractDto);
		} else {
			this.contractService.registerContract(contract);
			model.addAttribute("message", "Contrato salvo com sucesso!");
		}

		return new ModelAndView(URLConsts.getContractPath());
	}

	@GetMapping()
	public ModelAndView showPayments(//
			@RequestParam(value = "id", required = true) final int id, //
			final Model model) {
		ModelAndView modelAndView = new ModelAndView(URLConsts.getPaymentListPath());
		try {

			modelAndView = new ModelAndView(URLConsts.getPaymentListPath());

			final Customer client = (Customer) model.getAttribute("clientSession");

			final Contract contract = this.contractRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Nenhum contrato foi encontrado"));

			if (contract.getCustomer().getId() != client.getId()) {
				throw new RuntimeException("O contrato não é do cliente.");
			}

			final List<Payment> payments = this.paymentRepository.findAllPaymentByContractId(contract.getId());

			model.addAttribute("payments", payments);
			model.addAttribute("needMorePayment", payments//
					.stream()//
					.filter(Objects::nonNull)//
					.noneMatch(item -> item.getInstallment().getInstallmentNbr() == 1));
			model.addAttribute("contract", contract);
		} catch (final RuntimeException e) {
			model.addAttribute("message", e.getMessage());
		}

		return modelAndView;
	}

}
