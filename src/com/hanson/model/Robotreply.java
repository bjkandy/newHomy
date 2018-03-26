package com.hanson.model;

public class Robotreply {
	private Integer id;// id 自增长
	private String rulename;// 规则名称
	private String keyword;// 关键词
	private String replycontent;// 回复内容
	private Integer matchingkeywordtype;// 关键字匹配类型 0:全匹配 1:半匹配
	private Integer type;// 回复语类型 0:关键字回复 1收到消息回复

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRulename() {
		return rulename;
	}

	public void setRulename(String rulename) {
		this.rulename = rulename;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getReplycontent() {
		return replycontent;
	}

	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}

	public Integer getMatchingkeywordtype() {
		return matchingkeywordtype;
	}

	public void setMatchingkeywordtype(Integer matchingkeywordtype) {
		this.matchingkeywordtype = matchingkeywordtype;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
