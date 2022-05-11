package models;
import java.util.ArrayList;
public class Team {
    private String name;
    private String cause;
    private String maxMembers;
    private  static final ArrayList<Team>  allTeams = new ArrayList<>();


    public Team(String name, String maxMembers, String cause) {
        this.name = name;
        this.maxMembers = maxMembers;
        this.cause = cause;
        allTeams.add(this);

    }


    public static ArrayList<Team> getAllTeams()  {
        return allTeams;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(String maxMembers) {
        this.maxMembers = maxMembers;
    }


    public String getCause() {
        return cause;
    }


    public void setCause(String cause) {
        this.cause = cause;
    }


}