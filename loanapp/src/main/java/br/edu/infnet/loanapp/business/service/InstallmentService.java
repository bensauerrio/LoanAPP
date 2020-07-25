package br.edu.infnet.loanapp.business.service;

import java.util.Optional;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.loanapp.business.model.Contract;
import br.edu.infnet.loanapp.business.model.Installment;
import br.edu.infnet.loanapp.core.utils.DateUtils;

@Service
public class InstallmentService {

	@Autowired
	private InstallmenteRepository installmentRepository;

	public double calculateBasicInstallmentBasedOnPrice(final Contract contract) {
		if (contract == null) {
			throw new RuntimeException("O contrato está inválido");
		}

		return Precision.round(contract.getLoanAmount() * (//
		contract.getInterestRate() / //
				(1 - Math.pow(1 + contract.getInterestRate(), -contract.getQttInstallments()))), 2);
	}

	public Installment calculateInstallmentFromContract(final Contract contract) {
		if (contract == null) {
			throw new RuntimeException("Para se iniciar o cálculo, informe um contrato válido");
		}
		final Optional<Installment> optInstallment = this.installmentRepository.findLastInstallment(contract.getId());
		if (!optInstallment.isPresent()) {
			final Installment installment = this.getNewInstallmentFromContract(contract);

			return installment;

		}

		return null;
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
