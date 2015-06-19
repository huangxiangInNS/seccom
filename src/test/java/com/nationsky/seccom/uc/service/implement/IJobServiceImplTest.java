package com.nationsky.seccom.uc.service.implement;

import com.nationsky.seccom.uc.model.JobDict;
import com.nationsky.seccom.uc.model.JobDictExample;
import com.nationsky.seccom.uc.service.ICompanyService;
import com.nationsky.seccom.uc.service.IJobService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by huangxiang on 15-6-19.
 */
public class IJobServiceImplTest {
    private IJobService jobService = null;
    private ClassPathXmlApplicationContext context = null;

    @Test
    public void testCreateJob() throws Exception {
        String companyId = "DGY2fF2D";
        String jobName = "CEO";
        String jobId = jobService.createJob(companyId, jobName);
        System.out.println(jobId);
    }

    @Test
    public void testDeleteJob() throws Exception {
        String jobId = "CZhA";
        jobService.deleteJob(jobId);
    }

    @Test
    public void testGetJobInfo() throws Exception {
        String jobId = "LHZx";
        JobDict jobDict = jobService.getJobInfo(jobId);
        System.out.println(jobDict.getCompanyId());
        System.out.println(jobDict.getJobName());
    }

    @Test
    public void testUpdateJobInfo() throws Exception {
        String jobId = "LHZx";
        String newJobName = "CTO";
        jobService.updateJobInfo(jobId, newJobName);
    }

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("spring_config.xml");
        jobService = (IJobService)context.getBean("jobService");
    }

    @After
    public void tearDown() throws Exception {
        context.close();
    }

    @Test
    public void testFindList() throws Exception {
        JobDictExample jobDictExample = new JobDictExample();
        jobDictExample.createCriteria().andJobNameLike("%C%");
        List<JobDict> jobDicts = jobService.findList(jobDictExample);

        for (JobDict jobDict : jobDicts)
        {
            System.out.println(jobDict.getJobName());
        }

    }
}