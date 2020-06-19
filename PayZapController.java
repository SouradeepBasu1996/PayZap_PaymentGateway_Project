package com.payzap;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.payzap.beans.CardDetailsBean;
import com.payzap.beans.HDFCBean;
import com.payzap.beans.IssuingBankBean;
import com.payzap.beans.MerchantBean;
import com.payzap.utility.HibernateUtility;
import com.payzap.validateandauthenticate.AuthoriseCard;
import com.payzap.validateandauthenticate.CheckNetwork;
import com.payzap.validateandauthenticate.UpdateTable;
import com.payzap.validateandauthenticate.Validation;

@Controller
public class PayZapController extends ClassLoader{
	
	Transaction transaction = null;
	
	@Autowired
	Validation validate;
	
	@Autowired
	CheckNetwork checkNetwork;
	
	@Autowired
	AuthoriseCard authoriseCard;
	
	@Autowired
	UpdateTable updateTable;
	
	@RequestMapping(value="payzap")
	public String home() {
		
		return "payzapHome";
	}
	
	@RequestMapping(value="merchantPage")
	public String merchantToNetwork(@RequestParam(name="cardno") String cardno,
							@RequestParam(name="nameOnCard") String name,
							@RequestParam(name="validity") String validity,
							@RequestParam(name="cvv") String cvv,
							ModelMap model) {
		String page ="";
		/*//System.out.println("Str Test : "+name);
		CardDetailsBean cardBean = new CardDetailsBean();
		cardBean.setCardNumber(cardno);
		cardBean.setNameOnCard(name);
		cardBean.setValidity(validity);
		cardBean.setCvv(cvv);*/
		
		String indicator =validate.networkValidate(cardno);
		
		System.out.println("Str Test : "+name);
		System.out.println("INDICATOR = "+indicator);
		if(indicator == "") {
			page = "errorPage";
		}
		else {		
				model.put("cardno", cardno);
				model.put("name", name);
				model.put("validity", validity);
				model.put("cvv", cvv);
								
				model.put("network", indicator);
				
				System.out.println("End of Trans");
			}
		System.out.println("Before return...");
		return "networkPage";
		
	}
	
	@RequestMapping(value="customerBank")
	public String issuingBankServer(@RequestParam(name="cardno") String cardno,
									@RequestParam(name="name") String name,
									@RequestParam(name="validity") String validity,
									@RequestParam(name="cvv") String cvv,
									@RequestParam(name="networkName") String networkName,
									ModelMap model) {
		
		String bankName = validate.bankValidate(cardno);
		
		System.out.println("card holder : "+name);
			
		model.put("cardno", cardno);
		model.put("name", name);
		model.put("validity", validity);
		model.put("cvv", cvv);
		model.put("bankName",bankName);
		model.put("networkName",networkName);
		
		return "customerBankPage";
	}
	
	@RequestMapping(value="analyse")
	public String analysing(@RequestParam(name="cardno") String cardno,
							@RequestParam(name="name") String name,
							@RequestParam(name="validity") String validity,
							@RequestParam(name="cvv") String cvv,
							@RequestParam(name="networkName") String networkName,
							@RequestParam(name="bankName") String bankName,
							ModelMap model) {
		
		System.out.println("Data Reached For Analysis ");
		
		System.out.println("Name : "+name);
		
		boolean validateAccount = false;
		String page = "";
		
		validateAccount = validate.findAccount(cardno, validity, networkName, bankName, name, cvv);
		
		if(validateAccount == true) {
			System.out.println("Inside if of controller Analyse");
			model.put("cardno",cardno);
			model.put("bankName",bankName);
			page = "amountDetails";
		}
		else {
			System.out.println("Inside else of controller Analyse");
			page = "errorPage";
		}
		
		return page;
		
	}
	
	@RequestMapping(value="pinDetails")
	public String pinDetailPage(@RequestParam(name="cardno") String cardno,
								@RequestParam(name="amount") String amount,
								@RequestParam(name="bankName") String bankName,
								ModelMap model) {
		model.put("cardno",cardno);
		model.put("amount",amount);
		model.put("bankName",bankName);
		
		System.out.println("Inside pin details ");
		
		return "pinPage";
	}
	
	@RequestMapping(value="calculate")
	public String calculateAm(@RequestParam(name="cardno") String cardno,
							@RequestParam(name="amount") String amount,
							@RequestParam(name="pin") String pin,
							@RequestParam(name="bankName") String bankName) {
		
		System.out.println("Into calculate");
		
		boolean authorised = false;
		long checkBalance = 0L;
		String returnPage = "";
		
		authorised = authoriseCard.checkPin(pin, cardno, bankName);
		
		if(authorised == true) {
			checkBalance = authoriseCard.balance(cardno, bankName, amount);
			if(checkBalance == 0) {
				returnPage = "paymentFailed";
			}
			else {
				updateTable.updateBalance(bankName, checkBalance, cardno);
				returnPage = "paymentSuccessful";
			}
			
		}
		
		return returnPage;
		
	}
	
	/*@RequestMapping(value="display")
	public String displayDeails(@RequestParam(name="cardno") String cardno,
								ModelMap model) {
		model.put("cardno", cardno);
		return "displayPage";
	}*/
}
