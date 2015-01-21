package loganalyzer.core.test;

import loganalyzer.core.AnalyzeTaskConfig;
import loganalyzer.core.AnalyzerContext;
import loganalyzer.core.AnalyzerLogTaskExecutor;
import loganalyzer.core.BasicAnalyzerLogTaskExecutor;
import org.junit.Test;

public class BasicExecutorTest {

	@Test
	public void testAll() throws Exception{
		AnalyzerLogTaskExecutor executor = BasicAnalyzerLogTaskExecutor.getInstance();
		AnalyzeTaskConfig taskConfig = AnalyzeTaskConfig.loadFromConfigFile("test_task.yaml");
		AnalyzerContext context = new AnalyzerContext();
		context.addParam(taskConfig.getInitParams());
		executor.execute(taskConfig, context);
	}
}
