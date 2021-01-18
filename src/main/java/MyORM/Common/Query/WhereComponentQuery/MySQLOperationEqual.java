package MyORM.Common.Query.WhereComponentQuery;

public class MySQLOperationEqual implements MySQLWhereComponent {
    String objName;
    Object obj;
    
    @Override
    public String getComponentString() {
        return objName + " > " + obj.toString();
    }

    public MySQLOperationEqual(String objName, Object obj) {
        this.objName = objName;
        this.obj = obj;
    }
}
