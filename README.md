MidiSplitter
============

Its a midi file splitter, splits a multitrack multichannel midi files to a 1 channel/track, 1 note/channel in the same time format midi.

Its split the chords. Need some further work for some more special functionality, but its working.

In the source you have possibilitis to resolve the track and channel grouping and made it fully to firste come first serve tracks.

Changelog:
* 2014.03.23
  * [bugfix] if the prefix is directory, now we creat it
  * [bugfix] no more too much chennel exception instead show them
  * [nontested feature] pitchs are now handled
    * I not meet pitched midis yet so I cant test it
	* It coud be working, but it coud make mess with pitched tracks
	
* 2014.03.01
  * [feature] new graphical interface
  * [feature] recursive directory readin
  * [feature] keep channels untouched (not split them)
  * [known bug] if you keep a channel untouched its channel number modified
  * [known issue] all the channels instrument will be piano
    * no its not bug :)





```javascript
If input like:
Track1:
ch1:
|---A---|
	|-------D--------|
ch2:
|---D---|
		|----F----|
Track3:
ch1:
|---C---|
	|-------G--------|
ch2:
|---F---|
		|----F----|
	   
Output will like:
Track1:
ch1:
|---A---|
Track2:
ch1:
	|-------D--------|
Track3:
ch1:
|---D---|
		|----F----|
Track4:
ch1:
|---C---|
Track5:
ch1:
	|-------G--------|
Track6:
ch1:
|---F---|
		|----F----|