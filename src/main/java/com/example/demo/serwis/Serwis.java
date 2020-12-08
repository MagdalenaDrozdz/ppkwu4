package com.example.demo.serwis;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import com.example.demo.Data;
import org.jsoup.Jsoup;
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

@org.springframework.stereotype.Service


public class Serwis {
    public String getData(Model model){
        Data data = new Data();
        model.addAttribute("data", data);
        return "index";
    }
}
