/**
 * 
 */
package com.alok91340.epam.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alok91340.epam.Exceptions.ResourceNotFoundExcepction;
import com.alok91340.epam.dto.CategoryDto;
import com.alok91340.epam.dto.PostDto;
import com.alok91340.epam.entities.Comment;
import com.alok91340.epam.entities.Post;
import com.alok91340.epam.entities.User;
import com.alok91340.epam.repositories.CommentRepo;
import com.alok91340.epam.repositories.PostRepo;
import com.alok91340.epam.repositories.UserRepo;
import com.alok91340.epam.services.PostService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author alok91340
 *
 */
@Controller
//@RequestMapping("/blogify")
public class BlogController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	
	@RequestMapping("/addNewBlog")
	public ModelAndView newBlogPage(@RequestParam Integer userId) {
		ModelAndView mav = new ModelAndView("newBlog");
		Post post= new Post();
		mav.addObject("post", post);
		mav.addObject("userId", userId);
		return mav;
	}
	
	@RequestMapping("/updateBlog")
	public ModelAndView updateBlogPage(@RequestParam Integer postId,@RequestParam Integer userId) {
		ModelAndView mav = new ModelAndView("newBlog");
		
		Post post= postRepo.findByPostId(postId);
		mav.addObject("post", post);
		mav.addObject("userId", userId);
		return mav;
	}
	
	@RequestMapping(path= "/blogform", method=RequestMethod.POST)
	public ModelAndView createBlog(@RequestParam Integer userId,HttpServletRequest request,Model model) {
		
		
		
//		String fileName = file.getOriginalFilename();
		Integer id= Integer.parseInt((String)request.getParameter("postId"));
		String title=(String)request.getParameter("title");
		String content=(String)request.getParameter("content");
		String categories=(String) request.getParameter("category");
		PostDto postDto = new PostDto();
		postDto.setContent(content);
		postDto.setCategory(new CategoryDto());
		postDto.setTitle(title);
		postDto.setImageName("default.png");
		PostDto result=postService.createPost(postDto, userId, categories,id);
		model.addAttribute("content","alok");
//		System.out.println(title+" "+ fileName+"post id:  "+id);
		ModelAndView mav= new ModelAndView("home");
		System.out.println("asdfghjklASDFGHJKLDFGH");
		List<Post> posts = postRepo.findAll();
		mav.addObject("posts",posts);
		mav.addObject("userId",userId);
		System.out.println("asdfghjklASDFGHJKLDFGH");
		return mav;
		
		
	}
	@RequestMapping("/readBlog")
	public ModelAndView readBlogPage(@RequestParam Integer postId,@RequestParam Integer userId) {
		ModelAndView mav = new ModelAndView("blog");
		
		Post post= postRepo.findByPostId(postId);
		Set<Comment> comments= commentRepo.findByPost(post);
		mav.addObject("post", post);
		mav.addObject("comments",comments);
		mav.addObject("userId", userId);
		return mav;
	}
	
	@RequestMapping( path="/home")
	public String getAllPost(HttpServletRequest request, Model model) {
		ModelAndView mav;
		
		String email= (String)request.getParameter("email");
		String password= (String)request.getParameter("pass");
		User user=userRepo.findByEmail(email);
//		System.out.println("abcdegGVHJBWFIBWEUFIA V;IFHBF"+user.getEmail());
		if(user==null||!user.getEmail().equals(email)|| !user.getPassword().equals(password)) {
//			 mav=new ModelAndView("login");
//			 mav.addObject("error", true);
			model.addAttribute("error", true);
			 return "redirect:/login";
		}
		else {
			Integer userId=user.getId();
//			mav= new ModelAndView("home");
			List<Post> posts = postRepo.findAll();
			model.addAttribute("posts",posts);
			model.addAttribute("userId",userId);
			return "home";
		}
		
//		Integer userId=Integer.parseInt((String)request.getParameter(""));
		
	}
	
	@RequestMapping(path="/deleteBlog")
	public ModelAndView deletePost(@RequestParam Integer postId, @RequestParam Integer userId) {
		postRepo.deleteById(postId);
		ModelAndView mav= new ModelAndView("Your-Blog");
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundExcepction("User", "id", userId));
		List<Post> posts=postRepo.findByUser(user);
		mav.addObject("posts",posts);
		mav.addObject("user", user);

		return mav;
	}
	
	@RequestMapping("/homepage")
	public ModelAndView homePage(@RequestParam Integer userId) {
		ModelAndView mav= new ModelAndView("home");
//		System.out.println("asdfghjklASDFGHJKLDFGH");
		List<Post> posts = postRepo.findAll();
		mav.addObject("posts",posts);
		mav.addObject("userId",userId);
//		System.out.println("asdfghjklASDFGHJKLDFGH");
		return mav;
	}
}
