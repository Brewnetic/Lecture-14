// ============================================
// LECTURE 14: Collections Framework & HashMap
// Gaming Leaderboard System
// ============================================

/* HashMap and Map live in java.util, the standard utility collections package.
   Scanner also lives in java.util alongside both of them. */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Lecture14 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("   GAMING LEADERBOARD SYSTEM");
        System.out.println("========================================\n");
        
        // ============================================
        // PART 1: Creating Player Leaderboard with HashMap
        // ============================================
        
        /* new HashMap<>() creates an empty map.
           HashMap requires TWO type parameters: the key type comes first, the value type comes second.
           Here String is the key (player name) and Integer is the value (score).
           The diamond <> on the right lets Java infer both types from the left side. */
        HashMap<String, Integer> playerScores = new HashMap<>();
        
        System.out.println("--- Starting Leaderboard ---\n");
        
        /* .put(key, value) adds a new key-value pair to the map.
           If the key already exists, .put() silently overwrites the old value.
           It returns the old value if the key existed, or null if the key was new. */
        playerScores.put("Alex", 1500);
        
        playerScores.put("Jordan", 2000);
        
        playerScores.put("Sam", 1800);
        
        playerScores.put("Casey", 1700);
        
        System.out.println("✓ Leaderboard initialized with 4 players.\n");
        
        System.out.println("--- Current Leaderboard ---");
        
        /* .size() returns the number of key-value pairs currently in the map.
           Each player name is a unique key, so size equals the number of distinct players. */
        System.out.println("Total players: " + playerScores.size());
        
        System.out.println("\nPlayer Scores:");
        
        /* .entrySet() returns a Set of Map.Entry objects, where each entry holds one key-value pair.
           Map.Entry<String, Integer> is the type for each entry because the map is HashMap<String, Integer>.
           This is the most efficient way to iterate when you need both the key and the value together. */
        int playerNum = 1;
        for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
            /* .getKey() retrieves the key (player name) from the current entry. */
            String playerName = entry.getKey();
            /* .getValue() retrieves the value (score) from the current entry. */
            Integer score = entry.getValue();
            System.out.println("  " + playerNum + ". " + playerName + ": " + score + " pts");
            playerNum++;
        }
        
        System.out.println();
        
        // ============================================
        // PART 2: Looking Up a Player's Score
        // ============================================
        
        System.out.println("--- Player Score Lookup ---");
        
        System.out.print("Enter player name to look up: ");
        String lookupPlayer = scanner.nextLine();
        
        /* .containsKey(key) returns true if the key exists in the map, false otherwise.
           Always check with .containsKey() before calling .get() so that you never
           accidentally use a null return value as if it were a real score. */
        if (playerScores.containsKey(lookupPlayer)) {
            /* .get(key) retrieves the value associated with that key.
               It returns null if the key is not in the map, which is why
               the .containsKey() check above is important before calling this. */
            Integer playerScore = playerScores.get(lookupPlayer);
            System.out.println("✓ " + lookupPlayer + "'s score: " + playerScore + " pts");
        } else {
            System.out.println("✗ Player '" + lookupPlayer + "' not found on leaderboard!");
        }
        
        System.out.println();
        
        // ============================================
        // PART 3: Updating a Player's Score
        // ============================================
        
        System.out.println("--- Score Update ---");
        
        System.out.print("Which player's score to update? ");
        String updatePlayer = scanner.nextLine();
        
        /* Same .containsKey() guard as Part 2 before touching the map. */
        if (playerScores.containsKey(updatePlayer)) {
            /* .get() here captures the old score before it gets overwritten.
               This lets us display a before and after message to confirm the change. */
            Integer oldScore = playerScores.get(updatePlayer);
            
            System.out.print("New score: ");
            Integer newScore = Integer.parseInt(scanner.nextLine());
            
            /* .put() with an existing key overwrites the old value with the new one.
               No separate update method is needed because put handles both adding and replacing. */
            playerScores.put(updatePlayer, newScore);
            
            System.out.println("✓ Updated " + updatePlayer + ": " + oldScore + " → " + newScore + " pts");
        } else {
            System.out.println("✗ Player '" + updatePlayer + "' not found!");
        }
        
        System.out.println();
        
        // ============================================
        // PART 4: Finding Highest Score
        // ============================================
        
        System.out.println("--- Leaderboard Statistics ---");
        
        String topPlayer = "";
        Integer topScore = 0;
        
        /* Same .entrySet() iteration pattern as Part 1.
           Each pass through the loop compares the current entry's score to
           the running maximum and updates topPlayer and topScore if a higher score is found. */
        for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
            String currentPlayer = entry.getKey();
            Integer currentScore = entry.getValue();
            
            if (currentScore > topScore) {
                topScore = currentScore;
                topPlayer = currentPlayer;
            }
        }
        
        System.out.println("🏆 Top Player: " + topPlayer + " with " + topScore + " pts");
        
        System.out.println();
        
        // ============================================
        // PART 5: Remove Player from Leaderboard
        // ============================================
        
        System.out.println("--- Remove Player ---");
        
        System.out.print("Remove which player? ");
        String removePlayer = scanner.nextLine();
        
        /* .containsKey() check before .remove() for the same reason as before:
           confirm the player exists so the else branch can give a clear message. */
        if (playerScores.containsKey(removePlayer)) {
            /* .remove(key) deletes the key-value pair and returns the value that was stored there.
               Capturing the return value lets us confirm what score the player had before removal. */
            Integer removedScore = playerScores.remove(removePlayer);
            System.out.println("✓ Removed " + removePlayer + " (had " + removedScore + " pts)");
        } else {
            System.out.println("✗ Player '" + removePlayer + "' not found!");
        }
        
        System.out.println();
        
        // ============================================
        // PART 6: Final Leaderboard State
        // ============================================
        
        System.out.println("--- Final Leaderboard ---");
        
        /* .size() here reflects the updated count after the removal in Part 5.
           If the user removed a player, this number will be one less than before. */
        System.out.println("Total players: " + playerScores.size());
        
        /* .size() is checked again to decide whether to iterate or print the empty message.
           Calling .size() multiple times is fine because it runs in O(1) constant time. */
        if (playerScores.size() > 0) {
            int finalNum = 1;
            /* Same .entrySet() pattern used in Parts 1 and 4.
               The map now reflects all changes made during the session. */
            for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
                String playerName = entry.getKey();
                Integer score = entry.getValue();
                System.out.println("  " + finalNum + ". " + playerName + ": " + score + " pts");
                finalNum++;
            }
        } else {
            System.out.println("  (empty leaderboard)");
        }
        
        System.out.println("\n========================================");
        System.out.println("    Leaderboard System Complete!");
        System.out.println("========================================");
        
        scanner.close();
    }
}

