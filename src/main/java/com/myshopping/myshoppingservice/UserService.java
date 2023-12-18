package com.myshopping.myshoppingservice;



import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Attribute.Use;

import com.myshopping.myshopping.dto.UserDto;
import com.myshopping.myshopping.modal.User;

public interface UserService {
	UserDto saveuser(UserDto userDto);
	UserDto checkLoginDetails(UserDto userDto);
	List<UserDto> findAllUser();
	UserDto updateUserDetails(UserDto userDto);
	UserDto deleteUser(UserDto userDto);
	UserDto deleteAllUser();
	UserDto findByUserId(String userId);
	void saveImage(String id,byte[] imageData);
	User getImageById(String id);
	boolean checkEmailId(String email);
	boolean checkUserNameAvailableOrNot(String uname);
	boolean checkEmailIdAvailableOrNot(String email);
}
