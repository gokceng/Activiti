package org.activiti.engine.test.bpmn.servicetask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * @author Gökçen Güner
 */
public class ServiceTaskEndExecutionListener implements ExecutionListener {
  private static final String END_COUNT = "endCount";

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    final boolean hasEndCount = execution.hasVariable(END_COUNT);
    if (!hasEndCount) {
      return;
    }
    long endCount = (Long) execution.getVariable(END_COUNT);
    endCount++;
    execution.setVariable(END_COUNT, endCount);
  }
}
