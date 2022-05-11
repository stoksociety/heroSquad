package models;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    private String heroName;
    private String power;
    private String weakness;
    private final int id;
    private final List<Team> teamHeroes;

    public static ArrayList<Hero> getAllHeroes() {
        return allHeroes;
    }
    private String imageUrl;
    private static final ArrayList<Hero> allHeroes = new ArrayList<>();


    public Hero(String heroName, String power, String weakness, String imageUrl) {
        this.heroName = heroName;
        this.power = power;
        this.weakness = weakness;
        this.imageUrl = imageUrl;
        allHeroes.add(this);
        this.id = allHeroes.size();
        this.teamHeroes = new ArrayList<Team>();
    }

    public int getId() {
        return id;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static Hero findHero(int id) {
        return  allHeroes.get(id - 1);
    }


    //function to add heroes to squad
    public  void addHeroToSquad(Team newTeam){
        teamHeroes.add(newTeam);

    }


    public List<Team> getTeamHeroes() {
        return teamHeroes;
    }


}