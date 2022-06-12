package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.exception.ManufacturerNotFoundException;
import ua.goit.model.dto.ManufacturerDto;
import ua.goit.service.ManufacturerService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping(path = "/form/find")
    public String findManufacturerForm() {
        return "findManufacturerForm";
    }

    @GetMapping(path = "/id/{id}")
    public ModelAndView getManufacturer(@PathVariable("id") UUID manufacturerId, ModelAndView modelAndView) {
        ManufacturerDto manufacturer = manufacturerService.findById(manufacturerId);
        modelAndView.addObject("manufacturer", manufacturer);
        modelAndView.setViewName("findManufacturerById");
        return modelAndView;
    }

    @GetMapping(path = "/all")
    public String getAllManufacturer(Model model) {
        List<ManufacturerDto> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "allManufacturers";
    }

    @GetMapping(path = "/form/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addManufacturerForm() {
        return "addManufacturerForm";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addManufacturer(@ModelAttribute("manufacturerDto") @Valid ManufacturerDto manufacturerDto,
                                        BindingResult bindingResult, ModelAndView model) {
        if (bindingResult.hasErrors()) {
            model.setViewName("addManufacturerForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        try {
            manufacturerService.saveOrUpdate(manufacturerDto);
            model.setViewName("redirect:/manufacturer/all");
            model.setStatus(HttpStatus.CREATED);
        } catch (ManufacturerNotFoundException ex) {
            model.addObject("message", ex.getMessage());
            model.setViewName("addManufacturerForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        return model;
    }

    @GetMapping(path = "/form/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateManufacturerForm(@PathVariable("id") UUID id, Model model) {
        ManufacturerDto manufacturer = manufacturerService.findById(id);
        model.addAttribute("manufacturer", manufacturer);
        return "updateManufacturerForm";
    }

    @PostMapping(path = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateManufacturer(@PathVariable("id") UUID id,
                                           @ModelAttribute("manufacturer") @Valid ManufacturerDto manufacturerDto,
                                           BindingResult bindingResult, ModelAndView model) {
        ManufacturerDto manufacturer = manufacturerService.findById(id);
        if (bindingResult.hasErrors()) {
            model.setViewName("updateManufacturerForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        try {
            manufacturer.setName(manufacturerDto.getName());
            manufacturerService.saveOrUpdate(manufacturer);
            model.setViewName("redirect:/manufacturer/all");
        } catch (ManufacturerNotFoundException ex) {
            model.addObject("message", ex.getMessage());
            model.setViewName("updateManufacturerForm");
            return model;
        }
        return model;
    }

    @GetMapping(path = "/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteManufacturer(@RequestParam("id") UUID id, Model model) {
        ManufacturerDto manufacturer = manufacturerService.findById(id);
        try {
            manufacturerService.delete(manufacturer);
            List<ManufacturerDto> manufacturers = manufacturerService.findAll();
            model.addAttribute("manufacturers", manufacturers);
        } catch (Exception ex) {
            model.addAttribute("message",
                    "Manufacturer \"" + manufacturer.getName() + "\" has products. To remove a manufacturer, first remove its products!");
            List<ManufacturerDto> manufacturers = manufacturerService.findAll();
            model.addAttribute("manufacturers", manufacturers);
            return  "allManufacturers";
        }
        return "allManufacturers";
    }

    @ModelAttribute
    public ManufacturerDto getDefaultManufacturerDto() {
        return new ManufacturerDto();
    }
}
