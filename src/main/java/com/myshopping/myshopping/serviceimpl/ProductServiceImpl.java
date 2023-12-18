package com.myshopping.myshopping.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myshopping.myshopping.dto.ProductDto;
import com.myshopping.myshopping.modal.Cart;
import com.myshopping.myshopping.modal.Product;
import com.myshopping.myshopping.modal.User;
import com.myshopping.myshopping.repository.CartRepository;
import com.myshopping.myshopping.repository.ProductRepository;
import com.myshopping.myshoppingservice.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired 
	ProductRepository productRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	CartRepository cartRepository;	
	
	@Override
	public ProductDto saveProduct(ProductDto productDto) {
		Product product=new Product();
		ProductDto productDtos=new ProductDto();
		product.setName(productDto.getName());
		product.setBattery(productDto.getBattery());
		product.setFrentCamara(productDto.getFrentCamara());
		product.setBackCamara(productDto.getBackCamara());
		product.setRam(productDto.getRam());
		product.setStorage(productDto.getStorage());
		product.setDisplay(productDto.getDisplay());
		product.setColor(productDto.getColor());
		product.setProductPic(productDto.getProductPic());
		product.setRate(productDto.getRate());
		product.setProcesser(productDto.getProcesser());
		product.setBrand(productDto.getBrand());
		product.setProduct(productDto.getProduct());
		Product products=productRepository.save(product);
		if(products!=null)
		{
			products.setMessage("SUCCESS");
			BeanUtils.copyProperties(products, productDtos);
			
		}		
		return productDtos;
		
	}

	@Override
	public List<ProductDto> findAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products=productRepository.findAll();
		List<ProductDto> listOfProduct=new ArrayList<>();
		for(Product productItr:products)
		{
			ProductDto productDto=new ProductDto();
			productDto.setName(productItr.getName());
			productDto.setBackCamara(productItr.getBackCamara());
			productDto.setFrentCamara(productItr.getFrentCamara());
			productDto.setRam(productItr.getRam());
			productDto.setStorage(productItr.getStorage());
			productDto.setDisplay(productItr.getDisplay());
			productDto.setId(productItr.getId());
			productDto.setRate(productItr.getRate());
			productDto.setProductPic(productItr.getProductPic());
			productDto.setColor(productItr.getColor());
			productDto.setBattery(productItr.getBattery());
			productDto.setProcesser(productItr.getProcesser());
			productDto.setProduct(productItr.getProduct());
			productDto.setBrand(productItr.getBrand());
			listOfProduct.add(productDto);
		}
		return listOfProduct;
	}

	@Override 	
	public Boolean saveProductDetailsFromExcel(MultipartFile file) throws IOException {
		List<Product> products=new ArrayList<>();
		try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {	      
	        Sheet sheet = workbook.getSheetAt(0);
	        Iterator<Row> row=sheet.iterator();
	        
 	        while(row.hasNext())
 	        {
 	        	Product product=new Product();
 	        	Row rows=row.next();	        	
 	        	
 	        	Cell name=rows.getCell(0);
 	        	Cell backCam=rows.getCell(1);
 	        	Cell frentCam=rows.getCell(2);
 	        	Cell ram=rows.getCell(3);
 	        	Cell storage=rows.getCell(4);
 	        	Cell color=rows.getCell(5);
 	        	Cell display=rows.getCell(6);
 	        	Cell battery=rows.getCell(7);
 	        	Cell rate=rows.getCell(8);
 	        	Cell processer=rows.getCell(9); 	        	
 	        	Cell brand=rows.getCell(10);
 	        	Cell whatProduct=rows.getCell(11);
 	        	Cell pic=rows.getCell(12);
 	        	
 	        	product.setName(name.toString());
 	        	product.setFrentCamara(frentCam.toString());
 	        	product.setBackCamara(backCam.toString());
 	        	product.setRam(ram.toString());
 	        	product.setStorage(storage.toString());
 	        	product.setColor(color.toString());
 	        	product.setDisplay(display.toString());
 	        	product.setProcesser(processer.toString());
 	        	product.setProductPic(pic.toString());
 	        	product.setRate(Double.parseDouble(rate.toString()));
 	        	product.setProduct(whatProduct.toString());
 	        	product.setBrand(brand.toString());
 	        	products.add(product);
 	        
 	        }
	        
	    } catch (EncryptedDocumentException | InvalidFormatException e) {
	        e.printStackTrace();	        
	    }
	    for(Product productItr : products)
	    {
	   productRepository.save(productItr);
	    }	    
	    return true;
	    
	}

	@Override
	public List<ProductDto> findBrandName(String brand) {
		Query query=new Query(Criteria.where("brand").is(brand));
		List<Product> listOfProducts=mongoTemplate.find(query, Product.class);
		List<ProductDto> productDto=new ArrayList<>();
		for(Product productItr:listOfProducts)
		{
			ProductDto porduct=new ProductDto();
			BeanUtils.copyProperties(productItr, porduct);
			productDto.add(porduct);
		}
		return productDto;
	}

	@Override
	public List<ProductDto> findWhatProduct(String product) {
		Query Query=new Query(Criteria.where("product").is(product));
		List<Product> products=mongoTemplate.find(Query, Product.class);
		List<ProductDto> productDtos=new ArrayList<>();
		for(Product productItr:products)
		{
			ProductDto productDto=new ProductDto();
			BeanUtils.copyProperties(productItr, productDto);
			productDtos.add(productDto);
		}
		return productDtos;
	}

	@Override
	public List<ProductDto> findByProductByRate(Double startRate,Double endRate) {
		Query query=new Query(Criteria.where("rate").gte(startRate).lte(endRate));
		List<Product> productList=mongoTemplate.find(query, Product.class);
		List<ProductDto> productDtos=new ArrayList<>();
		for(Product productItr:productList)
		{
			ProductDto productDto=new ProductDto();
			BeanUtils.copyProperties(productItr, productDto);
			productDtos.add(productDto);
		}
		return productDtos;
	}

	@Override
	public List<ProductDto> showOfWhateverWeWant(ProductDto productDto) {
		List<ProductDto> listOfProductDto=new ArrayList<>();
		if(productDto!=null)
		{		
		if(productDto.getBrand()!=null && productDto.getProduct()!=null)
		{
		Query query=new Query(Criteria.where("brand").is(productDto.getBrand()).andOperator(Criteria.where("product").is(productDto.getProduct())));
		List<Product> listOfProducts=mongoTemplate.find(query, Product.class);
		List<ProductDto> productDto2=new ArrayList<>();
		for(Product productItr:listOfProducts)
		{
			ProductDto porduct=new ProductDto();
			BeanUtils.copyProperties(productItr, porduct);
			productDto2.add(porduct);
		}
		return productDto2;
		}
		
		}
		return listOfProductDto;
	}

	@Override
	public Boolean findSelectedProduct(String productid,String id) {
		Optional<Product> product=productRepository.findById(productid);
		if(product.isPresent())
		{
			Product product2=product.get();
		Cart cart=new Cart();
		cart.setName(product2.getName());
		cart.setRate(product2.getRate());
		cart.setBattery(product2.getBattery());
		cart.setFrentCamara(product2.getFrentCamara());
		cart.setBackCamara(product2.getBackCamara());
		cart.setColor(product2.getColor());
		cart.setProcesser(product2.getProcesser());
		cart.setProduct(product2.getProduct());
		cart.setDisplay(product2.getDisplay());
		cart.setBrand(product2.getBrand());
		cart.setProductPic(product2.getProductPic());
		cart.setRam(product2.getRam());
		cart.setStorage(product2.getStorage());
		
		
		//BeanUtils.copyProperties(product.get(), cart);
		cart.setProductBuyId(1);
		User user=new User();
		user.setId(id);
		cart.setUser(user);
		cartRepository.save(cart);
		return true;
		}
		return false;
		
	}

	@Override
	public Boolean findCartAvailOrNot(String productid) {
		Optional<Cart> cart=cartRepository.findById(productid);
		if(cart.isPresent())
		{
			return true;
		}
		return false;
	}


}
