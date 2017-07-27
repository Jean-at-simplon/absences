package fr.simplon.services.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;

/**
 * Classe utilitaire pour le calcul des jours feriés
 * 
 * @Param year
 * 
 * @Return une liste des jours fériés
 * 
 * @version 1
 * 
 * @author JGL
 *
 */

/*
 * Il s'agit d'une classe utilitaire pour le calcul des jours feries, 
 * en fonction de l'année de pose des congés.
 * 
 */

public class JourFerie {
	
	/**
	 * Calcul de Paques
	 * @param year
	 * @return
	 */
	
	public static List<DateTime> calculerFerie(int year) {
		
		//Creation de la liste
		List<DateTime> feries = new ArrayList<DateTime>();
		
		//Ajout des données dans la liste
		//La methode "calculerComputus" calcule Paques et les fetes dependantes
		feries.add(new DateTime(year, 1, 1, 0, 0, 0, 0));
		feries.add(calculerComputus("paques",year));
		feries.add(new DateTime(year, 5, 1, 0, 0, 0, 0));
		feries.add(new DateTime(year, 5, 8, 0, 0, 0, 0));
		feries.add(calculerComputus("ascencion",year));
		feries.add(calculerComputus("pentecote",year));
		feries.add(new DateTime(year, 7, 14, 0, 0, 0, 0));
		feries.add(new DateTime(year, 8, 15, 0, 0, 0, 0));
		feries.add(new DateTime(year, 11, 1, 0, 0, 0, 0));
		feries.add(new DateTime(year, 11, 11, 0, 0, 0, 0));
		feries.add(new DateTime(year, 12, 25, 0, 0, 0, 0));

		/*
		 * La boucle permet de supprimer les jours feries tombant
		 * samedi ou dimanche de la liste,
		 * car la semaine de travail est du lundi au samedi 
		 */
		Iterator<DateTime> iterator = feries.iterator();
		while ( iterator.hasNext() ) {
		    DateTime dt = iterator.next();
		    if (dt.dayOfWeek().equals(6) || dt.dayOfWeek().equals(7)) {
		        // On supprime l'élément courant de la liste
		        iterator.remove();
		    }
		}
		
		return feries;
	}

	/*
	 * Methode mathematique du calcul de paques
	 */
	private static DateTime calculerComputus(String name, int year) {
		DateTime dt = new DateTime();
		DateTime comput = null;
		
		int nbr1 = year % 19;
		int nbr2 = year % 4;
		int nbr3 = year % 7;
		int nbr4 = ((nbr1 * 19) + 24) % 30;

		if (nbr4 == 29)
			nbr4 = 28;

		if (nbr4 == 28 && nbr1 > 10)
			nbr4 = 27;

		int nbr5 = ((nbr2 * 2) + (nbr3 * 4) + (nbr4 * 6) + 5) % 7;
		//ternaire qui détermine la date de Paques
		dt = (nbr4 + nbr5) > 10 ? new DateTime(year, 4, (nbr4 + nbr5 - 9), 0, 0, 0, 0)
				: new DateTime(year, 3, (nbr4 + nbr5 + 22), 0, 0, 0, 0);

		if (name.equals("paques")) {
			comput = dt.plusDays(1);
		} else if (name.equals("ascencion")) {
			comput = dt.plusDays(39);
		} else if (name.equals("pentecote")) {
			comput = dt.plusDays(50);
		}

		return comput;

	}
}
