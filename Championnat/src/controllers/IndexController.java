package controllers;


import entity.UserEntity;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller

public class IndexController {

    @RequestMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        return "home";
    }


    @RequestMapping("/register")
    public String register(HttpServletRequest request, Model model) {


        model.addAttribute("error", request.getParameter("error"));
        return "register";
    }


    @RequestMapping(value = "/registersave", method = RequestMethod.POST)
    public String registersave(HttpServletRequest request, Model model, @RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("login") String login, @RequestParam("motpasse1") String motpasse1, @RequestParam("motpasse2") String motpasse2) {
        User user = new User();
        UserEntity userentity = new UserEntity();
        if (motpasse1.equals(motpasse2)) {

            user.setLogin(login);
            user.setMotpasse(motpasse1);
            user.setNom(nom);
            user.setPrenom(prenom);
            if (userentity.save(user)) {
                //HttpSession session = request.getSession();
                //session.setAttribute("user", user);
                return "redirect:/login";
            } else {
                return "redirect:/register?error=2";
            }


        } else {
            return "redirect:/register?error=1";
        }


    }


    @RequestMapping("/changermotpasse")
    public String changermotpasse(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("ok", request.getParameter("ok"));
        model.addAttribute("error", request.getParameter("error"));

        return "changermotpasse";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }


    @RequestMapping(value = "/changermotpasseform", method = RequestMethod.POST)
    public String changermotpasseform(HttpServletRequest request, Model model, @RequestParam("ancienmotpasse") String ancienmotpasse, @RequestParam("nouveaumotpasse") String nouveaumotpasse, @RequestParam("renouveaumotpasse") String renouveaumotpasse) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        if (user.getMotpasse().equals(ancienmotpasse)) {
            if (nouveaumotpasse.equals(renouveaumotpasse)) {
                user.setMotpasse(nouveaumotpasse);
                UserEntity userentity = new UserEntity();
                boolean test = userentity.update(user);
                if (test) {
                    return "redirect:/changermotpasse?ok=1";
                } else {
                    return "redirect:/changermotpasse?error=3";
                }
            } else {
                return "redirect:/changermotpasse?error=2";
            }
        } else {
            return "redirect:/changermotpasse?error=1";
        }

    }

    @RequestMapping("/profil")
    public String profil(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("ok", request.getParameter("ok"));
        model.addAttribute("error", request.getParameter("error"));

        model.addAttribute("user", user);
        return "profil";
    }

    @RequestMapping(value = "/modifierprofil", method = RequestMethod.POST)
    public String modifierprofil(HttpServletRequest request, Model model, @RequestParam("login") String login, @RequestParam("nom") String nom, @RequestParam("prenom") String prenom) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        user.setLogin(login);
        user.setNom(nom);
        user.setPrenom(prenom);
        UserEntity userentity = new UserEntity();
        boolean test = userentity.update(user);
        if (test == true) {
            session.setAttribute("user", user);
            return "redirect:/profil?ok=1";
        } else {
            return "redirect:/profil?error=1";
        }


    }


    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        String error = request.getParameter("error");
        model.addAttribute("error", error);
        return "login";
    }

    @RequestMapping(value = "/checklogin", method = RequestMethod.POST)
    public String checklogin(HttpServletRequest request, Model model, @RequestParam("login") String login, @RequestParam("motpasse") String motpasse) {

        UserEntity userentity = new UserEntity();
        User user = userentity.findByUsername(login, motpasse);
        if (user == null) {
            return "redirect:/login?error=true";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/";
        }

    }

}
