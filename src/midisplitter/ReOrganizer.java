/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midisplitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author Törcsi
 */
public class ReOrganizer {

    Splitter sp;
    HashMap<String, HashMap<String, ArrayList<MidiNote>>> organizedNotes;
    Boolean splittotracks = true;
    Boolean keeptracks = true;
    Boolean keepchannels = true;

    public void setSplittotracks(Boolean splittotracks) {
        this.splittotracks = splittotracks;
    }

    public void setKeeptracks(Boolean keeptracks) {
        this.keeptracks = keeptracks;
    }

    public void setKeepchannels(Boolean keepchannels) {
        this.keepchannels = keepchannels;
    }

    public ReOrganizer(Splitter sp) {
        if (sp.getSeparatedNotes() == null) {
            throw new NullPointerException();
        }
        this.sp = sp;
        organizedNotes = new HashMap<String, HashMap<String, ArrayList<MidiNote>>>();
    }

    int firstComeFirstServe() {
        Boolean[] splitch=new Boolean[16];
        for(int i=0;i<16;i++){
            splitch[i]=true;
        }
        return firstComeFirstServe(splitch);
    }
    int firstComeFirstServe(Boolean[] splitch) {
        ArrayList<ArrayList<MidiNote>> lists = new ArrayList<ArrayList<MidiNote>>();
        for (Entry<String, HashMap<String, ArrayList<MidiNote>>> entry2 : sp.getSeparatedNotes().entrySet()) {
            ArrayList<ArrayList<MidiNote>> trackLists = new ArrayList<ArrayList<MidiNote>>();
            if (!keeptracks) {
                trackLists = lists;
            }
            for (Entry<String, ArrayList<MidiNote>> entry : entry2.getValue().entrySet()) {
                ArrayList<ArrayList<MidiNote>> channelLists = new ArrayList<ArrayList<MidiNote>>();
                String tmp = entry.getKey().replaceAll("[^\\d.]", "");
                if (!splitch[Integer.valueOf(tmp)]) {
                    if (splittotracks) {
                        organizedNotes.put("t" + (tmp + 1), new HashMap<String, ArrayList<MidiNote>>());
                        organizedNotes.get("t" + (tmp + 1)).put("ch" + tmp, entry.getValue());
                    }
                } else {
                    ArrayList<MidiNote> processList = entry.getValue();
                    if (!keepchannels) {
                        channelLists = trackLists;
                    }
                    for (MidiNote mn : processList) {
                        Boolean found = false;
                        for (ArrayList<MidiNote> list : channelLists) {
                            if (!onHit(list, mn)) {
                                list.add(mn);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            ArrayList<MidiNote> newlist = new ArrayList<MidiNote>();
                            newlist.add(mn);
                            channelLists.add(newlist);
                        }
                    }
                }
                if (keepchannels) {
                    trackLists.addAll(channelLists);
                }
            }
            if (keeptracks) {
                lists.addAll(trackLists);
            }

        }
        if (splittotracks) {
            int tracks = 1;
            for (ArrayList<MidiNote> chanellist : lists) {
                if (splitch[tracks-1]) {
                    organizedNotes.put("t" + tracks, new HashMap<String, ArrayList<MidiNote>>());
                    organizedNotes.get("t" + tracks).put("ch" + (tracks - 1), chanellist);
                }
                tracks++;
            }
        }

        return lists.size();
    }

    int chordIsIsolatedSame() {
        ArrayList<ArrayList<MidiNote>> lists = new ArrayList<ArrayList<MidiNote>>();
        for (Entry<String, HashMap<String, ArrayList<MidiNote>>> entry2 : sp.getSeparatedNotes().entrySet()) {
            ArrayList<ArrayList<MidiNote>> trackLists = new ArrayList<ArrayList<MidiNote>>();
            if (!keeptracks) {
                trackLists = lists;
            }
            for (Entry<String, ArrayList<MidiNote>> entry : entry2.getValue().entrySet()) {
                ArrayList<ArrayList<MidiNote>> channelLists = new ArrayList<ArrayList<MidiNote>>();
                ArrayList<MidiNote> processList = entry.getValue();
                if (!keepchannels) {
                    channelLists = trackLists;
                }
                for (MidiNote mn : processList) {
                    Boolean found = false;
                    for (int i = 0; i < channelLists.size(); i++) {
                        ArrayList<MidiNote> list = channelLists.get(i);
                        if (!onHit(list, mn)) {
                            list.add(mn);
                            found = true;
                            break;
                        } else {
                            MidiNote tmp1 = onSamePos(list, mn);
                            if (tmp1 != null) {
                                //TODO
                            }
                        }
                    }
                    if (!found) {
                        ArrayList<MidiNote> newlist = new ArrayList<MidiNote>();
                        newlist.add(mn);
                        channelLists.add(newlist);
                    }
                }
                if (keepchannels) {
                    trackLists.addAll(channelLists);
                }
            }
            if (keeptracks) {
                lists.addAll(trackLists);
            }

        }
        if (splittotracks) {
            int tracks = 1;
            for (ArrayList<MidiNote> chanellist : lists) {
                organizedNotes.put("t" + tracks, new HashMap<String, ArrayList<MidiNote>>());
                organizedNotes.get("t" + tracks).put("ch" + (tracks - 1), chanellist);
                tracks++;
            }
        }

        return lists.size();
    }

    public static Boolean onHit(ArrayList<MidiNote> noteList, MidiNote note) {
        for (MidiNote mn : noteList) {
            // mnstart--------------------mnend
            //          notest-noteend
            if (mn.isOn(note.startTick())) {
                return true;
            }
            if (mn.isOn(note.endTick())) {
                return true;
            }
            // notest--------------------noteend
            //          mnstart-mnend
            if (note.isOn(mn.startTick())) {
                return true;
            }
            if (note.isOn(mn.endTick())) {
                return true;
            }
        }
        return false;
    }

    public static MidiNote onSamePos(ArrayList<MidiNote> noteList, MidiNote note) {
        for (MidiNote mn : noteList) {
            if (mn.startTick() == note.startTick() && mn.endTick() == note.endTick()) {
                return mn;
            }
        }
        return null;
    }

    public HashMap<String, HashMap<String, ArrayList<MidiNote>>> getOrganizedNotes() {
        return organizedNotes;
    }
}
