#define ON  1 
#define OFF 0 
#define Empty 2 
#define NotEmpty 3 
mytype={mainScreen,loginScreen,createNewEvent,setting
,Changesettingairplane_mode,Changesettingwifi,Changesettingbluetooth}

/*define flag for action */
byte action[20];

/*define params and default value*/
byte date=Empty;
byte wifi=OFF;
byte pass=Empty;
byte description=Empty;
byte title=Empty;
byte onOff_0=OFF;
byte airplane_mode=OFF;
byte more_details=Empty;
byte onOff_2=OFF;
byte onOff_1=OFF;
byte onOff_4=OFF;
byte bluetooth=OFF;
byte onOff_3=OFF;
byte onOff_6=OFF;
byte onOff_5=OFF;
byte onOff_8=OFF;
byte onOff_7=OFF;
byte onOff_9=OFF;
byte time=Empty;
byte user=Empty;

active proctype vm(){
 do
	::(state==mainScreen)->
	  if
		 ::
		 ::
	  fi
	::(state==)->
	  if
		 ::
		 ::
	  fi
	::(state==loginScreen)->
	  if
		 ::(user==NotEmpty && pass==NotEmpty)->atomic(state=mainScreen); /* log in Button */
		 ::
		 ::
	  fi
	::(state==ChangeloginScreenpass)->
	  if
		 ::
		 ::
	  fi
	::(state==ChangeloginScreenuser)->
	  if
		 ::
		 ::
	  fi
	::(state==createNewEvent)->
	  if
		 ::(title==NotEmpty && description==NotEmpty && date==NotEmpty && time==NotEmpty)->atomic(state=mainScreen); /* save Button */
		 ::
		 ::
	  fi
	::(state==ChangecreateNewEventdate)->
	  if
		 ::
		 ::
	  fi
	::(state==ChangecreateNewEventmore_details)->
	  if
		 ::
		 ::
	  fi
	::(state==ChangecreateNewEventdescription)->
	  if
		 ::
		 ::
	  fi
	::(state==ChangecreateNewEventtime)->
	  if
		 ::
		 ::
	  fi
	::(state==ChangecreateNewEventtitle)->
	  if
		 ::
		 ::
	  fi
	::(state==setting)->
	  if
		 ::(airplane_mode==ON)->atomic(airplane_mode=OFF;state=changesettingairplane_mode);
		 ::(airplane_mode==OFF)->atomic(airplane_mode=ON;state=changesettingairplane_mode);
		 ::(wifi==ON)->atomic(wifi=OFF;state=changesettingwifi);
		 ::(wifi==OFF)->atomic(wifi=ON;state=changesettingwifi);
		 ::(bluetooth==ON)->atomic(bluetooth=OFF;state=changesettingbluetooth);
		 ::(bluetooth==OFF)->atomic(bluetooth=ON;state=changesettingbluetooth);
		 ::
		 ::
	  fi
	::(state==Changesettingairplane_mode)->
	  if
		 ::
		 ::
	  fi
	::(state==Changesettingwifi)->
	  if
		 ::
		 ::
	  fi
	::(state==Changesettingbluetooth)->
	  if
		 ::
		 ::
	  fi
od
}
