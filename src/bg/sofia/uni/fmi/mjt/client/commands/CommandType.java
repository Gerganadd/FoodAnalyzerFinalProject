package bg.sofia.uni.fmi.mjt.client.commands;

public enum CommandType {
    GET_FOOD_BY_NAME("get-food"),
    GET_FOOD_REPORT_BY_FCD_ID("get-food-report"),
    GET_FOOD_BY_BARCODE("get-food-by-barcode"),
    DISCONNECT("disconnect"),
    HELP("help"),
    UNKNOWN("unknown");

    private final String text;

    CommandType(String text1) {
        text = text1;
    }

    public static CommandType getValueOf(String commandName) {
        if (GET_FOOD_BY_NAME.getText().equals(commandName)) {
            return GET_FOOD_BY_NAME;
        }
        if (GET_FOOD_REPORT_BY_FCD_ID.getText().equals(commandName)) {
            return GET_FOOD_REPORT_BY_FCD_ID;
        }
        if (GET_FOOD_BY_BARCODE.getText().equals(commandName)) {
            return GET_FOOD_BY_BARCODE;
        }
        if (DISCONNECT.getText().equals(commandName)) {
            return DISCONNECT;
        }
        if (HELP.getText().equals(commandName)) {
            return HELP;
        }

        return CommandType.UNKNOWN;
    }

    public String getText() {
        return text;
    }
}
