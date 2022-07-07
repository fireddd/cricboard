package dao;

import exceptions.TeamExistsException;
import exceptions.TeamNotFoundException;
import model.Team;

import java.util.HashMap;
import java.util.Map;

public class TeamDao {

    private static TeamDao teamDaoInstance;

    private Map<Integer, Team> teamMap;

    public static TeamDao getInstance() {
        if (teamDaoInstance == null)
            teamDaoInstance = new TeamDao();
        return teamDaoInstance;
    }

    private TeamDao() {
        this.teamMap = new HashMap<>();
    }

    public void addTeam(Integer teamID) {
        if (this.teamMap.containsKey(teamID)) {
            throw new TeamExistsException();
        }
        this.teamMap.put(teamID, new Team());
    }

    public Team getTeam(Integer teamID) {
        if (!this.teamMap.containsKey(teamID)) {
            throw new TeamNotFoundException();
        }
        return this.teamMap.get(teamID);
    }
}
