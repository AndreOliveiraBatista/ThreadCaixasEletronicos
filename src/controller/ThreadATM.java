package controller;

import java.util.concurrent.Semaphore;

public class ThreadATM extends Thread{
	
	private int idOperacao;
	private int conta;
	private int saldo;
	private int transacao;
	private Semaphore mutex;
	
	public ThreadATM(int idOperacao, int conta, int saldo, int transacao, Semaphore  mutex) {
		this.idOperacao = idOperacao;
		this.conta = conta;
		this.saldo = saldo;
		this.transacao = transacao;
		this.mutex = mutex;
	}
	
	@Override
	public void run() {
		
		try {
			if(idOperacao == 0) {
				mutex.acquire();//se a operação for saque, apenas 1 permissão por vez
				saque(conta, saldo, transacao);
			}else {
				mutex.acquire();//se a operação for depósito, apenas 1 permissão por vez 
				depósito(conta, saldo, transacao);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			mutex.release();
		}
	}
	
	private void saque(int conta, int saldo, int transacao) {
		System.out.println("-> Conta: "+conta+"\n Saque: "+transacao+"\n Saldo: "+saldo);
		saldo -= transacao;
		System.out.println("-> Saldo final da conta #"+conta+" é "+saldo);
		
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void depósito(int conta, int saldo, int transacao) {
		System.out.println("-> Conta: "+conta+"\n Depósito: "+transacao+"\n Saldo: "+saldo);
		saldo += transacao;
		System.out.println("-> Saldo final da conta #"+conta+" é "+saldo);
		
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
