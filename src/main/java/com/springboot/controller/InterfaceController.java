package com.springboot.controller;
import com.springboot.model.AppUtil;
import com.springboot.service.CommentSecretService;
import com.springboot.service.OpenSecretService;
import com.springboot.service.PicService;
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
  private OpenSecretService openSecretService;
  @Autowired
  private PicService picService;
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
        case 4023: //图片上传
          datalist.put("data", ImageUtil.uploadMoreFile(request));
          break;
        case 103: //发布盒子  
          datalist.put("data", this.userSecretService.addUserSecret(param));
          break;
        case 104: //获取用户关注列表
          datalist.put("data", this.userService.getAttention(param));
          break;
        case 105: //首页盒子列表 
          datalist.put("data", this.userSecretService.getUserSecret(param));
          break;
        case 107: //修改个人信息
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
        case 114: //获取打开的盒子
            datalist.put("data", this.userSecretService.getOpenSecretAll(param));
            break;
        case 115: //获取已经发布的盒子
            datalist.put("data", this.userSecretService.getPublish(param));
            break;
        case 116: //根据盒子id查找详情
            datalist.put("data", this.userSecretService.getBySecretId(param));
            break;
        case 117: //删除盒子秘密
            datalist.put("data", this.userSecretService.deleteBySecretId(param));
            break;
        case 118: //根据用户id查找用户详情
            datalist.put("data", this.userService.finUserById(param));
            break;
        case 119: //获取图片
            datalist.put("data", this.picService.selectPicByUserId(param));
            break;
        case 120: //保存图片和打开的盒子id
            datalist.put("data", this.openSecretService.saveOpenSecret(param));
            break;
        case 121: //保存用户评论用户
            datalist.put("data", this.commentSecretService.saveCommentToUser(param));
            break;
        case 122: //保存用户给盒子点赞
            datalist.put("data", this.userSecretService.saveSecretPraise(param));
            break;
        case 123: //保存用户给评论点赞
            datalist.put("data", this.commentSecretService.saveCommentPraise(param));
            break;
        case 124: //获取用户的评论
            datalist.put("data", this.commentSecretService.selectByCommentId(param));
            break;
        case 125: //用户回复用户
            datalist.put("data", this.commentSecretService.saveCommentToUserToUser(param));
            break;
        case 126: //查询用户评论下的所有用户评论
            datalist.put("data", this.commentSecretService.selectCommentToUserToUser(param));
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
