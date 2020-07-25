package br.edu.infnet.loanapp.core.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class PaymentDTO implements Serializable {

	private static final long serialVersionUID = -7991496075304948709L;

	private int installmentId;
	
	private Date paymentDate;
	
	private double capitalPaid;
	
	private double interestPaid;

	public int getInstallmentId() {
		return installmentId;
	}

	public void setInstallmentId(int installmentId) {
		this.installmentId = installmentId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getCapitalPaid() {
		return capitalPaid;
	}

	public void setCapitalPaid(double capitalPaid) {
		this.capitalPaid = capitalPaid;
	}

	public double getInterestPaid() {
		return interestPaid;
	}

	public void setInterestPaid(double interestPaid) {
		this.interestPaid = interestPaid;
	}
	
	

}
