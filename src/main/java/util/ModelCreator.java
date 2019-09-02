package util;

import model.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelCreator {

    private static Logger logger = Logger.getLogger(ModelCreator.class.getName());

    private ModelCreator() {
    }

    private static List<Model> models = createModels();

    private static byte[] byteArrayGen() {
        byte[] bytes = new byte[new Random().nextInt(1024)];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) new Random().nextInt(1024);
        }
        return bytes;
    }

    private static Model model = getUnoModel();

    public static List<Model> getModels() {

        return models;
    }

    public static Model getModel() {
        return model;
    }

    private static List<Model> createModels() {
        List<Model> modelList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            modelList.add(
                    getUnoModel()
            );
        }
        return modelList;
    }

    private static Model getUnoModel() {
        Model model = new Model(new Random()
                .ints(0, Integer.MAX_VALUE / 2)
                .findAny()
                .getAsInt()
                , UUID.randomUUID().toString().substring(0, 7).toUpperCase()
                , LocalDate.now()
                , false
                , byteArrayGen());
        logger.log(Level.INFO, "create model: " + model.getName());
        return model;
    }
}
