package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.model.dto.ManufacturerDto;
import ua.goit.model.dto.ProductDto;
import ua.goit.service.ManufacturerService;
import ua.goit.service.ProductService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;
    private final ManufacturerService manufacturerService;

    @Autowired
    public ProductController(ProductService productService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping(path = "/findProductForm")
    public String findProductForm() {
        return "findProductForm";
    }

    @GetMapping(path = "/findProduct")
    public String findProduct(@RequestParam("findById") UUID productId, Model model) {
        ProductDto product = productService.findById(productId);
        model.addAttribute("product", product);
        return "findProduct";
    }

    @GetMapping(path = "/id/{id}")
    public ModelAndView getProductById(@PathVariable("id") UUID productId, ModelAndView modelAndView) {
        ProductDto product = productService.findById(productId);
        modelAndView.addObject("product", product);
        modelAndView.setViewName("findProductById");
        return modelAndView;
    }

    @GetMapping(path = "/addProductForm")
    public String addProductForm(Model model) {
        List<ManufacturerDto> manufacturer = manufacturerService.findAll();
        model.addAttribute("manufacturer", manufacturer);
        return "addProductForm";
    }

    @ModelAttribute
    public ProductDto getDefaultProductDto() {
        return new ProductDto();
    }
}
