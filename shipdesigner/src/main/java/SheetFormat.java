enum SheetFormat {
    ONE_UP("1-Up"),
    TWO_UP("2-Up"),
    FOUR_UP("4-Up");

    private final String format;

    SheetFormat(String format) {
        this.format = format;
    }

    String getFormat() {
        return format;
    }
}
