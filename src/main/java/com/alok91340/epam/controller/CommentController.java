/**
 * 
 */
package com.alok91340.epam.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alok91340.epam.repositories.CommentRepo;
import com.alok91340.epam.repositories.PostRepo;
import com.alok91340.epam.services.CommentService;
import com.alok91340.epam.dto.CommentDto;
import com.alok91340.epam.entities.Comment;
import com.alok91340.epam.entities.Post;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author alok91340
 *
 */
@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	
	@RequestMapping(path="/comment", method=RequestMethod.POST)
	public ModelAndView createComment(@RequestParam Integer postId,@RequestParam Integer userId,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("blog");
//		ModelAndView mav = new ModelAndView("blog");
		String content =(String) request.getParameter("content");
//		String postId=(String) request.getParameter("postId");
		
		CommentDto commentDto= new CommentDto();
		commentDto.setContent(content);
//		Post post = postRepo.findByPostId();
//		Integer id=Integer.parseInt(postId);
		if(content.length()==0) {
			Post post= postRepo.findByPostId(postId);
			Set<Comment> comments= commentRepo.findByPost(post);
			mav.addObject("post", post);
			mav.addObject("comments",comments);
			mav.addObject("userId", userId);
			return mav;
		}
		CommentDto dto=commentService.createComment(commentDto,postId,userId );

		
		Post post= postRepo.findByPostId(postId);
		Set<Comment> comments= commentRepo.findByPost(post);
		mav.addObject("post", post);
		mav.addObject("comments",comments);
		mav.addObject("userId", userId);
		return mav;
	}
}
