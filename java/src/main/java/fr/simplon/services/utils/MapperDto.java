package fr.simplon.services.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.simplon.dao.EmployeDao;
import fr.simplon.dao.StatutDao;
import fr.simplon.dao.TypeDao;
import fr.simplon.domain.Absence;
import fr.simplon.domain.Employe;
import fr.simplon.domain.User;
import fr.simplon.domain.dto.AbsenceDto;
import fr.simplon.domain.dto.EmployeDto;
import fr.simplon.domain.dto.UserDto;
import fr.simplon.exception.ServiceException;

/**
 * Mapper to convert Javabeans in DTO
 * 
 * 
 * @author JGL
 */

@Service
public class MapperDto {

	@Autowired
	TypeDao typeDao;

	@Autowired
	StatutDao statutDao;
	
	@Autowired
	EmployeDao employeDao;

	/**
	 * Convert a list of employe in list of employeDto
	 * 
	 * @param List<Employe>
	 * 
	 * @return List<EmployeDto>
	 */
	public List<EmployeDto> convertListeEmployeToDto(List<Employe> employe) {
		List<EmployeDto> resultatEmploye = new ArrayList<>();

		if (!employe.isEmpty()) {
			for (Employe transfert : employe) {
				resultatEmploye.add(convertEmployeToDto(transfert));
			}
		} else {
			throw new ServiceException("Liste Employé vide");
		}

		return resultatEmploye;
	}

	/**
	 * Convert a list of user in list of userDto
	 * 
	 * @param List<User>
	 * 
	 * @return List<UserDto>
	 */
	public List<UserDto> convertListUserToDto(List<User> user) {
		List<UserDto> resultatUser = new ArrayList<>();
		if (!user.isEmpty()) {
			for (User transfert : user) {
				resultatUser.add(convertUserToDto(transfert));
			}
		} else {
			throw new ServiceException("Liste User vide");
		}

		return resultatUser;
	}

	/**
	 * Convert a list of absence in list of absenceDto
	 * 
	 * @param List<Absence>
	 * 
	 * @return List<AbsenceDto>
	 */
	public List<AbsenceDto> convertListeAbsenceToDto(List<Absence> absence) {
		List<AbsenceDto> resultatAbsence = new ArrayList<>();
		absence.forEach((transfert) -> {
			resultatAbsence.add(convertAbsenceToDto(transfert));
		});
		return resultatAbsence;
	}

	public UserDto convertUserToDto(User user) {

		UserDto userDto = new UserDto();

		userDto.setEmail(user.getEmail());
		userDto.setRole(user.getRole().getValeur());
		userDto.setEmployeDto(convertEmployeToDto(user.getEmploye()));

		List<Absence> absence = user.getEmploye().getAbsence();
		userDto.setAbsenceDto((List<AbsenceDto>) convertListeAbsenceToDto(absence));

		return userDto;
	}

	public AbsenceDto convertAbsToDto(Absence absence) {
		AbsenceDto absenceDto = convertAbsenceToDto(absence);

		return absenceDto;
	}

	private EmployeDto convertEmployeToDto(Employe employe) {
		EmployeDto employeDto = new EmployeDto();

		employeDto.setNom(employe.getNom());
		employeDto.setPrenom(employe.getPrenom());
		employeDto.setMatricule(employe.getMatricule());
		employeDto.setEmail(employe.getUser().getEmail());
		employeDto.setNbCa(employe.getNbCa());
		employeDto.setNbRtt(employe.getNbRtt());
		employeDto.setNbRc(employe.getNbRc());
		employeDto.setNomRh(employe.getServiceRh().getNom());
		employeDto.setEmailRh(employe.getServiceRh().getEmail());
		employeDto.setNomEquipe(employe.getEquipe().getNom());
		employeDto.setNomResponsable(employe.getEquipe().getResponsable().getNom());
		employeDto.setPrenomResponsable(employe.getEquipe().getResponsable().getPrenom());
		employeDto.setEmailReponsable(employe.getEquipe().getResponsable().getUser().getEmail());

		return employeDto;
	}

	private AbsenceDto convertAbsenceToDto(Absence absence) {

		AbsenceDto absenceDto = new AbsenceDto();

		absenceDto.setDebut(absence.getDebut());
		absenceDto.setFin(absence.getFin());
		absenceDto.setType(absence.getType().getNom());
		absenceDto.setStatut(absence.getStatut().getNom());
		absenceDto.setNumDemande(absence.getNumDemande());
		absenceDto.setCommentaire(absence.getCommentaire());
		absenceDto.setMatricule(absence.getEmploye().getMatricule());
		absenceDto.setNom(absence.getEmploye().getNom());
		absenceDto.setPrenom(absence.getEmploye().getPrenom());
		absenceDto.setRelance(absence.getRelance());

		return absenceDto;
	}

	/**
	 * Converti une absenceDto en absence
	 * @param absenceDto
	 * @return
	 */
	public Absence convertDtoToAbs(AbsenceDto absenceDto) {
		Absence absence = convertDtoToAbsence(absenceDto);

		return absence;
	}

	private Absence convertDtoToAbsence(AbsenceDto absenceDto) {

		Absence absence = new Absence();

		absence.setDebut(absenceDto.getDebut());
		absence.setFin(absenceDto.getFin());
		absence.setNumDemande(absenceDto.getNumDemande());
		absence.setCommentaire(absenceDto.getCommentaire());
		absence.setType(typeDao.findTypeByNom(absenceDto.getType()));
		absence.setStatut(statutDao.findByNom(absenceDto.getStatut()));
		absence.setEmploye(employeDao.findByMatricule(absenceDto.getMatricule()));
		absence.setRelance(absenceDto.getRelance());
		
		return absence;
	}

}
