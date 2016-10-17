package ejava.week02.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = "Team.findByName", 
			query = "select t from Team t where t.name like :name")
})
@Entity
@Table(name = "team")
public class Team {

	@Id
	@Column(name = "team_id")
	private String teamId;

	private String name;

	@OneToMany(mappedBy = "team")
	private List<Member> members;

	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
}
