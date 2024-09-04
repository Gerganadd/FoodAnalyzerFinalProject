package bg.sofia.uni.fmi.mjt.client.commands;

public class Command {
    private static final String MENU = """
            Commands:
            help
            disconnect
            get-food <name>
            get-food-report <fsdId>
            get-food-by-barcode --img=<absoluteImagePath>
            get-food-by-barcode --code=<gtin/upcCode>
            get-food-by-barcode --img=<absoluteImagePath> --code=<gtin/upcCode>
            get-food-by-barcode --code=<gtin/upcCode> --img=<absoluteImagePath>
            """;
    private static final String MENU_OPTIONS_DESCRIPTION = """
            disconnect -> disconnect you from the server
            get-food <name> -> return information about all foods which match of given name
            get-food-report <fsdId> -> return information about food with given id (fsdId is specific for each food)
            get-food-by-barcode --code=<gtin/upcCode> -> return information about food with given gtin/upc code
            get-food-by-barcode --img=<absoluteImagePath> -> return information about food with given gtin/upc code from image
            get-food-by-barcode --img=<absoluteImagePath> --code=<gtin/upcCode> -> same as the above two but will prioritize the code value
            """;
    private static final String SEPARATOR = " ";
    private static final int INDEX_COMMAND_NAME = 0;

    private CommandType type;

    public Command(String text) {
        type = getCommandNameType(text);
    }

    public static void printMenu() {
        System.out.println(MENU);
    }

    public void printMenuDescription() {
        System.out.println(MENU_OPTIONS_DESCRIPTION);
    }

    public CommandType getType() {
        return type;
    }

    public void update(String text) {
        if (text == null || text.isBlank()) {
            return;
        }

        type = getCommandNameType(text);
    }

    private CommandType getCommandNameType(String text) {
        String commandName = text
                .split(SEPARATOR)[INDEX_COMMAND_NAME]
                .trim();

        return CommandType.getValueOf(commandName);
    }

}
