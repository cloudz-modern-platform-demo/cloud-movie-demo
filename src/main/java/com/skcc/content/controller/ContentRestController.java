package com.skcc.content.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skcc.content.service.ContentService;
import com.skcc.content.vo.Content;

@RestController
@RequestMapping(value = "/api/contents")
public class ContentRestController {

	public static Logger logger = LoggerFactory.getLogger(ContentRestController.class);

	@Autowired
	private ContentService contentService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<Content> getContents(HttpSession session) {
		@SuppressWarnings("unchecked")
		List<Content> contents = (List<Content>) session.getAttribute("contents");
		if (contents == null) {
			contents = contentService.getContents();
			session.setAttribute("contents", contents);
		}

		return contents;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Content getContent(@PathVariable("id") int id) {
		return contentService.getContentById(id);
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<?> createContent(HttpSession session, @RequestBody Content content) {
		contentService.createContent(content);
		removeSessionAttribute(session, "contents");
		return ResponseEntity.ok().build();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Content updateContents(HttpSession session, @PathVariable(name = "id") int id,
			@RequestBody Content content) {
		content.setId(id);
		contentService.updateContent(content);
		removeSessionAttribute(session, "contents");
		return content;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteContents(HttpSession session, @PathVariable("id") int id) {
		contentService.deleteContent(id);
		removeSessionAttribute(session, "contents");
		return ResponseEntity.ok().build();
	}

	private void removeSessionAttribute(HttpSession session, String key) {
		@SuppressWarnings("unchecked")
		List<Content> contents = (List<Content>) session.getAttribute(key);
		if (contents != null) {
			session.removeAttribute(key);
		}
	}

}
