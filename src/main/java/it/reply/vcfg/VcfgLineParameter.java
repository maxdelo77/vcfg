package it.reply.vcfg;

import org.kohsuke.args4j.Option;

/**
 * Created by Massimo on 08/05/14.
 */
public class VcfgLineParameter {

    public static enum TYPE {
        GPRS,
        OPT
    }

    @Option( name="-src", required=true, usage="Source File")
    private String source;

    @Option( name="-dest", required=true, usage="Destination Folder")
    private String destination;

    @Option( name="-type", required=true, usage="XML Process Type ")
    private TYPE type;

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VcfgLineParameter that = (VcfgLineParameter) o;

        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "sou rce=" + source + ", destination=" + destination + ", type=" + type ;
    }
}
