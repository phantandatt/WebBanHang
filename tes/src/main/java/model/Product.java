package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	int productID;
	@Column
	String productName;
	@Column
	int categoryID;
	@Column
	String description;
	@Column
	int price;
	@Column
	int stockQuantity;
	@Column
	String unit_price;
	@Column
	String imgPath;
	@Column
	String color;

	public Product(int productID, String productName, int categoryID, String description, int price,
			int stockQuantity, String unit_price, String imgPath, String color) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.categoryID = categoryID;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.unit_price = unit_price;
		this.imgPath = imgPath;
		this.color = color;
	}

	public int getProductID() {
		return productID;
	}

	public String getProductName() {
		return productName;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public String getDescription() {
		return description;
	}

	public int getPrice() {
		return price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public String getUnit_price() {
		return unit_price;
	}

	public String getImgPath() {
		return imgPath;
	}

	public String getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", categoryID=" + categoryID
				+ ", description=" + description + ", price=" + price + ", stockQuantity=" + stockQuantity
				+ ", unit_price=" + unit_price + ", imgPath=" + imgPath + "]";
	}

}