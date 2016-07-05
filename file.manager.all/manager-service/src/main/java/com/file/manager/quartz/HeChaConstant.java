package com.file.manager.quartz;

/**
 * 
   * @ClassName: HeChaConstant
   * @Description: 定义了触发器和调度器的key名称和group名称。可以通过配置文件读入
   * @author zhangHui
   * @date 2015年10月15日 下午9:46:58
   *
 */
public interface HeChaConstant {

	/**可定义为死值,全局只有一个即可*/
	public static final String JOBKEY="jobkey";
	
	/**多个job_card对应多个job任务，需要实现HeChaJobAbstract.java中的15个（数量可选）job任务*/
	public static final String JOB_CARD1="job1";
	public static final String JOB_CARD2="job2";
	public static final String JOB_CARD3="job3";
	public static final String JOB_CARD4="job4";
	public static final String JOB_CARD5="job5";
	public static final String JOB_CARD6="job6";
	public static final String JOB_CARD7="job7";
	public static final String JOB_CARD8="job8";
	public static final String JOB_CARD9="job9";
	public static final String JOB_CARD10="job10";
	public static final String JOB_CARD11="job11";
	public static final String JOB_CARD12="job12";
	public static final String JOB_CARD13="job13";
	public static final String JOB_CARD14="job14";
	public static final String JOB_CARD15="job15";
	
	
	/**可定义死值一个即可，所有job_card都可定义在此group下,目前配置在数据库*/
	//public static final String JOB_GROUPs="jobgroup";
	
	/**实现job任务的类--需要配置在配置文件中*/
	public static final String JOB_ClASS="com.hecha.quartz.TestJobImpl";
}
