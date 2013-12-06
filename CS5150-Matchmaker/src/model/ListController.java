package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListController{
	
	/**
	 * Create a new item of a given type and description.
	 */
	public static MultipleItem createItem(EntityManager em, String description, String type) throws InstantiationException, IllegalAccessException {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		MultipleItem m = (MultipleItem) ItemFactory.create(type, description);
		LatestAddition addition = new LatestAddition(type, description);
		
		em.persist(m);
		em.persist(addition);
		tx.commit();
		return m;
	}
	/**
	 * Removes a given item
	 */
	public static void removeItem(EntityManager em, MultipleItem typeElement) throws InstantiationException, IllegalAccessException {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (typeElement != null) {
			typeElement.removeStudents();
			em.remove(typeElement);
		}
		
		tx.commit();
		
	}
	/**
	 * Get Item of a given type by id.
	 */
	public static MultipleItem getItemById(EntityManager em, long id, String type) throws InstantiationException, IllegalAccessException{
		
		String query = ItemFactory.getQuery(type) + " where m.id = " + id;
		
        @SuppressWarnings("unchecked")
        List<MultipleItem> itens = (List<MultipleItem>) em.createQuery(query).getResultList();
        
        return itens.get(0);
	}
	/**
	 * Rename a given Item.
	 */
	public static void renameItem(EntityManager em, MultipleItem item, String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		item.setDescription(description);
		
		tx.commit();
	}
	/**
	 * Add student to the item table.
	 */
	public static void addStudent(EntityManager em, MultipleItem item, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		item.addStudent(s);
		
		tx.commit();
	}
	/**
	 *Remove a student from the item table.
	 */
	public static void removeStudent(EntityManager em, MultipleItem item, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		item.removeStudent(s);
		
		tx.commit();
	}
	/**
	 * Return a string array of all items of a given type.
	 */
	public static String[] get(EntityManager em, String type) {
        String query = ItemFactory.getQuery(ItemFactory.create(type, ""));
		@SuppressWarnings("unchecked")
		List<MultipleItem> it = (List<MultipleItem>) em.createQuery(query).getResultList();
		List<String> itens = new LinkedList<String>();
		for (MultipleItem m : it) {
			itens.add(m.getDescription());
		}
		
		return (String[]) itens.toArray();
	}
	/**
	 * Returns an array list of all item of given type. 
	 */
	public static List<MultipleItem> getItensList(EntityManager em,String type){
        try {
        	 String query = ItemFactory.getQuery(ItemFactory.create(type, ""));
		@SuppressWarnings("unchecked")
		List<MultipleItem> itens = (List<MultipleItem>) em.createQuery(query).getResultList();
		return itens;
        }
        catch (Exception e){
        	return new ArrayList<MultipleItem>();
        }
	}
	/**
	 * Return item by description.
	 */
	public static MultipleItem getItemByDescription(EntityManager em, String description, String type){
		
        String query = ItemFactory.getQuery(ItemFactory.create(type,""))+ 
        		" where m.description = \""+description+"\"";
		@SuppressWarnings("unchecked")
		List<MultipleItem> itens = (List<MultipleItem>) em.createQuery(query).getResultList();
		
		return itens.get(0);
	}
	
	public static List<LatestAddition> getLatestAddedFields(EntityManager em) {
		
		String query = "select a from LATESTADDITION a";
		List<LatestAddition> items = (List<LatestAddition>) em.createQuery(query).getResultList();
		return items;
	}
	
	
	/**
	 * Parse a string of items separated by ',' to an ArrayList of items with
	 * the string description.
	 */
	public static List<MultipleItem> parseItens(String itens, String type ){
		if (itens == "") return new ArrayList<MultipleItem>();
		String [] itensArray = itens.split(",");
		List<MultipleItem> itensList = new ArrayList<MultipleItem>();
		for (String m : itensArray){
			if (m != ""){
				itensList.add((MultipleItem) ItemFactory.create(type, m));
			}
		}
		return itensList;
	}
	/**
	 * Return a JSONObject with all items of a given type.
	 */
	public static JSONObject getItemJson(EntityManager em, String type) {
        String query = ItemFactory.getQuery(type);
		@SuppressWarnings("unchecked")
		List<MultipleItem> itens = (List<MultipleItem>) em.createQuery(query).getResultList();
		JSONArray jsonArray = new JSONArray();
		for (MultipleItem m : itens){
			JSONObject jsonObject= new JSONObject();
			try {
				jsonObject.put("value", String.valueOf(m.getId()));
				jsonObject.put("name", m.getDescription());
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		}
		JSONObject items_obj = new JSONObject();
		try {
			items_obj.put("items", jsonArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return items_obj;
	}
	
	

}
