package com.sam.webtasks.client;

import com.sam.webtasks.basictools.Names;

public class SessionInfo {
	/*******************************************************/
	/* edit the settings below to configure the experiment */
	/*******************************************************/
		
	//are we just testing locally? set this to true if so and it won't try to log data to the database
	public static boolean localTesting=false;
	public static boolean runInfoConsentPages=false; //should we do the info and consent pages?
	public static int experimentType = Names.EXPERIMENT_PROLIFIC;
	
	//what is the name for this experiment?
	public static String experimentCode="AS1";
	
	//which version of the experiment is this?
	public static int experimentVersion=3;
	
	//what is the minimum permitted screen size in pixels?
	//if the screen is smaller than this the participant will be asked
	//to maximise their display before continuing
	public static int minScreenSize=500;
	
	//who is eligible to take part?
	//Names.ANYONE = anybody
	//Names.NEVERACCESSED = anyone who has not yet started the experiment
	//Names.NEVERCOMPLETED = anyone who has not yet completed the experiment,
	//i.e. you can start again as long as you didn't get to the very end	
	public static int eligibility=Names.ELIGIBILITY_NEVERCOMPLETED;
	public static boolean newParticipantsOnly=false; //restrict eligibility to participants who have never completed any of your experiments?
	
	//what factors do we need to counterbalance?
	//set up the names as follows:
	//counterbalanceFactors = {"factor1", "factor2", "factor3"}; 
	//set up the number of possible levels for each factor as follows
	//counterbalanceLevels = {2, 3, 2};
	//if you want to specify the level of any of those factors, set it with specifiedLevels. otherwise set to -1
	//e.g. specifiedLevels = {-1, 2, -1}; would randomise factors 1 and 3, and set the second factor to level 2
	//NB levels range from 0 to (maximum - 1)
	public static String[] counterbalanceFactors = {"counterbtargetvals"};
	public static int[] counterbalanceLevels = {2};
	public static int[] specifiedLevels = {-1};
	
	/*************************************************/
    /* no need to edit the settings below this point */
	/*************************************************/
	
	//participant info variables
	public static boolean resume=false;      //is this a resumption of an earlier session?
	public static String status="";          //status loaded from the database
	public static int resumePosition=1;      //what position should we resume from, if the participant comes back?
	public static int resumeProgress=0;      //what should the progress bar be set to if we resume?
	public static int resumePoints=0;        //if we are scoring points, what number of points should we resume from?
	public static int gender;
	public static int age;
	public static String participantID;
	public static String sessionKey="";      //use this to store a random session key
	public static String rewardCode="";      //reward code to be revealed at end, in order to participant to claim paymen
}
