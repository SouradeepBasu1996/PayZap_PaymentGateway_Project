package com.payzap.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="merchantdata")
public class MerchantBean {
	
	@Id
	@Column
	private String cardNumberFormat;
	@Column
	private String networkName;
	
	
	public String getCardNumberFormat() {
		return cardNumberFormat;
	}
	public void setCardNumberFormat(String cardNumberFormat) {
		this.cardNumberFormat = cardNumberFormat;
	}
	public String getNetworkName() {
		return networkName;
	}
	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}
	@Override
	public String toString() {
		return "MerchantBean [cardNumberFormat=" + cardNumberFormat + ", networkName=" + networkName + "]";
	} 
	
	
	
	

}
