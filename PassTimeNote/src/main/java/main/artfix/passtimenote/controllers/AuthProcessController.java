package main.artfix.passtimenote.controllers;

import lombok.RequiredArgsConstructor;
import main.artfix.passtimenote.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/process")
@RequiredArgsConstructor
public class AuthProcessController {

    private final RegistrationService registrationService;
    private final LoginService loginService;
    private final VerifyService verifyService;

    @RequestMapping("/registration")
    public String regProcessPage(@RequestParam("REGFormName") String RegName,
                                 @RequestParam("REGFormMail") String RegMail,
                                 @RequestParam("REGFormPassword") String RegPassword, Model model) {
        registrationService.registration(RegName, RegMail, RegPassword);
        model.addAttribute("RegPageMessage", registrationService.regMessage);
        return "reg";
    }

    @RequestMapping("/login")
    public String loginProcessPage(@RequestParam("LOGINFormMail") String LoginMail,
                                   @RequestParam("LOGINFormPassword") String LoginPassword, Model model) {
        loginService.login(LoginMail, LoginPassword);
        model.addAttribute("LoginPageMessage", loginService.loginMessage);
        return "log";
    }

    @RequestMapping("/verify")
    public String verifyProcessPage(@RequestParam("MailForVerify") String MailForVerify,
                                    @RequestParam("VerificationCode") String VerificationCode, Model model) {
        verifyService.verify(MailForVerify, VerificationCode);
        model.addAttribute("HomePageMessage", "Verified Successfully!");
        return "home";
    }
}