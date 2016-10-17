package ejava.week02.business;

import ejava.week02.model.Team;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class TeamBean {

	@PersistenceContext private EntityManager em;

	public Optional<Team> find(String tid) {
		return (Optional.ofNullable(em.find(Team.class, tid)));
	}

	public List<Team> findByName(String name) {
		/*
		TypedQuery<Team> query = em.createQuery(
				"select t from Team t where t.name like :name", 
				Team.class);
		*/
		TypedQuery<Team> query = em.createNamedQuery(
				"Team.findByName", Team.class);
		query.setParameter("name", "%" + name + "%");

		List<Team> teams = query.getResultList();
		return (teams);
	}

	public void update(Team detachedTeam) {
		Team teamManaged = em.merge(detachedTeam);
	}

	public Team create(Team team) {
		String tid = UUID.randomUUID().toString().substring(0, 8);

		System.out.println(">>> tid: " + tid);

		team.setTeamId(tid);

		em.persist(team);

		return (team);
	}
	
}
