drop database if exists LoanAmountApp;
create database LoanAmountApp;
use LoanAmountApp;

create table LN_CUSTOMER(
    id int primary key AUTO_INCREMENT,
    name varchar(255) not null,
    address varchar(255) not null
);

create table LN_COLLECTOR(
    id int primary key AUTO_INCREMENT,
    name varchar(255) not null
);

create table LN_CONTRACT(
    id int primary key AUTO_INCREMENT,
    customerId int not null,
    collectorId int not null,
    startDate date not null,
    interestRate DECIMAL(13,2) not null,
    loanAmount DECIMAL(13,2) not null,
    loanPmentAmountDue DECIMAL(13,2) not null,
    qttInstallments int not null,
    FOREIGN KEY (customerId) references LN_CUSTOMER(id),
    FOREIGN KEY (collectorId) references LN_COLLECTOR(id)
);

create table LN_INSTALLMENT(
	id int primary key AUTO_INCREMENT,
	contractId int not null,
	interestInd DECIMAL(13,2) null,
	capitalInd DECIMAL(13,2) null,
	installmentNbr int not null,
	installmentDateDue date not null,
	FOREIGN KEY (contractId) references LN_CONTRACT(id)
);

create table LN_PAYMENT(
    id int primary key AUTO_INCREMENT,
    installmentId int not null,
    paymentDate date not null,
    capitalPaid DECIMAL(13,2) null,
    interestPaid DECIMAL(13,2) null,
    FOREIGN KEY (installmentId) references LN_INSTALLMENT(id)
);