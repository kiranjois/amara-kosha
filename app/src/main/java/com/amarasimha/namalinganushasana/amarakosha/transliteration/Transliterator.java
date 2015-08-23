package com.amarasimha.namalinganushasana.amarakosha.transliteration;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SnehaKirana on 22/08/15.
 */
public class Transliterator extends Devanagari {

    Matcher m = null;

    String match = null, sentnc = "";
    int index = 0;

    public ArrayList<String> split_word(String word) {
        ArrayList<String> syllables = new ArrayList<String>();
        boolean vowel_start_p = true;
        String matched = null;
        while (word.length() > 0) {
            Matcher m = Pattern.compile(vowels).matcher(word);

            if (m.lookingAt()) {
                matched = m.group(0);
                if (vowel_start_p)
                    syllables.add("~" + matched);
                else
                    syllables.add(matched);
                vowel_start_p = true;
                word = word.substring(matched.length());

            } else {
                m = Pattern.compile(consonants).matcher(word);
                if (m.lookingAt()) {
                    matched = m.group(0);
                    syllables.add(matched);
                    vowel_start_p = false;
                    word = word.substring(matched.length());

                    // look ahead for virama setting

                    m = Pattern.compile(vowels).matcher(word);
                    boolean next = m.lookingAt();
                    if (next != true || word.length() == 0) {
                        syllables.add("*");
                    } else {
                        syllables.add(m.group(0));
                        //System.out.println(m.lookingAt());
                        word = word.substring(m.group(0).length());
                    }
                }
            }
        }
        return syllables;
    }

    public String match_code(String syllable_mcc) {
        String matched = null;
        matched = letter_codes.get(syllable_mcc);
        if (matched != null)
            return matched;
        return syllable_mcc;
    }

    public String one_word(String word_ow) {
        ArrayList<String> letters_ow = new ArrayList<String>();
        ArrayList<String> syllables_ow = new ArrayList<String>();
        String oneword = "";
        if (!(word_ow.length() > 0))
            return "";
        syllables_ow = split_word(word_ow);
        for (int i_ow = 0; i_ow < syllables_ow.size(); i_ow++) {
            letters_ow.add(match_code(syllables_ow.get(i_ow)));
        }

        for (String s : letters_ow)
            oneword = oneword + s;
        return oneword;
    }

    public String many_words(String sentence) {
        ArrayList<String> words = new ArrayList<String>();
        String regex = "((" + vowels + ")|(" + consonants + "))+";
        while (sentence.length() >= 1) {
            m = Pattern.compile("^``" + regex).matcher(sentence);
            if (m.lookingAt()) {
                match = m.group(0);
                words.add("`");
                words.add(one_word(match.substring(2)));
                sentence = sentence.substring(match.length());
            } else {
                m = Pattern.compile("^`" + regex).matcher(sentence);
                if (m.lookingAt()) {
                    match = m.group(0);
                    words.add(one_word(match.substring(1)));
                    sentence = sentence.substring(match.length());
                } else {
                    m = Pattern.compile("^" + regex).matcher(sentence);
                    if (m.lookingAt()) {
                        match = m.group(0);
                        words.add(one_word(match));
                        sentence = sentence.substring(match.length());
                    } else {
                        words.add(sentence.substring(0, 1));
                        sentence = sentence.substring(1);
                    }
                }
            }
        }

        for (String s : words)
            sentnc = sentnc + s;

        return sentnc;
    }

    public String print_many_words(String sentence) {
        String text_pmw = many_words(sentence);
        String ans = "";
        while (text_pmw.length() > 0) {
            String unicode_chars = "//u[0-9][A-Z]+/;";
            m = Pattern.compile(unicode_chars).matcher(sentence);

            if (m.lookingAt()) {
                match = sentence.substring(0, m.start());
                int search = m.start(); // returns the start index of the
                // previous match
                ans += text_pmw.substring(0, search);
                text_pmw = text_pmw.substring(search + match.length());
            } else {
                ans += text_pmw.substring(0);
                text_pmw = "";
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String transText = new Transliterator().print_many_words("shree raama jaya raama");
        System.out.println(transText);
    }

}
