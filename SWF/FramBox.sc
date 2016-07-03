#########################################################################
## Simple SWF Viewer  v 0.1   This Release: 9th Oct 2011 
## ========================   ============================
##
## Preamble
## ========
## This script is free software ( that's 'free' as in 'free speech' 
## NOT 'free' as in 'free beer'!! ),  and distributed under the GNU
## General Public License version 3.0.  Details of both will be found
## here:
##
##   Free Software: http://www.gnu.org/philosophy/free-sw.html
##
##   GPL v 3.0 :  http://www.gnu.org/licenses/gpl.html
##
##  You, gentle reader/user, in the obtaining and subsequent use of this
##  script unequivocally agree to be bound by the terms contained in both
##  of the above documents.
##
## Compiling
## =========
## This script may be compiled into an SWF using 'swfc' which is part of the
## SWFTools Utility suite.  Details of which are here,
##
##      http://wiki.swftools.org
##
##      http://http://en.wikipedia.org/wiki/SWFTools
##
##  Should you per chance wish to amend and re-distribute this script, please
##  kindly leave these header lines, i.e. exerything between the two hash symbol
##  lines intact. Please DO NOT remove them.  Thank you.
##
##  Contact: gliese849b [ at ] gmail [ dot ] com
###################################################################
.flash filename="FrameBox.swf" bbox=550x400 version=8 fps=1 compress
.outline rb:
  M 0 0 L 5 5 L 0 10
.end
.outline lb:
  M 10 0 L 5 5 L 10 10
.end
.outline hook:
  M 0 0 L 20 20 M 20 0 L 0 20 L 20 20 L 20 0 L 0 0 L 0 20 
.end
.outline nav:
  M 0 0 L 0 0 L 0 0 L 0 0
.end
.outline framebp_vg:
  M 0 0
  L 100 0
  L 100 100
  L 0 100
.end
#.font FreeSans "FreeSans.ttf"
.button back
    #.filled left outline=lb fill=red color=black
#    .filled left_press outline=lb fill=gray color=black
#    .show left as=shape x=10 y=2
#    .show left as=hover x=10 y=2
#    .show left_press as=pressed x=10 y=2
.end
.button forward
    #.filled right outline=rb fill=green color=black
#    .filled right_press outline=rb fill=gray color=black
#    .show right as=shape x=10 y=2
#    .show right as=hover x=10 y=2
#    .show right_press as=pressed x=10 y=5
.end
.button the_hook
   #.filled hooky outline=hook color=black fill=green
#   .show hooky as=shape x=100 y=0
#   .show hooky as=hover x=100 y=0
#   .show hooky as=pressed x=100 y=0

.end

.sprite nav_bar
   .filled navbar outline=nav color=blue fill=gray
   #.edittext pageNo  width=80 height=24 text="" color=black size=18pt
   .put navbar x=20 y=50 alpha=100%
   .put back x=0 y=50 scale=300%
   .put forward x=80 y=50 scale=300%
   .put the_hook x=148 y=50
   #.put pageNo x=140 y=42 scale=160%

.action:
    _root.nav_bar.pageNo.selectable=false;
    _root.nav_bar.back.tabEnabled =false;
    _root.nav_bar.forward.tabEnabled =false;
    _root.nav_bar.pageNo.tabEnabled =false;
    _root.nav_bar.the_hook.tabEnabled =false;
    _root.nav_bar.the_hook.onPress=function(){ 
        dum=0;
        _root.nav_bar.startDrag(false);
     };
    _root.nav_bar.the_hook.onRelease=function(){
       dum=0;
            _root.nav_bar.stopDrag();
        };

    _root.nav_bar.forward.onPress=function(){ 
        _root.swfbox.nextFrame(); 
        _root.nav_bar.pageNo.text = _root.swfbox._currentframe + "/" + _root.swfbox._totalframes; 

     }; 
    _root.nav_bar.back.onPress=function(){ 
        _root.swfbox.prevFrame();
        _root.nav_bar.pageNo.text = _root.swfbox._currentframe + "/" + _root.swfbox._totalframes; 

     }; 
