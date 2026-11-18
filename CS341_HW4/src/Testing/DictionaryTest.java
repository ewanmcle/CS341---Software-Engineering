package Testing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ewan.Dictionary;

/**
 * JUnit tests for ewan.Dictionary (a binary search tree of lowercase words).
 */
public class DictionaryTest {

    private Dictionary dict;

    @BeforeEach
    public void setUp() {
        dict = new Dictionary();
    }

    // HELPER: INSERT SEVERAL WORDS AT ONCE
    private void insertAll(String... words) {
        for (String w : words) {
            dict.insert(w);
        }
    }

    // ---------- EMPTY DICTIONARY ----------

    @Test
    public void emptyDictionaryContainsNothing() {
        assertFalse(dict.contains("anything"));
        assertFalse(dict.spellCheck("anything"));
    }

    @Test
    public void removeFromEmptyDictionaryDoesNotThrow() {
        dict.remove("ghost");
        assertFalse(dict.contains("ghost"));
    }

    // ---------- INSERT / CONTAINS ----------

    @Test
    public void insertedWordIsFound() {
        dict.insert("apple");
        assertTrue(dict.contains("apple"));
    }

    @Test
    public void missingWordIsNotFound() {
        insertAll("apple", "banana", "cherry");
        assertFalse(dict.contains("grape"));
    }

    @Test
    public void allInsertedWordsAreFound() {
        String[] words = {"mango", "apple", "zebra", "kiwi", "banana", "peach", "grape"};
        insertAll(words);
        for (String w : words) {
            assertTrue(dict.contains(w), "expected to find " + w);
        }
    }

    @Test
    public void containsIsCaseInsensitive() {
        dict.insert("Apple");
        assertTrue(dict.contains("apple"));
        assertTrue(dict.contains("APPLE"));
        assertTrue(dict.contains("aPpLe"));
    }

    @Test
    public void duplicateInsertDoesNotBreakTree() {
        insertAll("apple", "apple", "APPLE");
        assertTrue(dict.contains("apple"));
    }

    @Test
    public void prefixIsNotAMatch() {
        dict.insert("apple");
        assertFalse(dict.contains("app"));
        assertFalse(dict.contains("apples"));
    }

    @Test
    public void sortedInsertOrderStillWorks() {
        // Inserting in sorted order makes a "linked list" shaped tree
        insertAll("a", "b", "c", "d", "e", "f");
        assertTrue(dict.contains("a"));
        assertTrue(dict.contains("f"));
        assertFalse(dict.contains("g"));
    }

    @Test
    public void emptyStringCanBeStored() {
        dict.insert("");
        assertTrue(dict.contains(""));
    }

    // ---------- SPELL CHECK ----------

    @Test
    public void spellCheckAcceptsKnownWord() {
        insertAll("receive", "separate", "definitely");
        assertTrue(dict.spellCheck("receive"));
    }

    @Test
    public void spellCheckRejectsMisspelledWord() {
        insertAll("receive", "separate", "definitely");
        assertFalse(dict.spellCheck("recieve"));
        assertFalse(dict.spellCheck("seperate"));
    }

    @Test
    public void spellCheckIsCaseInsensitive() {
        dict.insert("hello");
        assertTrue(dict.spellCheck("HELLO"));
    }

    @Test
    public void spellCheckAgreesWithContains() {
        insertAll("dog", "cat", "bird");
        String[] probes = {"dog", "cat", "bird", "fish", "DOG", ""};
        for (String p : probes) {
            assertTrue(dict.contains(p) == dict.spellCheck(p), "mismatch for \"" + p + "\"");
        }
    }

    // ---------- REMOVE ----------

    @Test
    public void removeLeafNode() {
        insertAll("m", "c", "x");
        dict.remove("c");
        assertFalse(dict.contains("c"));
        assertTrue(dict.contains("m"));
        assertTrue(dict.contains("x"));
    }

    @Test
    public void removeNodeWithOnlyLeftChild() {
        insertAll("m", "f", "a");
        dict.remove("f");
        assertFalse(dict.contains("f"));
        assertTrue(dict.contains("a"));
        assertTrue(dict.contains("m"));
    }

    @Test
    public void removeNodeWithOnlyRightChild() {
        insertAll("m", "f", "h");
        dict.remove("f");
        assertFalse(dict.contains("f"));
        assertTrue(dict.contains("h"));
        assertTrue(dict.contains("m"));
    }

    @Test
    public void removeNodeWithTwoChildren() {
        insertAll("m", "f", "t", "a", "h", "p", "z");
        dict.remove("f");
        assertFalse(dict.contains("f"));
        for (String w : new String[] {"m", "t", "a", "h", "p", "z"}) {
            assertTrue(dict.contains(w), "lost " + w + " after removing f");
        }
    }

    @Test
    public void removeRootWithTwoChildren() {
        insertAll("m", "f", "t", "a", "h", "p", "z");
        dict.remove("m");
        assertFalse(dict.contains("m"));
        for (String w : new String[] {"f", "t", "a", "h", "p", "z"}) {
            assertTrue(dict.contains(w), "lost " + w + " after removing root");
        }
    }

    @Test
    public void removeOnlyWordLeavesEmptyDictionary() {
        dict.insert("solo");
        dict.remove("solo");
        assertFalse(dict.contains("solo"));
        dict.insert("again");
        assertTrue(dict.contains("again"));
    }

    @Test
    public void removeIsCaseInsensitive() {
        dict.insert("apple");
        dict.remove("APPLE");
        assertFalse(dict.contains("apple"));
    }

    @Test
    public void removeMissingWordChangesNothing() {
        insertAll("apple", "banana");
        dict.remove("cherry");
        assertTrue(dict.contains("apple"));
        assertTrue(dict.contains("banana"));
    }

    @Test
    public void removeAllWordsOneByOne() {
        String[] words = {"mango", "apple", "zebra", "kiwi", "banana", "peach", "grape"};
        insertAll(words);
        for (String w : words) {
            dict.remove(w);
            assertFalse(dict.contains(w), w + " still present after removal");
        }
    }

    @Test
    public void removedWordCanBeReinserted() {
        insertAll("apple", "banana");
        dict.remove("apple");
        dict.insert("apple");
        assertTrue(dict.contains("apple"));
    }

    // ---------- NULL INPUT ----------

    @Test
    public void nullInputThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> dict.insert(null));
        assertThrows(NullPointerException.class, () -> dict.contains(null));
        assertThrows(NullPointerException.class, () -> dict.spellCheck(null));
        assertThrows(NullPointerException.class, () -> dict.remove(null));
    }
}
