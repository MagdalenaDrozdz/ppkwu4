package com.example.demo.serwis;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import com.example.demo.Data;
import com.example.demo.User;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.property.Address;
import ezvcard.property.Revision;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.jsoup.nodes.Document;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@org.springframework.stereotype.Service


public class Serwis {
    List<User> userList = new ArrayList<User>();
    Data dataSearched = new Data();

    public String getData(Model model) {
        Data data = new Data();
        model.addAttribute("data", data);
        return "index";
    }

    public String getUsers(@ModelAttribute Data data, Model model) {
        Document document = null;
        Elements elements;
        int i=0;

        if(data.getName() != null && !data.getName().isEmpty()) {
            dataSearched.setName(data.getName());
            dataSearched.setPage(1);
        }
        try {
            document = Jsoup.connect("https://panoramafirm.pl/" + dataSearched.getName() + "/firmy,"+ dataSearched.getPage() + ".html").get();
            userList.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }

        elements = document.select("li.card.company-item");

        for (Element e : elements) {
            User user = new User();

            String name = e.select("h2").text();
            String telefon = e.select("a.icon-telephone").attr("title");
            String web = e.select("a.icon-website").attr("href");
            String mail = e.select("a.ajax-modal-link").attr("data-company-email");
            String address = e.select("div.address").text();
            String szer = e.select("a.icon-check-point").attr("data-lat");
            String dlug = e.select("a.icon-check-point").attr("data-lon");
            user.setName(name);
            user.setAddress(address);
            user.setMail(mail);
            user.setNumber(telefon);
            user.setWeb(web);
            user.setSzer(szer);
            user.setDlug(dlug);
            user.setVcard(String.valueOf(i++));

            userList.add(user);
        }

        model.addAttribute("users", userList);
        return "users";
    }

    public ResponseEntity<Resource> getCard(@ModelAttribute String userForCard, Model model) {
        VCard card = new VCard();
        User user = new User();
        HttpHeaders headers = new HttpHeaders();
        String nameFile = "card"+userForCard+".vcf";
        File file= new File("card.vcf");
        Address address1 = new Address();

        for(User u: userList){
            if(u.getVcard().equals(userForCard)){
                user.setName(u.getName());
                user.setAddress(u.getAddress());

                String[] adres=user.getAddress().split(",");
                if(adres.length > 1) {
                    address1.setStreetAddress(adres[0]);
                    address1.setPostalCode(adres[1].substring(0, 7));
                    address1.setLocality(adres[1].substring(7));
                    address1.setCountry("Polska");
                }
                else {
                    address1.setLocality(adres[0].substring(7));
                    address1.setPostalCode(adres[0].substring(0, 7));
                    address1.setCountry("Polska");
                }
                user.setNumber(u.getNumber());
                user.setMail(u.getMail());
                user.setWeb(u.getWeb());
            }
        }

        card.setRevision(Revision.now());
        card.setFormattedName(user.getName());
        card.addEmail(user.getMail());
        card.addTelephoneNumber(user.getNumber());
        card.addUrl(user.getWeb());

        card.addAddress(address1);

        try {
            Ezvcard.write(card).version(VCardVersion.V4_0).go(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=card.vcf");

        Resource fileSystemResource = new FileSystemResource("card.vcf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(fileSystemResource);
    }

    public String getUsersNext(@ModelAttribute Data data, Model model) {
        dataSearched.setPage(dataSearched.getPage()+1);
        return getUsers(data,model);
    }

}
