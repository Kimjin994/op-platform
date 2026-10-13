package com.only4play.orderjob.batch.writer;

import com.only4play.orderjob.batch.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserWriter implements ItemWriter<User> {

  private final JdbcTemplate batchTemplate;

  @Override
  public void write(List<? extends User> list) throws Exception {
    list.stream().forEach(u -> System.out.println("用户ID:" + u.getId()));


  }
}
