import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    /** TODO
     * create a static LocationMap object
     */
    static LocationMap LM = new LocationMap();

    /** TODO
     * create a vocabulary HashMap to store all directions a user can go
     */
    HashMap vocabulary = new HashMap<String, String>();

    /** TODO
     * create a FileLogger object
     */
    FileLogger FL = new FileLogger();

    /** TODO
     * create a ConsoleLogger object
     */
    ConsoleLogger CL = new ConsoleLogger();


    public Mapping() {
        //vocabulary.put("QUIT", "Q"); //example
        /** TODO
         * complete the vocabulary HashMap <Key, Value> with all directions.
         * use the directions.txt file and crosscheck with the ExpectedInput and ExpectedOutput files to find the keys and the values
         */
        vocabulary.put("NORTH", "N");
        vocabulary.put("EAST", "E");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");

        vocabulary.put("NORTHEAST", "NE");
        vocabulary.put("SOUTHEAST", "SE");
        vocabulary.put("SOUTHWEST", "SW");
        vocabulary.put("NORTHWEST", "NW");

        vocabulary.put("UP", "U");
        vocabulary.put("DOWN", "D");

        vocabulary.put("QUIT", "Q");

    }

    public void mapping() {

        /** TODO
         * create a Scanner object
         */
        Scanner SC = new Scanner(System.in);

        /**
         * initialise a location variable with the INITIAL_LOCATION
         */
        int locationNow = INITIAL_LOCATION;

        while (true) {
            /** TODO
             * get the location and print its description to both console and file
             * use the FileLogger and ConsoleLogger objects
             */
            Location currentLocDetails = LM.get(locationNow);

            String desc = currentLocDetails.getDescription();
            FL.log(desc);
            CL.log(desc);

            /** TODO
             * verify if the location is exit
             */
            if (locationNow == 0)
                break;

            /** TODO
             * get a map of the exits for the location
             */
            Map<String, Integer> exits = currentLocDetails.getExits();

            /** TODO
             * print the available exits (to both console and file)
             * crosscheck with the ExpectedOutput files
             * Hint: you can use a StringBuilder to append the exits
             */
            StringBuilder exitsList = new StringBuilder();
            exitsList.append("Available exits are");
            exitsList.append(exits.keySet());
            String exitsString = exitsList.toString().replace("[", " ").replace("]", ", ");
            FL.log(exitsString);
            CL.log(exitsString);

            /** TODO
             * input a direction
             * ensure that the input is converted to uppercase
             */
            String move = SC.nextLine().toUpperCase().trim();

            /** TODO
             * are we dealing with a letter / word for the direction to go to?
             * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
             * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
             * if the input contains multiple words, extract each word
             * find the direction to go to using the vocabulary mapping
             * if multiple viable directions are specified in the input, choose the last one
             */
            String go = null;

            if (move.contains(" ")) {

                String[] moveArray = move.split(" ");
                boolean isIn = false;

                for (int i = 0; i < moveArray.length; i++) {
                    if (moveArray[i].contains(" "))
                        moveArray[i] = moveArray[i].trim();

                    if (vocabulary.containsKey(moveArray[i])) {
                        go = vocabulary.get(moveArray[i]).toString();
                        isIn = true;
                    }
                }
                if (!isIn) {
                    FL.log("You cannot go in that direction");
                    CL.log("You cannot go in that direction");
                    isIn = false;
                    continue;
                }
            }
            else {
                if (vocabulary.containsKey(move))
                    go = vocabulary.get(move).toString();

                else if (vocabulary.containsValue(move)) {
                    go = move;
                } else {
                    FL.log("You cannot go in that direction");
                    CL.log("You cannot go in that direction");
                    go = null;
                    continue;
                }
            }


            /** TODO
             * if user can go in that direction, then set the location to that direction
             * otherwise print an error message (to both console and file)
             * check the ExpectedOutput files
             */
            if (exits.containsKey(go))
                locationNow = exits.get(go);
            else{
                CL.log("You cannot go in that direction");
                FL.log("You cannot go in that direction");
            }

        }
    }

    public static void main(String[] args) {
        /**TODO
         * run the program from here
         * create a Mapping object
         * start the game
         */
        Mapping mapClass = new Mapping();
        mapClass.mapping();
    }

}
