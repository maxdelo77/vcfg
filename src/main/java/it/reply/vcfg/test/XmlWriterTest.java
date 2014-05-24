package it.reply.vcfg.test;

import it.reply.vcfg.model.GprsParameterConfiguration;
import org.beanio.BeanReader;
import org.beanio.StreamFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * Created by Massimo on 08/05/14.
 */
public class XmlWriterTest {

    private static String fileToReadPath = "D:/work/vodafone/vcfg/res/";
    private static String source = "estensioni_gprs_act_source.csv";
    private static String destination = "estensioni_gprs_act_destination.xml";
    private static String mappingFile = "gprs_conf.xml";

    private List<GprsParameterConfiguration> load() throws FileNotFoundException {

        List<GprsParameterConfiguration> rows  = new ArrayList<GprsParameterConfiguration>();

        final StreamFactory factory = StreamFactory.newInstance();
        factory.loadResource(mappingFile);

        final Reader in = new BufferedReader(new FileReader(fileToReadPath + source));
        final BeanReader reader = factory.createReader("records", in);

        Object record = null;
        reader.read(); // Salto la prima riga
        while ((record = reader.read()) != null) {

            GprsParameterConfiguration currentRecord = (GprsParameterConfiguration)record;
            System.out.println("Adding... " + currentRecord.toString());
            rows.add(currentRecord);
        };

        /* Rimuovo il primo elemento */

        return rows;
    }

    private void write(List<GprsParameterConfiguration> values, String resultFile){

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            doc.setStrictErrorChecking(false);

            //Element rootElement = doc.createElement("values");
            //doc.appendChild(rootElement);


            for (GprsParameterConfiguration next : values ) {

                /* Rate Model Value Range */
                Element rateModelValueRange = doc.createElement("value_range");

                Element rateModelValue = doc.createElement("value");
                Text rateModelValueContent = doc.createTextNode(next.getRateModel());
                rateModelValue.appendChild(rateModelValueContent);

                Element ratePlanName = doc.createElement("rate_plan_name");
                Text ratePlanNameContent = doc.createTextNode(next.getRatePlan());
                ratePlanName.appendChild(ratePlanNameContent);

                Element impactCategory = doc.createElement("impact_category");
                Text impactCategoryContent = doc.createTextNode("*");
                impactCategory.appendChild(impactCategoryContent);

                rateModelValueRange.appendChild(rateModelValue);
                rateModelValueRange.appendChild(ratePlanName);
                rateModelValueRange.appendChild(impactCategory);
                /* End Rate Model Value Range */

                /* Event Key Section */
                Element eventKeyValueRange = doc.createElement("value_range");

                Element eventKeyValue = doc.createElement("value");
                Text eventKeyValueContent = doc.createTextNode(next.getEventKey());
                eventKeyValue.appendChild(eventKeyValueContent);

                eventKeyValueRange.appendChild(eventKeyValue);
                eventKeyValueRange.appendChild(rateModelValueRange);
                /* End Event Key Section */

                /* Begin Sales Channel Section */
                Element salesChannelValueRange = doc.createElement("value_range");

                Element salesChannelValue = doc.createElement("value");
                Text salesChannelValueContent = doc.createTextNode(next.getSalesChannel());
                salesChannelValue.appendChild(salesChannelValueContent);

                salesChannelValueRange.appendChild(salesChannelValue);
                salesChannelValueRange.appendChild(eventKeyValueRange);
                /*  End Sales Channel Section  */

                /* Begin Common Code */
                Element commonCodeValueRange = doc.createElement("value_range");

                Element commonCodeValue = doc.createElement("value");
                Text commonCodeValueContent = doc.createTextNode(next.getCommonCode());
                commonCodeValue.appendChild(commonCodeValueContent);

                commonCodeValueRange.appendChild(commonCodeValue);
                commonCodeValueRange.appendChild(salesChannelValueRange);
                /*  End Common Code  */

                doc.appendChild(commonCodeValueRange);
                //rootElement.appendChild(commonCodeValueRange);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("omit-xml-declaration", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(resultFile));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public static void main(String args[]) throws FileNotFoundException {


        XmlWriterTest test = new XmlWriterTest();
        List<GprsParameterConfiguration> paramsList = test.load();

        test.write(paramsList, fileToReadPath + destination);
    }

    /*
    <value_range>
        <!-- Common Code -->
        <value>GPRS_ACT</value>
        <value_range>
            <!-- Sales Channel -->
            <value>O2</value>
            <value_range>
                <value>244</value>
                <value_range>
                    <value>Zero Partiva IVA Maxi New Giu14</value>
                    <rate_plan_name>Opz VF Sempre Smart Agenzie</rate_plan_name>
                    <impact_category>*</impact_category>
                </value_range>
            </value_range>
        </value_range>
    </value_range>
     */


}
