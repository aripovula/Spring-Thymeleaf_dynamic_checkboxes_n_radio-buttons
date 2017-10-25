package com.dch.controller;

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

import com.dch.model.Risk;
import com.dch.other.DoOtherStuff;
import com.dch.other.Modes;
import com.dch.service.RiskService;
import com.dch.service.TopicsService;
import com.dch.service.TypesService;

@Controller
public class RiskController {
    
	@Autowired
    private RiskService riskService;

	@Autowired
    private TopicsService topicsService;

	@Autowired
    private TypesService typesService;

	@Autowired
    private DoOtherStuff doOtherStuff;

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
        
        // We explicitly call method which dynamically creates Topics and Types - in your implementation you may want to remove this call
        // And make sure that at least one instance of both objects are created before this Controller is reached.
        // Implement your dynamic checkbox creating class as shown here - through Autowiring and through interface to achieve loose coupling 
        doOtherStuff.doStuff();
        
        model.addAttribute("topics", topicsService.findAll());
        model.addAttribute("types", typesService.findAll());
        model.addAttribute("modes", Modes.values());
        model.addAttribute("action","/risks");
        model.addAttribute("heading","New Risk");
        model.addAttribute("submit","Add");

        return "riskform";
    }
    
    // Form for adding a new risk  - checkboxes in table form
    @GetMapping("/riskstable/add")
    public String formNewRiskTable(Model model) {

        if(!model.containsAttribute("risk")) {
            model.addAttribute("risk",new Risk());
        }

        // We explicitly call method which dynamically creates Topics and Types - in your implementation you may want to remove this call
        // Make sure that at least one instance of both objects are created before this Controller is reached. 
        // Implement your dynamic checkbox creating class as shown here - through Autowiring and through interface to achieve loose coupling
        doOtherStuff.doStuff();
        
        model.addAttribute("topics", topicsService.findAll());
        model.addAttribute("types", typesService.findAll());

        model.addAttribute("modes", Modes.values());
        model.addAttribute("action","/risks");
        model.addAttribute("heading","New Risk");
        model.addAttribute("submit","Add");
        
        // for tabulating checkboxes - please remove following 2 lines if you do not need checkboxes in table form
        model.addAttribute("begincolumn", "<tr>");
        model.addAttribute("endcolumn", "</tr>");


        return "riskformtable";
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

        model.addAttribute("topics", topicsService.findAll());
        model.addAttribute("types", typesService.findAll());

        model.addAttribute("modes", Modes.values());
        model.addAttribute("action",String.format("/risks/%s",riskId));
        model.addAttribute("heading","Edit Risk");
        model.addAttribute("submit","Update");
        
        return "riskform";
    }
    
    // Form for editing an existing risk - checkboxes in table form
    @GetMapping("riskstable/{riskId}/edit")
    public String formEditRiskTable(@PathVariable Long riskId, Model model) {

        if(!model.containsAttribute("risk")) {
            model.addAttribute("risk",riskService.findById(riskId));
        }

        model.addAttribute("topics", topicsService.findAll());
        model.addAttribute("types", typesService.findAll());

        model.addAttribute("modes", Modes.values());
        model.addAttribute("action",String.format("/risks/%s",riskId));
        model.addAttribute("heading","Edit Risk");
        model.addAttribute("submit","Update");
        
        // for tabulating checkboxes - please remove following 2 lines if you do not need checkboxes in table form
        model.addAttribute("begincolumn", "<tr>");
        model.addAttribute("endcolumn", "</tr>");

        return "riskformtable";
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