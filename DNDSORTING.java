import java.util.*;
import java.util.stream.Collectors;

import javax.lang.model.util.ElementScanner6;

import java.io.*;
import java.io.FileNotFoundException;
public class DNDSORTING
{
    public static void main(String[] args) throws FileNotFoundException
    {
        //File characters = new File("characters.txt");
        HashMap<String, HashMap<String, Integer>> names = new HashMap<String, HashMap<String,Integer>>(); //Total Hashmap with nested skills hashmap
        HashMap<String, Integer> skills = new HashMap<String, Integer>(); //Nested Skills Hashmap
        Scanner sc = new Scanner(new File("characters.txt")); //Read File into Scanner
        String holder = ""; //empty holder
        while(sc.hasNextLine()) //While File still has lines
        {
            String string = sc.nextLine(); //Line of the File
            if(string.contains("-")) //Name and Class
            {
                skills = new HashMap(); // New Skills hashmap
                holder = string; //holds the string
                //System.out.println("holder");
                names.put(holder, skills); // Set name and empty skills
                //System.out.println(holder);
            }else if(string.equals("")) // New Line between characters
            {
                
            }else{ //Stats / Skills
                //System.out.println("Runnin");
                //System.out.println(string);
                String[] parts = string.split(":"); //Splits the Skill Name and Value
                String skillName = parts[0].trim().toLowerCase(); // Name
                String skillValueString = parts[1].trim().toLowerCase(); // Stats
                int skillValue = Integer.parseInt(skillValueString); //
                skills.put(skillName, skillValue);
                names.put(holder, skills);
            }
        }
         System.out.println(names.toString()); //Prints the HashMap Loaded In
        // System.out.println(names.get("Thief - Anna Prentice").get("Wisdom"));
        SkillSort(names);
        sc.close();
    }
    public static void SkillSort(HashMap<String, HashMap<String, Integer>> names)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Skill");
        String input = in.nextLine(); // searched for skill
        input = input.toLowerCase(); // lowercase all strings to match
        if(input.equalsIgnoreCase("recursion!"))
            System.out.println("Finished Sorting");
        else if(input.equals("strength") || input.equals("wisdom") || input.equals("charisma") || input.equals("intelligence") || input.equals("dexterity") || input.equals("constitution"))
        {
            HashMap<String, Integer> sorted = new HashMap<String, Integer>(); // holding hashmap
            for (String Key : names.keySet()) { // Iterates through the hashmas
                sorted.put(Key, names.get(Key).get(input)); // Puts in new hashmap 
            }
            Map<String, Integer> sortedMap = sorted.entrySet().stream() // Comparator that sorts
            .sorted(Comparator.comparingInt(e -> e.getValue()))
            .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,
            (a, b) -> { throw new AssertionError(); },
            LinkedHashMap::new));
            System.out.println("Sorted Values");
            for(String key : sortedMap.keySet())
            {
                System.out.println(key + " : " + "(" + sortedMap.get(key) + ")"); //Print Each key value pair with parentheses
            }
            SkillSort(names); // Recursive call
        }else{
            SkillSort(names); // Recursive call
        }
    }
}