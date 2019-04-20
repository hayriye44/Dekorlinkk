package com.example.hayri.dekorlink.Model;

import java.util.List;

public class ProdactList{
	private List<ProductsItem> products;

	public void setProducts(List<ProductsItem> products){
		this.products = products;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}

	@Override
 	public String toString(){
		return 
			"ProdactList{" + 
			"products = '" + products + '\'' + 
			"}";
		}
}