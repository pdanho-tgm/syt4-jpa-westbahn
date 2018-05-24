import java.util.Collection;

public class Benutzer {

	private Long ID;

	private String vorName;

	private String nachName;

	private String eMail;

	private String passwort;

	private String smsNummer;

	private Long verbuchtePraemienMeilen;

	private Ticket tickets;

	private Collection<Reservierung> reservierungen;

}
