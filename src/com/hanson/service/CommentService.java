package com.hanson.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hanson.common.util.Page;

public interface CommentService {
	
    public Page getPageList(Page pagepeper,Map map);
    
	public int showComment(int id);
	
	public int hideComment(int id);
	
	public int deleteComment(int id);
  
	public Map<String,Object> getCommentDetial(int id);
	
	public List<Map<String,Object>> getAllCommentTag();
	
	public List<Map<String,Object>> getByCommentTagByStarLevel(int starLevel);
	
	public int updateCommenttag(Map map);
	
	public int saveCommenttag(Map map);
	
	public int deleteCommentTag(int id);
}
