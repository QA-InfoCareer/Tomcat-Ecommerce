package com.myshopping.myshopping.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myshopping.myshopping.dto.UserDto;


@RestController
@RequestMapping("/mailvalidation")
public class MailConfig {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	
	@PostMapping("/sendmail")
	public ResponseEntity<Object> sendEmail(@RequestBody UserDto userDto)
	{
		Random random = new Random();
		int otp = random.nextInt(900000) + 100000; // Generates a random number between 100000 and 999999
		String str= String.valueOf(otp);
		SimpleMailMessage sms=new SimpleMailMessage();
		sms.setFrom("mnaveenkumarm716@gmail.com");
		sms.setText("welcome"+" "+userDto.getName()+" "+str+" "+"is your otp");
		sms.setTo(userDto.getEmail());
		sms.setSubject("Hello "+userDto.getName()+" welcome");
		javaMailSender.send(sms);
		return generateResponse(userDto.getEmail(), HttpStatus.OK, userDto);
	}
	
	public ResponseEntity<Object> generateResponse(String msg,HttpStatus st,Object response)
	{
		Map<String,Object> map=new HashMap<>();
		map.put("message", msg);
		map.put("status",st.value());
		map.put("data", response);
		return new ResponseEntity<Object>(map,st); 
	}
	@PostMapping("/commonmail")
	public ResponseEntity<Object> sendMailMessage(@RequestBody UserDto userDto)
	{
		
		SimpleMailMessage sms=new SimpleMailMessage();
		sms.setFrom("mnaveenkumarm716@gmail.com");
		sms.setText(userDto.getMassage());
		sms.setTo(userDto.getEmail());
		sms.setSubject("MyShopping");
		javaMailSender.send(sms);
		return generateResponseMail(userDto.getEmail(),HttpStatus.OK,userDto);
	}
	private ResponseEntity<Object> generateResponseMail(String email, HttpStatus ok, UserDto userDto) {
		Map< String,Object> map=new HashMap<>();
		map.put("message",email);
		map.put("status",ok.value());
		map.put("data",userDto);
		return new ResponseEntity<Object>(map,ok);
	}
	
	@PostMapping("/mailsendtouser")//this one is working
	public void mailSendWhenUserRegister(UserDto userDto) {
		try {
		    /*SimpleMailMessage sms = new SimpleMailMessage();
		    sms.setFrom("mnaveenkumarm716@gmail.com");
		    sms.setText(userDto.getMassage());
		    sms.setTo(userDto.getEmail());
		    sms.setSubject("MyShopping");
		    javaMailSender.send(sms);*/
		    
		    
		    
		    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	        simpleMailMessage.setTo(userDto.getEmail());
	        simpleMailMessage.setFrom("mnaveenkumarm716@gmail.com");
	        simpleMailMessage.setText(userDto.getMassage());
	        simpleMailMessage.setSubject("MyShopping");
	        Date date=new Date();
	        simpleMailMessage.setSentDate(date);	        
	        javaMailSender.send(simpleMailMessage);
		    System.out.println("Email sent successfully!");
		} catch (Exception e) {
		    System.out.println("Error sending email: " + e.getMessage());
		   
		}

	}
	
	@PostMapping("/otpmailsendtouser")//this one is working
	public void otpSentToUser(UserDto userDto) {
		try {	    
		    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	        simpleMailMessage.setTo(userDto.getEmail());
	        simpleMailMessage.setFrom("mnaveenkumarm716@gmail.com");
	        simpleMailMessage.setText(userDto.getMassage());
	        simpleMailMessage.setSubject("ForgetPassword");
	        Date date=new Date();
	        simpleMailMessage.setSentDate(date);	        
	        javaMailSender.send(simpleMailMessage);
		    System.out.println("Email sent successfully!");
		} catch (Exception e) {
		    System.out.println("Error sending email: " + e.getMessage());
		   
		}

	}

	
}
