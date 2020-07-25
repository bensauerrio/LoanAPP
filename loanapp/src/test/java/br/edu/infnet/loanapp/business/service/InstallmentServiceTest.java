package br.edu.infnet.loanapp.business.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import br.edu.infnet.loanapp.business.repository.CollectorRepository;
import br.edu.infnet.loanapp.business.repository.ContractRepository;
import br.edu.infnet.loanapp.business.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServletInitializerApplication.class)
public class InstallmentServiceTest {

	@Autowired
	private InstallmentService installmentService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CollectorRepository collectorRepository;

	@Autowired
	private ContractRepository contractRepository;

	@Test
	public void testCalculateBasicInstallmentBasedOnPriceTest1() {
		final Contract contract = new Contract();
		contract.setLoanAmount(20000d);
		contract.setInterestRate(0.04);
		contract.setQttInstallments(60);

		assertEquals(884.04, this.installmentService.calculateBasicInstallmentBasedOnPrice(contract), 0d);

		contract.setLoanAmount(250000d);
		contract.setInterestRate(0.065);
		contract.setQttInstallments(130);

		assertEquals(16254.52, this.installmentService.calculateBasicInstallmentBasedOnPrice(contract), 0d);

		contract.setLoanAmount(20000d);
		contract.setInterestRate(0.04);
		contract.setQttInstallments(180);

		assertEquals(800.69, this.installmentService.calculateBasicInstallmentBasedOnPrice(contract), 0d);

	}

	@Before
	public void antes() {

	}

	@Test
	public void testCalculateInstallmentFromContract1() {
		this.iniciarCenarioCalculateInstallmentFromContract1();

		assertFalse(this.customerRepository.findAll().isEmpty());
		assertFalse(this.collectorRepository.findAll().isEmpty());
		assertFalse(this.contractRepository.findAll().isEmpty());

		assertEquals(1, this.customerRepository.findAll().size());
		assertEquals(1, this.collectorRepository.findAll().size());
		assertEquals(1, this.contractRepository.findAll().size());

		final Contract contract = this.contractRepository.findAll().get(0);

		final Installment installment = this.installmentService.calculateInstallmentFromContract(contract);
		assertNotNull(installment);
		assertEquals(4, installment.getInstallmentNbr());
		assertEquals(800.0, installment.getInterestIndicated(), 0d);
		assertEquals(4709.80, installment.getCapitalIndicates(), 0d);
		assertEquals(
				Date.from(
						LocalDate.of(2020, Month.MARCH, 16).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
				installment.getInstallmentDateDue());

	}

	private void iniciarCenarioCalculateInstallmentFromContract1() {
		final Customer customer = new Customer();
		customer.setAddress("Rua A");
		customer.setName("Ruan");
		this.customerRepository.save(customer);

		final Collector collector = new Collector();
		collector.setName("Coletor");
		this.collectorRepository.save(collector);

		final Contract contract = new Contract();
		contract.setCollector(collector);
		contract.setCustomer(customer);
		contract.setLoanAmount(20000);
		contract.setInterestRate(0.04);
		contract.setQttInstallments(4);
		contract.setStartDate(Date.from(
				LocalDate.of(2020, Month.FEBRUARY, 14).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		this.contractRepository.save(contract);

	}

	@After
	public void after() {
		this.contractRepository.deleteAll();
		this.collectorRepository.deleteAll();
		this.customerRepository.deleteAll();
	}

}
