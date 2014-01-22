/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package midisplitter;

import java.io.File;
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
        if(args.length<2)
            System.out.println("The correct usage is: MidiSplitter input.mid output.mid");
        //Tester t=new Tester();
        //t.test("d:\\pirates.mid");
        Splitter sp=new Splitter();
        Sequence sequence = MidiSystem.getSequence(new File(args[0]));
        sp.splitit(sequence);
        ReOrganizer reo=new ReOrganizer(sp);
        int chnum=reo.firstComeFirstServe();
        if(chnum>16)
            throw new Exception("too much chennel: "+chnum);
        Sequence oseq=new Sequence(sequence.getDivisionType(), sequence.getResolution());
        OutputWriter opw=new OutputWriter();
        opw.write(oseq, reo);
        int[] allowedTypes = MidiSystem.getMidiFileTypes(oseq);
        MidiSystem.write(oseq,allowedTypes[0],new File(args[1]));
        //System.out.println("--------------------------------");
        //t.test("d:\\pirates_out.mid");
    }
    
}
