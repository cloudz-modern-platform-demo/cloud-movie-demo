package com.skcc.content.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.skcc.content.vo.Content;

@Mapper
public interface ContentMapper {
	
	@Select("select * from contents order by id")
	public List<Content> selectContents();
	
	@Select("select * from contents where id=#{id}")
	public Content selectContentById(int id);
	
	@Insert("insert into contents(title, director, year, genre) values (#{title}, #{director}, #{year}, #{genre})")
	public int insertContent(Content content);
	
	@Update("update contents set title=#{title}, director=#{director}, year=#{year}, genre=#{genre} where id=#{id}")
	public int updateContent(Content content);
	
	@Delete("delete from contents where id=#{id}")
	public int deleteContent(int id);
}
