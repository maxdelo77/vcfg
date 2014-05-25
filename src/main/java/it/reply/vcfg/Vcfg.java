package it.reply.vcfg;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Massimo on 08/05/14.
 */

import it.reply.vcfg.VcfgLineParameter.TYPE;

public class Vcfg {

    private static final Logger logger = LoggerFactory.getLogger(Vcfg.class);

    public void load(VcfgLineParameter param) throws Exception {
        logger.info("Loading " + param.getType() + " files");

        if (param.getType() == TYPE.GPRS) {

        } else if (param.getType() == TYPE.OPT) {

        }
    }


    public static void main(String args[]){


            logger.info("Initializing VCFG process");

		/* parse command line parameters */
            VcfgLineParameter param = new VcfgLineParameter();
            CmdLineParser parser = new CmdLineParser(param);

            try {
                parser.parseArgument(args);

                logger.info("Params: " + param.toString());

            } catch (CmdLineException e) {
                logger.error("Error parsing command line parameters: " + e.getMessage());
                System.err.println("java -jar vcfg.jar [options...] files...");
                parser.printUsage(System.err);
                System.exit(-1);
            }

            try {
                Vcfg vcfg = new Vcfg();
                vcfg.load(param);

            } catch (Exception e) {
                logger.error("Unexpected error loading CDM files", e);
                System.exit(-2);
            }

            logger.info("VCFG process completed successfully");

    }
}
