package com.payzap.validateandauthenticate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.payzap.beans.BOIBean;
import com.payzap.beans.HDFCBean;
import com.payzap.beans.JPMCBean;
import com.payzap.beans.SBIBean;
import com.payzap.utility.HibernateUtility;

@Component
public class AuthoriseCard {
	
	public boolean checkPin(String pin, String cardno, String bankName) {
		
		System.out.println("Reached Authorization : "+bankName);
		
		Transaction transaction = null;
		
		boolean returnStatement =false;
		
		String accno = cardno.substring(8,16);
		
		if(bankName.equals("HDFC")) {
			System.out.println("Hit HDFC");
		
			HDFCBean bank =new HDFCBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
				transaction = session.beginTransaction();
				
				session.load(bank,accno);
				
				if(pin.equals(bank.getPin())) {
					returnStatement = true;
					System.out.println("PIN Valid");
				}
				else {
					returnStatement = false;
				}
				transaction.commit();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if(bankName.equals("JP MORGAN CHASE")) {
			
			JPMCBean bank =new JPMCBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
				transaction = session.beginTransaction();
				
				session.load(bank,accno);
				
				if(pin.equals(bank.getPin())) {
					returnStatement = true;
				}
				else {
					returnStatement = false;
				}
				transaction.commit();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}		
		
		if(bankName.equals("SBI")) {
			
			SBIBean bank =new SBIBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
				transaction = session.beginTransaction();
				
				session.load(bank,accno);
				
				if(pin.equals(bank.getPin())) {
					returnStatement = true;
				}
				else {
					returnStatement = false;
				}
				transaction.commit();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if(bankName.equals("BOI")) {
			
			BOIBean bank =new BOIBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
				transaction = session.beginTransaction();
				
				session.load(bank,accno);
				
				if(pin.equals(bank.getPin())) {
					returnStatement = true;
				}
				else {
					returnStatement = false;
				}
				transaction.commit();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return returnStatement;
	}
	
	public long balance(String cardno, String bankName, String amount) {
		
		Transaction transaction = null;
		
		long returnStatement = 0l;
		
		String accno = cardno.substring(8,16);
		
		int amt = Integer.parseInt(amount);
		
		long fbal=0l;
		
		System.out.println("Amount Print "+amt);
		
		if(bankName.equals("HDFC")){
			HDFCBean bank = new HDFCBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
				transaction = session.beginTransaction();
				
				session.load(bank,accno);
				
				if(bank.getBalance() >= amt) {
					System.out.println("Balance Valid ");
					fbal = bank.getBalance()-amt;
					returnStatement = fbal;
				}
				else {
					returnStatement = 0;
				}
			}
		}
		
		if(bankName.equals("JP MORGAN CHASE")){
			JPMCBean bank = new JPMCBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
				transaction = session.beginTransaction();
				
				session.load(bank,accno);
				
				if(bank.getBalance() >= amt) {
					System.out.println("Balance Valid ");
					fbal = bank.getBalance()-amt;
					returnStatement = fbal;
				}
				else {
					returnStatement = 0;
				}
			}
		}
		
		if(bankName.equals("SBI")){
			SBIBean bank = new SBIBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
				transaction = session.beginTransaction();
				
				session.load(bank,accno);
				
				if(bank.getBalance() >= amt) {
					System.out.println("Balance Valid ");
					fbal = bank.getBalance()-amt;
					returnStatement = fbal;
				}
				else {
					returnStatement = 0;
				}
			}
		}
		
		if(bankName.equals("BOI")){
			BOIBean bank = new BOIBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
				transaction = session.beginTransaction();
				
				session.load(bank,accno);
				
				if(bank.getBalance() >= amt) {
					System.out.println("Balance Valid ");
					fbal = bank.getBalance()-amt;
					returnStatement = fbal;
				}
				else {
					returnStatement = 0;
				}
			}
		}
		return returnStatement;
		
	}
	
}
