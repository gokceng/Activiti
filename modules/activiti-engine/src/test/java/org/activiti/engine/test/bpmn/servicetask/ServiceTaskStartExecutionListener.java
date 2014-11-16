package org.activiti.engine.test.bpmn.servicetask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * @author Gökçen Güner
 */
public class ServiceTaskStartExecutionListener implements ExecutionListener {
  private static final String START_COUNT = "startCount";

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    final boolean hasStartCount = execution.hasVariable(START_COUNT);
    if (!hasStartCount) {
      return;
    }
    long startCount = (Long) execution.getVariable(START_COUNT);
    startCount++;
    execution.setVariable(START_COUNT, startCount);
  }
}
