package models;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import views.formdata.ResourceFormData;

/**
 * In memory database of resources for the learn more page.
 * @author Kyle
 *
 */
public class ResourceDB {
	private static long currentID = 1;
	
	private static Map<Long, Resource> gardenResources = new LinkedHashMap<Long, Resource>();
	private static Map<Long, Resource> barrelResources = new LinkedHashMap<Long, Resource>();
	private static Map<Long, Resource> paverResources = new LinkedHashMap<Long, Resource>();
	
	/**
	 * Returns the resource with the given id; returns null if resource cannot be found
	 * @param id
	 * @return
	 */
	public static Resource getResource(long id) {
		if (gardenResources.containsKey(id) == true) {
			return gardenResources.get(id);
		}
		else if (barrelResources.containsKey(id) == true) {
			return barrelResources.get(id);
		}
		else if (paverResources.containsKey(id) == true) {
			return paverResources.get(id);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Finds the list that the resource belongs to; returns either "garden" "barrel" or "paver" or ""
	 * @param id
	 * @return
	 */
	public static String findList(long id) {
		if (gardenResources.containsKey(id) == true) {
			return "garden";
		}
		else if (barrelResources.containsKey(id) == true) {
			return "barrel";
		}
		else if (paverResources.containsKey(id) == true) {
			return "paver";
		}
		return "";
	}
	
	/**
	 * Adds a resource to gardenResources based on the given formData
	 * @param formData
	 * @return
	 */
	public static Resource addGardenResource(ResourceFormData formData) {
		Resource resource;
		
		if (formData.id == 0) {
			long id = currentID;
			currentID++;
			resource = new Resource(id, formData.header, formData.url);
			gardenResources.put(id, resource);
			return resource;
		}
		else {
			byte [] picture = ResourceDB.getGardenResource(formData.id).getImage();
			resource = new Resource(formData.id, formData.header, formData.url);
			resource.setImage(picture);
			gardenResources.put(formData.id, resource);
			return resource;
		}
	}
	
	/**
	 * Get a resource from the Rain Garden DB based on the given header
	 * @param header
	 * @return
	 */
	public static Resource getGardenResource(long id) {
		return gardenResources.get(id);
	}
	
	/**
	 * Removes a resource from the Rain Garden DB based on the given header
	 * @param header
	 */
	public static void removeGardenResource(long id) {
		gardenResources.remove(id);
	}
	
	/**
	 * Returns a List of all Rain Garden Resources in the database.
	 * @return
	 */
	public static List<Resource> getGardenList() {
		return new ArrayList<Resource>(gardenResources.values());
	}
	
	/**
	 * Adds the given resource to the Rain Barrel Resources DB
	 * @param resource
	 */
	/*public static void addBarrelResource(Resource resource) {
		barrelResources.put(resource.getHeader(), resource);
	}*/
	
	/**
	 * Get a resource from the Rain Barrel DB based on the given header
	 * @param header
	 * @return
	 */
	/*public static Resource getBarrelResource(String header) {
		return barrelResources.get(header);
	}*/
	
	/**
	 * Removes a resource from the Rain Barrel DB based on the given header
	 * @param header
	 */
	/*public static void removeBarrelResource(String header) {
		barrelResources.remove(header);
	}*/
	
	/**
	 * Returns a List of all Rain Barrel Resources in the database.
	 * @return
	 */
	public static List<Resource> getBarrelList() {
		return new ArrayList<Resource>(barrelResources.values());
	}
	
	
	/**
	 * Adds the given resource to the Permeable Pavers DB
	 * @param resource
	 */
	/*public static void addPaverResource(Resource resource) {
		paverResources.put(resource.getHeader(), resource);
	}*/
	
	/**
	 * Get a resource from the Permeable Pavers DB based on the given header
	 * @param header
	 * @return
	 */
	/*public static Resource getPaverResource(String header) {
		return paverResources.get(header);
	}*/
	
	/**
	 * Removes a resource from the Permeable Pavers DB based on the given header
	 * @param header
	 */
	/*public static void removePaverResource(String header) {
		paverResources.remove(header);
	}*/
	
	/**
	 * Returns a List of all Permeable Paver Resources in the database.
	 * @return
	 */
	public static List<Resource> getPaverList() {
		return new ArrayList<Resource>(paverResources.values());
	}
}
