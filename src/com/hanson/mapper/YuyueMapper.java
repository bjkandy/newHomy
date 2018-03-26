package com.hanson.mapper;
import java.util.List;
import java.util.Map;
import com.hanson.model.ServiceProject;

public interface YuyueMapper {
	public List<ServiceProject> getYuyue( );
	
	public ServiceProject Selectprojectname(String projectname);
	
	public Integer saveyuepaiProject(ServiceProject model);
	
	public Integer  ReviseServiceProject(ServiceProject model);
	
	public  List<Map<String, Object>> search(Map<String,Object> map);
	
	public Integer Selectcountpages(Map<String, Object> map);
	
	public Integer DeleteServiceProject(Long id);
	
	public ServiceProject FindServiceProject(Long id);

    public List<String> getProductTagByQyid(Integer qyid);
    
    public Integer updateYuePai(ServiceProject model );
}
