package main.artfix.passtimenote.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Oops, something went wrong!");
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

}

