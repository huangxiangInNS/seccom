package com.nationsky.seccom.uc.model;

public class JobDict {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_uc_user_job.job_id
     *
     * @mbggenerated Tue Jun 16 16:41:26 CST 2015
     */
    private String jobId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_uc_user_job.company_id
     *
     * @mbggenerated Tue Jun 16 16:41:26 CST 2015
     */
    private String companyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_uc_user_job.job_name
     *
     * @mbggenerated Tue Jun 16 16:41:26 CST 2015
     */
    private String jobName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_uc_user_job.job_id
     *
     * @return the value of sc_uc_user_job.job_id
     *
     * @mbggenerated Tue Jun 16 16:41:26 CST 2015
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_uc_user_job.job_id
     *
     * @param jobId the value for sc_uc_user_job.job_id
     *
     * @mbggenerated Tue Jun 16 16:41:26 CST 2015
     */
    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_uc_user_job.company_id
     *
     * @return the value of sc_uc_user_job.company_id
     *
     * @mbggenerated Tue Jun 16 16:41:26 CST 2015
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_uc_user_job.company_id
     *
     * @param companyId the value for sc_uc_user_job.company_id
     *
     * @mbggenerated Tue Jun 16 16:41:26 CST 2015
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_uc_user_job.job_name
     *
     * @return the value of sc_uc_user_job.job_name
     *
     * @mbggenerated Tue Jun 16 16:41:26 CST 2015
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_uc_user_job.job_name
     *
     * @param jobName the value for sc_uc_user_job.job_name
     *
     * @mbggenerated Tue Jun 16 16:41:26 CST 2015
     */
    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }
}