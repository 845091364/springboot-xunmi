package com.springboot.mapper;

import com.springboot.model.PraiseComment;
import com.springboot.model.PraiseSecret;

public interface PraiseCommentMapper {
	 public void  insertPraiseComment(PraiseComment pc);
	 
	 public void insertPraiseSecret(PraiseSecret  ps);
	 
	 public  PraiseComment selectPraiseComment(PraiseComment pc);
	 
	 public PraiseSecret selectPraiseSecret(PraiseSecret  ps);
}
