package com.payzap.beans;

public class CardDetailsBean {
	
	private String cardNumber;
	private String nameOnCard;
	private String validity;
	private String cvv;
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	@Override
	public String toString() {
		return "CardDetailsBean [cardNumber=" + cardNumber + ", nameOnCard=" + nameOnCard + ", validity=" + validity
				+ ", cvv=" + cvv + "]";
	}
	
	

}
