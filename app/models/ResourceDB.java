package models;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * In memory database of resources for the learn more page.
 * @author Kyle
 *
 */
public class ResourceDB {
	private static Map<String, Resource> gardenResources = new LinkedHashMap<String, Resource>();
	private static Map<String, Resource> barrelResources = new LinkedHashMap<String, Resource>();
	private static Map<String, Resource> paverResources = new LinkedHashMap<String, Resource>();
	
	/**
	 * Adds the given resource to the Rain Garden Resources DB
	 * @param resource
	 */
	public static void addGardenResource(Resource resource) {
		gardenResources.put(resource.getHeader(), resource);
	}
	
	/**
	 * Get a resource from the Rain Garden DB based on the given header
	 * @param header
	 * @return
	 */
	public static Resource getGardenResource(String header) {
		return gardenResources.get(header);
	}
	
	/**
	 * Removes a resource from the Rain Garden DB based on the given header
	 * @param header
	 */
	public static void removeGardenResource(String header) {
		gardenResources.remove(header);
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
	public static void addBarrelResource(Resource resource) {
		barrelResources.put(resource.getHeader(), resource);
	}
	
	/**
	 * Get a resource from the Rain Barrel DB based on the given header
	 * @param header
	 * @return
	 */
	public static Resource getBarrelResource(String header) {
		return barrelResources.get(header);
	}
	
	/**
	 * Removes a resource from the Rain Barrel DB based on the given header
	 * @param header
	 */
	public static void removeBarrelResource(String header) {
		barrelResources.remove(header);
	}
	
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
	public static void addPaverResource(Resource resource) {
		paverResources.put(resource.getHeader(), resource);
	}
	
	/**
	 * Get a resource from the Permeable Pavers DB based on the given header
	 * @param header
	 * @return
	 */
	public static Resource getPaverResource(String header) {
		return paverResources.get(header);
	}
	
	/**
	 * Removes a resource from the Permeable Pavers DB based on the given header
	 * @param header
	 */
	public static void removePaverResource(String header) {
		paverResources.remove(header);
	}
	
	/**
	 * Returns a List of all Permeable Paver Resources in the database.
	 * @return
	 */
	public static List<Resource> getPaverList() {
		return new ArrayList<Resource>(paverResources.values());
	}
}
