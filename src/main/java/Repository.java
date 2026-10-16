import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class Repository<T> {
    protected List<T> transaktioner = new ArrayList<>();


    public void add(T item) {
       transaktioner.add(item);
    }
    public List<T> findAll() {
        return transaktioner;
    }
    public List<T> findWhere(Predicate<T> villkor) {
        return transaktioner.stream()
                .filter(villkor)
                .toList();
    }

}


