package fr.simplon;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.simplon.dao.AbsenceDao;
import fr.simplon.domain.Absence;
import fr.simplon.domain.Employe;
import fr.simplon.domain.ServiceRh;
import fr.simplon.domain.dto.AbsenceDto;
import fr.simplon.services.AbsenceService;
import fr.simplon.services.utils.MapperDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Config.class)
public class DemandeAbsencesApplicationTests {
	//L'annotation 'Mock' prends une classe en argument
	//et retourne un objet de cette classe
	@Mock
	AbsenceDao absenceDao;
	
	@Mock
	ServiceRh rh;

	@Mock
	MapperDto mapper;
	
	@Mock
	Absence absence;
	
	@Mock
	Employe employe;
	
	@InjectMocks
	AbsenceService absService;
	
	@Before
	public void servcieRhInit(){
		
	}
	

	//test avec simulation d'exception
	@Test(expected  = Exception.class)
	public void absenceTestException() throws SQLException{
		when(absenceDao.findById(anyLong())).thenThrow(new Exception());
		absService.getAbsenceById(anyLong());
	}
	
	//Test avec une liste
	@Test
	public void listeAbsenceTest() throws SQLException{
		List<Absence> abs = new ArrayList<Absence>();
		List<AbsenceDto> absDto = new ArrayList<AbsenceDto>();
		when(absenceDao.findAll()).thenReturn(abs);
		absDto = mapper.convertListeAbsenceToDto(abs);
		assertEquals(absDto,absService.listeServicesAbsence());
	}

}






