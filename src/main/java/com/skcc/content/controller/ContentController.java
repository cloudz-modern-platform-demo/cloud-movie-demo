package com.skcc.content.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skcc.content.service.ContentService;
import com.skcc.content.vo.Content;



@Controller
public class ContentController {
	
	public static Logger logger = LoggerFactory.getLogger(ContentController.class);
	
	private ContentService contentService;
	
	public ContentController(ContentService contentService){
		this.contentService = contentService;
	}
	

	@RequestMapping(path="/contents/{id}", method=RequestMethod.GET)
	public String getContent(@PathVariable("id")int id, Model model){
		model.addAttribute("content", contentService.getContentById(id));
		return "edit";
	}
	
	@RequestMapping(path="/contents", method=RequestMethod.GET)
	public String getContents(Model model){
		List<Content> contents = contentService.getContents();
		if (contents != null) {
			model.addAttribute("contents", contents);
		}
		return "contents";
	}
	
	@RequestMapping(path="/contents", method=RequestMethod.POST)
	public String createContent(HttpSession session, Content content){
		session.setAttribute("title", "It's NEW Movie! : " + content.getTitle());
		contentService.createContent(content);
		return "redirect:/";
	}
	
	@RequestMapping(path="/contents/{id}", method=RequestMethod.PUT)
	public String updateContents(@PathVariable(name="id") int id, Content content){
		content.setId(id);
		contentService.updateContent(content);
		return "redirect:/";
	}
	
	@RequestMapping(path="/contents/{id}", method=RequestMethod.DELETE)
	public String deleteContents(@PathVariable("id")int id){
		contentService.deleteContent(id);
		return "redirect:/";
	}

}
