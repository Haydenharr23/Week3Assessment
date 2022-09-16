package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListItem;

/**
 * Hayden Harris - CIS175 
 * DMACC FALL 2022
 * Sep 14, 2022
 */


public class ListItemHelper{
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("bookTable");
	
	
	public void cleanUp(){
		emfactory.close();
	}
	
	public void deleteItem(ListItem toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.author = :selectedAuthor and li.title = :selectedTitle", ListItem.class);
		typedQuery.setParameter("selectedStore", toDelete.getAuthor());
		typedQuery.setParameter("selectedItem", toDelete.getTitle());
		typedQuery.setMaxResults(1);
		ListItem result = typedQuery.getSingleResult();
		em.remove(result);
		
		em.getTransaction().commit();
		em.close();
		
	}
	

	
	public void insertItem(ListItem li){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}

	public List<ListItem> searchForItemByTitle(String itemName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.title = :selectedTitle", ListItem.class);
		typedQuery.setParameter("selectedTitle", itemName);

		List<ListItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<ListItem> searchForItemByAuthor(String storeName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.author = :selectedAuthor", ListItem.class);
		typedQuery.setParameter("selectedAuthor", storeName);

		List<ListItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
		
	}
	
	public ListItem searchForItemById(int id){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListItem found = em.find(ListItem.class, id);
		em.close();
		return found;
	}

	public List<ListItem> showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li", ListItem.class);
		List<ListItem> allItems = typedQuery.getResultList();
		em.close();
		return allItems;
	}

	public void updateItem(ListItem toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

}