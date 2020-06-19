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
public class UpdateTable {
	
	public void updateBalance(String bankName,long balance, String cardno) {
		
		Transaction transaction = null;
		
		String accno = cardno.substring(8,16);
			
		if(bankName.equals("HDFC")) {
			
			HDFCBean bank = new HDFCBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
					transaction = session.beginTransaction();
										
					session.load(bank,accno);
					
					System.out.println("Old balance : "+bank.getBalance());
					
					bank.setBalance(balance);
					
					session.update(bank);
					
					System.out.println("new balance : "+bank.getBalance());
					
					transaction.commit();		
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(bankName.equals("JP MORGAN CHASE")) {
			
			JPMCBean bank = new JPMCBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
					transaction = session.beginTransaction();
										
					session.load(bank,accno);
					
					System.out.println("Old balance : "+bank.getBalance());
					
					bank.setBalance(balance);
					
					session.update(bank);
					
					System.out.println("new balance : "+bank.getBalance());
					
					transaction.commit();		
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(bankName.equals("SBI")) {
			
			SBIBean bank = new SBIBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
					transaction = session.beginTransaction();
										
					session.load(bank,accno);
					
					System.out.println("Old balance : "+bank.getBalance());
					
					bank.setBalance(balance);
					
					session.update(bank);
					
					System.out.println("new balance : "+bank.getBalance());
					
					transaction.commit();		
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(bankName.equals("BOI")) {
			
			BOIBean bank = new BOIBean();
			
			try(Session session = HibernateUtility.getSessionFactory().openSession()){
				
					transaction = session.beginTransaction();
										
					session.load(bank,accno);
					
					System.out.println("Old balance : "+bank.getBalance());
					
					bank.setBalance(balance);
					
					session.update(bank);
					
					System.out.println("new balance : "+bank.getBalance());
					
					transaction.commit();		
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
	
