    package Modelo;  
      
import java.io.FileInputStream;
    import java.io.FileOutputStream;  
    import java.sql.Connection;  
    import java.sql.DriverManager;  
    import java.sql.SQLException;  
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import org.dbunit.database.DatabaseConnection;  
    import org.dbunit.database.DatabaseSequenceFilter;  
    import org.dbunit.database.IDatabaseConnection;  
    import org.dbunit.database.QueryDataSet;  
    import org.dbunit.dataset.FilteredDataSet;  
    import org.dbunit.dataset.IDataSet;  
    import org.dbunit.dataset.xml.FlatXmlDataSet;  
import org.dbunit.operation.DatabaseOperation;
      
    public class DBUnitUtils  
      
    {
        
    public static void generarXML(String driver,
                                   String direccion,
                                   String user,
                                   String pwd,
                                   String xml) throws SQLException{
            try {
                
                Class.forName(driver);
                                               
                Connection jdbcConnection = DriverManager.getConnection(direccion,user,pwd);
                
                IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
                    
                DatabaseSequenceFilter filter = new DatabaseSequenceFilter(connection);
                IDataSet datasetAll =  new FilteredDataSet(filter, connection.createDataSet());
                QueryDataSet partialDataSet = new QueryDataSet(connection);

                String[] listTableNames = filter.getTableNames(datasetAll);
                
                for (int i = 0; i < listTableNames.length; i++) {
                    final String tableName = listTableNames[i];
                    partialDataSet.addTable(tableName);
                }
                        
                FlatXmlDataSet.write(partialDataSet, new FileOutputStream("/home/manteco/" + xml + ".xml"));
            } catch (Exception ex) {
                Logger.getLogger(DBUnitUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public IDatabaseConnection getConnection() throws Exception {
  
            Class driverClass = 
            Class.forName("com.mysql.jdbc.Driver");

            Connection jdbcConnection = 
            DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","ubuntu");
        
            return new DatabaseConnection(jdbcConnection);
    }
    
    protected IDataSet getDataSet(String xml) throws Exception {
            return new FlatXmlDataSet(new FileInputStream("/home/manteco/" + xml + ".xml"));
    }
            
    public static void crearDatos(String driver,  
                                  String direccion,  
                                  String user,  
                                  String pwd,  
                                  String xml) throws SQLException {  
            try {
                Class.forName(driver);
                                                   
                Connection jdbcConnection = DriverManager.getConnection(direccion,user,pwd);
                    
                IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
                        
                DatabaseSequenceFilter filter = new DatabaseSequenceFilter(connection);
                IDataSet datasetAll =  new FilteredDataSet(filter, connection.createDataSet());
                QueryDataSet partialDataSet = new QueryDataSet(connection);  
      
                DatabaseOperation.CLEAN_INSERT.execute(connection,new FlatXmlDataSet(new FileInputStream("/home/manteco/" + xml + ".xml")));
            } catch (Exception ex) {
                Logger.getLogger(DBUnitUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
      
    }
    
    public static void borrarDatos(String driver,
                                    String direccion,
                                    String user,
                                    String pwd,
                                    String xml) throws SQLException {
            try {
                Class.forName(driver);
                                                   
                Connection jdbcConnection = DriverManager.getConnection(direccion,user,pwd);
                    
                IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
                        
                DatabaseSequenceFilter filter = new DatabaseSequenceFilter(connection);
                IDataSet datasetAll =  new FilteredDataSet(filter, connection.createDataSet());
                QueryDataSet partialDataSet = new QueryDataSet(connection);  
      
                DatabaseOperation.DELETE.execute(connection,new FlatXmlDataSet(new FileInputStream("/home/manteco/" + xml + ".xml")));
            } catch (Exception ex) {
                Logger.getLogger(DBUnitUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}  