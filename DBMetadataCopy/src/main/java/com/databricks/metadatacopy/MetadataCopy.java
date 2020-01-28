package metadatacopy;

import java.sql.*;
import java.util.*;

public class MetadataCopy 
{
    String srcDB;
    String srcConn;
    String dstDB;
    String dstConn;

    public MetadataCopy(String srcDB, String srcConn, String dstDb, String dstConn) {}

    /** Execute the metadata copy */
    public void run() {
      Connection srcCon = getConnection(srcConn);
      Connection dstCon = getConnection(dstConn);

      List<String> tblList = getTables(srcCon, srcDB);
      for (String tbl: tblList) {
        // For each table in the srcDB, get the list of SQL commands that will create the table
        // and re-create the stats.
        List<String> cmds = getTableMDCopyCmds(srcCon, srcDB, tbl);

        for (String cmd: cmds) {
          // For each command of the table, execute it in the destination connection's database
          executeCmd(dstCon, dstDB, cmd);
        }
      }
    }

    // Returns the connection for the given connection string
    Connection getConnection(String connStr) { return null; }

    // Returns the list of tables in the given database for the given connection 
    List<String> getTables(Connection conn, String db) { return null; }

    // Returns the list of SQL commands that replicate the table metadata of the given table
    List<String> getTableMDCopyCmds(Connection conn, String db, String tbl) { return null; }

    String getCreateTableCmd(Connection conn, String db, String tbl) { return null; }
    String getTableStatsCmd(Connection conn, String db, String tbl) { return null; }
    List<String> getColumnStatsCmds(Connection conn, String db, String tbl) { return null; }

    // Execute the SQL command for the given connection at the specified destination database
    void executeCmd(Connection conn, String dstDB, String cmd) {}

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
