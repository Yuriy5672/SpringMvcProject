package com.example.springmvc.solution.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first") //создает доп аддрес для перехода
public class FirstController {
    @GetMapping("/hello")
    //public String helloPage(@RequestParam("name") String name, @RequestParam("surname") String surname) //Получить конкретные параметры
    public String helloPage(HttpServletRequest request, Model model) { //HttpServletRequest - содержит все пораметры поступающего на сервер запроса
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        //System.out.println("Hello " + name + " " + surname);
        model.addAttribute("message", "Hello " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage(){
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculatorPage(@RequestParam(value = "a", required = false) int a,
                                 @RequestParam(value = "b", required = false) int b,
                                 @RequestParam(value = "action", required = false) String action,
                                 Model model){

        if(action != null){
            switch (action){
                case "multiplication":
                    model.addAttribute("result", "Result: " + (a * b));
                    break;
                case "addition":
                    model.addAttribute("result", "Result: " + (a + b));
                    break;
                case "subtraction":
                    model.addAttribute("result", "Result: " + (a - b));
                    break;
                case "division":
                    model.addAttribute("result", "Result: " + (a / b));
                    break;
                default:
                    model.addAttribute("result", "Incorrect 'Action' value was entered:" +
                            action + " a = " + a + " b = " + b);
                    break;
            }
        }
        return "first/calculator";
    }

}
