/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package midisplitter;

import java.io.File;
import javax.sound.midi.*;

/**
 *
 * @author stack overflow
 */
public class Tester {
        public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    public static final int NOTE_PITCH = 0xE0;
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    public void test(String filename) throws Exception {
        Sequence sequence = MidiSystem.getSequence(new File(filename));

        int trackNumber = 0;
        for (Track track :  sequence.getTracks()) {
            trackNumber++;
            System.out.println("Track " + trackNumber + ": size = " + track.size());
            System.out.println();
            for (int i=0; i < track.size(); i++) { 
                MidiEvent event = track.get(i);
                System.out.print("@" + event.getTick() + " ");
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                    //System.out.print("Channel: " + sm.getChannel() + " ");
                    if (sm.getCommand() == NOTE_ON) {
                        int key = sm.getData1();
                        int octave = (key / 12)-1;
                        int note = key % 12;
                        String noteName = NOTE_NAMES[note];
                        int velocity = sm.getData2();
                        System.out.println("Note on, " + noteName + octave + " key=" + key + " velocity: " + velocity);
                    } else if (sm.getCommand() == NOTE_OFF) {
                        int key = sm.getData1();
                        int octave = (key / 12)-1;
                        int note = key % 12;
                        String noteName = NOTE_NAMES[note];
                        int velocity = sm.getData2();
                        //System.out.println("Note off, " + noteName + octave + " key=" + key + " velocity: " + velocity);
                    } else {
                        System.out.println("Command:" + sm.getCommand()+ " data1: "+sm.getData1() + " data2: "+ sm.getData2());
                    }
                } else {
                    
                    if(message instanceof MetaMessage){
                        MetaMessage mm=(MetaMessage)message;
                        System.out.println("Meta message: " + Integer.toHexString(mm.getType()) + ", "+new String(mm.getData()));
                        
                    }
                    else
                        System.out.println("Other message: " + message.getClass());
                }
            }

            System.out.println();
        }

    }
}
