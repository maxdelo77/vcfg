package it.reply.vcfg.test;

import it.reply.vcfg.model.GprsParameterConfiguration;
import org.beanio.BeanReader;
import org.beanio.StreamFactory;

import java.io.*;

/**
 * Created by Massimo on 08/05/14.
 */
public class BeanIoReaderTest {

    private static String fileToReadPath = "C:/Users/Massimo/Documents/My Box Files/work/vf-configuration-files-generator/res/";
    private static String fileSource = "estensioni_gprs_act_source.csv";
    private static String mappingFile = "gprs_conf.xml";

    public static void main(String args[]) throws FileNotFoundException {

        final StreamFactory factory = StreamFactory.newInstance();
        factory.loadResource(mappingFile);

        final Reader in = new BufferedReader(new FileReader(fileToReadPath + fileSource));
        final BeanReader reader = factory.createReader("records", in);

        Object record = null;
        while ((record = reader.read()) != null) {

            GprsParameterConfiguration currentRecord = (GprsParameterConfiguration)record;
            System.out.println(currentRecord.toString());
        };

    }
}
