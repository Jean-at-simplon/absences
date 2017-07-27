package fr.simplon.services.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.simplon.dao.NumDemandeDao;
import fr.simplon.domain.NumDemande;

/**
 * Classe pour différents utilitaires
 * 
 * @author JGL
 *
 */


@Service
@Transactional
public class UtilitairesService {
	
	@Autowired
	NumDemandeDao numeroDao;
	
	/**
	 * méthode pour le calcul du numéro de demande
	 * 
	 * @param aucun
	 * 
	 * @return numéro de demande
	 */
	
	public String findNumDemande() {
		NumDemande numDemande = numeroDao.findById((long) 1);
		String demande;
		int numero = Integer.parseInt(numDemande.getNumDemande().substring(3, 9));
		numero++;
		demande = "DEM"+String.format("%06d", numero);
		numDemande.setNumDemande(demande);
		numeroDao.save(numDemande);
		return demande;
	}

}
