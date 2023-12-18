package com.myshopping.myshopping.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myshopping.myshopping.dto.ProductDto;
import com.myshopping.myshopping.dto.UserDto;
import com.myshopping.myshoppingservice.ProductService;
import com.myshopping.myshoppingservice.UserService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/saveproduct",method = RequestMethod.POST)
	public ProductDto saveProduct(ProductDto productDto) {
		ProductDto product=productService.saveProduct(productDto);
		return product;
	}
	
	@RequestMapping(value = "/showallproduct",method = RequestMethod.GET)
	public List<ProductDto> findAllProducts() {
		return productService.findAllProducts();
	}
	//Excel product
	@RequestMapping(value="/importproductdetailsfromexcel",method = RequestMethod.POST)
	public Boolean saveProductDetailsFromExcel(MultipartFile file) throws IOException {
		return productService.saveProductDetailsFromExcel(file);
	}
	//whatproduct
	@RequestMapping(value="/showproductbyproductname",method = RequestMethod.POST)
	public List<ProductDto> findWhatProduct(@RequestParam("product") String product) {
		return productService.findWhatProduct(product);
	}
	//brand
	@RequestMapping(value="/showproductbybrandname",method = RequestMethod.POST)
	public List<ProductDto> findBrandName(@RequestParam("brand") String brand) {
		return productService.findBrandName(brand);
	}
	@RequestMapping(value="/showproductbyrate",method = RequestMethod.GET)
	public List<ProductDto> findByProductByRate(Double startRate,Double endRate) {
		
		return productService.findByProductByRate((Double)startRate,(Double)endRate);
	}

	@RequestMapping(path="/getwhatproductyouwant",method = RequestMethod.GET)
	public List<ProductDto> showOfWhateverWeWant(ProductDto productDto) {
		return productService.showOfWhateverWeWant(productDto);
	}
	@RequestMapping(path="/showallusers",method = RequestMethod.GET)
	public List<UserDto> findAllUser() {
		List<UserDto> userDtos=userService.findAllUser();
		return userDtos;
	}
	@RequestMapping(path = "/addtocartproduct",method = RequestMethod.POST)
	public Boolean findSelectedProduct(String productid,String id) {
		return productService.findSelectedProduct(productid,id);
	}
	@RequestMapping(path = "/findproductavailornot",method = RequestMethod.POST)
	public Boolean findCartAvailOrNot(String productid) {
		return productService.findCartAvailOrNot(productid);
	}
}
