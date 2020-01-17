package modele;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * TP1-CORENTIN ET HUGO
 *
 */
public class GestionFiltre {

	/**
	 * @return une liste des mots avec une image
	 */
	public static Predicate<Mot> imgInclu() {
		return p -> p.getNomImageAssocie() != null;
	}

	/**
	 * @param local date du mot
	 * @return une liste des mots sans image saisie avant la date en parametre
	 */
	public static Predicate<Mot> dateSaisiAvant(LocalDate local) {
		return p -> p.getDateSaisieMotDate().compareTo(local) < 0;
	}

	/**
	 * @param local date du mot
	 * @return une liste de mots sans image saisie apres la date en parametre
	 */
	public static Predicate<Mot> dateSaisiApres(LocalDate local) {
		return p -> p.getDateSaisieMotDate().compareTo(local) > 0;
	}

	/**
	 * @param local date du mot
	 * @return une liste de mots sans image modifié avant la date en paramtre
	 */
	public static Predicate<Mot> dateModifAvant(LocalDate local) {
		return p -> p.getDateModifMotDate() != null && p.getDateModifMotDate().compareTo(local) < 0;
	}

	/**
	 * 
	 * @param local date du mot
	 * @return une liste de mots sans image modifié apres la date en parametre
	 */
	public static Predicate<Mot> dateModifApres(LocalDate local) {
		return p -> p.getDateModifMotDate() != null && p.getDateModifMotDate().compareTo(local) > 1;
	}

	/**
	 * @param local date du mot
	 * @return une liste des mots avec image et saisie avant la date en parametre
	 */
	public static Predicate<Mot> dateSaisiAvantImg(LocalDate local) {
		return p -> p.getDateSaisieMotDate().compareTo(local) < 0 && p.getNomImageAssocie() != null;
	}

	/**
	 * @param local date du mot
	 * @return une liste de mots avec image et saisie apres la date en parametre
	 */
	public static Predicate<Mot> dateSaisiApresImg(LocalDate local) {
		return p -> p.getDateSaisieMotDate().compareTo(local) > 0 && p.getNomImageAssocie() != null;
	}

	/**
	 * @param local date du mot
	 * @return une liste de mots avec image et modifié avant la date en parametre
	 */
	public static Predicate<Mot> dateModifAvantImg(LocalDate local) {
		return p -> p.getDateModifMotDate() != null && p.getDateModifMotDate().compareTo(local) < 0
				&& p.getNomImageAssocie() != null;
	}

	/**
	 * @param local date du mot
	 * @return une liste de mots avec image et modifié apres la date en parametre
	 */
	public static Predicate<Mot> dateModifApresImg(LocalDate local) {
		return p -> p.getDateModifMotDate() != null && p.getDateModifMotDate().compareTo(local) > 1
				&& p.getNomImageAssocie() != null;
	}

}
