package com.springboot.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.mapper.CommentSecretMapper;
import com.springboot.mapper.PraiseCommentMapper;
import com.springboot.mapper.UserSecretMapper;
import com.springboot.model.CommentSecret;
import com.springboot.model.PraiseComment;
import com.springboot.service.CommentSecretService;

import net.sf.json.JSONObject;

@Service
public class CommentSecretServiceImpl implements CommentSecretService{
	@Autowired
	private CommentSecretMapper commentSecretMapper;
	@Autowired
	private UserSecretMapper userSecretMapper;
	@Autowired
	private PraiseCommentMapper praiseCommentMapper;
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public HashMap<String, Object> saveComment(JSONObject param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CommentSecret  commentSecret   =  new CommentSecret();
		commentSecret.setContent(param.getString("content"));
		commentSecret.setUserId(param.getInt("userId"));
		commentSecret.setAddress(param.getString("address"));
		commentSecret.setSecretId(param.getInt("secretId"));
		commentSecretMapper.insert(commentSecret);
		userSecretMapper.updateComment(param.getInt("secretId"));
		map.put("code", 200);
		map.put("msg", "保存成功");
		return map;
	}
	@Override
	public HashMap<String, Object> getComment(JSONObject param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<CommentSecret> list=	commentSecretMapper.findAll(param.getInt("secretId"));
		if(list.size()>0) {
			PraiseComment pc =null;
			for(CommentSecret cs:list) {
				 pc = new PraiseComment();
				 if(praiseCommentMapper.selectPraiseComment(pc)!=null) {
					 cs.setPraiseStatus(1);
				 }
			}
		}
		map.put("data", list);
		map.put("code", 200);
		map.put("msg", "查询成功");
		return map;
	}
	@Override
	public HashMap<String, Object> saveCommentToUser(JSONObject param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CommentSecret  commentSecret   =  new CommentSecret();
		commentSecret.setContent(param.getString("content"));
		commentSecret.setUserId(param.getInt("userId"));
		commentSecret.setCommentId(param.getInt("commentId"));
		commentSecretMapper.insertCommentUser(commentSecret);
		userSecretMapper.updateComment(param.getInt("secretId"));
		commentSecretMapper.updateComment(param.getInt("commentId"));
		map.put("code", 200);
		map.put("msg", "保存成功");
		return map;
	}
	@Override
	public HashMap<String, Object> saveCommentPraise(JSONObject param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		commentSecretMapper.saveCommentPraise(param.getInt("commentId"));
		PraiseComment pc= new PraiseComment();
		pc.setCommentId(param.getInt("commentId"));
		pc.setUserId(param.getInt("userId"));
		praiseCommentMapper.insertPraiseComment(pc);
		map.put("code", 200);
		map.put("msg", "保存成功");
		return map;
	}
	@Override
	public HashMap<String, Object> selectByCommentId(JSONObject paramJSONObject) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HashMap<String, Object> saveCommentToUserToUser(JSONObject param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CommentSecret  commentSecret   =  new CommentSecret();
		commentSecret.setContent(param.getString("content"));
		commentSecret.setUserId(param.getInt("userId"));
		commentSecret.setCommentId(param.getInt("commentId"));
		commentSecret.setUserToUserId(param.getInt("userToUserId"));
		commentSecretMapper.insertCommentUserToUser(commentSecret);
		userSecretMapper.updateComment(param.getInt("secretId"));
		commentSecretMapper.updateComment(param.getInt("commentId"));
		map.put("code", 200);
		map.put("msg", "保存成功");
		return map;
	}
	@Override
	public HashMap<String, Object> selectCommentToUserToUser(JSONObject param) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, Object>> list=	commentSecretMapper.selectCommentToUserToUser(param.getInt("comment"));
		map.put("data", list);
		map.put("code", 200);
		map.put("msg", "查询成功");
		return map;
	}

}
