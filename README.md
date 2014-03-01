MidiSplitter
============

Its a midi file splitter, splits a multitrack multichannel midi files to a 1 channel/track, 1 note/channel in the same time format midi.

Its split the chords. Need some further work for some special functionality, and more user frendly but its working.

In the source you have possibilitis to resolve the track and channel grouping and made it fully to firste come first serve tracks.



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