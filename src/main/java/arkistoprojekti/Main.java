/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkistoprojekti;

import java.io.File;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:food.db");
        RuokaDao ruokaDao = new RuokaDao(database);
        RaakaAineDao raakaaineDao = new RaakaAineDao(database);
        RuokaRaakaAineDao rraDao = new RuokaRaakaAineDao(database, ruokaDao, raakaaineDao);

        Spark.get("/hei", (req, res) -> {
            return "Hei maailma!";
        });

        Spark.get("/ruoka", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("ruoka", ruokaDao.findAll());

            return new ModelAndView(map, "ruoka");
        }, new ThymeleafTemplateEngine());

        Spark.post("/ruoka", (req, res) -> {
            Ruoka ruoka = new Ruoka(-1, req.queryParams("ruoannimi"));
            ruokaDao.saveOrUpdate(ruoka);

            res.redirect("/ruoka");
            return "";
        });

        Spark.get("/raakaaine", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("raakaaine", raakaaineDao.findAll());

            return new ModelAndView(map, "raakaaine");
        }, new ThymeleafTemplateEngine());

        Spark.post("/raakaaine", (req, res) -> {
            RaakaAine ra = new RaakaAine(-1, req.queryParams("nimi"));
            raakaaineDao.saveOrUpdate(ra);

            res.redirect("/raakaaine");
            return "";
        });

        Spark.get("/rra", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("rra", rraDao.findAll());

            return new ModelAndView(map, "rra");
        }, new ThymeleafTemplateEngine());

        Spark.post("/rra/:id", (req, res) -> {
            Integer idd = Integer.parseInt(req.params(":id"));
            Integer ruokaId = Integer.parseInt(req.queryParams("ruokaId"));
            Integer raakaaineId = Integer.parseInt(req.queryParams("raakaaineId"));

            RuokaRaakaAine rra = new RuokaRaakaAine(-1, ruokaId, raakaaineId, 1, 1, "");
            rraDao.saveOrUpdate(rra);

            res.redirect("/rra/:id");
            return "";
        });

    }
}
