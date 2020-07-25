package br.edu.infnet.loanapp.business.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.infnet.loanapp.business.model.Installment;

@Repository
public interface InstallmenteRepository extends JpaRepository<Installment, Integer> {

	@Query(value = "select " + //
			"	* " + //
			"from LN_INSTALLMENT " + //
			"where contractid = :contractid " + //
			"	and exists ( " + //
			"		select " + //
			"			'*' " + //
			"		from LN_INSTALLMENT inInstallment " + //
			"		where inInstallment.contractid = LN_INSTALLMENT.contractid " + //
			"		having max(inInstallment.id) = LN_INSTALLMENT.id " + //
			"	)", //
			nativeQuery = true)
	Optional<Installment> findLastInstallment(@Param("contractid") Integer contractId);

}
