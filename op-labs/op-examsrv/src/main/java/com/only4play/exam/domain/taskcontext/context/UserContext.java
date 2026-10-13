package com.only4play.exam.domain.taskcontext.context;

import com.only4play.exam.domain.taskcontext.ContextType;
import lombok.Data;

@Data
public class UserContext implements Context{

  private Long userId;

  private String username;

  @Override
  public ContextType getContextType() {
    return ContextType.USER;
  }
}
