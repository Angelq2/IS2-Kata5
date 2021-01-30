package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Debemos conectar con la BD Kata5 y realizar una consulta 
 * (query) para mostrar todos los registros de la tabla PEOPLE 
 * que previamente hemos creado para mostrarla por consola
 * @author angel
 */
public class Kata5P1 {
    
    private static Connection connect() {
    // SQLite connection string
        String url = "jdbc:sqlite:C:\\Users\\angel\\OneDrive\\Documentos\\NetBeansProjects\\Kata5P1\\BD_Kata5.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void selectAll(){
        String sql = "SELECT * FROM PEOPLE";
        try (
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            // Bucle sobre el conjunto de registros.
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                rs.getString("Name") + "\t" +
                rs.getString("Apellidos") + "\t" +
                rs.getString("Departamento") + "\t");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
        public static void createNewTable() {
            //Cadena de conexion SQLite
            //String url = "jdbc:sqlite:C:\\Users\\angel\\OneDrive\\Documentos\\NetBeansProjects\\Kata5P1\\BD_Kata5.db";
            
            // Instrucción SQL para crear nueva tabla
            String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " mail text NOT NULL);";
            try (
                Connection conn = connect();
                Statement stmt = conn.createStatement()) {
                // Se crea la nueva tabla
                stmt.execute(sql);
                System.out.println("Tabla creada");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    
    private static String fileName;
    private static List<String> emails;
    
    public static void insert(String email) {
        String sql = "INSERT INTO EMAIL(mail) VALUES(?)";
        try (
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        //selectAll();
        //createNewTable();   //Hay que cerrar el DB Browser SQlite porque si no sale mensaje error database is locked 
        
        try {
            fileName = new String("C:\\Users\\angel\\OneDrive\\Documentos\\NetBeansProjects\\Kata5P1\\txtFileEmail\\email.txt");
            emails = MailListReader.read(fileName);
            for( int i = 0; i < emails.size(); i++) {
                insert(emails.get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Ha ocurrido un error con el fichero");
        }
    }
}
