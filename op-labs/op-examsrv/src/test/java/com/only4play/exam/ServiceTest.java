package com.only4play.exam;

import com.google.common.collect.Lists;
import com.only4play.exam.domain.car.creator.CarCreator;
import com.only4play.exam.domain.car.service.ICarService;
import com.only4play.exam.domain.casegroup.domainservice.ICaseGroupDomainService;
import com.only4play.exam.domain.casegroup.model.CaseGroupCreateModel;
import com.only4play.exam.domain.casegroup.model.TaskGroupCreateModel;
import com.only4play.exam.domain.paramconfig.creator.ParamConfigCreator;
import com.only4play.exam.domain.paramconfig.service.IParamConfigService;
import com.only4play.exam.domain.taskcontext.context.CarContext;
import com.only4play.exam.domain.taskcontext.context.Context;
import com.only4play.exam.domain.taskcontext.context.UserContext;
import com.only4play.exam.domain.taskgroup.domainservice.ITaskGroupDomainService;
import com.only4play.exam.domain.testcase.creator.TestCaseCreator;
import com.only4play.exam.domain.testcase.service.ITestCaseService;
import com.only4play.exam.domain.user.creator.UserCreator;
import com.only4play.exam.domain.user.service.IUserService;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {

  @Autowired
  private ICarService carService;

  @Autowired
  private IUserService userService;

  @Autowired
  private ITestCaseService testCaseService;

  @Autowired
  private IParamConfigService paramConfigService;

  @Autowired
  private ICaseGroupDomainService caseGroupDomainService;

  @Autowired
  private ITaskGroupDomainService taskGroupDomainService;

  /**
   * 创建车
   */
  @Test
  public void testAddCar(){
    CarCreator creator = new CarCreator();
    creator.setCode("11111");
    creator.setColor("白色");
    creator.setPlatNum("京A23435");
    carService.createCar(creator);
  }

  /**
   * 创建考生
   */
  @Test
  public void testAddUser(){

    UserCreator userCreator = new UserCreator();
    userCreator.setBirthDay(Instant.now().toEpochMilli());
    userCreator.setExamNo("245132535");
    userCreator.setRealName("张三");
    userService.createUser(userCreator);


  }

  /**
   * 创建用例
   */
  @Test
  public void testAddUseCase(){

    TestCaseCreator testCaseCreator = new TestCaseCreator();
    testCaseCreator.setCode("WD001");
    testCaseCreator.setName("弯道");
    testCaseService.createTestCase(testCaseCreator);

  }

  /**
   * 给用例配置参数
   */
  @Test
  public void testAddCaseParams(){
    ParamConfigCreator paramConfigCreator = new ParamConfigCreator();
    paramConfigCreator.setName("通过时间");
    paramConfigCreator.setParamCode("passTime");
    paramConfigCreator.setParamValue("5");
    paramConfigCreator.setParamUnit("分钟");
    paramConfigCreator.setCaseId(1L);
    paramConfigService.createParamConfig(paramConfigCreator);

  }

  /**
   * 通过用例组装成用例组
   */
  @Test
  public void assembleCasesToGroup(){
    CaseGroupCreateModel createModel = new CaseGroupCreateModel();
    createModel.setName("科目二考试");
    List<Long> caseIds = Lists.newArrayList();
    caseIds.add(1L);
    createModel.setCaseIds(caseIds);
    Long groupId = caseGroupDomainService.assembleTestCase(createModel);

    System.out.println(groupId);
  }


  @Test
  public void testGroupAddContext(){
    List<Context> contexts = Lists.newArrayList();
    UserContext userContext = new UserContext();
    userContext.setUserId(1L);
    userContext.setUsername("张三");
    contexts.add(userContext);

    CarContext carContext = new CarContext();
    carContext.setCode("11111");
    carContext.setColor("白色");
    carContext.setPlatNum("京A23435");
    contexts.add(carContext);
    TaskGroupCreateModel model =  new TaskGroupCreateModel();
    model.setName("春季科目二考试");
    model.setBatchNo("3234234");
    model.setStartTime(Instant.now().toEpochMilli());
    model.setEndTime(Instant.now().toEpochMilli());
    model.setCaseGroupId(1L);
    caseGroupDomainService.addContext(model,contexts);

  }


  /**
   * 通过一个用例组来启动一个任务
   */
  @Test
  public void testStartTaskByGroup(){
    //开始考试
    taskGroupDomainService.start(1L);
  }

  /**
   * 生成报告  拿到报告
   */
  @Test
  public void testGenerate(){

  }



}
