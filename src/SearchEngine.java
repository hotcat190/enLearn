import java.util.List;

public interface SearchEngine {
    public static <T extends Comparable> T binSearch(List<T> list,int low,int high,T target) {
        if(low > high || list.isEmpty()) {
            return null;
        }
        int mid = (low + high)/2;
        T temp = list.get(mid);
        int cmp = target.compareTo(temp);
        if(cmp == 0) {
            return list.get(mid);
        }else if(cmp > 0) {
            return binSearch(list,mid+1,high,target);
        }else {
            return binSearch(list,low,mid-1,target);
        }
    }
    public static <T extends Comparable> int binSearchIndex(List<T> list,int low,int high,T target) {
        if(low > high || list.isEmpty()) {
            return (low+1+high)/2;
        }
        int mid = (low + high)/2;
        T temp = list.get(mid);
        int cmp = target.compareTo(temp);
        if(cmp == 0) {
            return mid;
        }else if(cmp > 0) {
            return binSearchIndex(list,mid+1,high,target);
        }else {
            return binSearchIndex(list,low,mid-1,target);
        }
    }
}
