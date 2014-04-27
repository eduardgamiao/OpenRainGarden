package models;

import java.util.List;
import views.formdata.ResourceFormData;

/**
 * Database for the resources on the learn more page
 * @author Kyle
 *
 */
public class ResourceDB {
	/**
	 * Adds a Rain Garden resource to the database
	 * @param formData
	 * @return The resource that was just created/modified
	 */
	public static Resource addGardenResource(ResourceFormData formData) {
		Resource resource;
		System.out.println("formData.id = " + formData.id);
		if (formData.id == -1) {
			resource = new Resource(formData.header, formData.url, "garden");
			resource.save();
			return resource;
		}
		else {
			resource = ResourceDB.getResource(formData.id);
			//byte [] picture = resource.getImage();
			//resource.setImage(picture);
			resource.setHeader(formData.header);
			resource.setUrl(formData.url);
			resource.save();
			return resource;
		}
	}
	
	/**
	 * Adds a Rain Barrel resource to the database
	 * @param formData
	 * @return The resource that was just created/modified
	 */
	public static Resource addBarrelResource(ResourceFormData formData) {
		Resource resource;
		if (formData.id == -1) {
			resource = new Resource(formData.header, formData.url, "barrel");
			resource.save();
			return resource;
		}
		else {
			resource = ResourceDB.getResource(formData.id);
			//byte [] picture = resource.getImage();
			//resource.setImage(picture);
			resource.setHeader(formData.header);
			resource.setUrl(formData.url);
			resource.save();
			return resource;
		}
	}
	
	/**
	 * Adds a Permeable Paver resource to the database
	 * @param formData
	 * @return The resource that was just created/modifed
	 */
	public static Resource addPaverResource(ResourceFormData formData) {
		Resource resource;
		if (formData.id == -1) {
			resource = new Resource(formData.header, formData.url, "paver");
			resource.save();
			return resource;
		}
		else {
			resource = ResourceDB.getResource(formData.id);
			//byte [] picture = resource.getImage();
			//resource.setImage(picture);
			resource.setHeader(formData.header);
			resource.setUrl(formData.url);
			resource.save();
			return resource;
		}
	}
	
	/**
	 * Deletes the resource with the given id
	 * @param id
	 */
	public static void deleteResource(long id) {
		Resource.find().byId(id).delete();
	}
	
	/**
	 * Returns a list of Rain Garden Resources
	 * @return
	 */
	public static List<Resource> getGardenResources() {
		return Resource.find().where().eq("type", "garden").findList();
	}
	
	/**
	 * Returns a list of Rain Barrel Resources
	 * @return
	 */
	public static List<Resource> getBarrelResources() {
		return Resource.find().where().eq("type", "barrel").findList();
	}
	
	/**
	 * Returns a list of Permeable Paver Resources
	 * @return
	 */
	public static List<Resource> getPaverResources() {
		return Resource.find().where().eq("type", "paver").findList();
	}
	
	/**
	 * Returns the resource with the given id
	 * @param id
	 * @return
	 */
	public static Resource getResource(long id) {
		return Resource.find().byId(id);
	}
	
	/**
	 * Returns a list of all resources
	 * @return
	 */
	public static List<Resource> getResources() {
		return Resource.find().all();
	}
	
	/**
	 * Checks if the database has a resource with the given ID
	 * @param id
	 * @return true if the resource w/ the given ID exists, false otherwise
	 */
	public static boolean hasID(long id) {
		if (ResourceDB.getResource(id) != null) {
			return true;
		}
		else {
			return false;
		}
	}
}
