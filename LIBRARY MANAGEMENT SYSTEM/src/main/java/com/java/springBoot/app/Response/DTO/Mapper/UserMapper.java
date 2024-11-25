package com.java.springBoot.app.Response.DTO.Mapper;




import com.java.springBoot.app.Response.DTO.UserDTO;
import com.java.springBoot.app.Model.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor

public class UserMapper
{	
	public static UserDTO parse(User user)
	{
		UserDTO userDTO = null;
		
		do 
		{
			if(user == null) break;
			
			userDTO = new UserDTO();	
			
			userDTO.setUsername(user.getUsername());
			
		} 
		while (false);
		
		return userDTO;	
	}

}
