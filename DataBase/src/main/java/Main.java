public class Main {
    public static void main(String[] args) {
        try {
            DataBase.connect(); //соединение
            System.out.println("Выборка из таблицы");
            DataBase.Select("users");
            System.out.println("Вставка в таблицу");
            DataBase.InsertTable("Jen",
                    "Jen",
                    "Jen");
            System.out.println("Обновление таблицы");
            DataBase.UpdateTable("Maks",
                    1);
            if (DataBase.CreateDB("tab")) {
                System.out.println("Создалась Таблица tab");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DataBase.disconnect();
        }
    }
}
