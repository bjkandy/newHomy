package com.hanson.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hanson.model.Announce;
import com.hanson.model.Company;
import com.hanson.model.YyPagemodel;

public interface AnnounceMapper {
	public List<Map<String, Object>> searchAnnounce(Map<String, Object> map);
	
	public long selectcount(Map<String, Object> map);
	
	public Integer insertAnnounce(Announce bean);
	
	public Integer RecommendSet(Announce bean);
	
	public Integer deletebean(Integer id);
	
    public Announce	Querybyid(Integer id);
    
    public List<Announce>  bannerList();
    
    public Integer updatebean(Announce bean);
    
    public Announce selectByName(@Param(value="title") String title);
    
    public List<Announce> findAnnounce(@Param(value="title") String title);
    
    public List<YyPagemodel> getAllYyPageModel();
    
    public Integer updateYyPagemodel(YyPagemodel yyPagemodel);
    
    public YyPagemodel QueryYyPagemodelByid(Integer id);
}
