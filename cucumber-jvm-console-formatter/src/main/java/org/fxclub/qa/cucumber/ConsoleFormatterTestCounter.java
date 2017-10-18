package org.fxclub.qa.cucumber;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.util.Scanner;

class ConsoleFormatterTestCounter {

    static int getStartCounter(){
        String finishFileName = "target/startfilecounter.txt";
        return getValueFromFile(finishFileName);
    }

    static int getFinishCounter(){
        String finishFileName = "target/finishfilecounter.txt";
        return getValueFromFile(finishFileName);
    }

    private synchronized static int getValueFromFile(String filename){
        try{
            File lockFile = new File("target/lockFile.txt");
            FileChannel fileChannel = new RandomAccessFile(lockFile, "rw").getChannel();
            FileLock fileLock = fileChannel.lock();

            int index;
            try{
                File counterFile = new File(filename);
                if(counterFile.createNewFile()){
                    FileUtils.writeStringToFile(counterFile, "1", Charset.defaultCharset());
                    index = 1;
                } else {
                    Scanner counterScanner = new Scanner(counterFile);
                    index = counterScanner.nextInt() + 1;
                    FileUtils.writeStringToFile(counterFile, index + "", Charset.defaultCharset());
                }
            }catch (IOException e){
                index = 0;
            }

            fileLock.release();

            return index;
        }catch (IOException e){
            return 0;
        }
    }
}
