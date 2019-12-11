import java.util.*;

class datastructures {
  public static void main(String[] args) {
    // --------------------------------------------
    // arraylist
    ArrayList<Integer> list = new ArrayList<>();

    list.add(1);
    list.add(2);
    list.add(3);

    for (int v : list) {
      System.out.println(v);
    }

    // --------------------------------------------
    // hashset

    HashSet<Integer> set = new HashSet<>();
    set.add(1);
    set.add(2);
    set.add(3);
    set.add(1);

    for(int v : set) {
      System.out.println(v);
    }

    if (set.contains(1)) {
      System.out.println("set contains 1");
    }
  
    // --------------------------------------------
    // hashmap

    String[] ar = {"a","b","a","a","c","b"};
    HashMap<String, Integer> map = new HashMap<>();

    for(String v : ar) {
      map.put(v, map.getOrDefault(v, 0) + 1);
    }

    for(String key : map.keySet()) {
      int value = map.get(key);
      System.out.println(key + " = " + value);
    }
    

  }
}