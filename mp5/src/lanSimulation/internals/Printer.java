package lanSimulation.internals;

import lanSimulation.internals.Node;

import java.io.IOException;
import java.io.Writer;

public class Printer extends Node{
    /**
     * Construct a <em>Printer</em> with given #name.
     */
    public Printer (String _name) {
        super(_name);
    }
    public void printXMLOn(StringBuffer buf) {
        buf.append("<printer>");
        buf.append(name);
        buf.append("</printer>");
    }
    public void printOn(StringBuffer buf) {
        buf.append("Printer ");
        buf.append(name);
        buf.append(" [Printer]");
    }
    public void printHTMLOn(StringBuffer buf) {
        buf.append("Printer ");
        buf.append(name);
        buf.append(" [Printer]");
    }
    public boolean printDocument(Packet document, Writer report) {
        String author = "Unknown";
        String title = "Untitled";
        int startPos = 0, endPos = 0;

            try {
                if (document.message.startsWith("!PS")) {
                    startPos = document.message.indexOf("author:");
                    if (startPos >= 0) {
                        endPos = document.message.indexOf(".", startPos + 7);
                        if (endPos < 0) {
                            endPos = document.message.length();
                        }

                        author = document.message.substring(startPos + 7,
                                endPos);
                    }

                    startPos = document.message.indexOf("title:");
                    if (startPos >= 0) {
                        endPos = document.message.indexOf(".", startPos + 6);
                        if (endPos < 0) {
                            endPos = document.message.length();
                        }
                        title = document.message
                                .substring(startPos + 6, endPos);
                    }

                    logAccountingInfo(report, author, title);
                    report.write(">>> Postscript job delivered.\n\n");
                    report.flush();
                } else {
                    title = "ASCII DOCUMENT";
                    if (document.message.length() >= 16) {
                        author = document.message.substring(8, 16);
                    }

                    logAccountingInfo(report, author, title);
                    report.write(">>> ASCII Print job delivered.\n\n");
                    report.flush();
                }

            } catch (IOException exc) {
                // just ignore
            }
            return true;

    }

    /**
     * Write string #author and #title to #report.
     */
    private void logAccountingInfo(Writer report, String author, String title) throws IOException {
        report.write("\tAccounting -- author = '");
        report.write(author);
        report.write("' -- title = '");
        report.write(title);
        report.write("'\n");
    }
}
