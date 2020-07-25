package br.edu.infnet.loanapp.core.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ContractDTO implements Serializable {

	private static final long serialVersionUID = -7991496075304948709L;

	
	private int customerId;
	
	private int collectorId;
	
	private Date startDate;

	private double interestRate;

	private double loanAmount;

	private double loanPaymentAmountDue;

	private int qttInstallments;
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(int collectorId) {
		this.collectorId = collectorId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getLoanPaymentAmountDue() {
		return loanPaymentAmountDue;
	}

	public void setLoanPaymentAmountDue(double loanPaymentAmountDue) {
		this.loanPaymentAmountDue = loanPaymentAmountDue;
	}

	public int getQttInstallments() {
		return qttInstallments;
	}

	public void setQttInstallments(int qttInstallments) {
		this.qttInstallments = qttInstallments;
	}
}
