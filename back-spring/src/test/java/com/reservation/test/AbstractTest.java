package com.reservation.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.reservation.config.SpringBootConfiguration;

/**
 * Classe abstraite en charge de la preparation du chargement des fichiers
 * Spring.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SpringBootConfiguration.class)
public abstract class AbstractTest {
	// Classe mere des tests

	// FIXME Spring boot ne prend pas en compte le fichier de log4j2 pour les tests ?
}
