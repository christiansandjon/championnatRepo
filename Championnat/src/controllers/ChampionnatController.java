package controllers;


import entity.*;
import model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Controller

public class ChampionnatController {

    private String page;

    @RequestMapping("/championnat/add")
    public String add(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("ok", request.getParameter("ok"));
        model.addAttribute("error", request.getParameter("error"));
        return "/championnat/add";

    }

    @RequestMapping(value = "/championnat/addsave", method = RequestMethod.POST)
    public String addsave(HttpServletRequest request, Model model, @RequestParam("nom") String nom, @RequestParam("description") String description) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        Championnat obj = new Championnat();
        obj.setNom(nom);
        obj.setDescription(description);
        ChampionnatEntity championnatentity = new ChampionnatEntity();
        boolean test = championnatentity.save(obj);
        if (test) {
            return "redirect:/championnat/add?ok=1";
        } else {
            return "redirect:/championnat/add?error=1";
        }

    }

    @RequestMapping("/championnat/matchsattente")
    public String matchsattente(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }
        String id = request.getParameter("id");
        ChampionnatEntity championnatentity = new ChampionnatEntity();
        Championnat championnat = championnatentity.find(Integer.parseInt(id));
        ButEntity butentity = new ButEntity();

        // Matchs en cours
        MatchEntity matchentity = new MatchEntity();
        List<Match> listematchencours = matchentity.findMatch(championnat.getId(), false);
        model.addAttribute("matchscours", listematchencours);
        // fin

        // Matchs terminés
        List<Match> listematchterminer = matchentity.findMatch(championnat.getId(), true);

        List<MatchTerminer> matchterminer = new ArrayList<MatchTerminer>();


        for (int i = 0; i < listematchterminer.size(); i++) {
            MatchTerminer m = new MatchTerminer();
            m.setMatch(listematchterminer.get(i));
            m.setEquipe1(listematchterminer.get(i).getEquipe1());
            m.setEquipe2(listematchterminer.get(i).getEquipe2());
            m.setButs1((butentity.findButMatchEquipe(listematchterminer.get(i), listematchterminer.get(i).getEquipe1())).size());
            m.setButs2((butentity.findButMatchEquipe(listematchterminer.get(i), listematchterminer.get(i).getEquipe2())).size());
            matchterminer.add(m);
        }
        model.addAttribute("matchsterminer", matchterminer);
        // fin

        // les buteurs
        JoueurEntity joueurentity = new JoueurEntity();
        CartonsEntity cartonentity = new CartonsEntity();
        List<Joueur> listejoueur = joueurentity.findJoueurChampionnatAll(championnat);
        List<JoueurBut> listejoueurbut = new ArrayList<JoueurBut>();
        for (int i = 0; i < listejoueur.size(); i++) {
            JoueurBut jb = new JoueurBut();
            jb.setJoueur(listejoueur.get(i));
            jb.setNombrecartonjaune(cartonentity.findCartonsJoueur(listejoueur.get(i), false).size());
            jb.setNombrecartonrouge(cartonentity.findCartonsJoueur(listejoueur.get(i), true).size());
            jb.setNombrebut(butentity.findButJoueur(listejoueur.get(i)).size());
            listejoueurbut.add(jb);

        }
        //Collections.sort(listejoueurbut,comparing((JoueurBut.class).getName()));
        //Collections.sort(list, comparing(ClassName::getName));
        //sort(persons, on(Person.class).getAge());

        listejoueurbut.sort(Comparator.comparing(JoueurBut::getNombrebut).reversed());
        // Collections.sort(listejoueurbut, Ordering.natural().onResultOf(JoueurBut::getNombrebut).reverse());
        model.addAttribute("listejoueurbut", listejoueurbut);
        //fin

        model.addAttribute("championnat", championnat);
        List<Equipe> liste = championnatentity.findAllEquipe(championnat.getId());
        List<Classement> listeclassement = new ArrayList<Classement>();

        //nombre de buts marques au cours d'un match

        //nombre de but marques par x au cours d'un match


        //les equipes du championnat
        for (int i = 0; i < liste.size(); i++) {
            Classement c = new Classement();
            c.setEquipe(liste.get(i));
            //nombre de match joeur
            List<Match> matchjouers = matchentity.findMatchEquipe(liste.get(i), true);
            int nombrematchgagne = 0;
            int nombrematchnull = 0;
            int nombrematchperdu = 0;
            int nombrematchjoueur = matchjouers.size();

            for (int j = 0; j < matchjouers.size(); j++) {
                int butTotalMatchsi = butentity.findButMatch(matchjouers.get(j)).size();
                int butMarquerEquipesi = butentity.findButMatchEquipe(matchjouers.get(j), liste.get(i)).size();
                int differencebut = butTotalMatchsi - butMarquerEquipesi;

                if (differencebut == butMarquerEquipesi) {
                    nombrematchnull++;
                }
                if (differencebut > butMarquerEquipesi) {
                    nombrematchperdu++;
                }
                if (differencebut < butMarquerEquipesi) {
                    nombrematchgagne++;
                }
            }

            c.setMatchgagner(nombrematchgagne);
            c.setMatchjouer(nombrematchjoueur);
            c.setMatchperdu(nombrematchperdu);
            c.setMatchnull(nombrematchnull);
            int point = nombrematchgagne * 3 + nombrematchnull * 1;
            c.setPoint(point);
            listeclassement.add(c);
        }

        //fin

        listeclassement.sort(Comparator.comparing(Classement::getPoint).reversed());
        model.addAttribute("listeclassement", listeclassement);
        model.addAttribute("liste", liste);
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("ok", request.getParameter("ok"));

        return "/championnat/matchsattente";
    }
    @RequestMapping("/championnat/matchstermines")
    public String matchstermines(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }
        return "/championnat/matchstermines";
    }
    @RequestMapping("/championnat/statsjoueurs")
    public String statsjoueurs(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }
        return "/championnat/statsjoueurs";
    }

    @RequestMapping("/championnat/classement2")
    public String classement2(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }
        return "redirect:/championnat/liste?ok=1";
    }

    @RequestMapping("/championnat/classement")
    public String classement(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }


        String id = request.getParameter("id");
        ChampionnatEntity championnatentity = new ChampionnatEntity();
        Championnat championnat = championnatentity.find(Integer.parseInt(id));
        ButEntity butentity = new ButEntity();

        // Matchs en cours
        MatchEntity matchentity = new MatchEntity();
        List<Match> listematchencours = matchentity.findMatch(championnat.getId(), false);
        model.addAttribute("matchscours", listematchencours);
        // fin

        // Matchs terminés
        List<Match> listematchterminer = matchentity.findMatch(championnat.getId(), true);

        List<MatchTerminer> matchterminer = new ArrayList<MatchTerminer>();


        for (int i = 0; i < listematchterminer.size(); i++) {
            MatchTerminer m = new MatchTerminer();
            m.setMatch(listematchterminer.get(i));
            m.setEquipe1(listematchterminer.get(i).getEquipe1());
            m.setEquipe2(listematchterminer.get(i).getEquipe2());
            m.setButs1((butentity.findButMatchEquipe(listematchterminer.get(i), listematchterminer.get(i).getEquipe1())).size());
            m.setButs2((butentity.findButMatchEquipe(listematchterminer.get(i), listematchterminer.get(i).getEquipe2())).size());
            matchterminer.add(m);
        }
        model.addAttribute("matchsterminer", matchterminer);
            // fin

        // les buteurs
        JoueurEntity joueurentity = new JoueurEntity();
        CartonsEntity cartonentity = new CartonsEntity();
        List<Joueur> listejoueur = joueurentity.findJoueurChampionnatAll(championnat);
        List<JoueurBut> listejoueurbut = new ArrayList<JoueurBut>();
        for (int i = 0; i < listejoueur.size(); i++) {
            JoueurBut jb = new JoueurBut();
            jb.setJoueur(listejoueur.get(i));
            jb.setNombrecartonjaune(cartonentity.findCartonsJoueur(listejoueur.get(i), false).size());
            jb.setNombrecartonrouge(cartonentity.findCartonsJoueur(listejoueur.get(i), true).size());
            jb.setNombrebut(butentity.findButJoueur(listejoueur.get(i)).size());
            listejoueurbut.add(jb);

        }
        //Collections.sort(listejoueurbut,comparing((JoueurBut.class).getName()));
        //Collections.sort(list, comparing(ClassName::getName));
        //sort(persons, on(Person.class).getAge());

        listejoueurbut.sort(Comparator.comparing(JoueurBut::getNombrebut).reversed());
        // Collections.sort(listejoueurbut, Ordering.natural().onResultOf(JoueurBut::getNombrebut).reverse());
        model.addAttribute("listejoueurbut", listejoueurbut);
        //fin

        model.addAttribute("championnat", championnat);
        List<Equipe> liste = championnatentity.findAllEquipe(championnat.getId());
        List<Classement> listeclassement = new ArrayList<Classement>();

        //nombre de buts marques au cours d'un match

        //nombre de but marques par x au cours d'un match


        //les equipes du championnat
        for (int i = 0; i < liste.size(); i++) {
            Classement c = new Classement();
            c.setEquipe(liste.get(i));
            //nombre de match joeur
            List<Match> matchjouers = matchentity.findMatchEquipe(liste.get(i), true);
            int nombrematchgagne = 0;
            int nombrematchnull = 0;
            int nombrematchperdu = 0;
            int nombrematchjoueur = matchjouers.size();

            for (int j = 0; j < matchjouers.size(); j++) {
                int butTotalMatchsi = butentity.findButMatch(matchjouers.get(j)).size();
                int butMarquerEquipesi = butentity.findButMatchEquipe(matchjouers.get(j), liste.get(i)).size();
                int differencebut = butTotalMatchsi - butMarquerEquipesi;

                if (differencebut == butMarquerEquipesi) {
                    nombrematchnull++;
                }
                if (differencebut > butMarquerEquipesi) {
                    nombrematchperdu++;
                }
                if (differencebut < butMarquerEquipesi) {
                    nombrematchgagne++;
                }
            }

            c.setMatchgagner(nombrematchgagne);
            c.setMatchjouer(nombrematchjoueur);
            c.setMatchperdu(nombrematchperdu);
            c.setMatchnull(nombrematchnull);
            int point = nombrematchgagne * 3 + nombrematchnull * 1;
            c.setPoint(point);
            listeclassement.add(c);
        }

        //fin

        listeclassement.sort(Comparator.comparing(Classement::getPoint).reversed());
        model.addAttribute("listeclassement", listeclassement);
        model.addAttribute("liste", liste);
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("ok", request.getParameter("ok"));
        return "/championnat/classement";
    }


    @RequestMapping("/championnat/liste")
    public String liste(HttpServletRequest request, Model model) {
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
        return "/championnat/liste";
    }

    @RequestMapping("/championnat/update")
    public String update(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String id = request.getParameter("id");
        ChampionnatEntity championnatentity = new ChampionnatEntity();
        Championnat championnat = championnatentity.find(Integer.parseInt(id));

        model.addAttribute("championnat", championnat);
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("ok", request.getParameter("ok"));
        return "/championnat/update";
    }


    @RequestMapping(value = "/championnat/updatesave", method = RequestMethod.POST)
    public String updatesave(HttpServletRequest request, Model model, @RequestParam("id") String id, @RequestParam("nom") String nom, @RequestParam("description") String description) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        ChampionnatEntity championnatentity = new ChampionnatEntity();
        Championnat championnat = championnatentity.find(Integer.parseInt(id));
        championnat.setDescription(description);
        championnat.setNom(nom);
        if (championnatentity.update(championnat)) {
            return "redirect:/championnat/update?ok=1&id=" + id;
        } else {
            return "redirect:/championnat/update?error=1&id" + id;
        }

    }


    @RequestMapping("/championnat/delete")
    public String delete(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String id = request.getParameter("id");
        ChampionnatEntity championnatentity = new ChampionnatEntity();
        Championnat championnat = championnatentity.find(Integer.parseInt(id));

        model.addAttribute("championnat", championnat);

        return "/championnat/delete";
    }

    @RequestMapping(value = "/championnat/deletechampionnat", method = RequestMethod.POST)
    public String deletechampionnat(HttpServletRequest request, Model model, @RequestParam("id") String id) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }
        ChampionnatEntity championnatentity = new ChampionnatEntity();
        Championnat championnat = championnatentity.find(Integer.parseInt(id));
        if (championnatentity.delete(championnat)) {
            return "redirect:/championnat/liste?ok=1";
        } else {
            return "redirect:/championnat/liste?error=1";
        }

    }

}
