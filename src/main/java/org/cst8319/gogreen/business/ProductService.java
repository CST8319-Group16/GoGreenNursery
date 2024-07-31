package org.cst8319.gogreen.business;

import org.cst8319.gogreen.DAO.ProductDAO;
import org.cst8319.gogreen.DTO.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    public List<Product> getAllProducts(){
        return productDAO.getAllProducts();
    }

    public Product getProductById(int productId){

            return productDAO.getProductById(productId);

    }

    public void updateProduct(Product product) {

            productDAO.updateProduct(product);

    }

    public void deleteProduct(int productId){
             productDAO.deleteProduct(productId);
    }
}


