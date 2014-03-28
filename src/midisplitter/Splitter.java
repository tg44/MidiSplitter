/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midisplitter;

import com.sun.media.sound.MidiUtils;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import static midisplitter.Tester.NOTE_NAMES;
import static midisplitter.Tester.NOTE_OFF;
import static midisplitter.Tester.NOTE_ON;
import static midisplitter.Tester.NOTE_PITCH;
/**
 *
 * @author Törcsi
 */
public class Splitter {

    HashMap<String, HashMap<String, ArrayList<MidiNote>>> separatedNotes;
    byte[] tempo=null;
    public byte[] getTempo(){
        return tempo;
    }
    public HashMap<String, HashMap<String, ArrayList<MidiNote>>> getSeparatedNotes() {
        return separatedNotes;
    }

    public void splitit(Sequence sequence) throws InvalidMidiDataException {

        separatedNotes = new HashMap<String, HashMap<String, ArrayList<MidiNote>>>();
        int trackNumber = 0;
        for (Track track : sequence.getTracks()) {
            trackNumber++;
            HashMap<String, ArrayList<MidiNote>> trackdata = new HashMap<String, ArrayList<MidiNote>>();
            separatedNotes.put("t" + trackNumber, trackdata);
            for (int i = 0; i < track.size(); i++) {
                MidiEvent event = track.get(i);
                //System.out.print("@" + event.getTick() + " ");
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                    //System.out.print("Channel: " + sm.getChannel() + " ");
                    ArrayList<MidiNote> channelNoteList = trackdata.get("ch" + sm.getChannel());
                    if (channelNoteList == null) {
                        channelNoteList = new ArrayList<MidiNote>();
                        trackdata.put("ch" + sm.getChannel(), channelNoteList);
                    }
                    if (sm.getCommand() == NOTE_ON && sm.getData2()>0) {
                        MidiNote note = new MidiNote();
                        note.setStartMsg(event);
                        channelNoteList.add(note);
                    } else if (sm.getCommand() == NOTE_OFF || sm.getData2()==0) {
                        //find the startpair
                        for (MidiNote mn : channelNoteList) {
                            if (mn.getEndMsg() == null) {
                                ShortMessage stm=(ShortMessage) mn.getStartMsg().getMessage();
                                if (stm.getData1() == sm.getData1()) {
                                    mn.setEndMsg(event);
                                }
                            }
                        }
                    }else if (message.getStatus() > 223 && message.getStatus() < 240) {
                        //find the startpair
                        for (MidiNote mn : channelNoteList) {
                            if (mn.getEndMsg() == null) {
                                //ShortMessage stm=(ShortMessage) mn.getStartMsg().getMessage();
                                if(message.getStatus() - 223==mn.getStartMsg().getMessage().getStatus() - 143)
                                    mn.pitch.add(event);
                                    /*if (stm.getData1() == sm.getData1()) {
                                    mn.pitch.add(event);
                                }*/
                            }
                        }
                    } else {
                        //System.out.println("Command:" + sm.getCommand());
                    }
                } else {
                    //tempo msg
                    if(message instanceof MetaMessage){
                        MetaMessage mm= (MetaMessage)message;
                        if(mm.getType()==0x51){
                            tempo=mm.getData();
                        }
                    }
                }

            }

            //System.out.println();
        }
        //sequence.getTickLength();
        for (Map.Entry<String, HashMap<String, ArrayList<MidiNote>>> entry2 : separatedNotes.entrySet()) {
            for (Map.Entry<String, ArrayList<MidiNote>> entry : entry2.getValue().entrySet()) {
                ArrayList<MidiNote> list = entry.getValue();
                for (MidiNote mn : list) {
                    if (mn.getEndMsg() == null) {
                        ShortMessage stm = ((ShortMessage) mn.getStartMsg().getMessage());
                        ShortMessage sm = new ShortMessage(NOTE_OFF, stm.getData1(),stm.getData2());
                        MidiEvent event=new MidiEvent(sm, sequence.getTickLength());
                        mn.setEndMsg(event);
                    }
                }
            }
        }

    }
}
