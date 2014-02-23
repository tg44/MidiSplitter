/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midisplitter;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;

/**
 *
 * @author Törcsi
 */
public class MidiSplitter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Correct usage:");
            System.out.println("    MidiSplitter input.mid output.mid");
            System.out.println("    MidiSplitter inputdir -po postfix [-r]");
            System.out.println("    MidiSplitter inputdir -pr prefix [-r]");
        }
        HashMap<String, String> runon = new HashMap<String, String>();
        //Tester t=new Tester();
        //t.test("d:\\pirates.mid");

        //one file
        if (args[0].endsWith(".mid")) {
            runon.put(args[0], args[1]);
        }
        //dir
        if (args.length > 2) {
            File homeDir = new File(args[0]);
            for (File f : homeDir.listFiles()) {
                if (f.getAbsolutePath().endsWith(".mid")) {
                    if (args[1].contains("-po")) {
                        String outFName = f.getAbsolutePath();
                        outFName = outFName.replace(".mid", args[2] + ".mid");
                        runon.put(f.getAbsolutePath(), outFName);
                    }
                    if (args[1].contains("-pr")) {
                        String outFName = f.getParentFile().getAbsolutePath();
                        outFName += args[2]+"\\"+f.getName();
                        runon.put(f.getAbsolutePath(), outFName);
                        
                    }
                }
            }
            //recursive
            if (args.length == 4 && args[3].equals("-r")) {
                System.out.println("recursive wlak not implemented yet");
            }
        }
        for (Entry<String, String> entry : runon.entrySet()) {
            Splitter sp = new Splitter();
            Sequence sequence = MidiSystem.getSequence(new File(entry.getKey()));
            sp.splitit(sequence);
            ReOrganizer reo = new ReOrganizer(sp);

            int chnum = reo.firstComeFirstServe();
            if (chnum > 16) {
                throw new Exception("too much chennel: " + chnum);
            }
            Sequence oseq = new Sequence(sequence.getDivisionType(), sequence.getResolution());
            OutputWriter opw = new OutputWriter();
            opw.write(oseq, reo);
            int[] allowedTypes = MidiSystem.getMidiFileTypes(oseq);
            MidiSystem.write(oseq, allowedTypes[0], new File(entry.getValue()));
            //System.out.println("--------------------------------");
            //t.test("d:\\pirates_out.mid");
        }
    }

}
