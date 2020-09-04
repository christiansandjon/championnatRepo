package controllers;


import entity.ChampionnatEntity;
import entity.EquipeEntity;
import entity.JoueurEntity;
import model.Championnat;
import model.Equipe;
import model.Joueur;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller

public class EquipeController {


    @RequestMapping("/equipe/add")
    public String add(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        ChampionnatEntity championnatentity = new ChampionnatEntity();

        List<Championnat> liste = championnatentity.findAll();
        model.addAttribute("liste", liste);

        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("ok", request.getParameter("ok"));
        return "equipe/add";
    }

    @RequestMapping(value = "/equipe/addsave", method = RequestMethod.POST)
    public String addsave(HttpServletRequest request, Model model, @RequestParam("nom") String nom, @RequestParam("description") String description, @RequestParam("championnat") String idchampionnat) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        Equipe obj = new Equipe();
        obj.setNom(nom);
        obj.setDescription(description);
        ChampionnatEntity championnatentity = new ChampionnatEntity();
        Championnat championnat = championnatentity.find(Integer.parseInt(idchampionnat));
        obj.setChampionnat(championnat);
        EquipeEntity equipeentity = new EquipeEntity();
        boolean test = equipeentity.save(obj);
        if (test) {
            String url = request.getParameter("url");
            if (url == null) {
                return "redirect:/equipe/add?ok=1";

            } else {
                return "redirect:/championnat/classement?id=" + championnat.getId() + "&ok=1";
            }

        } else {
            return "redirect:/championnat/classement?id=" + championnat.getId() + "error=1";
        }

    }

    @RequestMapping("/equipe/joueurs")
    public String lesjoueurs(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String id = request.getParameter("id");
        EquipeEntity equipeentity = new EquipeEntity();
        Equipe equipe = equipeentity.find(Integer.parseInt(id));

        JoueurEntity joueurentity = new JoueurEntity();

        List<Joueur> listejoueurs = joueurentity.findAllEquipe(equipe);

        ChampionnatEntity championnatentity = new ChampionnatEntity();

        List<Championnat> liste = championnatentity.findAll();
        model.addAttribute("liste", liste);

        model.addAttribute("listejoueurs", listejoueurs);
        model.addAttribute("nombrejoueur", listejoueurs.size());
        model.addAttribute("equipe", equipe);
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("ok", request.getParameter("ok"));
        return "equipe/listejoueurs";
    }

    @RequestMapping("/equipe/update")
    public String update(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String id = request.getParameter("id");
        EquipeEntity equipeentity = new EquipeEntity();
        Equipe equipe = equipeentity.find(Integer.parseInt(id));

        ChampionnatEntity championnatentity = new ChampionnatEntity();

        List<Championnat> liste = championnatentity.findAll();
        model.addAttribute("liste", liste);

        model.addAttribute("equipe", equipe);
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("ok", request.getParameter("ok"));
        return "equipe/update";
    }

    @RequestMapping("/equipe/liste")
    public String liste(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        EquipeEntity equipeentity = new EquipeEntity();

        List<Equipe> liste = equipeentity.findAll();

        model.addAttribute("liste", liste);
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("ok", request.getParameter("ok"));
        return "equipe/liste";
    }

    @RequestMapping(value = "/equipe/updatesave", method = RequestMethod.POST)
    public String updatesave(HttpServletRequest request, Model model, @RequestParam("id") String id, @RequestParam("nom") String nom, @RequestParam("description") String description, @RequestParam("championnat") String idchampionnat) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        EquipeEntity equipeentity = new EquipeEntity();
        Equipe equipe = equipeentity.find(Integer.parseInt(id));
        equipe.setDescription(description);
        equipe.setNom(nom);
        ChampionnatEntity championnatentity = new ChampionnatEntity();
        Championnat championnat = championnatentity.find(Integer.parseInt(idchampionnat));
        equipe.setChampionnat(championnat);
        if (equipeentity.update(equipe)) {
            return "redirect:/equipe/update?ok=1&id=" + id;
        } else {
            return "redirect:/equipe/update?error=1&id" + id;
        }

    }

    @RequestMapping("/equipe/delete")
    public String delete(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String id = request.getParameter("id");
        EquipeEntity equipeentity = new EquipeEntity();
        Equipe equipe = equipeentity.find(Integer.parseInt(id));

        model.addAttribute("equipe", equipe);
        return "equipe/delete";
    }

    @RequestMapping(value = "/equipe/deleteequipe", method = RequestMethod.POST)
    public String deleteequipe(HttpServletRequest request, Model model, @RequestParam("id") String id) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        EquipeEntity equipeentity = new EquipeEntity();
        Equipe equipe = equipeentity.find(Integer.parseInt(id));
        if (equipeentity.delete(equipe)) {
            return "redirect:/equipe/liste?ok=1";
        } else {
            return "redirect:/equipe/liste?error=1";
        }

    }

}
