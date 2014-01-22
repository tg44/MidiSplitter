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
    Boolean splittotracks=true;
    Boolean keeptracks=true;
    Boolean keepchannels=true;
    
    public ReOrganizer(Splitter sp) {
        if(sp.getSeparatedNotes()==null){
            throw new NullPointerException();
        }
        this.sp = sp;
        organizedNotes=new HashMap<String, HashMap<String, ArrayList<MidiNote>>>();
    }
    int firstComeFirstServe(){
        ArrayList<ArrayList<MidiNote>> lists=new ArrayList<ArrayList<MidiNote>>();
        for(Entry<String, HashMap<String, ArrayList<MidiNote>>> entry2:sp.getSeparatedNotes().entrySet()){
            ArrayList<ArrayList<MidiNote>> trackLists=new ArrayList<ArrayList<MidiNote>>();
            if(!keeptracks){
                trackLists=lists;
            }
            for(Entry<String,ArrayList<MidiNote>> entry:entry2.getValue().entrySet()){
                ArrayList<ArrayList<MidiNote>> channelLists=new ArrayList<ArrayList<MidiNote>>();
                ArrayList<MidiNote> processList=entry.getValue();
                if(!keepchannels){
                    channelLists=trackLists;
                }
                for(MidiNote mn:processList){
                    Boolean found=false;
                    for(ArrayList<MidiNote> list:channelLists){
                        if(!onHit(list,mn)){
                            list.add(mn);
                            found=true;
                            break;
                        }
                    }
                    if(!found){
                        ArrayList<MidiNote> newlist=new ArrayList<MidiNote>();
                        newlist.add(mn);
                        channelLists.add(newlist);
                    }
                }
                trackLists.addAll(channelLists);
            }
            lists.addAll(trackLists);
        }
        if(splittotracks){
            int tracks=1;
            for(ArrayList<MidiNote> chanellist:lists){
                organizedNotes.put("t"+tracks,new HashMap<String, ArrayList<MidiNote>>()) ;
                organizedNotes.get("t"+tracks).put("ch"+(tracks-1), chanellist);
                tracks++;
            }
        }
                
        return lists.size();
    }
    
    public static Boolean onHit(ArrayList<MidiNote> noteList, MidiNote note){
        for(MidiNote mn:noteList){
            // mnstart--------------------mnend
            //          notest-noteend
            if(mn.isOn(note.startTick()))
                return true;
            if(mn.isOn(note.endTick()))
                return true;
            // notest--------------------noteend
            //          mnstart-mnend
            if(note.isOn(mn.startTick()))
                return true;
            if(note.isOn(mn.endTick()))
                return true;
        }
        return false;
    }

    public HashMap<String, HashMap<String, ArrayList<MidiNote>>> getOrganizedNotes() {
        return organizedNotes;
    }
    
}
