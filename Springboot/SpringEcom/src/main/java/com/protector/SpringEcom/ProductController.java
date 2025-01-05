package com.protector.SpringEcom;

import com.protector.SpringEcom.model.Product;
import com.protector.SpringEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
       return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
         Optional<Product> product = productService.getProductById(productId);
         return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile image) {
        try {
            Product createdProduct =  productService.addOrUpdateProduct(product, image);
            return  new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int productId) {
        Optional<Product> product = productService.getProductById(productId);
        return product.map(value -> new ResponseEntity<>(value.getImageData(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<?> updateProduct(@RequestPart Product product, @RequestPart MultipartFile image) {
        try {
            Product updateProduct =  productService.addOrUpdateProduct(product, image);
            return  new ResponseEntity<>(updateProduct, HttpStatus.ACCEPTED);
        } catch (IOException | NullPointerException e) {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
        final boolean isDeleted =  productService.deleteProduct(productId);
        if(isDeleted)
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        else
            return  new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        return new ResponseEntity<>(productService.searchProducts(keyword), HttpStatus.OK);
    }
}
