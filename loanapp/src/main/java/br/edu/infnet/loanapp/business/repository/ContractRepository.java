package br.edu.infnet.loanapp.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.CollectionUtils;
import org.springframework.data.jpa.repository.Query;

import br.edu.infnet.loanapp.business.model.Contract;
import br.edu.infnet.loanapp.business.model.Customer;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

	static List<Contract> findAllByAttribute(final Customer customer) {
		final List<Contract> contracts = null;
		// TODO

		return contracts;

	}

	@Query(value = "select  " //
			+ "	LN_CONTRACT.*  " //
			+ "from LN_CONTRACT " //
			+ "where customerId = ?1", //
			nativeQuery = true)
	List<Contract> findAllByClientId(int id);

}
