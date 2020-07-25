package br.edu.infnet.loanapp.business.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.infnet.loanapp.ServletInitializerApplication;
import br.edu.infnet.loanapp.business.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServletInitializerApplication.class)
public class PaymentIntegratedTest {

	@Autowired
	private ContractService contractService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private InstallmentService installmentService;

	@Autowired
	private CustomerRepository customerRepository;

}
