package loganalyzer.core;

import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * 命令行入口
 * @author hongze.chi@gmail.com
 *
 */
public class AnalyseLogTaskLauncher {

	public static void main(String[] args) {
		//读取命令行参数
		CommandLineParser parser = new PosixParser();
        Options options = new Options();
        options.addOption("log", true, "log4j配置路径");
        options.addOption("task", true, "分析任务配置文件名");
        
        CommandLine cmdline = null;

        try {
            cmdline = parser.parse(options, args, false);
        } catch (org.apache.commons.cli.ParseException e) {
        	System.out.println("");
            e.printStackTrace();
            System.exit(0);
        }
        
        //初始化log4j
        String log4jConfigPath = cmdline.getOptionValue("log");
        if(StringUtils.isNotBlank(log4jConfigPath)){
        	DOMConfigurator.configure(AnalyseLogTaskLauncher.class.getClassLoader().getResource(
                    log4jConfigPath));
        }
        
        Logger logger = LogManager.getLogger(AnalyseLogTaskLauncher.class);
        
        //载入Task配置
        String taskConfigPath = cmdline.getOptionValue("task");
        AnalyzeTaskConfig taskConfig = null;
        if(StringUtils.isNotBlank(taskConfigPath)) {
        	try {
				taskConfig = AnalyzeTaskConfig.loadFromConfigFile(taskConfigPath);
			} catch (Exception e) {
				logger.error("Ops!", e);
				System.exit(0);
			}
        }
        
        try{
        	AnalyzerLogTaskExecutor logTaskExecutor = BasicAnalyzerLogTaskExecutor.getInstance();
        	AnalyzerContext analyzerContext = buildContext(cmdline, taskConfig);
        	logTaskExecutor.execute(taskConfig, analyzerContext);
        }catch(LogAnalyzeException e) {
        	logger.error("Ops!", e);
        }finally{
        	logger.info("log analyze task finished!");
        	System.exit(0);
        }
	}
	
	private static AnalyzerContext buildContext(CommandLine cmdLine, AnalyzeTaskConfig config) {
		AnalyzerContext analyzerContext = new AnalyzerContext();
		List<String> cmdOptions = config.getCmdOptions();
		//先把命令行参数加进去
		if(CollectionUtils.isNotEmpty(cmdOptions)) {
			for(String cmdOpt : cmdOptions) {
				analyzerContext.addParam(cmdOpt, cmdLine.getOptionValue(cmdOpt));
			}
		}
		//再把配置参数加进去
		Map<String, String> initParams = config.getInitParams();
		if(MapUtils.isNotEmpty(initParams)) {
			for(Map.Entry<String, String> entry : initParams.entrySet()) {
				analyzerContext.addParam(entry.getKey(), entry.getValue());
			}
		}
		return analyzerContext;
	}
}
