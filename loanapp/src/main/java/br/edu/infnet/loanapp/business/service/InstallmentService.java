package br.edu.infnet.loanapp.business.service;

import java.util.Optional;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.loanapp.business.model.Contract;
import br.edu.infnet.loanapp.business.model.Installment;
import br.edu.infnet.loanapp.business.repository.PaymentRepository;
import br.edu.infnet.loanapp.core.utils.DateUtils;

@Service
public class InstallmentService {

	@Autowired
	private InstallmenteRepository installmentRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	public double calculateBasicInstallmentBasedOnPrice(final Contract contract) {
		if (contract == null) {
			throw new RuntimeException("O contrato está inválido");
		}

		return this.getBasicInstallment(contract.getLoanAmount(), contract.getInterestRate(),
				contract.getQttInstallments());
	}

	private double getBasicInstallment(final double loanAmount, final double interestRate,
			final int installmentsQuantity) {
		return Precision.round(loanAmount * (//
		interestRate / //
				(1 - Math.pow(1 + interestRate, -installmentsQuantity))), 2);
	}

	public Installment calculateInstallmentFromContract(final Contract contract) {
		if (contract == null) {
			throw new RuntimeException("Para se iniciar o cálculo, informe um contrato válido");
		}
		final Optional<Installment> optInstallment = this.installmentRepository.findLastInstallment(contract.getId());
		Installment installment = null;
		if (!optInstallment.isPresent()) {
			installment = this.getNewInstallmentFromContract(contract);
		} else {
			final Optional<Contract> optContract = this.paymentRepository.findPaymentByInstallment(installment.getId());
		}

		return installment;
	}

	private Installment getNewInstallmentFromContract(final Contract contract) {
		final double basicInstallmentRate = this.calculateBasicInstallmentBasedOnPrice(contract);
		final Installment installment = new Installment();
		installment.setContract(contract);
		installment.setInterestIndicated(//
				Precision.round(//
						(contract.getLoanAmount() * contract.getInterestRate()), //
						2)//
		);

		installment.setCapitalIndicates(Precision.round(//
				(basicInstallmentRate - installment.getInterestIndicated()), //
				2)//
		);

		installment.setInstallmentDateDue(DateUtils.addMonth(contract.getStartDate(), 1));
		installment.setInstallmentNbr(contract.getQttInstallments());
		this.installmentRepository.save(installment);
		return installment;
	}

}
