public class SwitchDemo {
    public static void main(String[] args) {
        // switch (statement)
//        String day = "MON";
//        switch (day) {
//            case "MON":
//            case "TUE":
//            case "WED":
//            case "THU":
//            case "FRI":
//                System.out.println("Weekday");
//                break;
//            case "SAT":
//            case "SUN":
//                System.out.println("Weekend");
//                break;
//            default:
//                System.out.println("unknown day");
//                break;
//        }

        // switch (expression) '->'
        String day = "MON";
        String type = switch (day) {
            case "MON", "TUE", "WED", "FRI" -> "Weekday";
            case "SAT", "SUN" -> "Weekend";
            default -> "unknow";
        };

        System.out.println(type);

    }
}
