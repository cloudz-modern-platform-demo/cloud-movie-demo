package com.skcc.content.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skcc.content.dao.ContentMapper;
import com.skcc.content.vo.Content;

@Service
public class ContentService {

	private ContentMapper contentMapper;
	
	public ContentService(ContentMapper contentMapper){
		this.contentMapper = contentMapper;
	}
	
	public List<Content> getContents(){
		return contentMapper.selectContents();
	}
	
	public Content getContentById(int id){
		return contentMapper.selectContentById(id);
	}
	
	public int createContent(Content content){
		return contentMapper.insertContent(content);
	}
	
	public int updateContent(Content content){
		return contentMapper.updateContent(content);
	}
	
	public int deleteContent(int id){
		return contentMapper.deleteContent(id);
	}
}
