package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.acdc.cnoyel.Categories;
import com.acdc.cnoyel.Post;

public class TestCategories {

	@Test
	// [TODO] Check that the category is always added to category.txt
	public void testGetAddRemoveCategory() {
		Post p = new Post("title", "my testinG cateGOry", "aaaaa", "aaaaaaaaaaaaaaaaaaaaa", null, null);
		Categories.addCategory(p.getCategory());
		
		System.out.println(Categories.getCategories());
		assertEquals("title", p.getTitle());
	}
	

}
