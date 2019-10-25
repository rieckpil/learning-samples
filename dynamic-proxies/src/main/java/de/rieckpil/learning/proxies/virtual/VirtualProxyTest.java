package de.rieckpil.learning.proxies.virtual;

public class VirtualProxyTest {

    public static void main(String... args) {
        CustomMap<String, Integer> map =
                new VirtualCustomMap<>(CustomHashMap::new);

        System.out.println("Virtual Map created");
        map.put("one", 1);
        map.put("life", 42);

        System.out.println("map.get(\"life\") = " + map.get("life"));
        System.out.println("map.size() = " + map.size());
        System.out.println("clearing map");
        map.clear();
        System.out.println("map.size() = " + map.size());

    }
}
