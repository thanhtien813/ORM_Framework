package MyORM.MySQL;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import MyORM.Annotations.Column;
import MyORM.Annotations.PrimaryKey;

public class MySQLInsert<T> extends MySQLQuery {

    public <T> MySQLInsert(Connection cnt, String connectionString, Class<T> entityClass, T obj) throws Exception {
        super(cnt, connectionString);

        MySQLMapper mySQLMapper = new MySQLMapper();

        String tableName = mySQLMapper.getTableName(entityClass);
        List<String> primaryKeys = mySQLMapper.getPrimaryKey(entityClass);
        
        HashMap<String, Object> listColumnValues = mySQLMapper.getColumnValues(obj);

        StringBuilder columnStringbuilder = new StringBuilder("");
        StringBuilder valueStringBuilder = new StringBuilder("");

        Field[] fields = entityClass.getDeclaredFields();

        for (Field field : fields) {
            boolean isAutoID = false; 
            Column column = field.getAnnotation(Column.class);
            PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);

            if (primaryKey != null) {
                if (primaryKey.autoId()) {
                    isAutoID = true;
                    continue;
                }
            }
            if ((column != null || primaryKey != null) && !isAutoID) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
                
                columnStringbuilder.append(String.format("%s, ", column.name()));
                // if (listColumnValues.get(column).getClass() == Date.class)
                valueStringBuilder.append(String.format("'%s', ", listColumnValues.get(field.getName())));
            }
        }
 

        // for (String column : listColumnValues.keySet()) {
        //     boolean isAutoID = false;

        //     // Check primary key --> if true & id is auto --> no need to add key-value
        //     for ( primaryKey : primaryKeys) {


        //         if (column == primaryKey.name() && primaryKey.autoId()) {
        //             isAutoID = true;
        //             break;
        //         }
        //     }

           
        // }

        if (!columnStringbuilder.toString().equals("") && !valueStringBuilder.toString().equals("")) {
            // Remove the last 2 letters ", "
            String columnStr = columnStringbuilder.substring(0, columnStringbuilder.length() - 2);
            String valueStr = valueStringBuilder.substring(0, valueStringBuilder.length() - 2);

            query = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columnStr, valueStr);
            System.out.println(query);
        }
    }
}