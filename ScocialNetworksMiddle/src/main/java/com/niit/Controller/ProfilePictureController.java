package com.niit.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.Dao.ProfilePictureDao;
import com.niit.model.ErrorClazz;
import com.niit.model.ProfilePicture;

@Controller
public class ProfilePictureController {

	@Autowired
	private ProfilePictureDao profilePictureDao;
	public ProfilePictureController()
	{
		System.out.println("ProfilePicture controller been is created");
	}

	@RequestMapping(value = "/uploadprofilepic", method = RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image, HttpSession session) {
		String email = (String) session.getAttribute("loginId");
		System.out.println("profilepicctrl,upload:"+email);
		if (email == null) {

			ErrorClazz error = new ErrorClazz(6, "Unauthorized access..");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
		ProfilePicture profilePicture = new ProfilePicture();
		profilePicture.setEmail(email);
		profilePicture.setImage(image.getBytes());
		profilePictureDao.uploadProfilePicture(profilePicture);
		return new ResponseEntity<ProfilePicture>(profilePicture, HttpStatus.OK);
	}

	@RequestMapping(value = "/getimage/{email:.+}", method = RequestMethod.GET)
	public @ResponseBody byte[] getImage(@PathVariable String email, HttpSession session) {
		System.out.println(email);
		String auth = (String) session.getAttribute("loginId");
		if (auth == null) {
			return null; // <img src="null alt="no image"
		}
		ProfilePicture profilePicture = profilePictureDao.getImage(email); // requests profile pic of email;
		if (profilePicture == null)
		{
			return null;
		} 
		else
			return profilePicture.getImage();
	}
}
