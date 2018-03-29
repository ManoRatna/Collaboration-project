package com.niit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;
import com.niit.model.BlogComment;


public class BlogtestCase {
	
	static BlogDAO blogDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		blogDAO=(BlogDAO)context.getBean("blogDAO");
	}

	@Test
	public void addBlogtest() {
		Blog blog=new Blog();
		blog.setBlogName("science of deduction");
		blog.setBlogContent("science and its concepts");
		blog.setLikes(0);
		blog.setLoginname("mano");
		blog.setStatus("A");
		
		assertTrue("Problem in Blog Insertion",blogDAO.addBlog(blog));
	}

	@Test
	public void deleteBlogTest()
	{
		assertTrue("Problem in Blog deletion",blogDAO.deleteBlog(568));
	}
	
	@Test
	public void rejectBlogTest()
	{
		Blog blog=blogDAO.getBlog(850);
		assertTrue("Problem in Blog rejection",blogDAO.rejectBlog(blog));
	}
	
	@Test
	public void approveBlogTest()
	{
		Blog blog=blogDAO.getBlog(851);
		assertTrue("Problem in Blog approval",blogDAO.approveBlog(blog));
	}
	
	@Test
	public void listBlogsByUserBlogTest()
	{
		List<Blog> listBlogs=blogDAO.listBlogs("mano");
		assertTrue("Problem in Blog listing",listBlogs.size()>0);
		
		for(Blog blog:listBlogs)
		{
			System.out.println(blog.getBlogName()+"::");
			System.out.println(blog.getBlogContent()+"::");
			System.out.println(blog.getLoginname()+"::");
			
		}
	}
	
	@Test
	public void incrementLikeBlogTest()
	{
		Blog blog=blogDAO.getBlog(851);
		assertTrue("Problem in like increment",blogDAO.incrementLike(blog));
	}
	
	@Test
	public void addBlogCommenttest() {
		BlogComment comment=new BlogComment();
		comment.setBlogId(850);
		comment.setLoginname("abi");
		comment.setCommentDate(new java.util.Date());
		comment.setCommentText("informative");
		
		assertTrue("Problem in Blog Insertion",blogDAO.addBlogComment(comment));
	}
	
}
