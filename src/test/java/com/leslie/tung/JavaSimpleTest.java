package com.leslie.tung;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

/**
 * @author dongliangliang
 * @date 2023/3/20 21:31:02
 */
public class JavaSimpleTest {

    @Test
    public void testRegexSwapWords() throws InterruptedException {
        String inputStr = "apple orange";
        String regexStr = "^(\\S+)\\s+(\\S+)$";  // Regex pattern to be matched
        String replacementStr = "$2 $1";         // Replacement pattern with back references

        // Step 1: Allocate a Pattern object to compile a regex
        Pattern pattern = Pattern.compile(regexStr);

        // Step 2: Allocate a Matcher object from the Pattern, and provide the input
        Matcher matcher = pattern.matcher(inputStr);

        // Step 3: Perform the matching and process the matching result
        String outputStr = matcher.replaceFirst(replacementStr); // first match only
        System.out.println(outputStr);   // Output: orange apple
    }
}
