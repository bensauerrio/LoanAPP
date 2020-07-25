package br.edu.infnet.loanapp.business.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.infnet.loanapp.ServletInitializerApplication;
import br.edu.infnet.loanapp.business.model.Collector;
import br.edu.infnet.loanapp.business.model.Contract;
import br.edu.infnet.loanapp.business.model.Customer;
import br.edu.infnet.loanapp.business.model.Installment;
import br.edu.infnet.loanapp.business.model.Payment;
import br.edu.infnet.loanapp.business.repository.CollectorRepository;
import br.edu.infnet.loanapp.business.repository.ContractRepository;
import br.edu.infnet.loanapp.business.repository.CustomerRepository;
import br.edu.infnet.loanapp.business.repository.PaymentRepository;

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

	@Autowired
	private CollectorRepository collectorRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private InstallmenteRepository installmentRepository;

	@Autowired
	private ContractRepository contractRepository;

	@Before
	public void beforeEach() {
		final Customer customer = new Customer();
		customer.setAddress("Rua A");
		customer.setName("Ruan");
		this.customerRepository.save(customer);

		final Collector collector = new Collector();
		collector.setName("Coletor");
		this.collectorRepository.save(collector);

		this.collectorRepository.flush();

	}

	@Test
	public void basicLoanTest() {
		final Collector collector = this.collectorRepository.findAll().stream().findFirst().get();
		final Customer customer = this.customerRepository.findAll().stream().findFirst().get();

		final Contract contract = new Contract();
		contract.setCollector(collector);
		contract.setCustomer(customer);
		contract.setLoanAmount(20000);
		contract.setInterestRate(0.04);
		contract.setQttInstallments(4);
		contract.setStartDate(Date.from(
				LocalDate.of(2020, Month.FEBRUARY, 14).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		this.contractService.registerContract(contract);

		Contract contractFound = this.contractRepository.findAll().stream().findFirst().get();
		Payment payment = this.paymentService.getBasicPaymentBasedOnInstallment(contractFound);
		Installment installmentFound = this.installmentRepository.findLastInstallment(contract.getId()).get();

		assertNotNull(contractFound);
		assertNotNull(payment);
		assertEquals(installmentFound.getCapitalIndicates(), payment.getCapitalPaid(), 0);
		assertEquals(installmentFound.getInterestIndicated(), payment.getInterestPaid(), 0);
		assertEquals(20000, contractFound.getLoanPaymentAmountDue(), 0);
		assertEquals(4709.80, payment.getCapitalPaid(), 0);
		assertEquals(800.00, payment.getInterestPaid(), 0);
		assertEquals(4, installmentFound.getInstallmentNbr());

		this.paymentService.registerPayment(payment);

		contractFound = this.contractRepository.findAll().stream().findFirst().get();
		assertEquals(15290.20, contractFound.getLoanPaymentAmountDue(), 0);
		payment = this.paymentService.getBasicPaymentBasedOnInstallment(contractFound);
		installmentFound = payment.getInstallment();

		assertNotNull(contractFound);
		assertNotNull(payment);
		assertEquals(installmentFound.getCapitalIndicates(), payment.getCapitalPaid(), 0);
		assertEquals(installmentFound.getInterestIndicated(), payment.getInterestPaid(), 0);
		assertEquals(4898.19, payment.getCapitalPaid(), 0);
		assertEquals(611.61, payment.getInterestPaid(), 0);
		assertEquals(3, installmentFound.getInstallmentNbr());

		this.paymentService.registerPayment(payment);

		contractFound = this.contractRepository.findAll().stream().findFirst().get();
		assertEquals(10392.01, contractFound.getLoanPaymentAmountDue(), 0);
		payment = this.paymentService.getBasicPaymentBasedOnInstallment(contractFound);
		installmentFound = payment.getInstallment();

		assertNotNull(contractFound);
		assertNotNull(payment);
		assertEquals(installmentFound.getCapitalIndicates(), payment.getCapitalPaid(), 0);
		assertEquals(installmentFound.getInterestIndicated(), payment.getInterestPaid(), 0);
		assertEquals(5094.12, payment.getCapitalPaid(), 0);
		assertEquals(415.68, payment.getInterestPaid(), 0);
		assertEquals(2, installmentFound.getInstallmentNbr());

		this.paymentService.registerPayment(payment);

		contractFound = this.contractRepository.findAll().stream().findFirst().get();
		assertEquals(5297.89, contractFound.getLoanPaymentAmountDue(), 0);
		payment = this.paymentService.getBasicPaymentBasedOnInstallment(contractFound);
		installmentFound = payment.getInstallment();

		assertNotNull(contractFound);
		assertNotNull(payment);
		assertEquals(installmentFound.getCapitalIndicates(), payment.getCapitalPaid(), 0);
		assertEquals(installmentFound.getInterestIndicated(), payment.getInterestPaid(), 0);
		assertEquals(5297.89, payment.getCapitalPaid(), 0);
		assertEquals(211.92, payment.getInterestPaid(), 0);
		assertEquals(1, installmentFound.getInstallmentNbr());

	}

	@Test
	public void basicLoanTest2() {
		final Collector collector = this.collectorRepository.findAll().stream().findFirst().get();
		final Customer customer = this.customerRepository.findAll().stream().findFirst().get();

		final Contract contract = new Contract();
		contract.setCollector(collector);
		contract.setCustomer(customer);
		contract.setLoanAmount(20000);
		contract.setInterestRate(0.04);
		contract.setQttInstallments(4);
		contract.setStartDate(Date.from(
				LocalDate.of(2020, Month.FEBRUARY, 14).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		this.contractService.registerContract(contract);

		Contract contractFound = this.contractRepository.findAll().stream().findFirst().get();
		Payment payment = this.paymentService.getBasicPaymentBasedOnInstallment(contractFound);
		Installment installmentFound = this.installmentRepository.findLastInstallment(contract.getId()).get();

		assertNotNull(contractFound);
		assertNotNull(payment);
		assertEquals(installmentFound.getCapitalIndicates(), payment.getCapitalPaid(), 0);
		assertEquals(installmentFound.getInterestIndicated(), payment.getInterestPaid(), 0);
		assertEquals(20000, contractFound.getLoanPaymentAmountDue(), 0);
		assertEquals(4709.80, payment.getCapitalPaid(), 0);
		assertEquals(800.00, payment.getInterestPaid(), 0);
		assertEquals(4, installmentFound.getInstallmentNbr());

		this.paymentService.registerPayment(payment);

		contractFound = this.contractRepository.findAll().stream().findFirst().get();
		assertEquals(15290.20, contractFound.getLoanPaymentAmountDue(), 0);
		payment = this.paymentService.getBasicPaymentBasedOnInstallment(contractFound);
		installmentFound = payment.getInstallment();

		assertNotNull(contractFound);
		assertNotNull(payment);
		assertEquals(installmentFound.getCapitalIndicates(), payment.getCapitalPaid(), 0);
		assertEquals(installmentFound.getInterestIndicated(), payment.getInterestPaid(), 0);
		assertEquals(4898.19, payment.getCapitalPaid(), 0);
		assertEquals(611.61, payment.getInterestPaid(), 0);
		assertEquals(3, installmentFound.getInstallmentNbr());

		this.paymentService.registerPayment(payment);

		contractFound = this.contractRepository.findAll().stream().findFirst().get();
		assertEquals(10392.01, contractFound.getLoanPaymentAmountDue(), 0);
		payment = this.paymentService.getBasicPaymentBasedOnInstallment(contractFound);
		installmentFound = payment.getInstallment();

		assertNotNull(contractFound);
		assertNotNull(payment);
		assertEquals(installmentFound.getCapitalIndicates(), payment.getCapitalPaid(), 0);
		assertEquals(installmentFound.getInterestIndicated(), payment.getInterestPaid(), 0);
		assertEquals(5094.12, payment.getCapitalPaid(), 0);
		assertEquals(415.68, payment.getInterestPaid(), 0);
		assertEquals(2, installmentFound.getInstallmentNbr());

		this.paymentService.registerPayment(payment);

		contractFound = this.contractRepository.findAll().stream().findFirst().get();
		assertEquals(5297.89, contractFound.getLoanPaymentAmountDue(), 0);
		payment = this.paymentService.getBasicPaymentBasedOnInstallment(contractFound);
		installmentFound = payment.getInstallment();

		assertNotNull(contractFound);
		assertNotNull(payment);
		assertEquals(installmentFound.getCapitalIndicates(), payment.getCapitalPaid(), 0);
		assertEquals(installmentFound.getInterestIndicated(), payment.getInterestPaid(), 0);
		assertEquals(5297.89, payment.getCapitalPaid(), 0);
		assertEquals(211.92, payment.getInterestPaid(), 0);
		assertEquals(1, installmentFound.getInstallmentNbr());

	}

	@After
	public void afterEachTest() {
		this.paymentRepository.deleteAll();
		this.installmentRepository.deleteAll();
		this.contractRepository.deleteAll();

	}
}
