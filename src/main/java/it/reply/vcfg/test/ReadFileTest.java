package it.reply.vcfg.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by Massimo on 07/05/14.
 */
public class ReadFileTest {

    private static String fileToReadPath = "C:/Users/Massimo/Documents/My Box Files/work/vf-configuration-files-generator/res/";
    private static String fileSource = "estensioni_gprs_act_source.csv";
    private static String fileDestination = "estensioni_gprs_act_dest.csv";

    public static void main(String args[]){

        RandomAccessFile fromFile = null;
        RandomAccessFile toFile = null;
        String currentLine = null;
        try {
            fromFile = new RandomAccessFile(fileToReadPath + fileSource, "rw");
            FileChannel fromChannel = fromFile.getChannel();

            while ((currentLine = fromFile.readLine()) != null){
                System.out.println(currentLine);
            }

            toFile = new RandomAccessFile(fileToReadPath + fileDestination, "rw");
            FileChannel      toChannel = toFile.getChannel();

            long position = 0;
            long count    = fromChannel.size();

            toChannel.transferFrom(fromChannel, position, count);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch(IOException ioe){
            ioe.printStackTrace();
        }finally {

                try {
                    if (fromFile != null) {
                        fromFile.close();
                    }

                    if(toFile != null){
                        toFile.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

