package com.emprendesoftcr;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

	private final int POOL_SIZE = 4;
	

	 @Override
   public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		 ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();

     scheduler.setPoolSize(POOL_SIZE);
     scheduler.setThreadNamePrefix("Tarea-");
     scheduler.initialize();
		 taskRegistrar.setTaskScheduler(scheduler);
//       taskRegistrar.setScheduler(taskExecutor());
   }
	
//	/**
//   * @return executor for the scheduler (naming convention)
//   */
//  @Bean(name = "taskScheduler", destroyMethod = "shutdown")
//  public Executor taskExecutor() {
//      // use the Spring ThreadPoolTaskScheduler
//      ThreadPoolTaskScheduler scheduledExecutorService = new ThreadPoolTaskScheduler();
//      // always set the poolsize
//      scheduledExecutorService.setPoolSize(POOL_SIZE);
//      // for logging add a threadNamePrefix
//      scheduledExecutorService.setThreadNamePrefix("Tarea-");
//      scheduledExecutorService.initialize();
//      // do not wait for completion of the task
//      scheduledExecutorService.setWaitForTasksToCompleteOnShutdown(true);
//
//      return scheduledExecutorService;
//  }

}
