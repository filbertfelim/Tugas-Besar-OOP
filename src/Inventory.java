public class Inventory<T,Integer> {

    private T item;
    private Integer count;

    public Inventory(T item, Integer count) {
        this.item = item;
        this.count = count;
      }
    
    public T getItem() {
        return item;
      }
    
    public void setItem(T item) {
        this.item = item;
      }
    
    public Integer getCount() {
        return count;
      }
    
    public void setCount(Integer count) {
        this.count = count;
      }

}
