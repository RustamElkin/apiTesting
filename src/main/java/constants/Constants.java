package constants;
import static constants.Constants.Path.JMART_PATH;
import  static  constants.Constants.Servers.JMART_URL;

// Неизменяемые переменные
// сюда добавляются URL, PATH и эндпоинты для наших запросов (Actions - константы запросов)

public class Constants {

    public static class RunVariable {
        public static String server = Servers.JSON_PLACEHOLDER_URL;
//        public static String path = JMART_PATH;
        public static String path = "";
    }

    // в Servers добавим константы для наших доменов, чтобы можно было тестировать разные проекты
    public static class Servers {
        public static String JMART_URL = "https://jtest4.jmart.kz/";
        public static String GOOGLE_PLACES_URL;
        public static String JSON_PLACEHOLDER_URL= "https://jsonplaceholder.typicode.com/";
    }

    public static class Path {
        public static String JMART_PATH = "api/4.0/";
        public static String GOOGLE_PLACES_PATH;
    }

    // в Actions добавим константы запросов
    public static class Actions {
        // JMART
        public static String JMART_GET_SRA_FASTED_PRODUCTS_GET = "sra_faseted_products?category_id=";

        //GOOGLE_PLACES
        public static String GOOGLE_PLACES_PATH;

        //JSON_PLACEHOLDER
        public static String JSON_PLACEHOLDER_GET = "comments/";
    }
}
