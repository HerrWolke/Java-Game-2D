package de.marcus.javagame.datahandling;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.marcus.javagame.datahandling.data.Inventory;
import de.marcus.javagame.datahandling.data.InventoryItem;
import de.marcus.javagame.datahandling.data.InventorySlot;
import de.marcus.javagame.entities.Entity;
import de.marcus.javagame.managers.EntityManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Currently testing code only until final decisions on data locations etc.
 * Only needs to input data from correct data location
 */
public class SavedataHandler {


    /**
     *  <p>Loads a class based of the parameter value.</p> <br>
     * In order to load a class it has to extend {@link Loadable}. <br> <br>
     *
     * If there has never been a save of the data, it will create a new instance based on the a no args constructor (p. e. @{@link lombok.NoArgsConstructor}
     *
     * @param toLoad The class to load <b>Requires no args constructor</b>
     */
    public static <T extends Loadable> T load(Class<T> toLoad) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String name = toLoad.getSimpleName().toLowerCase();
        System.out.println(name + ".json");
        File file = new File(name + ".json");

        if(file.exists()) {

            try {

                System.out.println("Loading data");
                return mapper.readValue(file, toLoad);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                System.out.println("File does not exist. Instantiating new object");
                return toLoad.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Saves data in a .json file using the lowercase class name. Class has to simply extend {@link Loadable}. <br> <br>
     * Would recommend to annotate attributes that should not be set with {@link com.fasterxml.jackson.annotation.JsonIgnore}
     * and those that should be saved with @{@link com.fasterxml.jackson.annotation.JsonProperty} and give it a custom name
     * <br> <br>
     *<u> <b>For examples, see {@link EntityManager} </b> </u>
     *
     * @param loadable The class to save
     */
    public static void save(Loadable loadable) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String name = loadable.getClass().getSimpleName().toLowerCase();
        System.out.println(name);
        File file = new File(name + ".json");

        try {
            //            EntityManager entityManager = mapper.readValue(entityData, EntityManager.class);
            mapper.writeValue(file, loadable);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
    __load();
    }




    /**
     * Test method. DO NOT USE!
     */
    private static void __save() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File file = new File("inventory.json");
        File entityData = new File("entitymanager.json");

        EntityManager entityManager = new EntityManager();
        Entity entity = new Entity();
        entity.setPosition(new Vector2(0, 0));
        Entity entity2 = new Entity();
        entity2.setPosition(new Vector2(3, 0));

        entityManager.spawn(entity2, entity);

        Inventory inventory = new Inventory(
                //inventory
                Arrays.asList(new InventorySlot(InventoryItem.COIN, 2), new InventorySlot(InventoryItem.COIN, 5)),
                //hotbar
                Arrays.asList(new InventorySlot(InventoryItem.COIN, 2), new InventorySlot(InventoryItem.COIN, 5)));
        try {

            mapper.writeValue(file, inventory);
            mapper.writeValue(entityData, entityManager);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Test method. DO NOT USE!
     */
    private static void __load() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File file = new File("inventory.json");
        File entityData = new File("entitymanager.json");

        try {
            Inventory inventory = mapper.readValue(file, Inventory.class);
            EntityManager entityManager = mapper.readValue(entityData, EntityManager.class);
            System.out.println(inventory.toString());
            System.out.println(entityManager.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
