package com.davidjennings.selfstudy.ik.week4_graphs.practice;

import java.util.*;

/**
 * String Transformation Using Given Dictionary Of Words
 *
 *
 *
 * Problem Statement:
 *
 *
 *
 * You are given a dictionary of words named words, and two strings named start and stop. All given strings have
 * equal length. Dictionary words are not in any particular order, there may be duplicates, too.
 *
 *
 *
 * You need to transform string start to string stop using given dictionary words. In each transformation, you can
 * only change one character of the current string. e.g. "abc" -> "abd" is a valid transformation, because only one
 * character 'c' is changed to 'd', but, "abc" -> "axy" is not a valid transformation, because two characters are
 * changed, character 'b' is changed to 'x' and character 'c' is changed to 'y'.
 *
 *
 *
 * In other words, you need to find out the least amount of transformations between two words start and stop, given a
 * specific set of allowed transformations words. In other words, you need to find the shortest possible sequence of
 * strings (two or more strings) such that:
 *
 * First string is start.
 * Last string is stop.
 * Every  string (except the first one) differs from the previous one by exactly one character.
 * Every string (except, possibly, first and last ones) are in the dictionary of words.
 * i.e. output = [start, <strings from the given dictionary>, stop] and len(output) >= 2.
 *
 *
 *
 * If two or more such sequences exist, any one of them is a correct answer.
 *
 *
 *
 * If no such sequence is there to be found, [“-1”] (a sequence of one string, “-1”) is the correct answer.
 *
 *
 *
 * Constraints:
 *
 *
 *
 * All input strings consist of lowercase Latin characters only.
 * 0 <=  total number of characters in all dictionary words combined <= 10^5.
 *
 *
 * Input/Output Format For The Function:
 *
 *
 *
 * Input Format:
 *
 *
 *
 * There are three arguments:
 *
 * Array of strings words,
 * String start,
 * String stop.
 *
 *
 * Output Format:
 *
 *
 *
 * Function must return an array of strings of length >= 2, where the first string is start and the last string is
 * stop, if the transformation is possible. Else return an array of strings containing only one string "-1", i.e.
 * return ["-1"].
 *
 *
 *
 * Input/Output Format For The Custom Input:
 *
 *
 *
 * Input Format:
 *
 *
 *
 * The first line of input should contain an integer n, denoting size of input array words. In next n lines, ith line
 * should contain a string words[i], denoting a value at index i of words.
 *
 * In next line, there should be a string start, denoting the start string. In next line, there should be a string
 * stop, denoting the stop string.
 *
 *
 *
 * If n = 4, words = ["cat", "hat", "bad", "had"], start = “bat” and stop = “had”, then input should be:
 *
 *
 *
 * 4
 *
 * cat
 *
 * hat
 *
 * bad
 *
 * had
 *
 * bat
 *
 * had
 *
 *
 *
 * Output Format:
 *
 *
 *
 * Let’s denote the size of ans array as m, where ans is the output string array returned by solution function.
 *
 *
 *
 * There will be m lines of output, where ith line contains a string ans[i], denoting a value at index i of ans.
 *
 *
 *
 * For input n = 4, words = ["cat", "hat", "bad", "had"], start = “bat” and stop = “had”, output will be:
 *
 *
 *
 * bat
 *
 * hat
 *
 * had
 *
 *
 *
 * Sample Test Cases:
 *
 *
 *
 * Sample Test Case 1:
 *
 *
 *
 * Sample Input 1:
 *
 *
 *
 * words = ["cat", "hat", "bad", "had"]
 *
 * start = "bat"
 *
 * stop = "had"
 *
 *
 *
 * Sample Output 1:
 *
 *
 *
 * ["bat", "bad", "had"]
 *
 *
 *
 * or
 *
 *
 *
 * ["bat", "hat", "had"]
 *
 *
 *
 * Explanation 1:
 *
 *
 *
 * From "bat" change character 't' to 'd', so new string will be "bad".
 *
 * From "bad" change character 'b' to 'h', so new string will be "had".
 *
 *
 *
 * or
 *
 *
 *
 * From "bat" change character 'b' to 'h', so new string will be "hat".
 *
 * From "hat" change character 't' to 'd', so new string will be "had".
 *
 *
 *
 * Sample Test Case 2:
 *
 *
 *
 * Sample Input 2:
 *
 *
 *
 * words = []
 *
 * start = bbb
 *
 * stop = bbc
 *
 *
 *
 * Sample Output 2:
 *
 *
 *
 * ["bbb", "bbc"]
 *
 *
 *
 * Explanation 2:
 *
 *
 *
 * From "bbb" change the last character 'b' to 'c', so new string will be "bbc".
 *
 *
 *
 * Sample Test Case 3:
 *
 *
 *
 * Sample Input 3:
 *
 *
 *
 * words = []
 *
 * start = "zzzzzz"
 *
 * stop = "zzzzzz"
 *
 *
 *
 * Sample Output 3:
 *
 *
 *
 * [-1]
 *
 *
 *
 * Explanation 3:
 *
 *
 *
 * Function must return an array of strings of length >= 2, where the first string is start and the last string is
 * stop, if the transformation is possible. Else return an array of strings containing only one string "-1", i.e.
 * return ["-1"].
 *
 *
 *
 * Here, the words dictionary is empty and ["zzzzzz", "zzzzzz"] is not a valid transformation hence return ["-1"].
 *
 *
 *
 * Sample Test Case 4:
 *
 *
 *
 * Sample Input 4:
 *
 *
 *
 * words = ["cccw", "accc", "accw"]
 *
 * start = "cccc"
 *
 * stop = "cccc"
 *
 *
 *
 * Sample Output 4:
 *
 *
 *
 * ["cccc", "cccw", "cccc"]
 *
 *
 *
 * Or:
 *
 *
 *
 * ["cccc", "accc", "cccc"]
 *
 *
 *
 * Explanation 4:
 *
 *
 *
 * All the given conditions are met.
 */
