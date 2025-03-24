package view;

import java.util.concurrent.Semaphore;

import controller.ThreadATM;

public class Operacoes {
	public static int conta = 0;
	public static void main(String[] args) {
		
		Semaphore mutex = new Semaphore(1);
		
		for(int i = 0; i < 20; i++) {
			conta++;//sempre uma conta diferente/nova
			int idOperacao = (int)(Math.random()*2);
			int saldo = (int)(Math.random() * 1001);//saldo inicial de R$0 a R$1000
			int transacao = (int)((Math.random()*1000)+1);//valor da transação
				Thread t = new ThreadATM(idOperacao, conta, saldo, transacao, mutex);
				t.start();
		}
	}
}
