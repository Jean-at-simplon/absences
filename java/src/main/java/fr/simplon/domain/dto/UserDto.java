package fr.simplon.domain.dto;

import java.util.List;

public class UserDto {
	
	private String email;
	private String role;
	private EmployeDto employeDto;
	private List<AbsenceDto> absenceDto;
	
	public UserDto() {
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public EmployeDto getEmployeDto() {
		return employeDto;
	}

	public void setEmployeDto(EmployeDto employeDto) {
		this.employeDto = employeDto;
	}


	public List<AbsenceDto> getAbsenceDto() {
		return absenceDto;
	}


	public void setAbsenceDto(List<AbsenceDto> absenceDto) {
		this.absenceDto = absenceDto;
	}


	
}
