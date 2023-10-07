import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME =  "locations.txt";
    private static final String DIRECTIONS_FILE_NAME =  "directions.txt";

    /** TODO
     * create a static locations HashMap
     */
    static HashMap<Integer, Location> locations = new HashMap<Integer, Location>();

    static {
        /** TODO
         * create a FileLogger object
         */
        FileLogger FL = new FileLogger();

        /** TODO
         * create a ConsoleLogger object
         */
        ConsoleLogger CL = new ConsoleLogger();

        /** TODO
         * Read from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         * use try-with-resources/catch block for the FileReader
         * extract the location and the description on each line
         * print all locations and descriptions to both console and file
         * check the ExpectedOutput files
         * put each location in the locations HashMap using temporary empty hashmaps for exits
         */
        try {
            FileReader FRLoc = new FileReader(LOCATIONS_FILE_NAME);
            Scanner SC = new Scanner(FRLoc);

            CL.log("Available locations:");
            FL.log("Available locations:");
            while (SC.hasNextLine()) {
                String currentLine = SC.nextLine();
                int firstCommaIndex = currentLine.indexOf(',');
                int iD = Integer.parseInt(currentLine.substring(0, firstCommaIndex));
                String description = currentLine.substring(firstCommaIndex+1, currentLine.length());

                Location loc = new Location(iD, description, null);
                locations.put(iD, loc);
                CL.log(iD + ": " + description);
                FL.log(iD + ": " + description);
            }
        }

        catch (IOException exception){
            exception.printStackTrace();
        }

        /**TODO
         * Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * use try-with-resources/catch block for the FileReader
         * extract the 3 elements  on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * check the ExpectedOutput files
         * for each location, create a new location object and add its exit
         */
        try {
            FileReader FRDir = new FileReader(DIRECTIONS_FILE_NAME);
            Scanner SC = new Scanner(FRDir);

            CL.log("Available directions:");
            FL.log("Available directions:");
            while (SC.hasNextLine()) {
                String[] currentLine = SC.nextLine().split(",");
                String currentID = currentLine[0];
                String currentDirection = currentLine[1];
                int currentDest = Integer.parseInt(currentLine[2]);

                CL.log(currentID + ": " + currentDirection + ": " + currentDest);
                FL.log(currentID + ": " + currentDirection + ": " + currentDest);

                locations.get(Integer.parseInt(currentID)).addExit(currentDirection, currentDest);
            }
        }

        catch (IOException exception){
            exception.printStackTrace();
        }
    }

    /**TODO
     * implement all methods for Map
     * @return
     */
    @Override
    public int size() {
        //TODO
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        //TODO
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        //TODO
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //TODO
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        //TODO
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        //TODO
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        //TODO
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        //TODO
        locations.putAll(m);
    }

    @Override
    public void clear() {
        //TODO
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        //TODO
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        //TODO
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        //TODO
        return locations.entrySet();
    }
}
