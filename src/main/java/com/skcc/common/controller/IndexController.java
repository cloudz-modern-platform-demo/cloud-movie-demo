package com.skcc.common.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skcc.authentication.service.UserService;
import com.skcc.authentication.vo.User;
import com.skcc.content.controller.ContentController;
import com.skcc.content.service.ContentService;
import com.skcc.content.vo.Content;

@Controller
public class IndexController {
	public static Logger logger = LoggerFactory.getLogger(ContentController.class);
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(path="/", method=RequestMethod.GET)
	public String getContents(HttpSession session, Model model){
		model.addAttribute("welcome", messageSource.getMessage("welcome", null, LocaleContextHolder.getLocale()));
		
		List<Content> contents = contentService.getContents(); 
		if (contents != null && !contents.isEmpty()) {
			model.addAttribute("contentsCount", contents.size());
			model.addAttribute("contents", contents);
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			User user = (User) auth.getPrincipal();
			session.setAttribute("user", user);
		}
		
		List<User> users = userService.getUsers();
		if (users != null) {
			model.addAttribute("usersCount", users.size());
		}
		
		logger.debug("authentication is {}", auth);
		
		logger.debug("Title in the session is {}", session.getAttribute("title"));

		return "index";
	}
}
