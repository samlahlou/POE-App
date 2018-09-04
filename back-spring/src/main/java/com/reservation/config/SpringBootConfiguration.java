package com.reservation.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Utilisation de Spring Boot. <br/>
 *
 * Ici, comme starter-jpa est en place, le Spring Boot va fabriquer la sessionFactory adaptee.
 * De même la data source est paramétrée à travers le fichier de configuration du Spring.
 * Vous n'avez pas à préciser @EnableTransactionManagement, @SpringBootApplication va s'en occuper pour vous. <br/>
 *
 * L'URL racine est fixé via la propriete server.contextPath. <br/>
 *
 * Le @EntityScan(basePackages = { "com.banque.entity" }) est très important ici, sinon Hibernate ne trouve pas les entites à mapper.<br/>
 *
 * Le @EnableJpaRepositories permet aux Spring Data d'être opérationnel. <br/>
 *
 * L'héritage de SpringBootServletInitializer et l'implémentation de la methode configure permet de déployer l'application normalement.
 */
@SpringBootApplication
@EntityScan(basePackages = { "com.reservation.entity" })
@ComponentScan({ "com.reservation.service" , "com.reservation.controleur"})
@EnableJpaRepositories({ "com.reservation.dao" })
public class SpringBootConfiguration extends SpringBootServletInitializer {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Methode issue de SpringBootServletInitializer. Permet de deployer son application de manière standard (un WAR).
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootConfiguration.class);
	}

	/**
	 * Methode obligatoire qui va demarrer Spring Boot.
	 *
	 * @param args
	 *            paramètres à faire passer
	 */
	public static void main(String[] args) {
		SpringBootConfiguration.LOG.debug("-- Debut -- ");
		SpringApplication springApplication = new SpringApplication(SpringBootConfiguration.class);
		// On désactive la banière au démarrage
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.setLogStartupInfo(false);
		// Il n'y a pas de fichier à charger
		springApplication.run(args);
		SpringBootConfiguration.LOG.debug("-- Fin (le serveur Tomcat de Spring Boot est lance) -- ");
	}

}
