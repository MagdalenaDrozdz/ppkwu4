package com.example.demo.restapi;

import com.example.demo.Data;
import com.example.demo.serwis.Serwis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class Restapi {

    private Serwis serwis;

    @Autowired
    public Restapi(Serwis serwis) {
        this.serwis = serwis;
    }

    @GetMapping("/")
    public String getData(Model model) throws IOException {
        return serwis.getData(model);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String getUsers(@ModelAttribute Data data, Model model){
        return serwis.getUsers(data,model);
    }

    @RequestMapping(value = "/card/{user}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> getCard(@PathVariable String user, Model model) throws IOException {
        return serwis.getCard(user, model);
    }

    @RequestMapping(value = "/users/next", method = RequestMethod.GET)
    public String getUsersNext(@ModelAttribute Data data, Model model){
        return serwis.getUsersNext(data,model);
    }


}
