# Digital-Doilies
This program creates a drawing board for the user to doodle on. The user is able to add/remove reflective lines where lines drawn
in each segment will be copied over to the other segments same as when a doily is created.
## Instructions
### Launch
To run, run Main.java or if using command line run the command:

   `java Main`
  
This will bring up the main user interface

### Functions
* **Clear** - clears the drawing board
* **Undo** - removes the user's last stroke
* **Redo** - if the user has made an undo, redo will redo the undo
* **Color** - allows the user to change the color of the strokes
* **Number of sectors** - sets the number of reflective segments (minimum is 2)
* **Stroke size** - sets the size of the stroke
* **Toggle sector lines** - shows/hides the lines for the segments
* **Draw/Erase** - select draw to go into draw mode which allows the user to draw on the canvas, select erase to erase pixels (set color to black) on the canvas
* **Save** - select to store the current drawing in the bottom segment
* **Delete** - delete any saved drawings by entering the index of the saved drawing