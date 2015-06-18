package com.nationsky.seccom.uc.service;

import com.nationsky.seccom.uc.model.JobDict;

/**
 * Created by huangxiang on 15-6-18.
 */
public interface IJobService {
    String createJob(String companyId, String jobName);
    boolean deleteJob(String jobId);
    JobDict getJobInfo(String jobId);
    boolean updateJobInfo(String jobId, String jobName);
}
