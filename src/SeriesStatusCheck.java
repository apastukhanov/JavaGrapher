import java.util.ArrayList;
import java.util.List;

public class SeriesStatusCheck {

    private List<String> list = new ArrayList<>();

    SeriesStatusCheck(String k){
        add(k);
    }

    SeriesStatusCheck(){}


    public void add (String k) {
        list.add(k);
    }

    public boolean isKExists(String k) {
        return list.contains(k);
    }

    public void removeStatus()
    {
        list = new ArrayList<>();
    }
}
