package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("unused")
@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView loginView(HttpServletRequest request,
                                  HttpServletResponse response,
                                  Model modelUi
    ) {

        ModelAndView model = new ModelAndView("login");
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        if (savedRequest == null) return model;

        String requestedUrl = savedRequest.getRedirectUrl();

        model.addObject("requestedUrl", requestedUrl);
        String[] paramVal = savedRequest.getParameterValues("signupSuccess");

        if (paramVal != null && paramVal.length > 0) {
            modelUi.addAttribute("signupSuccess", Boolean.parseBoolean(paramVal[0]));
        }

        return model;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "login";
    }
}
