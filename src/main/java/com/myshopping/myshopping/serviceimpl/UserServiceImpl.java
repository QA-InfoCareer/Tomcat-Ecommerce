package com.myshopping.myshopping.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.myshopping.myshopping.controller.MailConfig;
import com.myshopping.myshopping.dto.UserDto;
import com.myshopping.myshopping.modal.User;
import com.myshopping.myshopping.repository.UserRepository;
import com.myshopping.myshopping.utility.Utility;
import com.myshopping.myshoppingservice.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	MailConfig mailConfig;
	
	@Autowired
	Utility utility;
	
	public UserDto saveuser(UserDto userDto){
		User user=new User();
		user.setName(userDto.getName());
		user.setAge(userDto.getAge());
		user.setCity(userDto.getCity());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());		
		Date date=new Date();
		user.setAccountCreatedDate(date);
		user.setUserType(userDto.getUserType());
		user.setPhone(userDto.getPhone());
		byte[] image=userDto.getImageData();
		user.setImageData(image);
		User saveUser=userRepository.save(user);
		UserDto savedUserDto=new UserDto();
		BeanUtils.copyProperties(saveUser, savedUserDto);
		savedUserDto.setMassage("Your account created successfully");
		mailConfig.mailSendWhenUserRegister(savedUserDto);
		return savedUserDto;
	}	
	
	@Override
	public UserDto checkLoginDetails(UserDto userDto) {
		/*User user=userRepository.findByNameAndPassword(userDto.getName(),userDto.getPassword());
		if(user!=null)
			return true;
		else
			return false;*/
		UserDto userDtos=new UserDto();
		Query query = new Query(Criteria.where("name").is(userDto.getName()).andOperator(Criteria.where("password").is(userDto.getPassword())));
		User user=mongoTemplate.findOne(query, User.class);
		if(user != null)
		{
			user.setUflag(true);
			User users=userRepository.save(user);
			users.setMassage("true");				
			BeanUtils.copyProperties(users, userDtos);				
			userDtos.setUserToken(utility.generateJwtToken(users));
			return userDtos;			
		}			
		else
		{
			return userDtos;
		}
	}
	
	public List<UserDto> findAllUser() {
		List<User> allUsers=userRepository.findAll();
		List<UserDto> userDtoList=new ArrayList<>();
		for(User userList:allUsers)
		{
			UserDto usersDtoList=new UserDto();
			usersDtoList.setId(userList.getId());
			usersDtoList.setName(userList.getName());
			usersDtoList.setAge(userList.getAge());
			usersDtoList.setCity(userList.getCity());
			usersDtoList.setPassword(userList.getPassword());
			usersDtoList.setMassage(userList.getMassage());
			usersDtoList.setUserToken(userList.getUserToken());
			usersDtoList.setEmail(userList.getEmail());	
			usersDtoList.setPhone(userList.getPhone());
			usersDtoList.setUserType(userList.getUserType());
			userDtoList.add(usersDtoList);
			
		}
		
		return userDtoList;
	}
	
	@Override
	public UserDto updateUserDetails(UserDto userDto) {
		UserDto userDtos=new UserDto();
		Query query = new Query(Criteria.where("id").is(userDto.getId()));
		Update update=new Update();
		update.set("name",userDto.getName());
		update.set("age",userDto.getAge());
		update.set("city",userDto.getCity());
		update.set("email",userDto.getEmail());
		update.set("password",userDto.getPassword());
		User users=mongoTemplate.findAndModify(query, update, User.class);
		if(users!=null)
		{
			BeanUtils.copyProperties(users, userDtos);
			userDtos.setMassage("update successfully...");
			return userDtos;
		}
		else
		{
			return null;
		}
		
	}

	@Override
	public UserDto deleteUser(UserDto userDto) {
		UserDto userDtos=new UserDto();
		Optional<User> user=userRepository.findById(userDto.getId());
		if(user.isPresent())
		{
			userRepository.deleteById(userDto.getId());
			userDtos.setMassage("deleted successfully...");
			return userDtos;
		}
		else
		{
			return null;
		}	
		
	}

	@Override
	public UserDto deleteAllUser() {
		UserDto userDtos=new UserDto();
		userRepository.deleteAll();
		userDtos.setMassage("deleted all users");
		return userDtos;
	}

	@Override
	public UserDto findByUserId(String userId) {
		UserDto userDto=new UserDto();
		Query query=new Query(Criteria.where("id").is(userId));
		User user=mongoTemplate.findOne(query, User.class);
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}

	@Override
	public void saveImage(String id,byte[] imageData) {
		User users=mongoTemplate.findById(id, User.class);
		users.setImageData(imageData);
		userRepository.save(users);
		
	}

	@Override
	public User getImageById(String id) {		
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public boolean checkEmailId(String email) {
		Query query=new Query(Criteria.where("email").is(email));
		User user= mongoTemplate.findOne(query, User.class);
		int random4DigitNumber = new Random().ints(1000, 10000).findFirst().getAsInt();
		String url="<html>"+"<head>+"+"</head>"+"<body"+"Your OTP IS"+random4DigitNumber+"<br>"+"http://localhost:8080/Forgetpassword.html"+"</body>"+"</html>";
		if(user!=null)
		{
			UserDto userDto=new UserDto();
			userDto.setEmail(user.getEmail());
			userDto.setMassage(url);
			mailConfig.otpSentToUser(userDto);
			return true;
		}
		return false;
	}

	@Override
	public boolean checkUserNameAvailableOrNot(String uname) {
		Query query=new Query(Criteria.where("name").is(uname));
		User user=mongoTemplate.findOne(query, User.class);
		if(user!=null)
		{
			return false;
		}
		return true;
	}

	@Override
	public boolean checkEmailIdAvailableOrNot(String email) {
		Query query=new Query(Criteria.where("email").is(email));
		User user=mongoTemplate.findOne(query, User.class);
		if(user!=null)
		{
			return false;
		}
		return true;
	}
	


}
