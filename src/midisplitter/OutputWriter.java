/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midisplitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.sound.midi.*;

/**
 *
 * @author Törcsi
 */
public class OutputWriter {

    public void write(Sequence sequence, ReOrganizer reOrganizer) throws InvalidMidiDataException {
        int tracks = 1;
        int chanels = 0;
        for (Map.Entry<String, HashMap<String, ArrayList<MidiNote>>> entry2 : reOrganizer.getOrganizedNotes().entrySet()) {
            //start a new track
            Track track = sequence.createTrack();
            for (Map.Entry<String, ArrayList<MidiNote>> entry : entry2.getValue().entrySet()) {
                //add some meta
                MetaMessage mm=new MetaMessage();
                byte[] data=(new String("Track"+tracks).getBytes());
                mm.setMessage(3, data, data.length);
                track.add(new MidiEvent(mm, 0));
                //mm=new MetaMessage();
                //data=(new String("Track"+tracks).getBytes());
                //mm.setMessage(2*16+1, new byte[0], 0);
                //track.add(new MidiEvent(mm, 0));
                // Set the instrument on channel 0
                ShortMessage sm = new ShortMessage();
                sm.setMessage(ShortMessage.PROGRAM_CHANGE, chanels, 0, 0);
                track.add(new MidiEvent(sm, 0));
                //set the out volume
                sm = new ShortMessage();
                sm.setMessage(ShortMessage.CONTROL_CHANGE, chanels, 7, 127);
                track.add(new MidiEvent(sm, 0));

                ArrayList<MidiNote> list = entry.getValue();
                //ArrayList<MidiEvent> meList=new ArrayList<MidiEvent>();
                for (MidiNote mn : list) {
                    //start
                    ShortMessage stmsg = ((ShortMessage) mn.getStartMsg().getMessage());
                    stmsg.setMessage(stmsg.getCommand(), chanels, stmsg.getData1(), stmsg.getData2());
                    track.add(mn.getStartMsg());
                    //pitch
                    for(MidiEvent pme : mn.pitch){
                        ShortMessage pmsg=(ShortMessage)pme.getMessage();
                        pmsg.setMessage(pmsg.getCommand(), chanels, pmsg.getData1(), pmsg.getData2());
                        track.add(pme);
                    }
                    //stop
                    ShortMessage endmsg = ((ShortMessage) mn.getEndMsg().getMessage());
                    endmsg.setMessage(endmsg.getCommand(), chanels, endmsg.getData1(), endmsg.getData2());
                    track.add(mn.getEndMsg());
                }
                chanels++;
            }
            tracks++;
        }

    }
}
