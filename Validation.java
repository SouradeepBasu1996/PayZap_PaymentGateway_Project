package com.payzap.validateandauthenticate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.payzap.beans.BOIBean;
import com.payzap.beans.HDFCBean;
import com.payzap.beans.IssuingBankBean;
import com.payzap.beans.JPMCBean;
import com.payzap.beans.MerchantBean;
import com.payzap.beans.SBIBean;
import com.payzap.utility.HibernateUtility;

@Component
public class Validation {
	
	Transaction transaction = null;
	
	public String networkValidate(String cardno) {
				
		boolean numFormat = false;
		String subStr = "";
		String ntwrkNm = null;
		int i = 1;
		
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(MerchantBean.class);
			
			List network = criteria.list();
			
			Iterator itr = network.iterator();
			
			while(itr.hasNext()) {
				
				MerchantBean merchant = (MerchantBean)itr.next();
				
				System.out.println("network Name : "+merchant.getCardNumberFormat());
				subStr = cardno.substring(0,merchant.getCardNumberFormat().length());
				System.out.println("index = "+subStr);
				
				if(subStr.equals(merchant.getCardNumberFormat())) {
					ntwrkNm = merchant.getNetworkName();
					
					System.out.println(" network name ::: "+ntwrkNm);
				}
				
			}
			
			transaction.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		
		return ntwrkNm;
	}
	
	public String bankValidate(String cardno) {
		int index = 0;
		String bankName = "";
		String subStr = "";
		try(Session session = HibernateUtility.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(IssuingBankBean.class);
			
			List bankList = criteria.list();
			
			Iterator itr = bankList.iterator();
			
			while(itr.hasNext()) {
				
				IssuingBankBean bank = (IssuingBankBean)itr.next();
				
				System.out.println(" Bank ID :- "+bank.getBankID());
				System.out.println(" Bank Name :- "+bank.getBankName());
				
				subStr = cardno.substring(4,8);
				if(subStr.equals(bank.getBankID())){
					bankName = bank.getBankName();
					
					System.out.println("bank name :::: "+bankName);
				}
				
			}
			transaction.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bankName;
	}
	
	public boolean findAccount(String cardno,String validity,String networkName,String bankName, String name, String cvv){
		
		boolean returnStatement = false;
		boolean dateValid = false;
		String accNo = "";
		
		accNo = cardno.substring(8, 16);
		System.out.println("Account Number :   "+accNo);
		System.out.println("Name #### "+name);
		System.out.println("Bank Name : "+bankName);
		
		if(bankName.equals("HDFC")) {
			
			HDFCBean bank = new HDFCBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
				transaction = session.beginTransaction();
				
				session.load(bank, accNo);
				
				dateValid = validateDate(validity,bank.getValidity());
				
				if(cardno.equals(bank.getCardNo()) && name.equals(bank.getName()) && cvv.equals(bank.getCvv()) && dateValid == true) {
					 System.out.println("Inside if of Find Account");
					returnStatement = true;
				}
				else {
					System.out.println("Inside else of Find Account");
					returnStatement = false;
				}
				
				System.out.println("Name : "+bank.getName());
				
				transaction.commit();	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(bankName.equals("JP MORGAN CHASE")) {
			
			JPMCBean bank = new JPMCBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
				transaction = session.beginTransaction();
				
				session.load(bank, accNo);
				
				dateValid = validateDate(validity,bank.getValidity());
				
				if(cardno.equals(bank.getCardNo()) && name.equals(bank.getName()) && cvv.equals(bank.getCvv()) && dateValid == true) {
					 
					returnStatement = true;
				}
				else {
					returnStatement = false;
				}
				
				System.out.println("Name : "+bank.getName());
				
				transaction.commit();	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(bankName.equals("BOI")) {
			
			BOIBean bank = new BOIBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
				transaction = session.beginTransaction();
				
				session.load(bank, accNo);
				
				dateValid = validateDate(validity,bank.getValidity());
				
				if(cardno.equals(bank.getCardNo()) && name.equals(bank.getName()) && cvv.equals(bank.getCvv()) && dateValid == true) {
					 
					returnStatement = true;
				}
				else {
					returnStatement = false;
				}
				
				System.out.println("Name : "+bank.getName());
				
				transaction.commit();	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(bankName.equals("SBI")) {
			
			SBIBean bank = new SBIBean();
	
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
		
				transaction = session.beginTransaction();
		
				session.load(bank, accNo);
				
				dateValid = validateDate(validity,bank.getValidity());
				
				if(cardno.equals(bank.getCardNo()) && name.equals(bank.getName()) && cvv.equals(bank.getCvv()) && dateValid == true) {
					 
					returnStatement = true;
				}
				else {
					returnStatement = false;
				}
		
				
		System.out.println("Name : "+bank.getName());
		
		transaction.commit();	
	}catch(Exception e){
		e.printStackTrace();
	}
}

		return returnStatement;
	}
	
	public boolean validateDate(String validity,String dbDate) throws Exception{
		
		String valDate = validity;
		String day = "";
		String actualValidity = "";
		
		boolean returnValue = false;
		
		String[] month = valDate.split("/");
		String month0 = month[0];
		
		System.out.println("valDate : "+valDate);
		System.out.println("month : "+month0);
		
		
		if(month0.equals("04") || month0.equals("06") || month0.equals("09") || month0.equals("11")) {
			day = "30/";
		}
		else if(month0.equals("01") || month0.equals("03") || month0.equals("05") || month0.equals("07") || month0.equals("08") || month0.equals("10") || month0.equals("12")) {
			day = "31/";
		}
		else if(month0.equals("02")){
			day = "28/";
		}
		else {
			day = "";
		}
		System.out.println("day : "+day);
		
		actualValidity = day+valDate;
		System.out.println(" Actual validity :"+actualValidity);
		
		Date accValidity = new SimpleDateFormat("dd/MM/yy").parse(actualValidity);
		Date dbValidity = new SimpleDateFormat("dd/MM/yy").parse(dbDate);
		Date nowDate = Calendar.getInstance().getTime();
		
		if(accValidity.compareTo(dbValidity) == 0 && accValidity.compareTo(nowDate)>0) {
			System.out.println("Valid");
			returnValue = true;
		}
		else {
			returnValue =false;
		}
		
		return returnValue;
	}

}
