package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FieldValueController{
	
	/**
	 * Create a new field value of a given type and description.
	 */
	public static FieldValue createFieldValue(EntityManager em, String description, 
			String type) throws InstantiationException, IllegalAccessException {
		EntityTransaction tx = em.getTransaction();
		System.out.println("Creating a field");
		System.out.println("Searching if "+type+":"+description+" exists");
		if (getItemByDescription(em, description, type) != null) {
			System.out.println("IT exists");
			return null;
		}
		System.out.println("Doesn't exist, creating");
		tx.begin();
		
		FieldValue m = (FieldValue) FieldFactory.createField(type, description);
		LatestAddition addition = new LatestAddition(type, description);
		
		em.persist(m);
		em.persist(addition);
		tx.commit();
		System.out.println("Created");
		return m;
	}
	/**
	 * Removes a given field value
	 */
	@SuppressWarnings("unchecked")
	public static void removeFieldValue(EntityManager em, FieldValue value) 
			throws InstantiationException, IllegalAccessException {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (value == null) {
			return;
		}
		value.removeElements();
		String description = value.getDescription();
		String type = FieldFactory.getType(value);
		
		String query = "select a FROM LATESTADDITION a WHERE a.type = \""+type+"\" "
				+ "AND a.name = \""+description+"\"";
		List<LatestAddition> results = (List<LatestAddition>) em.createQuery(query).getResultList();
		try {
			LatestAddition add = results.get(0);
			em.remove(add);
		}
		catch (Exception e) {
			System.out.println("Addition not found while removing");
		}
		em.remove(value);
		tx.commit();
	}
	
	/**
	 * Get Item of a given type by id.
	 */
	public static FieldValue getFieldValueById(EntityManager em, long id, String type) throws InstantiationException, IllegalAccessException{
		
		String query = FieldFactory.getQuery(type) + " where m.id = " + id;
		
        @SuppressWarnings("unchecked")
        List<FieldValue> itens = (List<FieldValue>) em.createQuery(query).getResultList();
        try {
        	return itens.get(0);
        }
        catch (Exception e) {
        	return null;
        }
	}
	/**
	 * Rename a given Item.
	 */
	public static void renameFieldValue(EntityManager em, FieldValue item, 
			String newDescription) {
		EntityTransaction tx = em.getTransaction();
		if (item == null) {
			return;
		}
		tx.begin();
		
		item.setDescription(newDescription);
		
		tx.commit();
	}
	
	/**
	 * Add student to the item table.
	 */
	public static void addStudent(EntityManager em, FieldValue item, Student s) {
		EntityTransaction tx = em.getTransaction();
		if (item == null || s == null) {
			return;
		}
		tx.begin();
		
		item.addStudent(s);
		
		tx.commit();
	}
	/**
	 *Remove a student from the item table.
	 */
	public static void removeStudent(EntityManager em, FieldValue item, Student s) {
		EntityTransaction tx = em.getTransaction();
		if (item == null || s == null) {
			return;
		}
		tx.begin();
		
		item.removeStudent(s);
		
		tx.commit();
	}
	
	/**
	 * Add researcher to the item table.
	 */
	public static void addResearcher(EntityManager em, FieldValue item, Researcher r) {
		EntityTransaction tx = em.getTransaction();
		if (item == null || r == null) {
			return;
		}
		tx.begin();
		
		item.addResearcher(r);
		
		tx.commit();
	}
	
	/**
	 *Remove a student from the item table.
	 */
	public static void removeResearcher(EntityManager em, 
			FieldValue item, Researcher r) {
		EntityTransaction tx = em.getTransaction();
		if (item == null || r == null) {
			return;
		}
		tx.begin();
		
		item.removeResearcher(r);
		
		tx.commit();
	}
	
	/**
	 * Add student to the item table.
	 */
	public static void addProject(EntityManager em, FieldValue item, Project p) {
		EntityTransaction tx = em.getTransaction();
		if (item == null || p == null) {
			return;
		}
		tx.begin();
		
		item.addProject(p);
		
		tx.commit();
	}
	
	/**
	 *Remove a student from the item table.
	 */
	public static void removeProject(EntityManager em, FieldValue item, Project p) {
		EntityTransaction tx = em.getTransaction();
		if (item == null || p == null) {
			return;
		}
		tx.begin();
		
		item.removeProject(p);
		
		tx.commit();
	}
	
	/**
	 * Return a string array of all items of a given type.
	 */
	public static String[] getArrayOfOfType(EntityManager em, String type) {
        String query = FieldFactory.getQuery(FieldFactory.createField(type, ""));
        try {
			@SuppressWarnings("unchecked")
			List<FieldValue> it = (List<FieldValue>) em.createQuery(query).getResultList();
			List<String> items = new LinkedList<String>();
			for (FieldValue m : it) {
				items.add(m.getDescription());
			}
			
			return (String[]) items.toArray();
        }
        catch (Exception e) {
        	return new String[0];
        }
	}
	
	/**
	 * Returns an array list of all item of given type. 
	 */
	public static List<FieldValue> getListOfType(EntityManager em, String type) {
		String query = FieldFactory.getQuery(FieldFactory.createField(type, ""));
        try {
        	 @SuppressWarnings("unchecked")
        	 List<FieldValue> items = (List<FieldValue>) em.createQuery(query).getResultList();
        	 return items;
        }
        catch (Exception e){
        	return new ArrayList<FieldValue>();
        }
	}
	
	/**
	 * Return item by description.
	 */
	public static FieldValue getItemByDescription(EntityManager em, 
			String description, String type) {
		System.out.println("Searching for "+type+": "+description);
        String query = FieldFactory.getQuery(type)+ 
        		" where m.description = \""+description+"\"";
		@SuppressWarnings("unchecked")
		List<FieldValue> itens = (List<FieldValue>) em.createQuery(query).getResultList();
		try {
			return itens.get(0);
		}
		catch (Exception e) {
			System.out.println("ItemByDescription: "+type+" + "+description+" not found.");
			System.out.println("Query: "+query);
			return null;
		}
	}
	
	public static List<LatestAddition> getLatestAddedFields(EntityManager em) {
		
		System.out.println("Looking up ALL additions");
		String query = "select a from LATESTADDITION a";
		List<LatestAddition> items = (List<LatestAddition>) em.createQuery(query).getResultList();
		return items;
	}
	
	public static List<LatestAddition> getLatestAddedFields(EntityManager em, String type) {
		System.out.println("Looking up all "+type+" additions");
		String query = "select a from LATESTADDITION a where a.type = \""+type+"\"";
		List<LatestAddition> items = (List<LatestAddition>) em.createQuery(query).getResultList();
		return items;
	}
	
	
	/**
	 * Parse a string of items separated by ',' to an ArrayList of items with
	 * the string description.
	 */
	public static List<FieldValue> parseItens(String itens, String type ){
		if (itens == "") return new ArrayList<FieldValue>();
		String [] itensArray = itens.split(",");
		List<FieldValue> itensList = new ArrayList<FieldValue>();
		for (String m : itensArray){
			if (m != ""){
				itensList.add((FieldValue) FieldFactory.createField(type, m));
			}
		}
		return itensList;
	}
	/**
	 * Return a JSONObject with all items of a given type.
	 */
	public static JSONObject getItemJson(EntityManager em, String type) {
        String query = FieldFactory.getQuery(type);
		@SuppressWarnings("unchecked")
		List<FieldValue> itens = (List<FieldValue>) em.createQuery(query).getResultList();
		JSONArray jsonArray = new JSONArray();
		for (FieldValue m : itens){
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
	/**
	 * Return a JsonObject containing elements of a collection
	 */
	public static JSONObject getObjectJson(List<? extends FieldValue> collection) {
		if(collection.size() > 0){
			Collections.sort(collection);
		}
		JSONArray jsonArray = new JSONArray();
		for (FieldValue t : collection){
			JSONObject jsonObject= new JSONObject();
			try {
				jsonObject.put("value", String.valueOf(t.getId()));
				jsonObject.put("name", t.getDescription());
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
	
	public static List<Student> getStudents(FieldValue field){
		if (field == null) {
			return null;
		}
		return field.getStudents();
	}
	public static List<Researcher> getResearchers(FieldValue field) {
		if (field == null) {
			return null;
		}
		return field.getResearchers();
	}
}
