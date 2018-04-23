package com.niit.Dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.ProfilePicture;

@Repository
@Transactional
public class ProfilePictureDaoImpl implements ProfilePictureDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void uploadProfilePicture(ProfilePicture profilePicture) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(profilePicture);
	}
	public ProfilePicture getImage(String email) {
		
		Session session=sessionFactory.getCurrentSession();
		ProfilePicture profilePicture=(ProfilePicture)session.get(ProfilePicture.class, email);
		return profilePicture;
	}
}
