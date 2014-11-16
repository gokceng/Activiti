package org.activiti.engine.test.bpmn.servicetask;

import org.activiti.bpmn.model.ServiceTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.bpmn.parser.handler.ServiceTaskParseHandler;
import org.activiti.engine.impl.pvm.process.ActivityImpl;

/**
 * @author Gökçen Güner
 */
public class ExecutionListenerRegistererServiceTaskParseHandler extends ServiceTaskParseHandler {
  private final ExecutionListener startExecutionListener;
  private final ExecutionListener endExecutionListener;

  public ExecutionListenerRegistererServiceTaskParseHandler(ExecutionListener startExecutionListener, ExecutionListener endExecutionListener) {
    this.startExecutionListener = startExecutionListener;
    this.endExecutionListener = endExecutionListener;
  }

  @Override
  protected void executeParse(BpmnParse bpmnParse, ServiceTask serviceTask) {
    super.executeParse(bpmnParse, serviceTask);
    final String serviceTaskId = serviceTask.getId();
    final String processDefinitionId = bpmnParse.getCurrentProcessDefinition().getProcessDefinition().getKey();
    if (!"miSequentialServiceTasksWithServiceTaskParseHandler".equals(processDefinitionId)) {
      return;
    }
    if (!serviceTaskId.startsWith("serviceTaskWithParseHandlerExecutionListener")) {
      return;
    }
    ActivityImpl activity = findActivity(bpmnParse, serviceTaskId);
    activity.addExecutionListener(ExecutionListener.EVENTNAME_START, startExecutionListener);
    activity.addExecutionListener(ExecutionListener.EVENTNAME_END, endExecutionListener);
  }
}
