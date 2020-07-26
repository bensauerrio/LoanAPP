package br.edu.infnet.loanapp.business.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.edu.infnet.loanapp.business.repository.ContractRepository;
import br.edu.infnet.loanapp.business.service.InstallmenteRepository;
import br.edu.infnet.loanapp.core.dto.PaymentDTO;
import br.edu.infnet.loanapp.core.utils.BeanUtils;

@Entity
@Table(name = "LN_PAYMENT")
public class Payment implements Serializable {

	private static final long serialVersionUID = -3947741510390010910L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(//
			name = "installmentId", //
			referencedColumnName = "id", //
			nullable = false)
	private Installment installment;

	@Column(name = "paymentDate", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date paymentDate;

	@Column(name = "capitalPaid", nullable = true)
	private double capitalPaid;

	@Column(name = "interestPaid", nullable = true)
	private double interestPaid;

	public static Payment fromDTO(final PaymentDTO dto) {

		final Installment installment = new Installment();
		installment.setCapitalIndicates(dto.getCapitalIndicates());
		installment.setInstallmentNbr(dto.getInstallmentNbr());
		installment.setInterestIndicated(dto.getInterestIndicated());
		installment.setContract(getContractRepository().findById(dto.getContractId())
				.orElseThrow(() -> new RuntimeException("Nenhum contrato foi encontrado")));
		installment.setInstallmentDateDue(getDateByString(dto.getInstallmentDateDue()));

		final Payment payment = new Payment();
		payment.setCapitalPaid(dto.getCapitalPaid());
		payment.setInterestPaid(dto.getInterestPaid());
		payment.setPaymentDate(new Date());
		payment.setInstallment(installment);
		return payment;
	}

	private static Date getDateByString(final String stringDate) {
		final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return dateFormat.parse(stringDate);
		} catch (final Exception e) {
			return new Date();
		}
	}

	private static InstallmenteRepository getInstallmenteRepository() {
		return BeanUtils.getBean(InstallmenteRepository.class);
	}

	private static ContractRepository getContractRepository() {
		return BeanUtils.getBean(ContractRepository.class);
	}

	public Payment() {
		super();
	}

	public Payment(//
			final int id, //
			final Installment installment, //
			final Date paymentDate, //
			final double capitalPaid, //
			final double interestPaid) {
		super();
		this.id = id;
		this.installment = installment;
		this.paymentDate = paymentDate;
		this.capitalPaid = capitalPaid;
		this.interestPaid = interestPaid;
	}

	public static Payment newPayment(final Installment lastInstallment) {
		final Payment payment = new Payment();
		payment.setCapitalPaid(lastInstallment.getCapitalIndicates());
		payment.setInstallment(lastInstallment);
		payment.setInterestPaid(lastInstallment.getInterestIndicated());
		payment.setPaymentDate(lastInstallment.getInstallmentDateDue());
		return payment;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public Installment getInstallment() {
		return this.installment;
	}

	public void setInstallment(final Installment installment) {
		this.installment = installment;
	}

	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(final Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getCapitalPaid() {
		return this.capitalPaid;
	}

	public void setCapitalPaid(final double capitalPaid) {
		this.capitalPaid = capitalPaid;
	}

	public double getInterestPaid() {
		return this.interestPaid;
	}

	public void setInterestPaid(final double interestPaid) {
		this.interestPaid = interestPaid;
	}

	@Override
	public String toString() {
		return String.format("Payment [id=%s, installment=%s, paymentDate=%s, capitalPaid=%s, interestPaid=%s]",
				this.id, this.installment, this.paymentDate, this.capitalPaid, this.interestPaid);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.capitalPaid);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + this.id;
		result = prime * result + ((this.installment == null) ? 0 : this.installment.hashCode());
		temp = Double.doubleToLongBits(this.interestPaid);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((this.paymentDate == null) ? 0 : this.paymentDate.hashCode());
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
		final Payment other = (Payment) obj;
		if (Double.doubleToLongBits(this.capitalPaid) != Double.doubleToLongBits(other.capitalPaid))
			return false;
		if (this.id != other.id)
			return false;
		if (this.installment == null) {
			if (other.installment != null)
				return false;
		} else if (!this.installment.equals(other.installment))
			return false;
		if (Double.doubleToLongBits(this.interestPaid) != Double.doubleToLongBits(other.interestPaid))
			return false;
		if (this.paymentDate == null) {
			if (other.paymentDate != null)
				return false;
		} else if (!this.paymentDate.equals(other.paymentDate))
			return false;
		return true;
	}

	@Transient
	public boolean isLastPayment() {
		return 1 == this.getInstallment().getInstallmentNbr();
	}

}
