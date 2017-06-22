#define ON  1 
#define OFF 0 
#define Empty 2 
#define NotEmpty 3 
mtype={Setting,LoginScreen,MainScreen,
changeBluetoothON,changeWifiON,changepassNotEmpty,changeBluetoothOFF,changeuserNotEmpty,changeAirplane_modeOFF,changeAirplane_modeON,changeWifiOFF}
mtype state= Setting;
/*define flag for action */
byte action[15];

/*define params and default value*/
byte Bluetooth=OFF;
byte pass=Empty;
byte onOff_0=OFF;
byte Airplane_mode=OFF;
byte onOff_2=OFF;
byte onOff_1=OFF;
byte onOff_4=OFF;
byte onOff_3=OFF;
byte onOff_6=OFF;
byte onOff_5=OFF;
byte onOff_8=OFF;
byte onOff_7=OFF;
byte onOff_9=OFF;
byte Wifi=OFF;
byte user=Empty;


ltl  req1 {[](((state==Setting)->((!([]<>((state==changeBluetoothON)&&(state==changeWifiON)&&(state==changepassNotEmpty)&&(state==changeBluetoothOFF)&&
(state==changeuserNotEmpty)&&(state==changeAirplane_modeOFF)&&(state==changeAirplane_modeON)&&(state==changeWifiOFF))))-><>
((state !=Setting)&&((state!=changeBluetoothON)&&(state!=changeWifiON)&&(state!=changepassNotEmpty)&&(state!=changeBluetoothOFF)&&(state!=changeuserNotEmpty)&&
(state!=changeAirplane_modeOFF)&&(state!=changeAirplane_modeON)&&(state!=changeWifiOFF))))))}

