package loganalyzer.core.test;

import loganalyzer.core.AnalyzerContext;
import loganalyzer.core.BasicAnalyzelet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 测试用的Analyzelet
 * @author sam
 *
 */
public class TestAnalyzelet extends BasicAnalyzelet{
	
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void doLine(String line, AnalyzerContext analyzerContext) {
		String date = line.substring(0,16);
		String value = line.substring(16,line.length());
		logger.info("line:" + date);
		System.out.println(date);
		System.out.println(value);

	}

	@Override
	public void end(AnalyzerContext analyzerContext) {
		super.end(analyzerContext);
	}
}
