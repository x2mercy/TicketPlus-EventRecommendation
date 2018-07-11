package rpc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import entity.Item;
import entity.Item.ItemBuilder;

public class RpcHelperTest {

	@Test
	public void testGetJSONArray() throws JSONException {
		Set<String> category = new HashSet<String>();
		category.add("category one");
		ItemBuilder one = new ItemBuilder();
		one.setItemId("one");
		one.setLatitude(33.33);
		one.setRating(5);
		one.setCategories(category);
		one.setLongitude(33.33);
		Item o = one.build();
		ItemBuilder two = new ItemBuilder();
		two.setItemId("two");
		two.setLatitude(33.33);
		two.setRating(5);
		two.setCategories(category);
		two.setLongitude(33.33);
		Item t = two.build();

		List<Item> listItem = new ArrayList<Item>();
		listItem.add(o);
		listItem.add(t);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(o.toJSONObject());
		jsonArray.put(t.toJSONObject());
		
		JSONAssert.assertEquals(jsonArray, RpcHelper.getJSONArray(listItem), true);
	}


}
