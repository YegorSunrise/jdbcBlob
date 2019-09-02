package util;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectToByteConverter {

    private static Logger logger = Logger.getLogger(ObjectToByteConverter.class.getName());

    public static byte[] serialize(Object obj) throws IOException {
        logger.log(Level.INFO,"Object serialize... ");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        out.close();
        return out.toByteArray();
    }

    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        logger.log(Level.INFO,"Object deserialize... ");
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        Object object = is.readObject();
        is.close();
        return object;
    }
}
