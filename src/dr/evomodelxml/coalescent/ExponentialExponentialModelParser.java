package dr.evomodelxml.coalescent;

import dr.evolution.util.Units;
import dr.evomodel.coalescent.ExponentialConstantModel;
import dr.evomodel.coalescent.ExponentialExponentialModel;
import dr.evoxml.util.XMLUnits;
import dr.inference.model.Parameter;
import dr.xml.*;

/**
 * Parses an element from an DOM document into a ExponentialExponentialModel.
 */
public class ExponentialExponentialModelParser extends AbstractXMLObjectParser {

    public static final String EXPONENTIAL_EXPONENTIAL_MODEL = "exponentialExponential";
    public static final String POPULATION_SIZE = "populationSize";
    public static final String TRANSITION_TIME = "transitionTime";
    public static final String ANCESTRAL_GROWTH_RATE = "ancestralGrowthRate";
    public static final String GROWTH_RATE = "growthRate";

    public String getParserName() {
        return EXPONENTIAL_EXPONENTIAL_MODEL;
    }

    public Object parseXMLObject(XMLObject xo) throws XMLParseException {

        Units.Type units = XMLUnits.Utils.getUnitsAttr(xo);

        XMLObject cxo = xo.getChild(POPULATION_SIZE);
        Parameter N0Param = (Parameter) cxo.getChild(Parameter.class);

        cxo = xo.getChild(GROWTH_RATE);
        Parameter growthParam = (Parameter) cxo.getChild(Parameter.class);

        cxo = xo.getChild(ANCESTRAL_GROWTH_RATE);
        Parameter ancestralGrowthParam = (Parameter) cxo.getChild(Parameter.class);

        cxo = xo.getChild(TRANSITION_TIME);
        Parameter timeParam = (Parameter) cxo.getChild(Parameter.class);

        return new ExponentialExponentialModel(N0Param, growthParam, ancestralGrowthParam, timeParam, units);
    }

    //************************************************************************
    // AbstractXMLObjectParser implementation
    //************************************************************************

    public String getParserDescription() {
        return "A demographic model of exponential growth followed by a different exponential growth.";
    }

    public Class getReturnType() {
        return ExponentialExponentialModel.class;
    }

    public XMLSyntaxRule[] getSyntaxRules() {
        return rules;
    }

    private XMLSyntaxRule[] rules = new XMLSyntaxRule[]{
            XMLUnits.SYNTAX_RULES[0],
            new ElementRule(POPULATION_SIZE,
                    new XMLSyntaxRule[]{new ElementRule(Parameter.class)}),
            new ElementRule(GROWTH_RATE,
                    new XMLSyntaxRule[]{new ElementRule(Parameter.class)}),
            new ElementRule(ANCESTRAL_GROWTH_RATE,
                    new XMLSyntaxRule[]{new ElementRule(Parameter.class)}),
            new ElementRule(TRANSITION_TIME,
                    new XMLSyntaxRule[]{new ElementRule(Parameter.class)}),
    };
}
