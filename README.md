# MyPaint
Android Paint program using paths drawn by finger swipes

My first Android project for Ivy's birthday gift. Targeting Android 14 and up.
<br>
<br>
App Launch behavior  
- [ ] Default to the last drawn sketch
- [ ] If there is no sketch saved then default to the Menu screen
<br>

Menu screen  
- [ ] New sketch menu option goes to the PaintView
- [ ] List (grid view?) of saved sketches
- [ ] Tapping on a sketch opens the file in the Paint screen
<br>

Paint screen
- [x] Default to ~~paintbrush~~ or pen
- [x] Default to red brush color
- [x] Default to white or ~~baked in background image~~
- [x] Control to ~~delete~~ or undo last path/swipe
- [x] Control to save sketch
- [ ] Control to share the sketch
- [ ] Add paintbrush control
- [x] Replace title bar with M3 style UI components
- [] Add control to go to the Menu screen, save current sketch confirmation
<br>

Share sketch
- [ ] Send to other users on the same local network
<br>

Testing<br>
- [ ] TBD
<br>

Updates
* 17 July 2026
  * Added logic to save the drawing to a JSON file for fancy loading to replay the sketch
  * Added simple load to just show the drawing
  * Added Save and Load menu options
  * Fixed an issue with losing the drawing after orientation change
* 18 Mar 2026
  * Updated the project to use M3 components and top toolbar and Gradle 9.x
* 18 Feb 2026 
   * Updated the project to target Android SDK 34 and Gradle 8.x
   * Updated .gitignore with new JetBrains files