public class StringTransformationUsingWordDictionary
{
    private static class Node
    {
        final String word;
        final Node parent;

        Node(String word, Node parent)
        {
            this.word = word;
            this.parent = parent;
        }
    }

    static String[] string_transformation(String[] words, String start, String stop)
    {
        boolean foundSolution = false;
        Set<String> visited = new HashSet<>(words.length);

        Set<String> dict = new HashSet<>(Arrays.asList(words));

        ArrayDeque<Node> q = new ArrayDeque<>();
        Node node = new Node(start, null);
        q.addLast(node);

        while (!q.isEmpty())
        {
            node = q.removeFirst();
            if (stop.equals(node.word))
            {
                foundSolution = !start.equals(stop);
                break;
            }

            if (!visited.contains(node.word))
            {
                visited.add(node.word);

                for (int charIndex = 0; charIndex < start.length(); charIndex++)
                {
                    for (char newChar = 'a'; newChar <= 'z'; newChar++)
                    {
                        String newWord = node.word.substring(0, charIndex) + newChar + node.word.substring(charIndex + 1);
                        if (!newWord.equals(node.word) && (dict.contains(newWord) || newWord.equals(stop)))
                            q.addLast(new Node(newWord, node));
                    }
                }
            }
        }

        if (!foundSolution)
            return new String[] { "-1"};

        List<String> result = new ArrayList<>();
        while (node != null)
        {
            result.add(node.word);
            node = node.parent;
        }

        String[] r = new String[result.size()];
        int j = 0;
        for (int i=result.size() -1; i >= 0; i--)
        {
            r[j++] = result.get(i);
        }

        return r;
    }

    public static void main(String[] args)
    {
//        String[] r = string_transformation(
//            new String[] {"cat", "hat", "bad", "had"},
//            "bat",
//            "had"
//        );

//        String[] r = string_transformation(
//            new String[] {"aaa"},
//            "baa",
//            "aab"
//        );

        String[] r = string_transformation(
            new String[0],
            "zzzzz",
            "zzzzz"
        );

        int x = 0;
    }
}
