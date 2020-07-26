package br.edu.infnet.loanapp.core.dto;

import java.io.Serializable;
import java.util.Date;

public class PaymentDTO implements Serializable {

	private static final long serialVersionUID = -7991496075304948709L;

	private int installmentId;

	private Date paymentDate;

	private double capitalPaid;

	private double interestPaid;

	private double interestIndicated;

	private double capitalIndicates;

	private String installmentDateDue;

	private int installmentNbr;

	private int contractId;

	public int getInstallmentId() {
		return this.installmentId;
	}

	public void setInstallmentId(final int installmentId) {
		this.installmentId = installmentId;
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

	public double getInterestIndicated() {
		return this.interestIndicated;
	}

	public void setInterestIndicated(final double interestIndicated) {
		this.interestIndicated = interestIndicated;
	}

	public double getCapitalIndicates() {
		return this.capitalIndicates;
	}

	public void setCapitalIndicates(final double capitalIndicates) {
		this.capitalIndicates = capitalIndicates;
	}

	public String getInstallmentDateDue() {
		return this.installmentDateDue;
	}

	public void setInstallmentDateDue(final String installmentDateDue) {
		this.installmentDateDue = installmentDateDue;
	}

	public int getInstallmentNbr() {
		return this.installmentNbr;
	}

	public void setInstallmentNbr(final int installmentNbr) {
		this.installmentNbr = installmentNbr;
	}

	public int getContractId() {
		return this.contractId;
	}

	public void setContractId(final int contractId) {
		this.contractId = contractId;
	}

}
