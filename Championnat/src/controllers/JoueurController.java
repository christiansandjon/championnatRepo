package controllers;


import entity.EquipeEntity;
import entity.JoueurEntity;
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
public class JoueurController {

    @RequestMapping("/joueur/add")
    public String add(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        //liste des equipes

        EquipeEntity equipeentity = new EquipeEntity();
        List<Equipe> liste = equipeentity.findAll();
        model.addAttribute("liste", liste);
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("ok", request.getParameter("ok"));
        return "joueur/add";
    }

    @RequestMapping(value = "/joueur/addsave", method = RequestMethod.POST)
    public String addsave(HttpServletRequest request, Model model, @RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("age") String age, @RequestParam("equipe") String equipeid) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        Joueur joueur = new Joueur();
        joueur.setAge(Integer.parseInt(age));
        joueur.setNom(nom);
        joueur.setPrenom(prenom);
        EquipeEntity equipeentity = new EquipeEntity();
        Equipe equipe = equipeentity.find(Integer.parseInt(equipeid));
        joueur.setEquipe(equipe);

        JoueurEntity joueurentity = new JoueurEntity();

        //nombre jouer < = 7

        if ((joueurentity.findAllEquipe(equipe)).size() >= 7) {
            return "redirect:/joueur/add?error=2";
        }

        boolean test = joueurentity.save(joueur);
        if (test) {
            String url = request.getParameter("url");
            if (url == null) {
                return "redirect:/equipe/joueurs?id=" + equipe.getId() + "&ok=1";
            } else {
                return "redirect:/joueur/liste?ok=1";
            }

        } else {
            return "redirect:/equipe/joueurs?id=" + equipe.getId() + "&error=1";
        }

    }


    @RequestMapping("/joueur/update")
    public String update(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String id = request.getParameter("id");
        JoueurEntity joueurentity = new JoueurEntity();
        Joueur joueur = joueurentity.find(Integer.parseInt(id));

        EquipeEntity equipeentity = new EquipeEntity();

        List<Equipe> liste = equipeentity.findAll();

        model.addAttribute("liste", liste);
        model.addAttribute("joueur", joueur);
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("ok", request.getParameter("ok"));
        return "joueur/update";
    }

    @RequestMapping("/joueur/liste")
    public String liste(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        JoueurEntity joueurentity = new JoueurEntity();

        List<Joueur> liste = joueurentity.findAll();

        model.addAttribute("liste", liste);
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("ok", request.getParameter("ok"));
        return "joueur/liste";
    }

    @RequestMapping(value = "/joueur/updatesave", method = RequestMethod.POST)
    public String updatesave(HttpServletRequest request, Model model, @RequestParam("id") String id, @RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("age") String age, @RequestParam("equipe") String equipeid) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        JoueurEntity joueurentity = new JoueurEntity();
        Joueur joueur = joueurentity.find(Integer.parseInt(id));
        joueur.setAge(Integer.parseInt(age));
        joueur.setNom(nom);
        joueur.setPrenom(prenom);

        EquipeEntity equipeentity = new EquipeEntity();
        Equipe equipe = equipeentity.find(Integer.parseInt(equipeid));
        joueur.setEquipe(equipe);

        if (joueurentity.update(joueur)) {
            return "redirect:/joueur/update?ok=1&id=" + id;
        } else {
            return "redirect:/joueur/update?error=1&id" + id;
        }

    }

    @RequestMapping("/joueur/delete")
    public String delete(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String id = request.getParameter("id");
        JoueurEntity joueurentity = new JoueurEntity();
        Joueur joueur = joueurentity.find(Integer.parseInt(id));


        model.addAttribute("joueur", joueur);
        return "joueur/delete";
    }

    @RequestMapping(value = "/joueur/deletejoueur", method = RequestMethod.POST)
    public String deleteequipe(HttpServletRequest request, Model model, @RequestParam("id") String id) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        JoueurEntity joueurentity = new JoueurEntity();
        Joueur joueur = joueurentity.find(Integer.parseInt(id));
        int s = joueur.getEquipe().getChampionnat().getId();
        if (joueurentity.delete(joueur)) {
            return "redirect:/championnat/classement?id=" + s + "&ok=1";
        } else {
            return "redirect:/championnat/classement?id=" + s + "&error=1";
        }

    }

}
