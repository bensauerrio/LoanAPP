package br.edu.infnet.loanapp.business.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.infnet.loanapp.business.model.Contract;
import br.edu.infnet.loanapp.business.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	@Query(value = "", nativeQuery = true)
	Optional<Contract> findPaymentByInstallment(int id);

}
