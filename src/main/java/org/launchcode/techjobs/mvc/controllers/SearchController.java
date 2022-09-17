package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Objects;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;
import static org.launchcode.techjobs.mvc.models.JobData.findAll;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam(required = false) String searchTerm){
        ArrayList<Job> jobs;
        String column = searchType;
        String value = searchTerm;
        if (value.equals("all")){
            jobs = findAll();
        } else if(column.equals("all")) {
            jobs = JobData.findByValue(searchTerm);

        } else {
            jobs = JobData.findByColumnAndValue(column,value);
        }
        model.addAttribute("title", "Jobs with " + column + ": " + value);
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs", jobs);

        return "search";

    }
}
