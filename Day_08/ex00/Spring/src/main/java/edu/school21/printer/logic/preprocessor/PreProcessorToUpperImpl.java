package edu.school21.printer.logic.preprocessor;

import java.util.Locale;

public class PreProcessorToUpperImpl implements PreProcessor{
    @Override
    public String preProcessesMessages(String str) {
        return str.toUpperCase();
    }
}
