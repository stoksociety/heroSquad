import models.Hero;
import models.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;


public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        //Root Route
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero>  allHeroes = Hero.getAllHeroes();
            model.put("allHeroes", allHeroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        //Hero Route
        get("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero>  allHeroes = Hero.getAllHeroes();
            ArrayList<Team> allTeams = Team.getAllTeams();
            model.put("allHeroes", allHeroes);
            model.put("allTeams", allTeams);
            return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());


        get("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Team> allTeams = Team.getAllTeams();
            ArrayList<Hero>  allHeroes = Hero.getAllHeroes();
            model.put("allTeams", allTeams);
            model.put("allHeroes", allHeroes);
            return new ModelAndView(model, "teams.hbs");
        }, new HandlebarsTemplateEngine());


        post("/getTeams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String cause = request.queryParams("cause");
            String maxMembers = request.queryParams("maxMembers");
            int squadHero = Integer.parseInt(request.queryParams("squadHero"));
            Hero newHero = Hero.findHero(squadHero);
            Team resume = new Team(name, cause, maxMembers);

            newHero.addHeroToSquad(resume);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        post("/new/hero", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String heroName = request.queryParams("heroName");
            String power = request.queryParams("power");
            String weakness = request.queryParams("weakness");
            String imageUrl = request.queryParams("imageUrl");
            Hero hero = new Hero(heroName, power, weakness, imageUrl);

            return new ModelAndView(model, "success.hbs");

        }, new HandlebarsTemplateEngine());

        get("/new/:id", (request,response) -> {
            Map<String, Object> model = new HashMap<>();
            Hero thisHero = Hero.findHero(Integer.parseInt(request.params("id")));
            List<Team> thisTeam = thisHero.getTeamHeroes();
            model.put("thisHero", thisHero);
            model.put("thisTeam", thisTeam);

            return  new ModelAndView(model, "teamMembers.hbs");
        }, new HandlebarsTemplateEngine());


    }
}