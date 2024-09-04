package bg.sofia.uni.fmi.mjt.server.commands;

import bg.sofia.uni.fmi.mjt.server.database.DatabaseManager;
import bg.sofia.uni.fmi.mjt.server.exceptions.NoSuchElementException;
import bg.sofia.uni.fmi.mjt.server.foods.FoodReport;

import java.util.List;

public class GetFoodReportCommand extends Command {
    public GetFoodReportCommand(List<String> attributes) {
        super(attributes);
    }

    @Override
    public String execute() throws NoSuchElementException {
        long fdcId = Long.parseLong(getAttributes().getFirst());

        FoodReport report = DatabaseManager.getInstance().getFoodReportBy(fdcId);

        return report.toString();
    }
}