.end 
.end
.action:
// ------------------
       swfbox = createEmptyMovieClip("swfbox",1);
       swfbox.attachMovie("frameb","swfbox",1);
//       swfbox._width = 32;
//       swfbox._height = 32;

       swfListener = new Object();
	swfListener.onLoadInit = function(swf_clip) {
    	var w = swfbox._width;
    	var h = swfbox._height;
         swfbox._x = Stage.width/1.3 - w/1.3;
         swfbox._y = Stage.height/1.3 - h/1.3;
        swfLast=swf_clip._totalframes;
    	
        pageVar=_root.swfbox._currentframe;
        _root.nav_bar.pageNo.text = pageVar + "/" + swfLast;

      _root.nav_bar.swapDepths(swfbox); 
swf_clip.gotoAndStop(1);
};

       swf_clip_l = new MovieClipLoader();
       swf_clip_l.addListener(swfListener);     

       
swf_clip_l.loadClip("NetonRingAnimation.swf", swfbox);

//--------------------

   var kShify=false;
   keyListener = new Object();
	Key.addListener(keyListener);
        keyListener.onKeyDown = function() { 
        var kc = Key.getCode();
	switch (kc) {
        case 9://tab 切换 上下左右键功能
            kShift=!kShift;
	break;
        case 32://空格
	  if (_root.nav_bar._alpha == 50 ) {
	      _root.nav_bar._alpha = 0; } 
	  else {
	      _root.nav_bar._alpha = 50;

                };
	break;
       case 70://F
          //Stage.scaleMode="noBorder";
          Stage.displayState = (Stage.displayState == "normal") ? "fullScreen" : "normal";   
       break;
	case 37: //左移
	  if (kShift){
	      swfbox._x -= 2; }
	  else {
	    swfbox.prevFrame();
        _root.nav_bar.pageNo.text = _root.swfbox._currentframe + "/" + _root.swfbox._totalframes; 
            	
	};
        break;
	case 38://右移 
	  if (kShift){
	      swfbox._y -= 2; }
	  else {
	    swfbox._xscale +=2;
	    swfbox._yscale +=2;
		};
        break;
	case 39:
	  if (kShift){
	      swfbox._x += 2; }

	  else {
	    swfbox.nextFrame();
        _root.nav_bar.pageNo.text = _root.swfbox._currentframe + "/" + _root.swfbox._totalframes; 
		};
        break;
	case 40:
	  if (kShift){
	      swfbox._y += 2; }
 	 else {
	    swfbox._xscale -=2;
	    swfbox._yscale -=2;
				};
        break;
        default:
           // void
	break;
	  	};
}; 
    onMouseDown=function(){ 
        dum=0;
        swfbox.useHandCursor = true;
        swfbox.startDrag(false);
     };
     onMouseUp=function(){
       dum=0;
	    swfbox.useHandCursor = false;
            swfbox.stopDrag();
        };

.end
   .filled frameb outline=framebp_vg color=blue line=5 
   .put nav_bar x=10 y=10 scale=80% alpha=50%
 .end
 
  .flash filename="check_key.swf" bbox=300x30 version=8
  #.font freesans "../fonts/FreeSans.ttf"
  .edittext keystring  width=300 height=30 color=green size=20pt text="??"
  .action:
      myListener = new Object();
      Key.addListener(myListener);
      myListener.onKeyDown = function () {
      if (Key.isDown(Key.DELETEKEY)){
         Stage["displayState"]="fullScreen";
                               };
      var kc= Key.getCode();
      var ka= Key.getAscii();
      keystring.text="Key Code:" + kc.toString() + " Ascii Code:'" + ka.toString() + "'";
                         };
      _root.enabled = true;
  .end
   .put keystring x=10 y=10
  .end
