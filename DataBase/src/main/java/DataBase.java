import java.sql.*;
/**
 * Класс для работы с БД.
 */
public class DataBase {
    /**
     * соединение с БД.
     */
    private static Connection connection;
    /**
     * Запросы в БД.
     */
    public static Statement stmt;

    /**
     * подключение с БД.
     * @throws Exception при не успешном подключении
     */
    public static void connect() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отключение соединение с бд.
     */
    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Запрос из Таблицы.
     * @param TableName имя таблицы.
     * @throws SQLException при неудачном выполнении запросов
     */
    public static void Select(String TableName) throws SQLException {

        String sql = String.format("select * from " + TableName + "");
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) System.out.println(rs.getInt(1) + " " + rs.getString(2));
    }

    /**
     * Вставка в таблицу.
     * @param login логин
     * @param passport пароль
     * @param nikename имя
     * @throws SQLException при вставке
     */
    public static void InsertTable(String login, String passport, String nikename) throws SQLException {
        String sql = String.format("INSERT INTO users (login,passport,nikename) values ('%s','%s','%s')",
                login,
                passport,
                nikename);
        stmt.executeUpdate(sql);
    }

    /**
     * Обновление таблицы.
     * @param maks  имя
     * @param i идентификатор
     * @throws Exception при обновление
     */
    public static void UpdateTable(String maks, int i) throws Exception {
        String sql = String.format("Update users set login='%s' where id='%s'",
                maks,
                i);
        stmt.executeUpdate(sql);
    }

    /**
     * Создание БД.
      * @param Filename имя файла.
     * @return истина, если БД создается
     * @throws Exception ошибки при работе с запросами
     */
    public static boolean CreateDB(String Filename) throws Exception {
        int i = 0;
        String sql = String.format("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='%s'",
                Filename);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            i = rs.getInt(1);
        }
        if (i == 0) {
            stmt.execute("CREATE TABLE " + Filename + " (id INTEGER PRIMARY KEY NOT NULL," +
                    " login varchar(10)  NOT NULL, " +
                    "passport varchar(15) NOT NULL, " +
                    " nikename varchar(10) NOT NULL);");
            stmt.execute(sql);
            return true;
        } else return false;
    }
}
