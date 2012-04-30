package loganalyzer.core.test;

import junit.framework.TestCase;
import loganalyzer.core.AnalyzeTaskConfig;
/**
 * loganalyzer.core.AnalyzerTaskConfig的测试用例
 * @author hongze.chi@gmail.com
 *
 */
public class AnalyzerTaskConfigTest extends TestCase{

	/** 测试载入配置文件 **/
	public void testLoad() throws Exception{
		AnalyzeTaskConfig analyzeTaskConfig = AnalyzeTaskConfig.loadFromConfigFile("/data/test_task.yaml");
		System.out.println(analyzeTaskConfig);
	}
}
