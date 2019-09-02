import controller.JdbcController;
import model.Model;

public class Runner {
    public static void main(String[] args) {
//        List<Model> models = ModelCreator.getModels();
//        models.forEach(JdbcController::saveModel);
        Model model = JdbcController.getModel("4E5A79A");
        System.out.println(model);
    }
}
