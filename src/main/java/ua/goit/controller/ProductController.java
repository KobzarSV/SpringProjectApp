package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.controller.propertyEditors.ManufacturerDtoPropertyEditor;
import ua.goit.model.dto.ManufacturerDto;
import ua.goit.model.dto.ProductDto;
import ua.goit.service.ManufacturerService;
import ua.goit.service.ProductService;

import javax.validation.Valid;
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

    @GetMapping(path = "/form/find")
    public String findProductForm() {
        return "findProductForm";
    }

    @GetMapping(path = "/id/{id}")
    public ModelAndView getProductById(@PathVariable("id") UUID productId, ModelAndView modelAndView) {
        ProductDto product = productService.findById(productId);
        modelAndView.addObject("product", product);
        modelAndView.setViewName("findProductById");
        return modelAndView;
    }

    @GetMapping(path = "/all")
    public String getAllProducts(Model model) {
        List<ProductDto> products = productService.findAll();
        model.addAttribute("products", products);
        return "allProducts";
    }

    @GetMapping(path = "/form/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductForm(Model model) {
        List<ManufacturerDto> manufacturer = manufacturerService.findAll();
        model.addAttribute("manufacturer", manufacturer);
        return "addProductForm";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addProduct(@ModelAttribute("productDto")
                                   @Valid ProductDto productDto, BindingResult bindingResult, ModelAndView model) {
        if (bindingResult.hasErrors()) {
            List<ManufacturerDto> manufacturer = manufacturerService.findAll();
            model.addObject("manufacturer", manufacturer);
            model.setViewName("addProductForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        productService.saveOrUpdate(productDto);
        model.setViewName("redirect:/products/all");
        model.setStatus(HttpStatus.CREATED);
        return model;
    }

    @GetMapping(path = "/form/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateProductForm(@PathVariable("id") UUID id, Model model) {
        ProductDto product = productService.findById(id);
        model.addAttribute("product", product);
        List<ManufacturerDto> manufacturer = manufacturerService.findAll();
        model.addAttribute("manufacturer", manufacturer);
        return "updateProductForm";
    }

    @PostMapping(path = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateProduct(@PathVariable("id") UUID id,
                                      @ModelAttribute("product") @Valid ProductDto productDto,
                                      BindingResult bindingResult, ModelAndView model) {
        if (bindingResult.hasErrors()) {
            List<ManufacturerDto> manufacturer = manufacturerService.findAll();
            model.addObject("manufacturer", manufacturer);
            model.setViewName("updateProductForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        productService.saveOrUpdate(productDto);
        model.setViewName("redirect:/products/all");
        model.setStatus(HttpStatus.CREATED);
        return model;
    }

    @GetMapping(path = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteProduct(@PathVariable("id") UUID productId) {
        ProductDto product = productService.findById(productId);
        productService.delete(product);
        return "redirect:/products/all";
    }

    @ModelAttribute
    public ProductDto getDefaultProductDto() {
        return new ProductDto();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(ManufacturerDto.class, new ManufacturerDtoPropertyEditor());
    }
}
