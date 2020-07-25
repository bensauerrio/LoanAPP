package br.edu.infnet.loanapp.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.infnet.loanapp.business.model.Payment;
import br.edu.infnet.loanapp.business.repository.PaymentRepository;
import br.edu.infnet.loanapp.business.service.PaymentService;
import br.edu.infnet.loanapp.core.constants.URLConsts;
import br.edu.infnet.loanapp.core.dto.PaymentDTO;

@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

	private PaymentRepository paymentRepository;
	private PaymentService paymentService;
	
	@Autowired
	public PaymentController(PaymentRepository paymentRepository, PaymentService paymentService) {
		this.paymentRepository = paymentRepository;
		this.paymentService = paymentService;
	}



	@PostMapping("/payment")
	public ModelAndView contractForm(@ModelAttribute("paymentForm") final PaymentDTO paymentDto, final Model model) {
		// Validar os dados do DTO

		Payment payment = Payment.fromDTO(paymentDto);
		this.paymentService.registerPayment(payment);
		model.addAttribute("message", "Pagamento realizado com sucesso!");
		return new ModelAndView(URLConsts.getPaymentPath());
	}
	}


