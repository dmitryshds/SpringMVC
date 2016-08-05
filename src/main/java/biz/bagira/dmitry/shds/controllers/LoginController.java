package biz.bagira.dmitry.shds.controllers;

import biz.bagira.dmitry.shds.objects.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by Дмитрий on 31.07.2016.
 */
@Controller
@SessionAttributes("user")
public class LoginController {
    private static final int WEAK_STRENGTH = 3;
    private static final int FEAR_STRENGTH = 5;
    private static final int STRONG_STRENGTH = 7;

    private static final String WEAK_COLOR = "#FF0000";
    private static final String FEAR_COLOR = "#FF9900";
    private static final String STRONG_COLOR = "#0099CC";

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ModelAttribute
    public User createNewUser(){
        return new User("usernamevalue");
    }

 @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main(HttpSession httpSession){

     return new org.springframework.web.servlet.ModelAndView("index","user",new User());
 }

    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    public org.springframework.web.servlet.ModelAndView checkUser(@Valid @ModelAttribute("user") User user,BindingResult result, RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView();
        if (!result.hasErrors()){

        RedirectView redirectView = new RedirectView("mainpage");
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        modelAndView.setView(redirectView);

        redirectAttributes.addFlashAttribute("redirect",true);



        return modelAndView;}
        else{return new ModelAndView("index","",null);}
    }
// @RequestMapping(value = "/check-user", method = RequestMethod.POST)
//    public String checkUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
//     redirectAttributes.addFlashAttribute("redirect",true);
//        return "redirect:/mainpage";
//    }
    @RequestMapping(value = "/mainpage", method = RequestMethod.GET)
    public String goMainPage(HttpServletRequest request){

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null)
        {
            logger.info("redirect!!!!!!!!!");

        }else {
            logger.info("Update!!!");
        }

       return "main";
    }

    @RequestMapping(value = "/checkStrength", method = RequestMethod.GET, produces = { "text/html; charset=UTF-8" })

    public @ResponseBody
    String checkStrengthPassword(@RequestParam String password)
{
    String result = "<span style=\"color:%s; font-weight:bold;\">%s</span>";
    if (password.length() >= WEAK_STRENGTH & password.length() < FEAR_STRENGTH) {

        return String.format(result,WEAK_COLOR,"Слабый пароль");
    } else if (password.length() >= FEAR_STRENGTH & password.length() < STRONG_STRENGTH) {
        return String.format(result,FEAR_COLOR,"Средний пароль");
    } else if (password.length() >= STRONG_STRENGTH) {
        return String.format(result,STRONG_COLOR,"Сильный пароль");
    }
    return "";
}

}
