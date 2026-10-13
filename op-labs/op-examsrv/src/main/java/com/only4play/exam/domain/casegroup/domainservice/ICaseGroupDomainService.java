package com.only4play.exam.domain.casegroup.domainservice;

import com.only4play.exam.domain.casegroup.model.CaseGroupCreateModel;
import com.only4play.exam.domain.casegroup.model.TaskGroupCreateModel;
import com.only4play.exam.domain.taskcontext.context.Context;
import java.util.List;

public interface ICaseGroupDomainService {

  Long assembleTestCase(CaseGroupCreateModel caseGroupCreateModel);

  /**
   * 添加上下文
   * @param contextList
   */
  void addContext(TaskGroupCreateModel model, List<Context> contextList);

}
