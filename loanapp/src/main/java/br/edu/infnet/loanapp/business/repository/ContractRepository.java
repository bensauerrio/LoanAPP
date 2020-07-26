package br.edu.infnet.loanapp.business.repository;

import java.util.List;

import org.hibernate.validator.internal.util.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.loanapp.business.model.Contract;
import br.edu.infnet.loanapp.business.model.Customer;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

	public static List<Contract> findAllByAttribute(Customer customer ) {
		List<Contract> contracts = null;
		//TODO
		
		return contracts;
		
	}
	
}
