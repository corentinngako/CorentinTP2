package modele;

import java.time.LocalDate;

/**
 * TP1-CORENTIN ET HUGO
 *
 */
public class Mot {
	private String mot;
	private String definition;
	private String nomImageAssocie;
	private LocalDate dateSaisieMot;
	private LocalDate dateModificationMot;

	/**
	 * Constructeur du mot fait a l'aide du string en parametre
	 * 
	 * @param mot nom du mot
	 */
	public Mot(String mot) {

		this.mot = mot;
		this.definition = null;
		this.nomImageAssocie = null;
		this.dateSaisieMot = dateSaisieMot;
		this.dateModificationMot = null;

		setDateSaisieMot(LocalDate.now());
	}

	/**
	 * Pour obtenir le nom du mot
	 * 
	 * @return le nom du mot
	 */
	public String getMot() {
		return mot;
	}

	/**
	 * Pour définire le nom du mot
	 * 
	 * @param mot le nom du mot
	 */
	public void setMot(String mot) {
		this.mot = mot;
	}

	/**
	 * @return la définition du mot
	 */
	public String getDefinition() {
		return definition;
	}

	/**
	 * Pour définire la dénition du mot
	 * 
	 * @param definition définition du mot
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/**
	 * @return le path de l'image du mot
	 */
	public String getNomImageAssocie() {
		return nomImageAssocie;
	}

	/**
	 * Pour définire le path de l'image du mot
	 * 
	 * @param nomImageAssocie path de l'image
	 */
	public void setNomImageAssocie(String nomImageAssocie) {
		this.nomImageAssocie = nomImageAssocie;
	}

	/**
	 * @return la date de saisie du mot en format string
	 */
	public String getDateSaisieMot() {
		if (dateModificationMot == null) {
			return ("Mot saisie le : " + dateSaisieMot.toString());
		} else {
			return ("Mot saisie le : " + dateSaisieMot.toString() + " /  Modifier le : " + dateModificationMot);
		}

	}

	/**
	 * @return la date de saisie du mot en format localdate
	 */
	public LocalDate getDateSaisieMotDate() {
		return dateSaisieMot;

	}

	/**
	 * @return la date de modification du mot en format localdate 
	 */
	public LocalDate getDateModifMotDate() {
		return dateModificationMot;

	}

	/**
	 * Pour définire la date de saisie du mot
	 * 
	 * @param dateSaisieMot date de saisie du mot
	 */
	public void setDateSaisieMot(LocalDate dateSaisieMot) {
		this.dateSaisieMot = dateSaisieMot;
	}

	/**
	 * @return  la date de modification du mot en format string
	 */
	public String getDateModificationMot() {
		if (dateModificationMot != null) {
			return (dateModificationMot.toString());
		} else {
			return null;
		}

	}

	/**
	 * Pour définir la date de mofication du mot
	 * 
	 * @param dateModificationMot date de modification du mot
	 */
	public void setDateModificationMot(LocalDate dateModificationMot) {
		this.dateModificationMot = dateModificationMot;
	}

}
