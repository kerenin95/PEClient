package com.example.peclient;

/**
 * @author bber9
 * @description byte-stream to UTF8 encoding for downloads
 */
public class CharacterEncoder {

    public static final String LAST_3_BYTE_UTF_CHAR = "\uFFFF";
    public static final String REPLACEMENT_CHAR = "\uFFFD";

    /**
     * @description UTF8 enforcing to allow download of emails
     * @param string string to enforce as UTF8
     * @return encoded string to pass into gmail handling classes
     */
    public static String toValid3ByteUTF8String(String string)  {
        final int length = string.length();
        StringBuilder b = new StringBuilder(length);
        for (int offset = 0; offset < length; ) {
            final int codepoint = string.codePointAt(offset);

            // do something with the codepoint
            if (codepoint > CharacterEncoder.LAST_3_BYTE_UTF_CHAR.codePointAt(0)) {
                b.append(CharacterEncoder.REPLACEMENT_CHAR);
            } else {
                if (Character.isValidCodePoint(codepoint)) {
                    b.appendCodePoint(codepoint);
                } else {
                    b.append(CharacterEncoder.REPLACEMENT_CHAR);
                }
            }
            offset += Character.charCount(codepoint);
        }
        return b.toString();
    }
}
