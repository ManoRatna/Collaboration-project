package com.niit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.JobDAO;
import com.niit.model.Job;

public class JobTestCase {

static JobDAO jobDAO;
	
    @BeforeClass
    public static void initialize()
    {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    	context.scan("com.niit");
    	context.refresh();
      	jobDAO=(JobDAO)context.getBean("jobDAO");
    	
    }
    
    @Test
    public void addJobTest()
    {
    	Job job=new Job();
    	job.setCompany("abi systems");
    	job.setJobDesc(" system analyst");
     	assertTrue("problem in job insertion", jobDAO.addJob(job));
    	
    }
    
    @Test
    public void updateJobTest()
    {
		Job job=jobDAO.getJob(350);
		job.setJobDesc("system engineer");
		assertTrue("Problem in Updation",jobDAO.updateJob(job));

    }
    
    @Test
	public void listJobsTest()
	{
		List<Job> listJobs=jobDAO.listAllJobs();
		assertNotNull("No Jobs",listJobs);
		
		for(Job job:listJobs)
		{
			System.out.print(job.getCompany()+":::");
			System.out.print(job.getJobDesc()+":::");
		}
	}

}
