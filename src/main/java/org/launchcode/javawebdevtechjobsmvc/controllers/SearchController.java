package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @RequestMapping(value = "results", method = {RequestMethod.POST})
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs = new ArrayList<>();
        if (searchType.toLowerCase().equals("all")) {
            model.addAttribute("title", "All Jobs containing: " + searchTerm);
        } else {
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        }
        jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        model.addAttribute("searchTerm", searchTerm);

        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
}

//    @PostMapping(value = "results")
//    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
//        ArrayList<Job> jobs;
//        searchTerm = searchTerm.toLowerCase();
//        if (searchType.equals("all") || searchTerm == null || searchTerm.equals("all")) {
//            jobs = JobData.findAll();
//            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
//        } else {
//            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
//            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
//        }
//        model.addAttribute("jobs", jobs);
//        model.addAttribute("columns", columnChoices);
//        return "search";
//    }
//}
