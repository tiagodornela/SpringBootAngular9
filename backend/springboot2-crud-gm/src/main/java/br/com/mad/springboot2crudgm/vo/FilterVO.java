package br.com.mad.springboot2crudgm.vo;

import java.io.Serializable;

import br.com.mad.springboot2crudgm.model.enumeration.CategoryType;

public class FilterVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private CategoryType category;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public CategoryType getCategory() {
		return category;
	}
	
	public void setCategory(CategoryType category) {
		this.category = category;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilterVO other = (FilterVO) obj;
		if (category != other.category)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
