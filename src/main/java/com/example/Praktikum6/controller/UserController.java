package com.example.Praktikum6.controller;


import com.example.Praktikum6.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    // GANTI DENGAN DATA KAMU
    private final String USERNAME = "admin";
    private final String PASSWORD = "087"; // contoh: 20210140019
    private final String JUDUL_WEB = "WEBSITE WONG TULUS";
    private final String NAMA_MAHASISWA = "Azis Dwi Mukti";

    // Data temporary, tidak disimpan ke database
    private final List<User> dataMahasiswa = new ArrayList<>();

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String prosesLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Model model) {

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            model.addAttribute("judulWeb", JUDUL_WEB);
            model.addAttribute("namaMahasiswa", NAMA_MAHASISWA);
            model.addAttribute("listUser", dataMahasiswa);
            return "home";
        } else {
            model.addAttribute("error", "Username atau password salah.");
            return "login";
        }
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("judulWeb", JUDUL_WEB);
        model.addAttribute("namaMahasiswa", NAMA_MAHASISWA);
        model.addAttribute("listUser", dataMahasiswa);
        return "home";
    }

    @GetMapping("/form")
    public String formPage(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/form")
    public String simpanData(@ModelAttribute User user, Model model) {
        dataMahasiswa.add(user);

        model.addAttribute("judulWeb", JUDUL_WEB);
        model.addAttribute("namaMahasiswa", NAMA_MAHASISWA);
        model.addAttribute("listUser", dataMahasiswa);

        return "home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}