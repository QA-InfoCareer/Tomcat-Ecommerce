package com.myshopping.myshoppingservice;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.myshopping.myshopping.dto.ProductDto;
import com.myshopping.myshopping.modal.Cart;
import com.myshopping.myshopping.modal.Product;

import jakarta.mail.Multipart;

public interface ProductService {
	ProductDto saveProduct(ProductDto productDto);
	List<ProductDto> findAllProducts();	
	Boolean saveProductDetailsFromExcel(MultipartFile file) throws IOException;
	List<ProductDto> findBrandName(String brand);
	List<ProductDto> findWhatProduct(String product);
	List<ProductDto> findByProductByRate(Double startRate,Double endRate);
	public List<ProductDto> showOfWhateverWeWant(ProductDto productDto);
	Boolean findSelectedProduct(String productid,String id);
	Boolean findCartAvailOrNot(String productid);
	
}
