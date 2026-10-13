package com.only4play.exam.domain.casegroup.domainservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.only4play.exam.domain.casegroup.GroupCaseRel;
import com.only4play.exam.domain.casegroup.creator.CaseGroupCreator;
import com.only4play.exam.domain.casegroup.model.CaseGroupCreateModel;
import com.only4play.exam.domain.casegroup.model.TaskGroupCreateModel;
import com.only4play.exam.domain.casegroup.repository.GroupCaseRelRepository;
import com.only4play.exam.domain.casegroup.service.ICaseGroupService;
import com.only4play.exam.domain.task.creator.TaskCreator;
import com.only4play.exam.domain.task.service.ITaskService;
import com.only4play.exam.domain.taskcontext.context.Context;
import com.only4play.exam.domain.taskcontext.creator.TaskContextCreator;
import com.only4play.exam.domain.taskcontext.service.ITaskContextService;
import com.only4play.exam.domain.taskgroup.creator.TaskGroupCreator;
import com.only4play.exam.domain.taskgroup.service.ITaskGroupService;
import com.only4play.exam.domain.testcase.TestCase;
import com.only4play.exam.domain.testcase.repository.TestCaseRepository;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CaseGroupDomainServiceImpl implements ICaseGroupDomainService{

  private final GroupCaseRelRepository groupCaseRelRepository;

  private final ICaseGroupService caseGroupService;

  private final ITaskGroupService taskGroupService;

  private final ITaskService taskService;

  private final TestCaseRepository caseRepository;

  private final ITaskContextService taskContextService;

  @Override
  public Long assembleTestCase(CaseGroupCreateModel caseGroupCreateModel) {
    CaseGroupCreator creator = new CaseGroupCreator();
    creator.setGroupName(caseGroupCreateModel.getName());
    creator.setUuid(UUID.randomUUID().toString());
    Long groupId = caseGroupService.createCaseGroup(creator);
    List<GroupCaseRel> relList = groupCaseRelRepository.findAllByGroupId(groupId);
    if(!Iterables.isEmpty(relList)){
      groupCaseRelRepository.deleteAll(relList);
    }
    List<Long> caseIds = caseGroupCreateModel.getCaseIds();
    List<GroupCaseRel> rels = Lists.newArrayList();
    for(Long caseId : caseIds){
      GroupCaseRel groupCaseRel = new GroupCaseRel();
      groupCaseRel.setGroupId(groupId);
      groupCaseRel.setCaseId(caseId);
      rels.add(groupCaseRel);
    }
    groupCaseRelRepository.saveAll(rels);
    return groupId;
  }

  @Override
  public void addContext(TaskGroupCreateModel model, List<Context> contextList) {

    //先创建一个任务组 ，然后再创建子任务 -》 再将上下文进行关联

    TaskGroupCreator taskGroupCreator = new TaskGroupCreator();
    taskGroupCreator.setBatchNo(model.getBatchNo());
    taskGroupCreator.setStartTime(model.getStartTime());
    taskGroupCreator.setCaseGroupId(model.getCaseGroupId());
    taskGroupCreator.setName(model.getName());
    Long taskGroupId = taskGroupService.createTaskGroup(taskGroupCreator);
    Long caseGroupId = model.getCaseGroupId();
    //通过相应的caseGroup 找到每个case -> 给每个case 创建对应的任务
    List<GroupCaseRel> caseRels = groupCaseRelRepository.findAllByGroupId(caseGroupId);
    List<Long> caseIds = caseRels.stream().map(r -> r.getCaseId()).collect(Collectors.toList());
    List<TestCase> caseList = caseRepository.findAllById(caseIds);
    AtomicInteger index = new AtomicInteger(1);
    for(TestCase tc : caseList){
      TaskCreator taskCreator = new TaskCreator();
      taskCreator.setCaseGroupId(caseGroupId);
      taskCreator.setTaskGroupId(taskGroupId);
      taskCreator.setCaseId(tc.getId());
      taskCreator.setSortNum(index.incrementAndGet());
      Long taskId = taskService.createTask(taskCreator);
      //添加上下文 可以关联任务组 ，也可以关联任务，看实际的需求
      for(Context context : contextList){
        TaskContextCreator contextCreator = new TaskContextCreator();
        contextCreator.setContextType(context.getContextType());
        contextCreator.setTaskGroupId(taskGroupId);
        contextCreator.setJsonStr(JSON.toJSONString(context, SerializerFeature.WriteClassName));
        contextCreator.setTaskId(taskId);
        taskContextService.createTaskContext(contextCreator);
      }
    }
  }

}