// ============================================
// KEY HASHMAP METHODS REFERENCE
// ============================================
/*
 * HashMap Methods Used in This Example:
 *
 * .put(K key, V value)
 *     Adds a new key-value pair or overwrites the existing value if the key already exists.
 *     Returns the old value if the key existed, or null if the key was new.
 *     Very fast: O(1) average time.
 *
 * .get(K key)
 *     Retrieves the value associated with the given key.
 *     Returns null if the key is not in the map (no exception thrown).
 *     Always use .containsKey() first or null-check the result before using it.
 *
 * .containsKey(K key)
 *     Checks whether a key exists in the map.
 *     Returns true if found, false otherwise.
 *     Very fast: O(1) average time.
 *
 * .remove(K key)
 *     Deletes the key-value pair at the given key.
 *     Returns the value that was removed, or null if the key was not present.
 *     Very fast: O(1) average time.
 *
 * .size()
 *     Returns the number of key-value pairs currently in the map.
 *     Very fast: O(1).
 *
 * .isEmpty()
 *     Returns true if the map contains no entries, false otherwise.
 *     Very fast: O(1).
 *
 * .entrySet()
 *     Returns a Set of Map.Entry objects representing every key-value pair.
 *     Used with a for-each loop to iterate over both keys and values at once.
 *
 * .keySet()
 *     Returns a Set of all keys in the map.
 *     Use when you only need the keys, not the values.
 *
 * .values()
 *     Returns a Collection of all values in the map.
 *     Use when you only need the values, not the keys.
 *
 *
 * Why HashMap for Gaming Leaderboard?
 * ✓ Instantly finds a player's score by name (O(1) vs O(n) for ArrayList)
 * ✓ Automatically prevents duplicate player entries
 * ✓ Easy to update scores when players play again
 * ✓ Easy to add and remove players dynamically
 * ✓ Each operation (add/get/remove) runs in constant time
 *
 *
 * HashMap Key Concepts:
 *     KEY-VALUE MAPPING     Each unique key maps to exactly one value.
 *     UNIQUE KEYS           Adding a duplicate key overwrites the old value silently.
 *     DUPLICATE VALUES      Multiple players can share the same score.
 *     FAST OPERATIONS       Add/remove/lookup all run in O(1) average time.
 *     UNORDERED             Iteration order is unpredictable (not insertion order).
 *
 *
 * Real-World Uses of HashMap:
 *     Gaming leaderboards (this example)
 *     Phone books (name to phone number)
 *     Dictionary lookups (word to definition)
 *     Database indexes (ID to record)
 *     Caching (key to cached result)
 *     Session management (session ID to user data)
 */


// ============================================
// ENTRY OBJECT REFERENCE
// ============================================
/*
 * Map.Entry<K, V> represents a single key-value pair inside a HashMap.
 *
 * When iterating with .entrySet():
 *
 *     for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
 *         String key   = entry.getKey();     gets the player name
 *         Integer value = entry.getValue();  gets the score
 *     }
 *
 * Why use Map.Entry?
 *     Retrieves both key and value in a single iteration step.
 *     More efficient than looping through .keySet() and calling .get() on each key.
 *
 *
 * Alternative iteration methods:
 *
 * Keys only:
 *     for (String playerName : playerScores.keySet()) {
 *         System.out.println(playerName);
 *     }
 *
 * Values only:
 *     for (Integer score : playerScores.values()) {
 *         System.out.println(score);
 *     }
 *
 * Both (recommended when you need key and value together):
 *     for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
 *         System.out.println(entry.getKey() + ": " + entry.getValue());
 *     }
 */


// ============================================
// HASHMAP vs ARRAYLIST COMPARISON
// ============================================
/*
 * Both are commonly used collections but serve different purposes:
 *
 *                    HashMap                      ArrayList
 * Purpose:           Key-value mapping             Ordered list
 * Lookup:            O(1) by key                  O(n) by value
 * Keys/elements:     Keys must be unique           All values can repeat
 * Values:            Values can repeat             N/A
 * Order:             Unpredictable                 Maintains insertion order
 * Index access:      Not supported                 Supported (get by position)
 * Best for:          Name to value lookups         Ordered sequences
 *
 *
 * When to use HashMap:
 *     Need to find a value by a meaningful key (name, ID, code)
 *     Order of entries does not matter
 *     Want O(1) average lookup time
 *     Data naturally exists as key-value relationships
 *
 * When to use ArrayList:
 *     Need ordered list of items
 *     Need to access items by position (index)
 *     Order of insertion matters
 *     Duplicate elements are allowed and expected
 */