package com.only4play.system.controller.user;

import com.only4play.common.model.JsonObject;
import com.only4play.system.domain.user.request.UserRegisterRequest;
import com.only4play.system.domain.user.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/user")
@RequiredArgsConstructor
public class UserAuthController {

  private final UserRegisterService userRegisterService;

  @PostMapping("register")
  public JsonObject<Long> register(@RequestBody UserRegisterRequest request) {
    return JsonObject.success(userRegisterService.register(request.getPhone(), request.getPassword()));
  }
}
