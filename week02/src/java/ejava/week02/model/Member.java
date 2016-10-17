package ejava.week02.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {

	public enum EnrolType { PT, FT };

	@Id
	private String matriculation;

	private String name;
	private String email;

	@Column(name="enroll_type")
	@Enumerated(EnumType.STRING)
	private EnrolType enrollment;

	@ManyToOne
	@JoinColumn(name = "team_id", referencedColumnName = "team_id")
	private Team team;

	public String getMatriculation() {
		return matriculation;
	}

	public void setMatriculation(String matriculation) {
		this.matriculation = matriculation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EnrolType getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(EnrolType enrollment) {
		this.enrollment = enrollment;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
