package lanSimulation.internals;
import lanSimulation.internals.Node;

import java.io.IOException;
import java.io.Writer;

public class WorkStation extends Node {
    /**
     * Construct a <em>WorkStation</em> with given #name.
     */
    public WorkStation (String _name) {
        super( _name);
    }

    public void printXMLOn(StringBuffer buf) {
        buf.append("<workstation>");
        buf.append(name);
        buf.append("</workstation>");
    }
    public void printOn(StringBuffer buf) {
        buf.append("Workstation ");
        buf.append(name);
        buf.append(" [Workstation]");
    }
    public void printHTMLOn(StringBuffer buf) {
        buf.append("Workstation ");
        buf.append(name);
        buf.append(" [Workstation]");
    }
    public boolean printDocument(Packet document, Writer report) {
        try{
            report.write(">>> Destination is not a printer, print job canceled.\n\n");
            report.flush();
        } catch (IOException exc) {
            // just ignore
        }
        return false;
    }
}
