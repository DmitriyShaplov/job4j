package ru.job4j.sqlparser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Properties;
import java.util.logging.LogManager;

import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.*;
import static ru.job4j.sqlparser.SqlRuParser.LOG;

/**
 * @author shaplov
 * @since 11.04.2019
 */
public class StartParser {

    private String properties;

    public StartParser(String properties) {
        this.properties = properties;
    }

    /**
     * Gets Properties from resource file.
     */
    private Properties getProp() {
        try (InputStream in = StartParser.class.getClassLoader().getResourceAsStream(this.properties)) {
            Properties config = new Properties();
            config.load(in);
            return config;
        } catch (IOException e) {
            throw new IllegalStateException("Could not get app.properties");
        }
    }

    /**
     * Runs scheduler.
     * @throws SchedulerException possible Exception
     */
    public void run() throws SchedulerException {
        Properties config = getProp();
        SchedulerFactory schedFact = new StdSchedulerFactory();
        Scheduler sched = schedFact.getScheduler();
        JobDetail job = newJob(ParserJob.class)
                .withIdentity("ParseJob", "group1")
                .build();
        CronTrigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule(config.getProperty("cron.time")))
                .build();
        job.getJobDataMap().put(ParserJob.PROPERTIES, config);
        job.getJobDataMap().put(ParserJob.DATE_TIME, LocalDate.now().withDayOfYear(1).atStartOfDay());
        sched.scheduleJob(job, trigger);
        LOG.info(job.getKey() + " has been scheduled to run and repeat base on expression: "
        + trigger.getCronExpression());
        LOG.info("------- Starting Scheduler ----------");
        sched.start();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            LOG.error("No start arguments");
            return;
        }
        try {
            new StartParser(args[0]).run();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
