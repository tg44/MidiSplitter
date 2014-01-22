/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midisplitter;

import javax.sound.midi.MidiEvent;

/**
 *
 * @author Törcsi
 */
public class MidiNote {

    private MidiEvent startMsg;
    private MidiEvent endMsg;

    public Boolean isOn(long tick) {
        if (tick < startMsg.getTick()) {
            return false;
        }
        if (tick > endMsg.getTick()) {
            return false;
        }
        return true;
    }

    public MidiEvent getStartMsg() {
        return startMsg;
    }

    public void setStartMsg(MidiEvent startMsg) {
        this.startMsg = startMsg;
    }

    public MidiEvent getEndMsg() {
        return endMsg;
    }

    public void setEndMsg(MidiEvent endMsg) {
        this.endMsg = endMsg;
    }

    public long startTick() {
        return startMsg.getTick();
    }

    public long endTick() {
        return endMsg.getTick();
    }
}
