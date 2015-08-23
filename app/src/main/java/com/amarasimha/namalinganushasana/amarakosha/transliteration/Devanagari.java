package com.amarasimha.namalinganushasana.amarakosha.transliteration;

import java.util.HashMap;

public class Devanagari
{

    public static final String vowels = "(A((o)|(O))?)|(a((A)|(a)|(u)|(i))?)|(En)|(e(e)?)|(I)|(H)|(TR)|(M)|(o(o)?)|(tR)|(i)|(U)|(u)|([:])|([|]([|])?)";

    public static final String consonants = "(Ch)|(D(dD|h)?)|(G)|(L(lL)?)|(N(nN)?)|(R(rR)?)|(Sh)|(Th?)|(Y)|(bh?)|(ch)|(dh?)|(f)|(g(h|G)?)|(h)|(jh?)|(kh?)|(l)|(m)|(nY?)|(ph?)|(qh?)|(r)|(sh?)|(th?)|(v)|(y)|(z)|(Bh?)";

    static HashMap<String, String> letter_codes = new HashMap<String, String>();

    static {

        letter_codes.put("~a", "\u0905");
        letter_codes.put("~aa", "\u0906");
        letter_codes.put("~A", "\u0906");
        letter_codes.put("~i", "\u0907");
        letter_codes.put("~ee", "\u0908");
        letter_codes.put("~I", "\u0908");
        letter_codes.put("~u", "\u0909");
        letter_codes.put("~oo", "\u090A");
        letter_codes.put("~U", "\u090A");
        letter_codes.put("~tR", "\u090B");
        letter_codes.put("~En", "\u090D");
        letter_codes.put("~e", "\u090F");
        letter_codes.put("~ai", "\u0910");
        letter_codes.put("~Ao", "\u0911");
        letter_codes.put("~o", "\u0913");
        letter_codes.put("~au", "\u0914");
        letter_codes.put("~TR", "\u0960");
        letter_codes.put("~AO", "\u0901");  //chandrabindu
        letter_codes.put("~AOM", "\u0950");
        letter_codes.put("~M", "\u0902");    //anusvara
        letter_codes.put("~H", "\u0903");    //visarga
        letter_codes.put("~:", "\u0903");    //visarga
        letter_codes.put("~aA", "\u093D");   //avagraha
        letter_codes.put("~|", "\u0964");
        letter_codes.put("~||", "\u0965");
        letter_codes.put("AO", "\u0901");  //chandrabindu
        letter_codes.put("H", "\u0903");    //visarga
        letter_codes.put(":", "\u0903");    //visarga
        letter_codes.put("Ao", "\u0949");   //chandra o
        letter_codes.put("M", "\u0902");    //anusvara
        letter_codes.put("aA", "\u093D");   //avagraha
        letter_codes.put("|", "\u0964");  //danda
        letter_codes.put("||", "\u0965"); //double-danda
        letter_codes.put("a", "");
        letter_codes.put("aa", "\u093E");
        letter_codes.put("A", "\u093E");
        letter_codes.put("i", "\u093F");
        letter_codes.put("e", "\u0947");
        letter_codes.put("ee", "\u0940");
        letter_codes.put("I", "\u0940");
        letter_codes.put("u", "\u0941");
        letter_codes.put("oo", "\u0942");
        letter_codes.put("U", "\u0942");
        letter_codes.put("ai", "\u0948");
        letter_codes.put("o",  "\u094B");
        letter_codes.put("au", "\u094C");
        letter_codes.put("*", "\u094D");
        letter_codes.put("tR", "\u0943");
        letter_codes.put("TR", "\u0944");
        letter_codes.put("En", "\u0945");
        letter_codes.put("k", "\u0915");
        letter_codes.put("kh", "\u0916");
        letter_codes.put("g", "\u0917");
        letter_codes.put("gh", "\u0918");
        letter_codes.put("G", "\u0919");
        letter_codes.put("ch", "\u091A");
        letter_codes.put("Ch", "\u091B");
        letter_codes.put("j", "\u091C");
        letter_codes.put("jh", "\u091D");
        letter_codes.put("nY", "\u091E");
        letter_codes.put("t", "\u091F");
        letter_codes.put("T", "\u0920");
        letter_codes.put("d", "\u0921");
        letter_codes.put("D", "\u0922");
        letter_codes.put("N", "\u0923");
        letter_codes.put("th", "\u0924");
        letter_codes.put("Th", "\u0925");
        letter_codes.put("dh", "\u0926");
        letter_codes.put("Dh", "\u0927");
        letter_codes.put("n", "\u0928");
        letter_codes.put("NnN", "\u0929");
        letter_codes.put("p", "\u092A");
        letter_codes.put("ph", "\u092B");
        letter_codes.put("b", "\u092C");
        letter_codes.put("bh", "\u092D");
        letter_codes.put("B", "\u092D");
        letter_codes.put("Bh", "\u092D");
        letter_codes.put("m", "\u092E");
        letter_codes.put("y", "\u092F");
        letter_codes.put("r", "\u0930");
        letter_codes.put("R", "\u0931");
        letter_codes.put("l", "\u0932");
        letter_codes.put("L", "\u0933");
        letter_codes.put("LlL", "\u0934");
        letter_codes.put("v", "\u0935");
        letter_codes.put("sh", "\u0936");
        letter_codes.put("Sh", "\u0937");
        letter_codes.put("s", "\u0938");
        letter_codes.put("h", "\u0939");
        letter_codes.put("q", "\u0958");
        letter_codes.put("qh", "\u0959");
        letter_codes.put("gG", "\u095A");
        letter_codes.put("z", "\u095B");
        letter_codes.put("DdD", "\u095C");
        letter_codes.put("RrR", "\u095D");
        letter_codes.put("f", "\u095E");
        letter_codes.put("Y", "\u095F");

    }
}