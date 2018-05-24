import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class.getName());
	private static long BAHNHOF_ID = 1L;

	public static void main(String[] args) {
		logger.info("Starting EntityManagerFactory..");

		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("westbahnPU");
		EntityManager entityManager = factory.createEntityManager();

		createStations(entityManager);
		createStrecken(entityManager);
		createUsers(entityManager);
		createSonderangebote(entityManager);
		//update(entityManager);
		//remove(entityManager);

		List<Benutzer> benutzerMitMonatskarte = entityManager.createNamedQuery("Benutzer.getAllWithMonatskarte").getResultList();
		for (Benutzer benutzer : benutzerMitMonatskarte) {
			System.out.println("Benutzer mit Monatkarte: " + benutzer);
		}

		List<Reservierung> benutzerMitReservierungen = entityManager.createNamedQuery("Reservierung.getReservierungenForUser")
				.setParameter("email", email)
				.getResultList();
		for (Reservierung reservierung : benutzerMitReservierungen) {
			System.out.println("Reservierung für " + email + ": " + reservierung);
		}

		long streckeId = 1;
		List<Ticket> ticketsOhneReservierung = entityManager.createNamedQuery("Ticket.getAllTicketsWithoutReservierung")
				.setParameter("streckeID", streckeId)
				.getResultList();
		for (Ticket ticket : ticketsOhneReservierung) {
			System.out.println("Strecke ohne Reservierung für Strecke#" + streckeId + ": " + ticket);
		}

		entityManager.close();
		factory.close();
		logger.info("Finished!");
	}

}
