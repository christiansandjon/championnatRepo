package controllers;


import entity.*;
import model.*;
import org.eclipse.persistence.sessions.factories.SessionFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller

public class MatchController {
    private static SessionFactory sessionFactory;
    private Session session;


    // les cartons


    @RequestMapping("/match/save-carton")
    public String savecarton(HttpServletRequest request, Model model, @RequestParam("match") String matchid, @RequestParam("joueur") String joueurid, @RequestParam("type") String type) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        MatchEntity matchentity = new MatchEntity();
        Match match = matchentity.find(Integer.parseInt(matchid));

        JoueurEntity joueurentity = new JoueurEntity();
        Joueur joueur = joueurentity.find(Integer.parseInt(joueurid));

        int typecarton = Integer.parseInt(type);

        Cartons carton = new Cartons();

        carton.setJoueur(joueur);
        carton.setMatch(match);
        boolean test;
        if (typecarton == 1) {
            test = true;
        } else {
            test = false;
        }
        carton.setType(test);


        CartonsEntity cartonsentity = new CartonsEntity();

        if (cartonsentity.save(carton)) {
            return "redirect:/match/gestion-score?id=" + matchid + "&ok=1";
        } else {
            return "redirect:/match/gestion-score?id=" + matchid + "&error=1";
        }

    }

    @RequestMapping("/match/deletecarton")
    public String deletecarton(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String id = request.getParameter("id");
        CartonsEntity cartonsentity = new CartonsEntity();
        Cartons carton = cartonsentity.find(Integer.parseInt(id));

        model.addAttribute("carton", carton);
        return "match/deletebut";
    }


    @RequestMapping(value = "/match/deletecartoncarton", method = RequestMethod.POST)
    public String deletecartoncarton(HttpServletRequest request, Model model, @RequestParam("id") String id) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        CartonsEntity cartonsentity = new CartonsEntity();
        Cartons carton = cartonsentity.find(Integer.parseInt(id));
        int idc = carton.getMatch().getId();
        if (cartonsentity.delete(carton)) {
            return "redirect:/match/gestion-score?id=" + idc + "&ok=1";
        } else {
            return "redirect:/match/gestion-score?id=" + idc + "&error=3";
        }

    }

    //fin 

    @RequestMapping("/match/save-but")
    public String savebut(HttpServletRequest request, Model model, @RequestParam("match") String matchid, @RequestParam("joueur") String joueurid, @RequestParam("minute") String minute) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        MatchEntity matchentity = new MatchEntity();
        Match match = matchentity.find(Integer.parseInt(matchid));

        JoueurEntity joueurentity = new JoueurEntity();
        Joueur joueur = joueurentity.find(Integer.parseInt(joueurid));
        But but = new But();
        but.setJoueur(joueur);
        but.setMatch(match);
        but.setMinute(Integer.parseInt(minute));

        ButEntity butentity = new ButEntity();

        if (butentity.save(but)) {
            return "redirect:/match/gestion-score?id=" + matchid + "&ok=1";
        } else {
            return "redirect:/match/gestion-score?id=" + matchid + "&error=1";
        }


    }


    @RequestMapping("/match/gestion-score")
    public String gestionscore(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String id = request.getParameter("id");
        MatchEntity matchentity = new MatchEntity();
        Match match = matchentity.find(Integer.parseInt(id));
        model.addAttribute("match", match);
        List<Joueur> listeselection = new ArrayList();
        listeselection = match.getMatchJoueurList();

        List<Joueur> listeselectionequipe1 = new ArrayList();
        List<Joueur> listeselectionequipe2 = new ArrayList();
        //listejoueur selectionner 1
        for (int i = 0; i < listeselection.size(); i++) {
            if (listeselection.get(i).getEquipe().getId() == match.getEquipe1().getId()) {
                listeselectionequipe1.add(listeselection.get(i));
            } else {
                listeselectionequipe2.add(listeselection.get(i));
            }

        }


        //listejoueur selectionner 2
        //liste de joueurs equipe 1 et 2
        JoueurEntity joueurentity = new JoueurEntity();
        List<Joueur> joueurs1 = joueurentity.findAllEquipe(match.getEquipe1());
        List<Joueur> joueurs2 = joueurentity.findAllEquipe(match.getEquipe2());
        model.addAttribute("joueurs1", joueurs1);
        model.addAttribute("joueurs2", joueurs2);
        model.addAttribute("listeselection", listeselection);
        model.addAttribute("listeselectionequipe2", listeselectionequipe2);
        model.addAttribute("listeselectionequipe1", listeselectionequipe1);

        // liste des buts

        ButEntity butentity = new ButEntity();
        List<But> lesbuts = butentity.findButMatch(match);

        model.addAttribute("lesbuts", lesbuts);

        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("ok", request.getParameter("ok"));

        // les cartons

        CartonsEntity cartonsentity = new CartonsEntity();
        List<Cartons> lescartons = cartonsentity.findCartonsMatch(match);
        model.addAttribute("lescartons", lescartons);

        return "match/gestionscore";

    }

    @RequestMapping("/match/terminer")
    public String terminer(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String id = request.getParameter("id");
        MatchEntity matchentity = new MatchEntity();
        Match match = matchentity.find(Integer.parseInt(id));
        match.setStatus(true);
        int championnatid = match.getChampionnat().getId();
        if (matchentity.update(match)) {
            return "redirect:/match/gestion-score?id=" + id;
        } else {
            return "redirect:/championnat/classement?id=" + championnatid + "&error=1";
        }


    }

    @RequestMapping(value = "/match/addsave", method = RequestMethod.POST)
    public String addsave(HttpServletRequest request, Model model, @RequestParam("championnat") String championnatid, @RequestParam("equipe1") String idequipe1, @RequestParam("equipe2") String idequipe2, @RequestParam("date") String date) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        if (idequipe1.equals(idequipe2)) {
            return "redirect:/championnat/classement?id=" + championnatid + "&error=1";
        }

        ChampionnatEntity championnatentity = new ChampionnatEntity();
        Championnat championnat = championnatentity.find(Integer.parseInt(championnatid));

        EquipeEntity equipeentity = new EquipeEntity();
        Equipe equipe1 = equipeentity.find(Integer.parseInt(idequipe1));
        Equipe equipe2 = equipeentity.find(Integer.parseInt(idequipe2));

        Match match = new Match();
        match.setEquipe1(equipe1);
        match.setEquipe2(equipe2);
        match.setChampionnat(championnat);


        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            Date date1 = (Date) formatter.parse(date);
            match.setStatus(false);
            match.setJourheurematch(date1);


            MatchEntity matchentity = new MatchEntity();
            boolean test = matchentity.save(match);
            if (test) {
                //rediriger pour ajouter les joueurs au match
                return "redirect:/championnat/classement?id=" + championnatid + "&ok=1";
            } else {
                return "redirect:/championnat/classement?id=" + championnatid + "&error=2";
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/championnat/classement?id=" + championnatid + "&error=3";

        }


    }


    @RequestMapping("/match/gerer-equipe")
    public String update(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String id = request.getParameter("id");
        MatchEntity matchentity = new MatchEntity();
        Match match = matchentity.find(Integer.parseInt(id));
        model.addAttribute("match", match);
        List<Joueur> listeselection = new ArrayList();
        listeselection = match.getMatchJoueurList();

        List<Joueur> listeselectionequipe1 = new ArrayList();
        List<Joueur> listeselectionequipe2 = new ArrayList();
        //listejoueur selectionner 1
        for (int i = 0; i < listeselection.size(); i++) {
            if (listeselection.get(i).getEquipe().getId() == match.getEquipe1().getId()) {
                listeselectionequipe1.add(listeselection.get(i));
            } else {
                listeselectionequipe2.add(listeselection.get(i));
            }

        }
        // TODO implementer le fait qu'un joueur suspendu ne peut pas être classé
        //TODO implementer le fait qu'une equipe qui n'a pas suffisamment de joueurs classés est declarée forfait


        //listejoueur selectionner 2
        //liste de joueurs equipe 1 et 2
        JoueurEntity joueurentity = new JoueurEntity();
        List<Joueur> joueurs1 = joueurentity.findAllEquipe(match.getEquipe1());
        List<Joueur> joueurs2 = joueurentity.findAllEquipe(match.getEquipe2());
        model.addAttribute("joueurs1", joueurs1);
        model.addAttribute("joueurs2", joueurs2);
        model.addAttribute("listeselection", listeselection);
        model.addAttribute("listeselectionequipe2", listeselectionequipe2);
        model.addAttribute("listeselectionequipe1", listeselectionequipe1);

        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("ok", request.getParameter("ok"));

        return "match/gererequipe";
    }

    @RequestMapping("/match/liste")
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

    @RequestMapping(value = "/match/savejoueur", method = RequestMethod.POST)
    public String updatesave(HttpServletRequest request, Model model, @RequestParam("match") String matchid, @RequestParam("lesjoueurs1") String lesjoueurs1, @RequestParam("lesjoueurs2") String lesjoueurs2) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        MatchEntity matchentity = new MatchEntity();
        Match match = matchentity.find(Integer.parseInt(matchid));
        String[] tab = lesjoueurs1.split(",");

        JoueurEntity joueurentity = new JoueurEntity();


        List<Joueur> joueurs = new ArrayList();
        for (int i = 0; i < tab.length; i++) {
            Joueur joueur = joueurentity.find(Integer.parseInt(tab[i]));
            joueurs.add(joueur);

        }

        String[] tab2 = lesjoueurs2.split(",");

        //verifier si cela depasse 5 par match

        if ((tab2.length != 5) && (tab.length != 5)) {
            return "redirect:/match/gerer-equipe?id=" + matchid + "&error=3";
        }

        for (int i = 0; i < tab2.length; i++) {
            Joueur joueur = joueurentity.find(Integer.parseInt(tab2[i]));
            joueurs.add(joueur);

        }

        match.setMatchJoueurList(joueurs);

        if (matchentity.update(match)) {
            return "redirect:/match/gerer-equipe?id=" + matchid + "&ok=1";
        } else {
            return "redirect:/match/gerer-equipe?id=" + matchid + "&error=1";
        }


    }

    @RequestMapping("/match/deletebut")
    public String deletebut(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String id = request.getParameter("id");
        ButEntity butentity = new ButEntity();
        But but = butentity.find(Integer.parseInt(id));

        model.addAttribute("but", but);
        return "match/deletebut";
    }

    @RequestMapping("/match/delete")
    public String delete(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String id = request.getParameter("id");
        MatchEntity matchentity = new MatchEntity();
        Match match = matchentity.find(Integer.parseInt(id));

        model.addAttribute("match", match);
        return "match/delete";
    }

    @RequestMapping(value = "/match/deletematch", method = RequestMethod.POST)
    public String deleteequipe(HttpServletRequest request, Model model, @RequestParam("id") String id) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        MatchEntity matchentity = new MatchEntity();
        Match match = matchentity.find(Integer.parseInt(id));
        int idc = match.getChampionnat().getId();
        if (matchentity.delete(match)) {
            return "redirect:/championnat/classement?id=" + idc + "&ok=1";
        } else {
            return "redirect:/championnat/classement?id=" + idc + "&error=3";
        }

    }


    @RequestMapping(value = "/match/deletebutbut", method = RequestMethod.POST)
    public String deletebutbut(HttpServletRequest request, Model model, @RequestParam("id") String id) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        ButEntity butentity = new ButEntity();
        But but = butentity.find(Integer.parseInt(id));
        int idc = but.getMatch().getId();
        if (butentity.delete(but)) {
            return "redirect:/match/gestion-score?id=" + idc + "&ok=1";
        } else {
            return "redirect:/match/gestion-score?id=" + idc + "&error=3";
        }

    }

}
