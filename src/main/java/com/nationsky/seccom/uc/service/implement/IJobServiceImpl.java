package com.nationsky.seccom.uc.service.implement;

import com.nationsky.seccom.uc.dao.JobDictMapper;
import com.nationsky.seccom.uc.model.JobDict;
import com.nationsky.seccom.uc.model.JobDictExample;
import com.nationsky.seccom.uc.service.IJobService;
import com.nationsky.seccom.uc.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangxiang on 15-6-18.
 */
@Service("jobService")
public class IJobServiceImpl implements IJobService {
    @Autowired
    private JobDictMapper jobDictMapper;

    @Override
    public String createJob(String companyId, String jobName) {
        JobDict jobDict = new JobDict();
        jobDict.setCompanyId(companyId);
        String jobId = ServiceUtil.getRandomString(4);
        jobDict.setJobId(jobId);
        jobDict.setJobName(jobName);
        return jobDictMapper.insert(jobDict) > 0 ? jobId : null;
    }

    @Override
    public boolean deleteJob(String jobId) {
        return jobDictMapper.deleteByPrimaryKey(jobId) > 0;
    }

    @Override
    public JobDict getJobInfo(String jobId) {
        return jobDictMapper.selectByPrimaryKey(jobId);
    }

    @Override
    public boolean updateJobInfo(String jobId, String jobName) {
        JobDict jobDict = new JobDict();
        jobDict.setJobId(jobId);
        jobDict.setJobName(jobName);

        return jobDictMapper.updateByPrimaryKeySelective(jobDict) > 0;
    }

    @Override
    public List<JobDict> findList(JobDictExample example) {
        return jobDictMapper.selectByExample(example);
    }
}
