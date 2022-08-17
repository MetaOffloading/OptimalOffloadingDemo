package com.sam.webtasks.iotask2;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sam.webtasks.basictools.Names;
import com.sam.webtasks.basictools.PHP;

public class IOtask2ChoiceOverwrite {
	public static void Run() {
		final Date overwriteStart = new Date();
		
		final HTML displayText = new HTML();
		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		final VerticalPanel verticalPanel = new VerticalPanel();
		final HorizontalPanel buttonPanel = new HorizontalPanel();
		String overwriteString = "" ;
		 
		int overwriteState = IOtask2BlockContext.currentOverwriteChoice();
		
		String remindInstruction = "You must do the task <b>without</b> setting any reminders.<br><br>";
		String noremindInstruction = "You <b>must</b> set a reminder for every special circle. You will "
								   + "not be able to continue otherwise.<br><br>";

		if ((overwriteState == 1)&(IOtask2BlockContext.getReminderChoice() == 1)) {
			overwriteString = "The computer will decide this time.<br><br>" + remindInstruction;
			IOtask2BlockContext.setReminderCost(0);
			IOtask2BlockContext.setOffloadCondition(Names.REMINDERS_NOTALLOWED);
			IOtask2BlockContext.setActualPoints(IOtask2BlockContext.maxPoints());
		} else if ((overwriteState == 2)&(IOtask2BlockContext.getReminderChoice() == 0)) {
			overwriteString = "The computer will decide this time.<br><br>" + noremindInstruction; 
			IOtask2BlockContext.setReminderCost(1);
			IOtask2BlockContext.setOffloadCondition(Names.REMINDERS_MANDATORY_TARGETONLY);
			IOtask2BlockContext.setActualPoints(IOtask2BlockContext.currentTargetValue());
		} else {
			overwriteString = "The computer will stick with <b>your choice</b> this time.<br><br>";
			
			if (IOtask2BlockContext.getReminderChoice() == 0) {
				overwriteString = overwriteString + remindInstruction;
			} else {
				overwriteString = overwriteString + noremindInstruction;
			}
		}
		
	
		displayText.setHTML(overwriteString);
		
		final Button continueButton = new Button("Continue");
		buttonPanel.add(continueButton);
		
		
		// set up vertical panel
		verticalPanel.setWidth("75%");
		// verticalPanel.setHeight(Window.getClientHeight() + "px");
		verticalPanel.setHeight("300px");

		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		// add elements to panel
		displayText.setStyleName("instructionText");
		verticalPanel.add(displayText);
		verticalPanel.add(buttonPanel);
		
		// place vertical panel inside horizontal panel, so it can be centred
		horizontalPanel.setWidth(Window.getClientWidth() + "px");
		horizontalPanel.setHeight(Window.getClientHeight() + "px");

		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		horizontalPanel.add(verticalPanel);

		// add panel to root
		RootPanel.get().add(horizontalPanel);
		
		
		
		continueButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Date responseTime = new Date();
				
				final String data = IOtask2BlockContext.getBlockNum() + "," + IOtask2BlockContext.getTrialNum() + "," + IOtask2BlockContext.currentTargetValue() + "," + IOtask2BlockContext.currentOverwriteChoice() + "," + (int) (responseTime.getTime() - overwriteStart.getTime());
				
				RootPanel.get().remove(horizontalPanel);
				
				new Timer() {
					public void run() {
						PHP.logData("choiceOverwrite", data, true);
					}
				}.schedule(500);
			}
		});
		
		
	}

}
