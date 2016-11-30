package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.util.List;

import org.junit.Test;

import pl.lodz.p.iis.ppkwu.reddit.api.Category;
import pl.lodz.p.iis.ppkwu.reddit.api.Result;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.CategoryImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.ResultTest;

public class CategoryLoaderTest {

	@Test
	public void test() {
		CategoryLoader loader = new CategoryLoader();
		Result<List<Category>> result = loader.load();
		ResultTest.rezultatJestSpojny(result);
		System.out.println("-------");
		result.content().ifPresent(list -> list.forEach(cat -> {
			System.out.println("Name: " + cat.name());
			if(cat instanceof CategoryImpl) {
				CategoryImpl cati = (CategoryImpl)cat;
				System.out.println("RelUrl:" + cati.relativeUrl());
			}
			System.out.println("-------");
		}));
	}

}
