package com.model2.mvc.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method 구현 않음
		
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
	public User getUser( @PathVariable String userId ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		//Business Logic
		return userService.getUser(userId);
	}

	@RequestMapping( value="json/login", method=RequestMethod.POST )
	public User login(	@RequestBody User user,
									HttpSession session ) throws Exception{
	
		System.out.println("/user/json/login : POST");
		//Business Logic
		System.out.println("::"+user);
		User dbUser=userService.getUser(user.getUserId());
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
		}
		
		return dbUser;
	}
	@RequestMapping(value="json/addUser", method=RequestMethod.POST)
	public User addUser(@RequestBody User user) throws Exception {
		userService.addUser(user);
		return user;
	}
	
	@RequestMapping( value="json/updateUser", method=RequestMethod.GET )
	public User updateUser( @PathVariable String userId) throws Exception{

		System.out.println("/user/updateUser : GET");
		//Business Logic
		User user = userService.getUser(userId);
		
		return user;
	}

	@RequestMapping( value="json/updateUser", method=RequestMethod.POST )
	public User updateUser(@RequestBody User user, HttpSession session) throws Exception{

		System.out.println("/user/updateUser : POST");
		//Business Logic
				
		// userService.updateUser(user);
		User sessionId=userService.getUser(user.getUserId());
		if(user.getUserId().equals(sessionId.getUserId())){
			session.setAttribute("user", sessionId);
		}
		
		return sessionId;
	}
	
	
	@RequestMapping( value="json/checkDuplication/{userId}", method=RequestMethod.POST )
	public boolean checkDuplication(@PathVariable String userId ) throws Exception{
		
		System.out.println("/user/checkDuplication : POST");
		
		return userService.checkDuplication(userId);
	}
}