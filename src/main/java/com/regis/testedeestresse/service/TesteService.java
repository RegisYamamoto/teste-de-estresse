package com.regis.testedeestresse.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.regis.testedeestresse.util.RestUtil;

@Service
public class TesteService {

	public void teste() throws InterruptedException {
		AtomicInteger sucesso = new AtomicInteger();
		AtomicInteger falha = new AtomicInteger();
		
		System.out.println("---------- Iniciou o teste ----------");
		long inicio = System.currentTimeMillis();
		
		// Quantidade de requisições simultaneas
		int simultaneas = 2000;
		System.out.println("Teste com " + simultaneas + " requisições simultâneas");
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(simultaneas);

		for (int i = 0; i < simultaneas; i++) {
			int contador = i;
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					RestUtil restUtil = new RestUtil();
					try {
						HttpEntity<String> resultado = restUtil.testeGet();
						sucesso.addAndGet(1);
						// System.out.println("sucesso"); // Teste

					} catch (Exception e) {
						falha.addAndGet(1);
						// System.out.println("falha"); // Teste
					}
					// System.out.println("thread " + contador); // Teste
					
					long fim = System.currentTimeMillis();
					long tempo = fim - inicio;
				}
			});

		}

		executor.shutdown();
		executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		long fim = System.currentTimeMillis();
		long tempo = fim - inicio;
		
		System.out.println("sucesso: " + sucesso);
		System.out.println("falha: " + falha);
		double tempoSegundos = tempo/1000;
		System.out.println("demorou " + tempoSegundos + " segundos");
	}

}