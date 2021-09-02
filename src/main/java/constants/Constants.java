package constants;
import static constants.Constants.Path.JMART_PATH;
import static constants.Constants.Path.SWAPI_PATH;
import  static  constants.Constants.Servers.JMART_URL;

// Неизменяемые переменные
// сюда добавляются URL, PATH и эндпоинты для наших запросов (Actions - константы запросов)

public class Constants {

    public static class RunVariable {
//        public static String server = Servers.JMART_URL;
        public static String server = Servers.SWAPI_URL;
        public static String path = ""; // Если ничего не передаем в качестве Path, оставляем пустой
//        public static String path = JMART_PATH;
//        public static String path = SWAPI_PATH;
    }

    // в Servers добавим константы для наших доменов, чтобы можно было тестировать разные проекты
    public static class Servers {
        public static String JMART_URL = "https://jtest3.jmart.kz/";
        public static String SWAPI_URL = "https://swapi.dev/";
        public static String GOOGLE_PLACES_URL;
        public static String JSON_PLACEHOLDER_URL= "https://jsonplaceholder.typicode.com/";
        public static String REQUESTBIN_URL= "https://eno3lknlvvzmidx.m.pipedream.net";

    }

    public static class Path {
        public static String JMART_PATH = "api/4.0/";
        public static String SWAPI_PATH = "api/";
        public static String GOOGLE_PLACES_PATH = "maps/api/place/";
    }

    // в Actions добавим константы запросов
    public static class Actions {

        // JMART
        public static String JMART_GET_SRA_FASTED_PRODUCTS_GET = "sra_faseted_products?category_id=";

        // SWAPI
        public static String SWAPI_GET_PEOPLE = "people/";

        // GOOGLE_PLACES
        public static String GOOGLE_PLACES_SEARCH = "findplacefromtext/xml";

        // JSON_PLACEHOLDER
        public static String JSON_PLACEHOLDER_GET = "comments/";
        public static String JSON_PLACEHOLDER_GET2 = "posts/1";
        public static String JSON_PLACEHOLDER_PUT = "posts/1/";
        public static String JSON_PLACEHOLDER_DELETE = "posts/1/"; // Создаем отдельный эндпоинт, чтоб если изменится эндпоинт для PUT, не сломался тест DELETE
        public static String JSON_PLACEHOLDER_POST = "posts/";

    }
}
