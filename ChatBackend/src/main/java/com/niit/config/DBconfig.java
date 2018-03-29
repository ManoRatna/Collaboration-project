package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.dao.BlogDAO;
import com.niit.dao.BlogDAOImpl;
import com.niit.dao.ForumDAO;
import com.niit.dao.ForumDAOImpl;
import com.niit.dao.JobDAO;
import com.niit.dao.JobDAOImpl;
import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.Forum;
import com.niit.model.ForumComment;
import com.niit.model.Job;
import com.niit.model.UserDetail;


@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class DBconfig {

	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/test");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		
		//System.out.println("---Data Source Created---");
		return dataSource;
	}
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		Properties hibernateProp=new Properties();
		hibernateProp.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProp.put("hibernate.dialect","org.hibernate.dialect.Oracle11gDialect");
		LocalSessionFactoryBuilder sessionfactoryBuilder=new LocalSessionFactoryBuilder(getDataSource());
		sessionfactoryBuilder.addAnnotatedClass(Blog.class);
		sessionfactoryBuilder.addAnnotatedClass(BlogComment.class);
		sessionfactoryBuilder.addAnnotatedClass(Forum.class);
		sessionfactoryBuilder.addAnnotatedClass(ForumComment.class);
		sessionfactoryBuilder.addAnnotatedClass(Job.class);
		sessionfactoryBuilder.addAnnotatedClass(UserDetail.class);
		sessionfactoryBuilder.addProperties(hibernateProp);
		System.out.println("Creating SessionFactory Bean");
		return sessionfactoryBuilder.buildSessionFactory();
	}
	@Bean(name="blogDAO")
	public BlogDAO getBlogDAO()
	{
		return new BlogDAOImpl();
		
	}
	@Bean(name="forumDAO")
	public ForumDAO getForumDAO()
	{
		return new ForumDAOImpl();
		
	}
	@Bean(name="jobDAO")
	public JobDAO getJobDAO()
	{
		return new JobDAOImpl();
		
	}
	@Bean(name="txManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("---Transaction Manager----");
		return new HibernateTransactionManager(sessionFactory);
	}
}
