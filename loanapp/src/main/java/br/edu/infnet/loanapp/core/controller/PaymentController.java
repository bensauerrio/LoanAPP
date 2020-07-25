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
import br.edu.infnet.loanapp.core.constants.URLConsts;

@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

	@Autowired
	private PaymentRepository paymentRepository;

	@PostMapping
	public ModelAndView paymentForm(@ModelAttribute("paymentForm") final Payment payment, final Model model) {
		paymentRepository.save(payment);
		return new ModelAndView(URLConsts.getPaymentPath());
	}

}
