package com.example.pizzaapptemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @Autowired
    PizzaOrderRepository pizzaOrderRepository;

    @GetMapping("/")
    public String display(Model model) {
        model.addAttribute("list", pizzaOrderRepository.findAll());
        return "display";
    }


    @GetMapping("/addPizzaOrder")
    public String addPizzaOrder(Model model) {
        model.addAttribute("order", new PizzaOrder());
        return "index";
    }
    @PostMapping("/addPizzaOrder")
    public @ResponseBody String addPizzaOrder(HttpServletResponse  response, HttpServletRequest request){

        PizzaOrder p = new PizzaOrder();
        p.setName(request.getParameter("name"));
        p.setToppings(request.getParameter("toppings"));
        pizzaOrderRepository.save(p);
        return "redirect:/";
    }

}
