package MyORM.Common.Query;

import java.util.*;

public interface HavingOrRun {
    <T> Run having(String condition);
    <T> List<T> run(Class<T> entityClass);
}
