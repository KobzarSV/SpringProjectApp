package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.goit.model.dto.ManufacturerDto;
import ua.goit.service.ManufacturerService;
import ua.goit.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;
    private final ProductService productService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService, ProductService productService) {
        this.manufacturerService = manufacturerService;
        this.productService = productService;
    }

    @GetMapping(path = "/form/find")
    public String findManufacturerForm() {
        return "findManufacturerForm";
    }

    @GetMapping(path = "/name/")
    public String findManufacturer(@RequestParam("name") String manufacturerName, Model model) {
        ManufacturerDto manufacturer = manufacturerService.findByName(manufacturerName);
        model.addAttribute("manufacturers", manufacturer);
        return "findManufacturer";
    }

    @GetMapping(path = "/addManufacturerForm")
    public String addManufacturerForm() {
        return "addManufacturerForm";
    }

    @PostMapping
    public String addManufacturer(@ModelAttribute("ManufacturerDto")
                                  @Valid ManufacturerDto manufacturerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addManufacturerForm";
        }
        manufacturerService.save(manufacturerDto);
        return "manufacturerAdded";
    }
}
