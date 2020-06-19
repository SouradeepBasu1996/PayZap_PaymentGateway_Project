package com.payzap.validateandauthenticate;

import org.springframework.stereotype.Component;

@Component
public class CheckNetwork {
	
	public String networkName(String merchantNetwork) {
		
		String page = "";
		if(merchantNetwork.equals("VISA") == true) {
			page = "VISA";
		}
		else if(merchantNetwork.equals("MASTER CARD") == true) {
			page = "MASTER CARD";
		}
		else if(merchantNetwork.equals("AMERICAN EXPRESS") == true) {
			page = "AMERICAN EXPRESS";
		}
		else if(merchantNetwork.equals("DISCOVER") == true) {
			page = "DISCOVER";
		}
		else {
			page = "errorPage";
		}
		
		return page;
	}
	
}
