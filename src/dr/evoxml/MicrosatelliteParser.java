package dr.evoxml;

import dr.evolution.datatype.Microsatellite;
import dr.xml.*;


/**
 * @author Chieh-Hsi Wu
 *
 * Microsatellite data type parser
 *
 */
public class MicrosatelliteParser extends AbstractXMLObjectParser {

    public static final String MICROSAT = "microsatellite";
    public static final String MIN = "min";
    public static final String MAX = "max";

    public String getParserName() {
        return MICROSAT;
    }

    public Object parseXMLObject(XMLObject xo) throws XMLParseException {

        int min = xo.getIntegerAttribute(MIN);
        int max = xo.getIntegerAttribute(MAX);
        int unitLength = xo.getIntegerAttribute("unitLength");
        String name = xo.getId();

        return new Microsatellite(name, min, max, unitLength);
    }

    public String getParserDescription() {
        return "This element represents a microsatellite data type.";
    }

    public String getExample() {
        return "<microsatellite min=\"0\" max=\"20\" unitLength=\"2\"/>";
    }

    public XMLSyntaxRule[] getSyntaxRules() {
        return new XMLSyntaxRule[]{
                new AndRule(new XMLSyntaxRule[]{
                    AttributeRule.newStringRule(XMLObject.ID),
                    AttributeRule.newIntegerRule(MIN),
                    AttributeRule.newIntegerRule(MAX),
                    AttributeRule.newIntegerRule("unitLength")})
        };
    }


    public Class getReturnType() {
        return Microsatellite.class;
    }
}