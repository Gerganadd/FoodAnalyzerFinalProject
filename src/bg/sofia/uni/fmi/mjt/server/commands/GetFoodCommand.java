package bg.sofia.uni.fmi.mjt.server.commands;

import bg.sofia.uni.fmi.mjt.server.database.DatabaseManager;
import bg.sofia.uni.fmi.mjt.server.exceptions.NoSuchElementException;
import bg.sofia.uni.fmi.mjt.server.foods.Foods;

import java.util.List;

public class GetFoodCommand extends Command {
    public static final String DELIMITER = " ";

    public GetFoodCommand(List<String> attributes) {
        super(attributes);
    }

    @Override
    public String execute() throws NoSuchElementException {
        String foodName = String.join(DELIMITER, getAttributes());

        Foods result = DatabaseManager.getInstance().getFoodsByName(foodName);

        return result.toString();
    }
}
