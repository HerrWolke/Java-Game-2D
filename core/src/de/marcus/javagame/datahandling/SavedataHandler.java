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
import java.util.Arrays;

/**
 * Currently testing code only until final decisions on data locations etc.
 * Only needs to input data from correct data location
 */
public class SavedataHandler {

    public static void save() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File file = new File("inventory.json");
        File entityData = new File("entity_data.json");

        EntityManager entityManager = new EntityManager();
        Entity entity = new Entity();
        entity.setPosition(new Vector2(0, 0));
        Entity entity2 = new Entity();
        entity2.setPosition(new Vector2(3, 0));

        entityManager.spawn(entity2, entity);

        Inventory inventory = new Inventory(Arrays.asList(new InventorySlot(InventoryItem.COIN, 2), new InventorySlot(InventoryItem.COIN, 5)));
        try {

            mapper.writeValue(file, inventory);
            mapper.writeValue(entityData, entityManager);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        load();
    }


    public static void load() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File file = new File("inventory.json");
        File entityData = new File("entity_data.json");

        try {
            Inventory inventory = mapper.readValue(file, Inventory.class);
            EntityManager entityManager = mapper.readValue(entityData, EntityManager.class);
            System.out.println(entityManager.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
