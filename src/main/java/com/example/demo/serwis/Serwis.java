package com.example.demo.serwis;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import com.example.demo.Data;
import com.example.demo.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.jsoup.nodes.Document;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@org.springframework.stereotype.Service


public class Serwis {
    public String getData(Model model){
        Data data = new Data();
        model.addAttribute("data", data);
        return "index";
    }

    public String getUsers(@ModelAttribute Data data, Model model) {
        Document document = null;

        try {
            document = Jsoup.connect("https://panoramafirm.pl/szukaj?k=" + data.getName()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(document);

        return "users";
    }
}
