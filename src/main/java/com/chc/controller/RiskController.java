package com.chc.controller;

// Coded by ULUGBEK ( ULA ) ARIPOV
// using guidance in
// http://forum.thymeleaf.org/The-checked-attribute-of-the-checkbox-is-not-set-in-th-each-td3043675.html

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chc.model.Risk;
import com.chc.other.Topics;
import com.chc.other.Types;
import com.chc.service.RiskService;

@Controller
public class RiskController {
    @Autowired
    private RiskService riskService;

    // Index of all risks
    @GetMapping("/risks")
    public String listRisks(Model model) {

        model.addAttribute("risks",riskService.findAll());
        return "index";
    }

    // Form for adding a new risk
    @GetMapping("risks/add")
    public String formNewRisk(Model model) {

        if(!model.containsAttribute("risk")) {
            model.addAttribute("risk",new Risk());
        }
        model.addAttribute("topics", Topics.values());
        model.addAttribute("types", Types.values());
        model.addAttribute("action","/risks");
        model.addAttribute("heading","New Risk");
        model.addAttribute("submit","Add");

        return "riskform";
    }

    // Add a risk
    @PostMapping(value = "/risks")
    public String addRisk(@Valid Risk risk, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.risk",result);

            // Add  risk if invalid was received
            redirectAttributes.addFlashAttribute("risk",risk);

            // Redirect back to the form
            return "redirect:/risks/add";
        }

        riskService.save(risk);

        return "redirect:/risks";
    }

    
    // Form for editing an existing risk
    @GetMapping("risks/{riskId}/edit")
    public String formEditRisk(@PathVariable Long riskId, Model model) {

        if(!model.containsAttribute("risk")) {
            model.addAttribute("risk",riskService.findById(riskId));
        }
        model.addAttribute("topics", Topics.values());
        model.addAttribute("types", Types.values());
        model.addAttribute("action",String.format("/risks/%s",riskId));
        model.addAttribute("heading","Edit Risk");
        model.addAttribute("submit","Update");

        return "riskform";
    }

    // Update an existing risk
    @PostMapping(value = "/risks/{riskId}")
    public String updateRisk(@Valid Risk risk, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.risk",result);

            // Add  risk if invalid was received
            redirectAttributes.addFlashAttribute("risk",risk);

            // Redirect back to the form
            return String.format("redirect:/risks/%s/edit",risk.getId());
        }

        riskService.save(risk);

        return "redirect:/risks";
    }


}