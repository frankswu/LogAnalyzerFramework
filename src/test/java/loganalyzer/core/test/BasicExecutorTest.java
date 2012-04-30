package loganalyzer.core.test;

import junit.framework.TestCase;
import loganalyzer.core.AnalyzeTaskConfig;
import loganalyzer.core.AnalyzerContext;
import loganalyzer.core.AnalyzerLogTaskExecutor;
import loganalyzer.core.BasicAnalyzerLogTaskExecutor;

public class BasicExecutorTest extends TestCase{

	public void testAll() throws Exception{
		AnalyzerLogTaskExecutor executor = BasicAnalyzerLogTaskExecutor.getInstance();
		AnalyzeTaskConfig taskConfig = AnalyzeTaskConfig.loadFromConfigFile("/data/test_task.yaml");
		AnalyzerContext context = new AnalyzerContext();
		context.addParam(taskConfig.getInitParams());
		executor.execute(taskConfig, context);
	}
}
