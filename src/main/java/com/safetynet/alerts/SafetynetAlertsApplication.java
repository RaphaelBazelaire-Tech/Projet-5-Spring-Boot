package com.safetynet.alerts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de l'application Spring Boot SafetyNet Alerts.
 *
 * <p>Cette classe contient le point d'entrée de
 * l'application et démarre le contexte Spring Boot.</p>
 *
 * <p>Elle est annotée avec {@link SpringBootApplication} qui combine
 * les annotations {@code @Configuration}, {@code @EnableAutoConfiguration} et {@code @ComponentScan}.</p>
 */
@SpringBootApplication
public class SafetynetAlertsApplication {

	/**
	 * Constructeur par défaut.
	 */
	public SafetynetAlertsApplication() {
	}

	/**
	 * Point d'entrée principal de l'application.
	 *
	 * @param args arguments de ligne de commande (non utilisés).
	 */
	public static void main(String[] args) {
		SpringApplication.run(SafetynetAlertsApplication.class, args);
	}
}
