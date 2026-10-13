package com.only4play.genserver.controller;

import com.only4play.common.model.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟一些用户接口
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

  @PostMapping(value = "login")
  public JsonObject<LoginRes> login(@RequestBody LoginReq req) {
    LoginRes res = new LoginRes(1L, UUID.randomUUID().toString());
    return JsonObject.success(res);
  }

  @Data
  static class LoginReq {

    private String account;
    private String password;
  }

  @Value
  static class LoginRes {

    Long userId;
    String token;
  }

  @GetMapping(value = "menu")
  public JsonObject<List<Menu>> menu() {
    List<Menu> list = new ArrayList<>();
    Menu menu = new Menu();
    menu.setId("/workspace");
    menu.setIcon("layui-icon-home");
    menu.setTitle("工作空间");

    List<Menu> children = new ArrayList<>();
    Menu child = new Menu();
    child.setId("/workspace/workbench");
    child.setIcon("layui-icon-home");
    child.setTitle("代码生成");
    children.add(child);
    menu.setChildren(children);
    return JsonObject.success(list);
  }

  @GetMapping(value = "permission")
  public JsonObject<List<String>> permission() {
    return JsonObject.success(Collections.singletonList("user:add"));
  }

  @PostMapping(value = "getUserInfo")
  public JsonObject<UserInfoRes> getUserInfo() {
    UserInfoRes res = new UserInfoRes();
    res.setUserId(1L);
    res.setUsername("coder1v5");
    return JsonObject.success(res);
  }

  @Data
  static class UserInfoRes {

    private Long userId;
    private String username;
  }

  @Data
  static class Menu {

    private String id;
    private String icon;
    private String title;
    private List<Menu> children;
  }

}
