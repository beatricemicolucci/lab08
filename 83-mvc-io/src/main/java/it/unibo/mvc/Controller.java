package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    /* A method for setting the next string to print. Null values are not */
    void setNextString(String string);

    /* A method for getting the next string to print */
    String getNexString(String string);

    /* A method for getting the history of the printed strings (in form of a `List` of `Strings`) */
    List<String> getHistoryOfPrintedStrings();

    /* A method that prints the current string. 
    If the current string is unset, an `IllegalStateException` should be thrown */
    void printCurrentString();

}
