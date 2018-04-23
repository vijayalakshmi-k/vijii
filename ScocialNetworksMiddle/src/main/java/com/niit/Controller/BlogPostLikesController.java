package com.niit.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.BlogPostDao;
import com.niit.Dao.BlogPostLikesDao;
import com.niit.model.BlogPost;
import com.niit.model.BlogPostLikes;
import com.niit.model.ErrorClazz;

@Controller
public class BlogPostLikesController {
@Autowired
private BlogPostLikesDao blogPostLikesDao;
@RequestMapping(value="/hasUserLikedblog/{blogId}",method=RequestMethod.GET)
public ResponseEntity<?> hasUserLikedBlog(@PathVariable int blogId,HttpSession session)
{
	String email = (String)session.getAttribute("loginId");
	if (email == null) //Not loggedin
	{
		ErrorClazz error = new ErrorClazz(5, "Unauthorized access..");
		return new ResponseEntity<ErrorClazz>(error ,HttpStatus.UNAUTHORIZED);
}
BlogPostLikes blogPostLikes=blogPostLikesDao.hasUserLikedBlog(blogId,email);
return new ResponseEntity<BlogPostLikes>(blogPostLikes,HttpStatus.OK);
}

@RequestMapping(value="/updateLikes/{id}",method=RequestMethod.PUT)
public ResponseEntity<?> updateLikes(@PathVariable int id,HttpSession session)
{
	String email = (String)session.getAttribute("loginId");
	if (email == null) //Not loggedin
	{
		ErrorClazz error = new ErrorClazz(5, "Unauthorized access..");
		return new ResponseEntity<ErrorClazz>(error ,HttpStatus.UNAUTHORIZED);
}
	System.out.println("The blogPostLikes is added:"+id);
	//blogpost object with updated value
	BlogPost blogPost=blogPostLikesDao.updateLikes(id,email);
	return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);

	
	


}



}
