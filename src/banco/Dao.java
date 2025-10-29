package banco;

import java.util.List;

public interface Dao<T> {
    public void add(T objeto) throws Exception; 
    public List<T> getAll() throws Exception;
    public T getById(long id) throws Exception;
    public void update(T objeto) throws Exception;
    public void delete(T objeto) throws Exception;
}
