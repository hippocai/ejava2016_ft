package ejava.week02.view;

import ejava.week02.business.TeamBean;
import ejava.week02.model.Team;
import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class TeamViews {

	@EJB private TeamBean teamBean;

	private String teamId;
	private Team team;

	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}

	public void createTeam() {
		Team t = new Team();
		t.setName(teamId);
		teamBean.create(t);
		teamId = "";
	}

	public void query() {

		Optional<Team> opt = teamBean.find(teamId);

		if (!opt.isPresent()) {
			FacesMessage m = new FacesMessage("Team not found");
			FacesContext.getCurrentInstance().addMessage(null, m);
			return;
		}

		team = opt.get();
		
	}

	
}