ltl  req1 {[](((state==Setting)->((!([]<>((state==changeBluetoothON)&&(state==changeWifiON)&&(state==changepassNotEmpty)&&(state==changeBluetoothOFF)&&(state==changeuserNotEmpty)&&(state==changeAirplane_modeOFF)&&(state==changeAirplane_modeON)&&(state==changeWifiOFF))))-><>((state !=Setting)&&((state!=changeBluetoothON)&&(state!=changeWifiON)&&(state!=changepassNotEmpty)&&(state!=changeBluetoothOFF)&&(state!=changeuserNotEmpty)&&(state!=changeAirplane_modeOFF)&&(state!=changeAirplane_modeON)&&(state!=changeWifiOFF)))))&&((state==LoginScreen)->((!([]<>((state==changeBluetoothON)&&(state==changeWifiON)&&(state==changepassNotEmpty)&&(state==changeBluetoothOFF)&&(state==changeuserNotEmpty)&&(state==changeAirplane_modeOFF)&&(state==changeAirplane_modeON)&&(state==changeWifiOFF))))-><>((state !=LoginScreen)&&((state!=changeBluetoothON)&&(state!=changeWifiON)&&(state!=changepassNotEmpty)&&(state!=changeBluetoothOFF)&&(state!=changeuserNotEmpty)&&(state!=changeAirplane_modeOFF)&&(state!=changeAirplane_modeON)&&(state!=changeWifiOFF)))))&&((state==MainScreen)->((!([]<>((state==changeBluetoothON)&&(state==changeWifiON)&&(state==changepassNotEmpty)&&(state==changeBluetoothOFF)&&(state==changeuserNotEmpty)&&(state==changeAirplane_modeOFF)&&(state==changeAirplane_modeON)&&(state==changeWifiOFF))))-><>((state !=MainScreen)&&((state!=changeBluetoothON)&&(state!=changeWifiON)&&(state!=changepassNotEmpty)&&(state!=changeBluetoothOFF)&&(state!=changeuserNotEmpty)&&(state!=changeAirplane_modeOFF)&&(state!=changeAirplane_modeON)&&(state!=changeWifiOFF))))))}
active proctype vm(){
 do
	::(state==Setting)->
	  if
		 ::(Airplane_mode==OFF)->atomic{Airplane_mode=ON;action[12]=1;action[10]=1;action[11]=1;state=changeAirplane_modeON};
		 ::(Airplane_mode==ON)->atomic{Airplane_mode=OFF;action[12]=1;state=changeAirplane_modeOFF};
		 ::(Bluetooth==OFF && Airplane_mode==OFF)->atomic{Bluetooth=ON;action[11]=1;state=changeBluetoothON};
		 ::(Bluetooth==ON)->atomic{Bluetooth=OFF;action[11]=1;state=changeBluetoothOFF};
		 ::(Wifi==OFF && Airplane_mode==OFF)->atomic{Wifi=ON;action[10]=1;state=changeWifiON};
		 ::(Wifi==ON)->atomic{Wifi=OFF;action[10]=1;state=changeWifiOFF};
		 ::atomic{state=LoginScreen};
	  fi
/*
*/////////////////////////////////////// End of changeParamScreens for screen Setting////////////////////////////////////////////////
*/

	::(state==LoginScreen)->
	  if
		 ::(pass==Empty)->atomic{pass=NotEmpty;action[14]=1;state=changepassNotEmpty};
		 ::(user==NotEmpty && pass==NotEmpty)->atomic{state=MainScreen}; /* Log_in Button */
		 ::(user==Empty)->atomic{user=NotEmpty;action[13]=1;state=changeuserNotEmpty};
	  fi
/*
*/////////////////////////////////////// End of changeParamScreens for screen LoginScreen////////////////////////////////////////////////
*/

	::(state==MainScreen)->
	  if
			 ::atomic{state=MainScreen};
	  fi
/*
*/////////////////////////////////////// End of changeParamScreens for screen MainScreen////////////////////////////////////////////////
*/

	::(state==changeBluetoothON)->
	  if
		 ::(action[12]==0 && action[11]==1)->atomic{action[11]=0;state=Setting};
		 ::(action[12]==0 && action[11]==1)->atomic{action[11]=0;state=Setting};
	  fi
	::(state==changeWifiON)->
	  if
		 ::(action[12]==0 && action[10]==1)->atomic{action[10]=0;state=Setting};
		 ::(action[12]==0 && action[10]==1)->atomic{action[10]=0;state=Setting};
	  fi
	::(state==changepassNotEmpty)->
	  if
		 ::(action[14]==1)->atomic{action[14]=0;state=LoginScreen};
		 ::(action[14]==1)->atomic{action[14]=0;state=LoginScreen};
	  fi
	::(state==changeBluetoothOFF)->
	  if
		 ::(action[12]==1 && action[11]==1)->atomic{action[11]=0;state=changeAirplane_modeON};
		 ::(action[12]==0 && action[11]==1)->atomic{action[11]=0;state=Setting};
		 ::(action[12]==1 && action[11]==1)->atomic{action[11]=0;state=changeAirplane_modeON};
		 ::(action[12]==0 && action[11]==1)->atomic{action[11]=0;state=Setting};
	  fi
	::(state==changeuserNotEmpty)->
	  if
		 ::(action[13]==1)->atomic{action[13]=0;state=LoginScreen};
		 ::(action[13]==1)->atomic{action[13]=0;state=LoginScreen};
	  fi
	::(state==changeAirplane_modeOFF)->
	  if
		 ::(action[12]==1)->atomic{action[12]=0;state=Setting};
		 ::(action[12]==1)->atomic{action[12]=0;state=Setting};
	  fi
	::(state==changeAirplane_modeON)->
	  if
		 ::(action[12]==1 && action[10]==0 && action[11]==0)->atomic{action[12]=0;state=Setting};
		 ::(action[10]==1)->atomic{Wifi=OFF;action[10]=1;state=changeWifiOFF};
		 ::(action[11]==1)->atomic{Bluetooth=OFF;action[11]=1;state=changeBluetoothOFF};
		 ::(action[12]==1 && action[10]==0 && action[11]==0)->atomic{action[12]=0;state=Setting};
		 ::(action[10]==1)->atomic{Wifi=OFF;action[10]=1;state=changeWifiOFF};
		 ::(action[11]==1)->atomic{Bluetooth=OFF;action[11]=1;state=changeBluetoothOFF};
	  fi
	::(state==changeWifiOFF)->
	  if
		 ::(action[12]==1 && action[10]==1)->atomic{action[10]=0;state=changeAirplane_modeON};
		 ::(action[12]==0 && action[10]==1)->atomic{action[10]=0;state=Setting};
		 ::(action[12]==1 && action[10]==1)->atomic{action[10]=0;state=changeAirplane_modeON};
		 ::(action[12]==0 && action[10]==1)->atomic{action[10]=0;state=Setting};
	  fi
od
}
