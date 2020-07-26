package br.edu.infnet.loanapp.business.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.infnet.loanapp.business.repository.CollectorRepository;
import br.edu.infnet.loanapp.business.repository.CustomerRepository;
import br.edu.infnet.loanapp.core.dto.ContractDTO;
import br.edu.infnet.loanapp.core.utils.BeanUtils;

@Entity
@Table(name = "LN_CONTRACT")
public class Contract implements Serializable {

	private static final long serialVersionUID = -1109907034863549271L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(//
			name = "customerId", //
			referencedColumnName = "id", //
			nullable = false)
	private Customer customer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(//
			name = "collectorId", //
			referencedColumnName = "id", //
			nullable = false)
	private Collector collector;

	@Column(name = "startDate", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name = "interestRate", nullable = false)
	private double interestRate;

	@Column(name = "loanAmount", nullable = false)
	private double loanAmount;

	@Column(name = "loanPmentAmountDue", nullable = false)
	private double loanPaymentAmountDue;

	@Column(name = "qttInstallments", nullable = false)
	private int qttInstallments;

	public static Contract fromDTO(final ContractDTO dto) {
		final Contract contract = new Contract();
		contract.setCustomer(getCustomerRepository().findById(dto.getCustomerId())
				.orElseThrow(() -> new RuntimeException("O cliente não foi encontrado!")));
		contract.setCollector(getCollectorRepository().findById(dto.getCollectorId())
				.orElseThrow(() -> new RuntimeException("O coletor não foi encontrado!")));
		contract.setInterestRate(dto.getInterestRate());
		contract.setLoanAmount(dto.getLoanAmount());
		contract.setLoanPaymentAmountDue(dto.getLoanPaymentAmountDue());
		contract.setQttInstallments(dto.getQttInstallments());
		return contract;
	}

	public Contract() {
		this.startDate = new Date();
	}

	private static CustomerRepository getCustomerRepository() {
		return BeanUtils.getBean(CustomerRepository.class);
	}

	private static CollectorRepository getCollectorRepository() {
		return BeanUtils.getBean(CollectorRepository.class);
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public double getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(final double interestRate) {
		this.interestRate = interestRate;
	}

	public double getLoanAmount() {
		return this.loanAmount;
	}

	public void setLoanAmount(final double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getLoanPaymentAmountDue() {
		return this.loanPaymentAmountDue;
	}

	public void setLoanPaymentAmountDue(final double loanPaymentAmountDue) {
		this.loanPaymentAmountDue = loanPaymentAmountDue;
	}

	public int getQttInstallments() {
		return this.qttInstallments;
	}

	public void setQttInstallments(final int qttInstallments) {
		this.qttInstallments = qttInstallments;
	}

	public Collector getCollector() {
		return this.collector;
	}

	public void setCollector(final Collector collector) {
		this.collector = collector;
	}

	@Override
	public String toString() {
		return String.format(
				"Contract [id=%s, customer=%s, collector=%s, startDate=%s, interestRate=%s, loanAmount=%s, loanPaymentAmountDue=%s, qttInstallments=%s]",
				this.id, this.customer, this.collector, this.startDate, this.interestRate, this.loanAmount,
				this.loanPaymentAmountDue, this.qttInstallments);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.collector == null) ? 0 : this.collector.hashCode());
		result = prime * result + ((this.customer == null) ? 0 : this.customer.hashCode());
		result = prime * result + this.id;
		result = prime * result + this.qttInstallments;
		long temp;
		temp = Double.doubleToLongBits(this.interestRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.loanAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.loanPaymentAmountDue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((this.startDate == null) ? 0 : this.startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final Contract other = (Contract) obj;
		if (this.collector == null) {
			if (other.collector != null)
				return false;
		} else if (!this.collector.equals(other.collector)) {
			return false;
		}
		if (this.customer == null) {
			if (other.customer != null) {
				return false;
			}
		} else if (!this.customer.equals(other.customer)) {
			return false;
		}
		if (this.id != other.id) {
			return false;
		}
		if (Double.doubleToLongBits(this.interestRate) != Double.doubleToLongBits(other.interestRate)) {
			return false;
		}
		if (Double.doubleToLongBits(this.loanAmount) != Double.doubleToLongBits(other.loanAmount)) {
			return false;
		}
		if (this.qttInstallments != other.qttInstallments) {
			return false;
		}
		if (Double.doubleToLongBits(this.loanPaymentAmountDue) != Double.doubleToLongBits(other.loanPaymentAmountDue))
			return false;
		if (this.startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!this.startDate.equals(other.startDate)) {
			return false;
		}
		return true;
	}

}
