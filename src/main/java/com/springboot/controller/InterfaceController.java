package com.springboot.controller;

import com.springboot.mapper.CommentSecretMapper;
import com.springboot.model.AppUtil;
import com.springboot.service.CommentSecretService;
import com.springboot.service.UserSecretService;
import com.springboot.service.UserService;
import com.springboot.utils.AES256Encrypt;
import com.springboot.utils.ImageUtil;
import com.springboot.utils.Util;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterfaceController
{
  @Autowired
  private UserService userService;
  @Autowired
  private UserSecretService userSecretService;
  @Autowired
  private  CommentSecretService commentSecretService;
  @PostMapping({"/interface"})
  public HashMap<String, Object> queryInterface(AppUtil model, HttpServletRequest request)
  {
    HashMap<String, Object> datalist = new HashMap();
    try
    {
      model.setData(AES256Encrypt.decrypt(model.getData()));
      JSONObject param = JSONObject.fromObject(model.getData());
      if (Util.RegAppUtil(model))
      {
        switch (model.getQueryid())
        {
        case 100:  //验证手机号
          datalist.put("data", this.userService.sendRegister(param));
          break;
        case 101: //注册账号
          datalist.put("data", this.userService.validRegister(param));
          break;
        case 102:// 用户登录
          datalist.put("data", this.userService.login(param));
          break;
        case 4023: //头像上传
          datalist.put("data", ImageUtil.uploadMoreFile(request));
          break;
        case 103: //发布盒子
          datalist.put("data", this.userSecretService.addUserSecret(param));
          break;
        case 104: //获取用户关注列表
          datalist.put("data", this.userService.getAttention(param));
          break;
        case 105: //随即打捞秘密盒子
          datalist.put("data", this.userSecretService.getUserSecret(param));
          break;
        case 106: //获取问题
          datalist.put("data", this.userSecretService.getQuestions());
          break;
        case 107: //休息个人信息
          datalist.put("data", this.userService.updateUser(param));
          break;
        case 108: //修改密码
          datalist.put("data", this.userService.updateUserPassword(param));
          break;
        case 109: //第三方登录
          datalist.put("data", this.userService.validRegisterTWO(param));
          break;
        case 110: //添加关注  和取消关注
          datalist.put("data", this.userService.addAttention(param));
          break;
        case 111: //保存评论
            datalist.put("data", this.commentSecretService.saveComment(param));
            break;
        case 112: //榜单
            datalist.put("data", this.userSecretService.getTop(param));
            break;
        case 113: //获取评论列表
            datalist.put("data", this.commentSecretService.getComment(param));
            break;
        }
      } else{
        datalist.put("msg", "数据错误");
        datalist.put("code", -200);
      }
      datalist.put("msg", "接口调用成功");
      datalist.put("code", 200);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return datalist;
  }
}
