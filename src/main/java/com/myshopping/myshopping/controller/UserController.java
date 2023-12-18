package com.myshopping.myshopping.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myshopping.myshopping.dto.ProductDto;
import com.myshopping.myshopping.dto.UserDto;
import com.myshopping.myshopping.modal.User;
import com.myshopping.myshopping.utility.ApiConfig;
import com.myshopping.myshoppingservice.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	MailConfig mailConfig;
	
	
	  @Value("${myApp:default value is running}")
	  private String myApp;
	  
	  @Value("dev.appname")
	  private String devVar;
	 
	@Value("my name is naveen")
	private String myName;
	
	@Autowired
	private ApiConfig apiConfig;
	
	@Autowired
	Environment env;
	
	@RequestMapping(value="/saveuser",method = RequestMethod.POST)
	public ResponseEntity<UserDto> saveuser(@ModelAttribute("user") @Valid UserDto userDto,BindingResult bindingResult) {
		String errMsg="";
		UserDto userDto1=new UserDto();
		userDto1.setMassage(errMsg);
		if(bindingResult.hasFieldErrors("name"))
		{
			errMsg=bindingResult.getFieldError().getDefaultMessage();
			userDto1.setMassage(errMsg);
			return ResponseEntity.ok(userDto1);			
		}
		else if(bindingResult.hasFieldErrors("city"))
		{
			errMsg=bindingResult.getFieldError().getDefaultMessage();
			userDto1.setMassage(errMsg);
			return ResponseEntity.ok(userDto1);
		}
		else if(bindingResult.hasFieldErrors("password"))
		{
			errMsg=bindingResult.getFieldError().getDefaultMessage();
			userDto1.setMassage(errMsg);
			return ResponseEntity.ok(userDto1);
		}
		else
		{
			UserDto userDto2=userService.saveuser(userDto);
			userDto2.setMassage("SUCCESS");
			return ResponseEntity.ok(userDto2); 
		}
		
	}
	
	@RequestMapping(path="/checklogin",method = RequestMethod.POST)
	public UserDto checkLoginDetails(UserDto userDto) {
		return userService.checkLoginDetails(userDto);
		
	}
	
	@RequestMapping(path="/showall",method = RequestMethod.GET)
	public List<UserDto> findAllUser() {
		List<UserDto> userDtos=userService.findAllUser();
		return userDtos;
	}
	@RequestMapping(path="/updateuser",method = RequestMethod.POST)
	public UserDto updateUserDetails(UserDto userDto) {
		return userService.updateUserDetails(userDto);
	}
	@RequestMapping(path="/deleteuser",method = RequestMethod.POST)
	public UserDto deleteUser(UserDto userDto) {
		return userService.deleteUser(userDto);
	}
	@RequestMapping(path="/deleteallusers",method=RequestMethod.POST)
	public UserDto deleteAllUser() {
		return userService.deleteAllUser();
	}
	@RequestMapping(path="/finduserbyid",method=RequestMethod.POST)
	public UserDto findByUserId(String userId) {
		return userService.findByUserId(userId);
	}
	
	@RequestMapping(path="/uploadimage",method=RequestMethod.POST)
    public String uploadImage(@RequestParam("id") String id,@RequestParam("file") MultipartFile file) {
        try {
        	userService.saveImage(id,file.getBytes());
            return "Image uploaded successfully!";
        } catch (Exception e) {
            return "Error uploading image: " + e.getMessage();
        }
    }
	
	@RequestMapping(path="/getuserpic",method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageById(@RequestParam("id") String id) {
        User user = userService.getImageById(id);
        if (user != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(user.getImageData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }  
    
	@RequestMapping(path="/forgetpassword",method = RequestMethod.POST)
	public boolean checkEmailId(@RequestParam("email") String email) {
		return userService.checkEmailId(email);
	}
	@RequestMapping(path="/usernamecheck",method = RequestMethod.POST)
	public boolean checkUserNameAvailableOrNot(@RequestParam("uname") String uname) {
		return userService.checkUserNameAvailableOrNot(uname);
	}
	@RequestMapping(path="/emailidcheck",method = RequestMethod.POST)
	public boolean checkEmailIdAvailableOrNot(@RequestParam("email") String email) {
		return userService.checkEmailIdAvailableOrNot(email);
	}
	
	@GetMapping("/showappname")
	public String showAppName()
	{
		
		System.out.println("host :"+apiConfig.getHost());
		System.out.println("port :"+apiConfig.getPassword());
		System.out.println("username :"+apiConfig.getUserName());
		System.out.println("my list "+apiConfig.getValue());
		return myApp+" "+myName+" "+devVar;
	}
	
	@GetMapping("/envdetails")
	public String envDetails()
	{
		return env.getProperty("env.name");
	}

}
