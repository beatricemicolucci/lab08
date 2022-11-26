package it.unibo.mvc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String nextString;
    private final List<String> historyStrings = new LinkedList<>();

    @Override
    public void setNextString(String string) {
        this.nextString = Objects.requireNonNull(string, "Null values are not acceptable");
    }

    @Override
    public String getNexString(String string) {
        return this.nextString;
    }

    @Override
    public List<String> getHistoryOfPrintedStrings() {
        return Collections.unmodifiableList(this.historyStrings);
    }

    @Override
    public void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalStateException("There is no string set");
        }
        this.historyStrings.add(this.nextString);
        System.out.println(nextString);
    }

}
