package com.hanson.service.impl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hanson.common.util.Page;
import com.hanson.mapper.AnnounceMapper;
import com.hanson.model.Announce;
import com.hanson.model.YyPagemodel;
import com.hanson.service.AnnounceService;

@Service
public class AnnounceServiceImpl implements AnnounceService {
	@Autowired
	private AnnounceMapper announceMapper;

	@Override
	public long selectcount(Map<String, Object> map) {
		return announceMapper.selectcount(map);
	}

	@Override
	public Integer insertAnnounce(Announce bean) {
        return announceMapper.insertAnnounce(bean);
	}
    @Override
	public List<Announce> findAnnounce(String title){
		return announceMapper.findAnnounce(title);
	}
    @Override
    public Announce selectByName(String title){
    	return announceMapper.selectByName(title);
    }
	@Override
	public Integer RecommendSet(Announce bean) {
		return announceMapper.RecommendSet(bean);
	}
	@Override
	public List<Announce>  bannerList(){
		return announceMapper.bannerList();
	}
	@Override
	public Integer updatebean(Announce bean){
		 return announceMapper.updatebean(bean);
	 }
	@Override
	public Page<Map<String, Object>> searchAnnounce(Page<Map<String, Object>> page, Map<String, Object> map) {
		//分页查询通用参数
		map.put("BEGIN_ROW", (page.getPageNo()-1) * page.getPageSize());
		map.put("PAGE_SIZE", page.getPageSize());
		map.put("ORDER_BY_MENU", page.getOrderBy());
		map.put("ORDER_BY_DESC", page.getOrder());
		long totalCount =announceMapper.selectcount(map);	
		page.setTotalCount(totalCount);
		if(totalCount >0){
			page.setResult(announceMapper.searchAnnounce(map));
		}
		return page;
	}
	public Integer deletebean(Integer id){
		return announceMapper.deletebean(id);
	}
	public Announce	Querybyid(Integer id){
		return announceMapper.Querybyid(id);
	}

	@Override
	public List<YyPagemodel> getAllYyPageModel() {
       return announceMapper.getAllYyPageModel();
	}

	@Override
	public Integer updateYyPagemodel(YyPagemodel yyPagemodel) {
		return announceMapper.updateYyPagemodel(yyPagemodel);
	}

	@Override
	public YyPagemodel QueryYyPagemodelByid(Integer id) {
		return announceMapper.QueryYyPagemodelByid(id);
	}
}
