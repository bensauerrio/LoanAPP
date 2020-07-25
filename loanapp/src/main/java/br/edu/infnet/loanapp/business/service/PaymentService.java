package br.edu.infnet.loanapp.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.loanapp.business.model.Contract;
import br.edu.infnet.loanapp.business.model.Installment;
import br.edu.infnet.loanapp.business.model.Payment;
import br.edu.infnet.loanapp.business.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private InstallmentService installmentService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ContractService contractService;

	public Payment getBasicPaymentBasedOnInstallment(final Contract contract) {
		final Installment lastInstallment = this.installmentService.calculateInstallmentFromContract(contract);

		if (this.installmentService == null) {
			throw new RuntimeException("Nenhuma parcela foi encontrada");
		}

		return lastInstallment.getInstallmentNbr() == 0 ? null : Payment.newPayment(lastInstallment);
	}

	public void registerPayment(final Payment payment) {
		if (payment.isLastPayment() //
				&& (payment.getCapitalPaid() == payment.getInstallment().getCapitalIndicates() //
						|| payment.getInterestPaid() == payment.getInstallment().getInterestIndicated())) {
			throw new RuntimeException("Os valores dá ultima parcela devem ser os valores indicados.");
		}

		final Contract contract = payment.getInstallment().getContract();
		this.contractService.resgisterPayment(//
				contract, //
				payment.getInstallment().getInterestIndicated(), //
				payment.getInterestPaid(), //
				payment.getCapitalPaid());

		this.installmentService.registerInstallment(payment.getInstallment());

		this.paymentRepository.saveAndFlush(payment);
	}

}
