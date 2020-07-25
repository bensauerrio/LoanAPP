package br.edu.infnet.loanapp.business.service;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.loanapp.business.model.Contract;
import br.edu.infnet.loanapp.business.repository.ContractRepository;

@Service
public class ContractService {

	@Autowired
	private ContractRepository contractRepository;

	public void resgisterPayment(//
			final Contract contract, //
			final double interestedIndicated, //
			final double interestedPaid, //
			final double capitalPaid) {

		contract.setLoanPaymentAmountDue(Precision.round(//
				(contract.getLoanAmount() //
						+ interestedIndicated //
						- interestedPaid //
						- capitalPaid //
				), 2));

		this.contractRepository.save(contract);

	}

}
