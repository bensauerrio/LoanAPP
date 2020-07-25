package br.edu.infnet.loanapp.business.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.infnet.loanapp.business.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	@Query(value = "select  " //
			+ "	LN_PAYMENT.*  " //
			+ " from LN_PAYMENT " //
			+ " where LN_PAYMENT.installmentId = :installmentId", //
			nativeQuery = true)
	Optional<Payment> findLastPaymentByInstallment(@Param("installmentId") int installmentId);

}
